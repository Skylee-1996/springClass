package com.myweb.www.handler;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.myweb.www.domain.FileVO;
import com.myweb.www.repository.FileDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
@EnableScheduling
public class FileSweeper {
   private final String BASE_PATH = "D:\\_myProject\\_java\\_fileUpload\\";
   
   //남아있는 정보들 비교해야하므로...
   private final FileDAO fdao;
   
   //초 분 시 일 월 요일 년도(생략가능) => 기본 순서
   //12시 실행
   //작동이 잘되는지 로그 처음과 끝 찍어보기
   @Scheduled(cron="0 41 10 * * *")
   public void fileSweeper() {
      log.info(">>> FileSweeper start~! >> : {} ", LocalDateTime.now()); //시작 시간 체크
      
      //1.db에 등록된 파일 전체 목록 가져오기
      List<FileVO> dbList = fdao.selectListAllFile();
      
      //2.저장소를 검색할 때 필요한 파일의 경로 => 리스트(실제 존재해야 할 리스트 값)
      List<String> currentFiles = new ArrayList<String>();
      for(FileVO fvo : dbList) {
         String filePath = fvo.getSaveDir()+File.separator+fvo.getUuid();
         String fileName = fvo.getFileName();
         currentFiles.add(BASE_PATH+filePath+"_"+fileName);
         
         //이미지라면 썸네임 경로도 추가
         if(fvo.getFileType() > 0) {
        	  currentFiles.add(BASE_PATH+filePath+"_th_"+fileName);
         }
         
      }
      log.info("currentFile >> {}", currentFiles);
      
      //오늘날짜를 반영한 폴더 구조 경로 만들기
      LocalDate now = LocalDate.now();
      String today = now.toString();
      today = today.replace("-", File.separator);
      
      //경로를 기반으로 저장되어있는 파일을 검색 (오늘 날짜 폴더 안에있는 전체 파일)
      File dir = Paths.get(BASE_PATH + today).toFile();
      File[] allFileObjects = dir.listFiles();
      
      //실제 저장되어있는 파일과 DB에 존재하는 파일을 비교하여 없는 파일은 삭제 진행
      for(File file : allFileObjects) {
    	  String storeFileName = file.toPath().toString();
    	  if(!currentFiles.contains(storeFileName)) {
    		  file.delete(); //파일삭제
    		  log.info(">>>delete file > {} ", storeFileName);
    	  }
      }
      log.info(">>> FileSweeper finish~! >> : {} ", LocalDateTime.now()); //끝나는 시간 체크
   }
}