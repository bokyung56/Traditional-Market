package com.ktds.traditionalmarket.store.reply.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;


import com.ktds.traditionalmarket.common.session.Session;
import com.ktds.traditionalmarket.member.vo.MemberVO;
import com.ktds.traditionalmarket.store.reply.service.StoreReplyService;
import com.ktds.traditionalmarket.store.reply.vo.StoreReplyVO;
import com.nhncorp.lucy.security.xss.XssFilter;

@Controller
public class StoreReplyController {
	
	@Autowired
	private StoreReplyService storeReplyService;
	
	// Store 댓글 작성하기
	@PostMapping("/store/reply/write")
	public String doReplyWriteAction( @ModelAttribute StoreReplyVO storeReplyVO
				                         , @SessionAttribute(Session.USER) MemberVO memberVO) {
		
		storeReplyVO.setMemberId(memberVO.getMemberId());
			
		boolean inSuccess = this.storeReplyService.createOneStoreReply(storeReplyVO);
			
		// XSS: 댓글의 내용에 대해 XSS HTML 인코딩
		XssFilter filter = XssFilter.getInstance("lucy-xss-superset.xml");
		storeReplyVO.setReply( filter.doFilter( storeReplyVO.getReply() ) );
		
		return "redirect:/trdtnmarket/oneMarket?marketId="+storeReplyVO.getMarketId();		
	}
	
	
	
		
	
	// Store 하나의 댓글 삭제 여부(POST)
	@PostMapping("/storeReply/delete")
	@ResponseBody
	public Map<String, Object> doDeleteOneReply( @RequestParam String storeReplyId){
			
		boolean isSuccessDeleteReply = this.storeReplyService.updateDeleteStoreOneReply(storeReplyId);	// 하나의 상점에 댓글 삭제여부

		Map<String, Object> result = new HashMap<>();
		result.put("isSuccessDeleteReply", isSuccessDeleteReply);
			
		return result;
	}
	
	
	

}
