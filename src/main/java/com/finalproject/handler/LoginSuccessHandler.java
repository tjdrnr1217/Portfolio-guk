package com.finalproject.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

                log.info("LoginSuccessHandler => {}", authentication.toString());
                
                String role =authentication.getAuthorities().toArray()[0].toString();
                log.info("LoginSuccessHandler", role);
                if(role.equals("Role_COMPANY")){
                    response.sendRedirect(request.getContextPath()+"/orca/home.do");
                }
                else if(role.equals("Role_ADMIN")){
                    response.sendRedirect(request.getContextPath()+"/orca/admin/home.do");
                }
                else{
                    response.sendRedirect( request.getContextPath()+"/orca/home.do");
                }
            }
    
}