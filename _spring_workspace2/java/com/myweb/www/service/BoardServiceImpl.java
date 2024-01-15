package com.myweb.www.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.repository.BoardDAO;
import com.myweb.www.repository.FileDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
	private final BoardDAO bdao;
	
	private final FileDAO fdao;


	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		bdao.updateFileCount();
		bdao.updateCommentCount();
		
		return bdao.selectList(pgvo);
	}

	@Override
	public BoardDTO getDetail(int bno) {

		bdao.updateRead(bno);
		
		
		BoardVO bvo = bdao.selectOne(bno);
		List<FileVO> flist = fdao.getFileList(bno);
		BoardDTO bdto = new BoardDTO(bvo,flist);
		return  bdto;
	}

	@Override
	public int update(BoardDTO bdto) {
		int isOk = bdao.update(bdto.getBvo());
		if(bdto.getFlist()==null) {
			return isOk;
		}
		
		//bvo insert 후 파일도 있다면...
		if(isOk > 0 && bdto.getFlist().size() > 0) {
			//bno setting
			long bno = bdto.getBvo().getBno();
			for(FileVO fvo : bdto.getFlist()) {
				fvo.setBno(bno);
				isOk *= fdao.insertFile(fvo);
			}
		}
		return isOk;
	}

	@Override
	public int remove(int bno) {
		return bdao.delete(bno);
	}
	
	@Override
	public int getTotalCount(PagingVO pgvo) {
		return bdao.selectTotalCount(pgvo);
	}
	
	@Transactional
	@Override
	public int register(BoardDTO bdto) {
		log.info("inser service in");
		//boardMapper / flist fileMapper 등록
		int isOk = bdao.insert(bdto.getBvo());
		if(bdto.getFlist()==null) {
			return isOk;
		}
		
		//bvo insert 후 파일도 있다면...
		if(isOk > 0 && bdto.getFlist().size() > 0) {
			//bno setting
			long bno = bdao.selectOneBno(); //가장 마지막에 등록된 bno
			for(FileVO fvo : bdto.getFlist()) {
				fvo.setBno(bno);
				isOk *= fdao.insertFile(fvo);
			}
		}
		return isOk;
	}

	@Override
	public int reomveFile(String uuid) {
		
		return fdao.remove(uuid);
	}
}
 