package com.ktds.traditionalmarket.board.reply.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ktds.traditionalmarket.board.reply.dao.BoardReplyDao;
import com.ktds.traditionalmarket.board.reply.vo.BoardReplyVO;

@Component
public class BoardReplyBizImpl implements BoardReplyBiz{

	@Autowired
	private BoardReplyDao boardReplyDao;
	
	@Override
	public boolean createOneBoardReply(BoardReplyVO boardReplyVO) {
		
		return boardReplyDao.insertOneBoardReply(boardReplyVO) > 0;
	}

	@Override
	public boolean deleteOneBoardReply(String boardReplyId) {
		
		return boardReplyDao.deleteOneBoardReply(boardReplyId) > 0;
	}

}
