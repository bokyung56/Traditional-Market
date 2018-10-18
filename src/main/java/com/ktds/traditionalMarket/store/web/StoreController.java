package com.ktds.traditionalmarket.store.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktds.traditionalmarket.store.service.StoreService;
import com.ktds.traditionalmarket.store.vo.StoreVO;

@Controller
public class StoreController {
	
	@Autowired
	private StoreService storeService;
	
	
	// 하나의 재래시장에서 하나의 Store 가져오기
	@PostMapping("/oneTrdtnMarket/oneStore")
	@ResponseBody
	public Map<String, Object> doReadRepliesAction(@RequestParam String storeId){
		
		StoreVO storeVO = this.storeService.readOneStore(storeId);
		
		Map<String, Object> result = new HashMap<>();
		result.put("storeVO", storeVO);
		
		return result;
	}

}
