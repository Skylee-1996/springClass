package com.myweb.www.service;

import javax.inject.Inject;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.myweb.www.repository.MemberDao;
import com.myweb.www.security.MemberVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	
	private final MemberDao mdao;
	
	@Inject
	BCryptPasswordEncoder passwordEncoder;

	@Override
	public int register(MemberVO mvo) {
		//아이디가 중복되면 회원가입 실패
				// => 아이디만 주고 DB에서 같은 일치하는 mvo 객체를 리턴
				// 일치하는 유저가 있으면 가입 실패, 없다면 가입가능
				MemberVO tempMvo = mdao.getUser(mvo.getEmail());
				if(tempMvo != null) {
					//기존아이디가 있는 경우
					return 0;
				}
				//아이디가 중복되지 않는 다면 회원가입 진행
				//password가 null이거나 값이 없다면 가입불가.
				
				if(mvo.getEmail()==null || mvo.getEmail().length() == 0) {
					return 0;
				}
				if(mvo.getPwd() == null || mvo.getPwd().length() == 0) {
					return 0;
					
				}
				//회원가입 진행
				//password는 암호화 하여 가입
				//암호화(encode) / matches(입력된 비번, 암호화된비번) => true / false
				String pwd = mvo.getPwd();
				
				String encodePw = passwordEncoder.encode(pwd);//패스워드 암호화
				mvo.setPwd(encodePw);
				
				//회원가입
				int isOk = mdao.insert(mvo);
				
				
				return isOk;
	}
}
