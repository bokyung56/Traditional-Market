package com.ktds.traditionalmarket.main.biz;

import java.util.List;

import com.ktds.traditionalmarket.board.vo.BoardVO;
import com.ktds.traditionalmarket.trdtnmarket.vo.TrdtnmarketVO;

public interface MainBiz {

	public List<BoardVO> readTenDateBoard();			// main페이지에서 최시글 띄어줄 메소드
	
	public List<BoardVO> readTenRecommendBoard();		// main페이지에서 인기글 띄어줄 메소드
	
	public List<TrdtnmarketVO> readTenTrdtnName();				// 조회수순으로 인기 재래시장명 순위 
	
}
