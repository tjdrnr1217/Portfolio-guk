package com.finalproject.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.finalproject.SG.service.SecurityServiceImpl;
import com.finalproject.handler.ClientLogoutSuccessHandler;
import com.finalproject.handler.LoginSuccessHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록이 된다
@Slf4j
@RequiredArgsConstructor
public class SecurityConfig {
    
    final SecurityServiceImpl ClientTableService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("SecurityConfig => {}", "start filter chain");

        // http.authorizeRequests()
            // .antMatchers("/**").permitAll();
        
        http.csrf().ignoringAntMatchers("/api/**");

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    @Order(value = 1)
    public SecurityFilterChain filterChain1(HttpSecurity http) throws Exception {
        log.info("SecurityConfig => {}", "start filter chain");

        // 권할 설정
        http.csrf().disable();
        http.authorizeRequests()
        .antMatchers("/orca/admin","/orca/admin/*").hasAnyAuthority("Role_ADMIN")
        .antMatchers("/orca/company","/orca/company/*").hasAnyAuthority("Role_ADMIN","Role_COMPANY")
        // .antMatchers("/orca/estimatepage1","/orca/estimatepage2","/orca/estimatetotalpage").hasAnyAuthority("Role_ADMIN","Role_COMPANY")
        .anyRequest().permitAll();

        // 403페이지설정
        http.exceptionHandling().accessDeniedPage("/orca/403page.do");
        
        // 로그인 처리
        http.formLogin()
            .loginPage("/orca/login.do")
            .loginProcessingUrl("/orca/loginaction.do") 
            // .defaultSuccessUrl("/home.do")
            .usernameParameter("id")
            .passwordParameter("password")
            .successHandler(new LoginSuccessHandler())
            .permitAll();

        // 로그아웃 처리(반드시post로 호출)
        http.logout()
        .logoutUrl("/logout.do")
        .logoutSuccessHandler( new ClientLogoutSuccessHandler())
        // .logoutSuccessUrl("/home.do")
        .invalidateHttpSession(true)
        .clearAuthentication(true)
        .permitAll();

        http.userDetailsService(ClientTableService);
        return http.build();


        // 127.0.0.1:9090/ROOT/student2/login.do
        // 127.0.0.1:9090/ROOT/student2/loginaction.do
        // 위의 두개의 주소만 필터함.

        // http.antMatcher("/company/login.do")
        //     .antMatcher("/company/loginaction.do")
        //     .authorizeRequests()
        //     .anyRequest().authenticated().and();

        // 로그인 처리
        // http.formLogin()
        //     .loginPage("/company/login.do")
        //     // 이 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행
        //     // controller에서 /loginaction.do 안 만들어도 된다
        //     .loginProcessingUrl("/company/loginaction.do") 
        //     .defaultSuccessUrl("/company/home.do")
        //     .usernameParameter("id")
        //     .passwordParameter("password")
        //     .successHandler(new LoginSuccessHandler())
        //     .permitAll();
       
    }

    // 회원가입에서 사용했던 암호화 알고리즘 설정, 로그인에서도 같은 것을 사용해야 하니까?
    @Bean // 서버 구동시 자동으로 실행됨 @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
