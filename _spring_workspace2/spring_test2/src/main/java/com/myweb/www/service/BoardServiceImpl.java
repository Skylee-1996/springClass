package com.myweb.www.service;

import org.springframework.stereotype.Service;

import com.myweb.www.repository.BoardDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
	private final BoardDAO bdao;
}
 