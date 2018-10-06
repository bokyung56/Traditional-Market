package com.ktds.traditionalmarket.board.reply.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktds.traditionalmarket.board.reply.service.BoardReplyService;
import com.ktds.traditionalmarket.board.reply.vo.BoardReplyVO;
import com.ktds.traditionalmarket.common.session.Session;
import com.ktds.traditionalmarket.member.vo.MemberVO;

@Controller
public class BoardReplyController {
	
	@Autowired
	private BoardReplyService boardReplyService;
	
	@PostMapping("/reply/write")
	public String doReplyWriteAction( @ModelAttribute BoardReplyVO boardReplyVO
			                          , @SessionAttribute(Session.USER) MemberVO memberVO) {
	
		boardReplyVO.setMemberId(memberVO.getMemberId());
		
		boolean inSuccess = this.boardReplyService.createOneBoardReply(boardReplyVO);
		
		System.out.println("!!!BoardId()= " + boardReplyVO.getBoardId());
		System.out.println("!!!BoardReplyId()= " + boardReplyVO.getBoardReplyId());
		System.out.println("!!!Reply()= " + boardReplyVO.getReply());
		System.out.println("!!!MemberId()= " + boardReplyVO.getMemberId());
		System.out.println("***getReplyList()= " + boardReplyVO.getBoardVO().getReplyList());
		
	
		return "redirect:/board/detail/" + boardReplyVO.getBoardId();
	}
	
}
