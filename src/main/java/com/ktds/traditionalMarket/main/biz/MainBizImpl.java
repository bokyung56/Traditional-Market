package com.ktds.traditionalmarket.main.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ktds.traditionalmarket.board.vo.BoardVO;
import com.ktds.traditionalmarket.main.dao.MainDao;
import com.ktds.traditionalmarket.trdtnmarket.vo.TrdtnmarketVO;

@Component
public class MainBizImpl implements MainBiz{

	@Autowired
	private MainDao mainDao;
		
	@Override
	public List<BoardVO> readTenDateBoard() {		// main페이지에서 최시글 띄어줄 메소드
		
		return this.mainDao.selectTenDateBoard();
	}

	@Override
	public List<BoardVO> readTenRecommendBoard() {	// main페이지에서 인기글 띄어줄 메소드
		
		return this.mainDao.selectTenRecommendBoard();
	}

	@Override
	public List<TrdtnmarketVO> readTenTrdtnName() {		// 조회수순으로 인기 재래시장명 순위 
		
		return this.mainDao.selectTenTrdtnName();
	}

}
