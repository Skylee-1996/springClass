package com.myweb.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.repository.BoardDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
	private final BoardDAO bdao;

	@Override
	public int register(BoardVO bvo) {
		log.info("insert  service in");
		return bdao.insert(bvo);
	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		return bdao.selectList(pgvo);
	}

	@Override
	public BoardVO getDetail(int bno) {
		
		bdao.updateRead(bno);
		return bdao.selectOne(bno);
	}

	@Override
	public int update(BoardVO bvo) {
		return bdao.update(bvo);
	}

	@Override
	public int remove(int bno) {
		return bdao.delete(bno);
	}
	
	@Override
	public int getTotalCount(PagingVO pgvo) {
		return bdao.selectTotalCount(pgvo);
	}
}
 