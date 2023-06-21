package com.finalproject.JE.service;

import org.springframework.stereotype.Service;

import com.finalproject.entity.Client;
import com.finalproject.entity.ClientProjection;
import com.finalproject.entity.ClientProjection2;

@Service
public interface JEservice {
    // 기업 회원가입
	public int insertCompanyOne(Client obj);
	
	// 회원가입 ID 중복확인
	public int checkId(String id);
	
	// 탈퇴
	public Client deactiveClient(String id);

	// 비밀번호 변경
	public Client updatePassword(String id);
	
	// 아이디 찾기
	public ClientProjection findClientId(Client obj);
	
	// 비밀번호 찾기
	public ClientProjection2 findClientPassword(Client obj);
	
	// 마이페이지(이미지변경)
	public Client Mypage(String id);

	//회원정보수정
	public Client updateInfo(String id);

	//이미지
	public Client image(String id);

}
