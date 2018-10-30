package com.ktds.traditionalmarket.main.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.traditionalmarket.board.vo.BoardVO;
import com.ktds.traditionalmarket.main.service.MainService;
import com.ktds.traditionalmarket.trdtnmarket.vo.TrdtnmarketVO;

@Controller
public class MainController {
	
	@Autowired
	private MainService mainService;
	
	// 메인 페이지
	@GetMapping("/main/main")
	public ModelAndView viewTenBoardListPage() {
		
		ModelAndView view = new ModelAndView("main/main");
		
		List<BoardVO> boardTenList = this.mainService.readTenDateBoard();
		List<BoardVO> boardRecommendList = this.mainService.readTenRecommendBoard();
		List<TrdtnmarketVO> trdtnName = this.mainService.readTenTrdtnName();
		
		view.addObject("boardTenList", boardTenList);
		view.addObject("boardRecommendList", boardRecommendList);
		view.addObject("trdtnName", trdtnName);
		
		
		/*for ( Object boardVO : boardTenList ) {
			BoardVO board = (BoardVO) boardVO;
			System.out.println("board.title= " + board.getTitle());
		}*/
					
		return view;
	}

}
