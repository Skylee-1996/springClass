package com.myweb.www.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.myweb.www.security.CustomAuthMemberService;
import com.myweb.www.security.LoginFailureHandler;
import com.myweb.www.security.LoginSuccessHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
   //WebSecurityConfigurerAdapter 상속 받아 환경설정
   //WebConfig에 SecurityConfig.class 등록해야함!
   
   //비밀번호 암호화 객체 PasswordEncoder bean 생성
   @Bean
   public PasswordEncoder bcPasswordEncoder() {
      return new BCryptPasswordEncoder();
   }
   //SuccessHandler => 사용자가 생성하게끔...
   @Bean
   public AuthenticationSuccessHandler authSuccessHandler() {
      return new LoginSuccessHandler();   
   }
   //FailureHandler => 얘도 사용자가 지정하게끔...
   @Bean
   public AuthenticationFailureHandler authFailureHandler() {
      return new LoginFailureHandler();
   }
   //UserDetail => 이것도...커스텀 생성
   @Bean
   public UserDetailsService customUserService() {
      return new CustomAuthMemberService();
   }
   
   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      //인증되는 객체 설정
      auth.userDetailsService(customUserService()).passwordEncoder(bcPasswordEncoder());
   }

   @Override
   protected void configure(HttpSecurity http) throws Exception {
      //화면에서 설정되는 권한에 따른 주소 매핑 설정
      http.csrf().disable(); //공격에 대한 설정 막기
      
      //승인 요청
      //antMatchers(): 접근을 허용하는 값(경로)
      //permitAll(): 누구나 접근가능한 경로
      //authenticated(): 인증된 사용자만 가능
      http.authorizeRequests().antMatchers("/member/list").hasRole("ADMIN")
      .antMatchers("/", "/board/list", "/board/detail", "/comment/**","/upload/**",
            "/resources/**","/member/register", "/member/login").permitAll()
      .anyRequest().authenticated(); //나머지 기타등등은.. 인증된 사용자만..
      
      //커스텀로그인페이지 생성
      //Controller에 주소요청 매핑이 같이 있어야함 (!필수!)
      http.formLogin()
      .usernameParameter("email")
      .passwordParameter("pwd")
      .loginPage("/member/login")
      .successHandler(authSuccessHandler())
      .failureHandler(authFailureHandler());
      
      //로그아웃 페이지 구성
      //반드시 메서드가 post여야함
      http.logout()
      .logoutUrl("/member/logout")
      .invalidateHttpSession(true)
      .deleteCookies("JSESSIONID")
      .logoutSuccessUrl("/"); //index로 가게 설정      
            
   }
   
}