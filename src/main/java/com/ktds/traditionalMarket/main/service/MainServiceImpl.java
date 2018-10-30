package com.ktds.traditionalmarket.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktds.traditionalmarket.board.vo.BoardVO;
import com.ktds.traditionalmarket.main.biz.MainBiz;
import com.ktds.traditionalmarket.trdtnmarket.vo.TrdtnmarketVO;

@Service
public class MainServiceImpl implements MainService{

	@Autowired
	private MainBiz mainBiz;
	
	@Override
	public List<BoardVO> readTenDateBoard() {		// main페이지에서 최시글 띄어줄 메소드
		
		return this.mainBiz.readTenDateBoard();
	}

	@Override
	public List<BoardVO> readTenRecommendBoard() {	// main페이지에서 인기글 띄어줄 메소드
		
		return this.mainBiz.readTenRecommendBoard();
	}

	@Override
	public List<TrdtnmarketVO> readTenTrdtnName() {		// 조회수순으로 인기 재래시장명 순위
		
		return this.mainBiz.readTenTrdtnName();
	}

}
