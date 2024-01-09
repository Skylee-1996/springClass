package com.myweb.www.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.repository.CommentDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class CommentServiceImpl implements CommentService{	
	
	private final CommentDAO cdao;

	@Override
	public int post(CommentVO cvo) {
		return cdao.insert(cvo);
	}

	@Override
	public List<CommentVO> getList(long bno, PagingVO pgvo) {
		return cdao.selectList(bno, pgvo);
	}

	@Override
	public int getTotal(long bno) {
		return cdao.countTotal(bno);
	}

	@Override
	public int modify(CommentVO cvo) {
		return cdao.updateCvo(cvo);
	}

}
