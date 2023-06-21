package com.finalproject.common;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SendMail{
    
    final JavaMailSender mailSender;

    public String sendMail(String email) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 임의의 authKey 생성
        Random random = new Random();
        String authKey = String.valueOf(random.nextInt(888888) + 111111);// 범위 : 111111 ~ 999999
        try {
            // 1. 메일 수신자 설정
            simpleMailMessage.setTo(email);
            // 1-1 메일 발신자 설정
            simpleMailMessage.setFrom("ldj8196@naver.com");
            // 2. 메일 제목 설정
            simpleMailMessage.setSubject("Orca 인증번호 발송");
            // 3. 메일 내용 설정
            simpleMailMessage.setText("인증번호는"+authKey+"입니다");
            // 4. 메일 전송
            mailSender.send(simpleMailMessage);
            return authKey;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
   
}
