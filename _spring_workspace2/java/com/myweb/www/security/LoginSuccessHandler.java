package com.myweb.www.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import com.myweb.www.service.MemberService;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

   @Setter
   @Getter
   private String authEmail;
   
   @Setter
   @Getter
   private String authUrl; //성공 후 가야하는 경로
   
   //redirect 객체 => 데이터를 가지고 리다이렉트하는 역할, 방금 본 위치로 다시 리다이렉트
   private RedirectStrategy rdstg = new DefaultRedirectStrategy();
   
   //실제 로그인 정보, 경로 같은 것들을 캐시에 저장해줌, 임시저장장치로 사용
   private RequestCache reqCache = new HttpSessionRequestCache();
   
   @Inject
   private MemberService msv;
      
   @Override
   public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
         Authentication authentication) throws IOException, ServletException {
      //Authentication: 인증된 authMember의 객체
      setAuthEmail(authentication.getName());
      setAuthUrl("/board/list"); //내가 보낼 경로 지정
      
      boolean isOk = msv.updateLastLogin(getAuthEmail());
      
      //이미 내부에서 로그인 세션 저장됨
      HttpSession ses = request.getSession();
      log.info("loginSuccess >> ses >>> {} ", ses.toString());
      if(!isOk || ses == null) {
         return;
      } else {
         //시큐리티에서 로그인을 시도하면 시도한 '로그인의 기록'이 남게됨 => 'AUTHENTICATION_EXCEPTION'
         //이전에 시도한 시큐리티의 인증 실패 기록을 삭제
         ses.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
      }
      SavedRequest saveReq = reqCache.getRequest(request, response);
      rdstg.sendRedirect(request, response, (saveReq != null ? 
            saveReq.getRedirectUrl() : getAuthUrl()));
   }

}