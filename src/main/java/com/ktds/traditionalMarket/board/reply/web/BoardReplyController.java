package com.ktds.traditionalmarket.board.reply.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

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
	
		return "redirect:/board/detail?boardId="+boardReplyVO.getBoardId();		//"redirect:/board/detail/" + boardReplyVO.getBoardId();
	}
	
	// 하나의 댓글 삭제(POST)
	@PostMapping("/reply/delete")
	@ResponseBody
	public Map<String, Object> doDeleteOneReply( @RequestParam String boardReplyId ){
		
		//ModelAndView view = new ModelAndView("redirect:/board/detail/{boardId}");
		
		boolean replyId = this.boardReplyService.deleteOneBoardReply(boardReplyId);
	
		//System.out.println("****************replyId= " + replyId);
		
		Map<String, Object> result = new HashMap<>();
		result.put("reply_Id", replyId);
		
		return result;
	}
	
	
	// 댓글 좋아요 
	@PostMapping("/reply/good")
	@ResponseBody
	public Map<String, Object> doReplyGood( @RequestParam String boardReplyId		
			 								, @SessionAttribute(Session.USER) MemberVO memberVO	) {
		
		//ModelAndView view = new ModelAndView("redirect:/board/detail/" + boardId);
		
		// CSRF 방어하기
/*		String sessionToken = (String)session.getAttribute(Session.CSRF_TOKEN);
		if ( !sessionToken.equals(token) ){
			throw new RuntimeException("잘못된 접근입니다.");
		} */

		
		Map<String, String> goodVO = new HashMap<>();
		goodVO.put("boardReplyId", boardReplyId);
		goodVO.put("memberId", memberVO.getMemberId());
		
		
		Map<String, Object> result = new HashMap<>();
		boolean isSuccess = false;
		isSuccess = this.boardReplyService.insertOneBoardReplyGood(goodVO);
		int goodCount = this.boardReplyService.selectOneBoardReplyGoodCount(boardReplyId);
		if ( isSuccess ) {
			result.put("good", isSuccess);			
		}
		else {
			result.put("good", false);
		}
		
		result.put("goodCount", goodCount);
		
		return result;	//return view; 		
	}
	
	// 댓글 싫어요
	@PostMapping("/reply/bad")
	@ResponseBody
	public Map<String, Object> doReplyBad( @RequestParam String boardReplyId		
			, @SessionAttribute(Session.USER) MemberVO memberVO	) {
		
		//ModelAndView view = new ModelAndView("redirect:/board/detail/" + boardId);
		
		// CSRF 방어하기
/*		String sessionToken = (String)session.getAttribute(Session.CSRF_TOKEN);
		if ( !sessionToken.equals(token) ){
			throw new RuntimeException("잘못된 접근입니다.");
		} */
		
		Map<String, String> badVO = new HashMap<>();
		badVO.put("boardReplyId", boardReplyId);
		badVO.put("memberId", memberVO.getMemberId());
		
		boolean isSuccess = this.boardReplyService.insertOneBoardReplyBad(badVO);
		
		Map<String, Object> result = new HashMap<>();
		result.put("recommend", isSuccess);
		
		return result;	//return view; 		
	}
	
	
	
}
