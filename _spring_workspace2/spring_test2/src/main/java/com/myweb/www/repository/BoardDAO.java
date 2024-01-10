package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardDAO {

	int insert(BoardVO bvo);

	List<BoardVO> selectList(PagingVO pgvo);

	BoardVO selectOne(int bno);

	int update(BoardVO bvo);

	void updateRead(int bno);

	int delete(int bno);

	int selectTotalCount(PagingVO pgvo);

	long selectOneBno();

}
