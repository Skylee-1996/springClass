package com.myweb.www.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
@Getter
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

   private String authEmail;
   private String errorMessage;
   
   @Override
   public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
         AuthenticationException exception) throws IOException, ServletException {
      setAuthEmail(request.getParameter("email"));//로그인한 이메일 가져오기
      
      //exception에서 발생한 메세지를 저장
      if(exception instanceof BadCredentialsException ||
             exception instanceof InternalAuthenticationServiceException) {
         setErrorMessage(exception.getMessage().toString());
      }
         log.info(">> errorMsg >>> {} ", errorMessage);
         request.setAttribute("email", getAuthEmail());
         request.setAttribute("errorMsg", getErrorMessage());
         request.getRequestDispatcher("/member/login?error")
         .forward(request, response);
   }

}