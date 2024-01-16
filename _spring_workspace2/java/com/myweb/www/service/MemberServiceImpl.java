package com.myweb.www.service;



import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.repository.MemberDAO;
import com.myweb.www.security.MemberVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	
	private final MemberDAO mdao;

	
	@Transactional
	@Override
	public int register(MemberVO mvo) {
	
		//회원가입
	int isOk = mdao.insert(mvo);
	
				
				
	return mdao.insertAuthInit(mvo.getEmail());
	}


	@Override
	public boolean updateLastLogin(String authEmail) {
		
		return mdao.updateLastLogin(authEmail) > 0 ? true : false;
	}


	@Override
	public MemberVO detail(String email) {
		MemberVO mvo = mdao.selectEmail(email);
		mvo.setAuthList(mdao.selectAuths(email));
		return mvo;
	}


	@Override
	public List<MemberVO> getList(){ 
		return mdao.selectList();
	}


	@Override
	public int update(MemberVO mvo) {
		return mdao.update(mvo);
	}


	@Override
	public int updateWp(MemberVO mvo) {
		
		return mdao.updateWp(mvo);
	}


	@Override
	public void delete(String email) {
		
		mdao.adelete(email);
		mdao.delete(email);
		
	}
}
