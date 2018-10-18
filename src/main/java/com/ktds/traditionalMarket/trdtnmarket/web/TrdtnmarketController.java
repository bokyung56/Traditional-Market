package com.ktds.traditionalmarket.trdtnmarket.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.ktds.traditionalmarket.trdtnmarket.service.TrdtnmarketService;
import com.ktds.traditionalmarket.trdtnmarket.vo.TrdtnmarketVO;

@Controller
public class TrdtnmarketController {
	
	@Autowired
	private TrdtnmarketService trdtnmarketService;
	
	
	@GetMapping("/trdtnmarket/map")
	public ModelAndView viewTraditionalMarketPage() {
		
		List<TrdtnmarketVO> location = this.trdtnmarketService.readAllTrdtnMarkets();		
			
		ModelAndView view = new ModelAndView("trdtnmarket/map");
		view.addObject("location", location);	
		
		return view;
	}

	
	// 전국재래시장 페이지
	@RequestMapping(value="/trdtnmarket/map", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView viewTraditionalMarketListPage( ) {
		
		List<TrdtnmarketVO> location = this.trdtnmarketService.readAllTrdtnMarkets();
			
		ModelAndView view = new ModelAndView("trdtnmarket/map");
		view.addObject("location", location);	
		
		return view;
	}
	
	
	// 하나의 재래시장 페이지
	@GetMapping("/trdtnmarket/oneMarket")
	public ModelAndView viewOneTraditionalMarketPage( @RequestParam String marketId ) {	// 10.18.목 storeReplyId추가
		
		TrdtnmarketVO trdtnmarketVO = this.trdtnmarketService.readOneTrdtnMarket(marketId);	// 하나의 재래시장 정보 가져옴
				
		ModelAndView view = new ModelAndView("trdtnmarket/" + trdtnmarketVO.getPage());		// 가져온 재래시장의 사이트로 이동
		view.addObject("trdtnmarket", trdtnmarketVO);
		
		return view;
		
	}
		
}
