package com.finalproject.SG.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.finalproject.SG.dto.Client;
import com.finalproject.SG.dto.ClientUser;
import com.finalproject.SG.mapper.OrcaClientMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 시큐리티 설정에서 .loginProcessingUrl("/loginaction.do")
// "/company/login.do" 요청이 오면 자동으로 UserDetailsService 타입으로 Ioc되어 있는 loadUserByUsername 함수가 실행된다

@Service
@Slf4j
@RequiredArgsConstructor
public class SecurityServiceImpl implements UserDetailsService {
    final String format ="SecurityServiceImpl => {}";
    final OrcaClientMapper cMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = cMapper.selectClientone(username);
        if(client != null){//가져올 정보가 있다. 
            //Member dto를 사용해서 처리하나 시큐리티는 user dto를 사용함.
           //user를 이용할 경우(세션내용 아이디, 암호, 권한)
            // return  User.builder()
            // .username(member.getId())
            // .password(member.getPassword())
            // .roles(member.getRole())
            // .build();
            //customer을 사용할 경우 (세션내용, 아이디 ,암호, 권한, 이름, 나이)
            String[] strRole={"Role_"+client.getRole()};
            Collection<GrantedAuthority> role = AuthorityUtils.createAuthorityList(strRole);
            return new ClientUser(
                client.getId(),
                client.getPassword(), 
                role, 
                client.getName(), 
                client.getPhone());           
        }
            return  User.builder()
            .username("_")
            .password("_")
            .roles("_")
            .build();
    }
    
    
    
}
