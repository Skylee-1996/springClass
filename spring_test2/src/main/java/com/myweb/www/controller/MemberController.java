package com.myweb.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myweb.www.security.MemberVO;
import com.myweb.www.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/member/*")
@RequiredArgsConstructor
public class MemberController {
	private final MemberService msv;
	
	
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String register(MemberVO mvo) {
		
		log.info(">>>> mvo >>> {}", mvo);

			
		int isOk = msv.register(mvo);
		
		return "/";
	}
	

}
