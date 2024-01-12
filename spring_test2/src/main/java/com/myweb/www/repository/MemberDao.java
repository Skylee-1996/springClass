package com.myweb.www.repository;

import com.myweb.www.security.MemberVO;

public interface MemberDao {

	int insert(MemberVO mvo);

	MemberVO getUser(String email);

}
