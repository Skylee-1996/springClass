package com.myweb.www.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//RequiredArgsConstructor 선언후
//private final로 객체 등록 => 생성자 주입
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board/*")
@Controller
public class BoardController {
	private final BoardService bsv;
	
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String register(BoardVO bvo) {
		log.info(">>>bvo >>> {}", bvo);
		int isOk=bsv.register(bvo);
		log.info("register check>>> {}", isOk);
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/list")
	public void list(Model m, PagingVO pgvo) {
		log.info(">>>pagingVO >> {}", pgvo);
		//페이징 처리
		List<BoardVO> list = bsv.getList(pgvo);
		//totalCount 구하기
		int totalCount = bsv.getTotalCount(pgvo);
		PagingHandler ph = new PagingHandler(pgvo,totalCount);
		
		m.addAttribute("list", list);
		m.addAttribute("ph",ph);
	}
	@GetMapping({"/detail","/modify"})
	public void detail(Model m, @RequestParam("bno") int bno) {
		log.info(">>>>> bno" + bno);
		m.addAttribute("bvo", bsv.getDetail(bno));
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO bvo){
		log.info(">>>>>modift check {}",bvo);
		
		
		//update
		int isOk = bsv.update(bvo);
		log.info(">>>update check>>> {}", isOk);
		
		//m.addAtrribute("bno",bvo.getBno());
		return "redirect:/board/detail?bno="+bvo.getBno(); //bno가 필요
		
	}
	
	@GetMapping("/remove")
	public String remove(@RequestParam("bno") int bno, RedirectAttributes re){

		int isOk = bsv.remove(bno);
		log.info(">>>delete check {}", isOk);
		re.addFlashAttribute("isDel", isOk);
		return "redirect:/board/list";
	}
	
	
	
}
