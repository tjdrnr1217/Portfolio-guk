package com.finalproject.DJ.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.common.SendMail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController // => html을 표시할 수 없음.  Map, Member, Board, List => 를 반환하면 자동으로 json 바꿔줌.
@RequestMapping(value = "/api/orca")
@RequiredArgsConstructor
@Slf4j
public class OrcaVerificationCodeRestController {
    final String format = "VerificationCodeRestController => {}";
    @Autowired SendMail smail;
    // ajax오는 값을 String단일로 받을 수 없음 DTO를 만들거나 맵형식으로 받아야함.
    @PostMapping(value = "/verificationcode.json")
    public Map<String, String> verificationPOST(@RequestBody Map<String, String> code) {
        // ajax 통신값 확인
        log.info(format, code);

        // 버튼 값 확인 후 메일 전송 결과 값으로 인증번호 전송
        if(code.get("sendbtn").equals("1")) {
            String authkey = smail.sendMail(code.get("email"));
            log.info(format, authkey);
            Map<String, String> retMap = new HashMap<>();
            retMap.put("verificationcode", authkey);
            return retMap;
        }
        return null;
    }
}
