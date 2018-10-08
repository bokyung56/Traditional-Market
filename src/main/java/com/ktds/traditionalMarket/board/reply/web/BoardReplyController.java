package com.ktds.traditionalmarket.board.reply.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktds.traditionalmarket.board.reply.service.BoardReplyService;
import com.ktds.traditionalmarket.board.reply.vo.BoardReplyVO;
import com.ktds.traditionalmarket.common.session.Session;
import com.ktds.traditionalmarket.member.vo.MemberVO;
import com.nhncorp.lucy.security.xss.XssFilter;

@Controller
public class BoardReplyController {
	
	@Autowired
	private BoardReplyService boardReplyService;
	
	// 댓글 작성하기
	@PostMapping("/reply/write")
	public String doReplyWriteAction( @ModelAttribute BoardReplyVO boardReplyVO
			                          , @SessionAttribute(Session.USER) MemberVO memberVO) {
	
		boardReplyVO.setMemberId(memberVO.getMemberId());
		
		boolean inSuccess = this.boardReplyService.createOneBoardReply(boardReplyVO);
		
		// XSS: 댓글의 내용에 대해 XSS HTML 인코딩
		XssFilter filter = XssFilter.getInstance("lucy-xss-superset.xml");
		boardReplyVO.setReply( filter.doFilter( boardReplyVO.getReply() ) );
	
		return "redirect:/board/detail/" + boardReplyVO.getBoardId();
	}
	
	
	// 댓글 지우기
	@PostMapping("/reply/delete")
	@ResponseBody
	public Map<String, Object> doCheckDuplicateId( @RequestParam String boardReplyId, @RequestParam String memberId
													, @SessionAttribute(Session.USER) MemberVO memberVO ){
		
		boolean replyId = false;
		if ( memberVO.getMemberId().equals(memberId) ) {	// memberId(reply 작성자)와 지금 로그인한 회원의 memberId가 같은지 비교
			replyId = this.boardReplyService.deleteOneBoardReply(boardReplyId);
		}
			
		System.out.println("과연 두구두구두구두구 replyId= " + replyId);
		
		Map<String, Object> result = new HashMap<>();
		result.put("status", "OK");
		result.put("replyId", replyId);
		
		return result;
	}
	
}
