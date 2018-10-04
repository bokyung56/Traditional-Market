package com.ktds.traditionalmarket.board.web;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.traditionalmarket.board.service.BoardService;
import com.ktds.traditionalmarket.board.vo.BoardSearchVO;
import com.ktds.traditionalmarket.board.vo.BoardVO;
import com.ktds.traditionalmarket.common.session.Session;
import com.ktds.traditionalmarket.member.vo.MemberVO;

import io.github.seccoding.web.pager.explorer.PageExplorer;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	@Qualifier("uploadPath")
	private String uploadPath;
	/*	@Bean("uploadPath")			// class에서도 bean을 만들 수 있다.
	public String uploadPath( ) {
		return "D:/uploadVideos";
	}*/
	
	
	
	
	@RequestMapping("/board/list/init")
	public String viewBoardListPageForInitiate( HttpSession session ) {	// init이 들어오면 검색을 초기화하기위해서 session받음
		session.removeAttribute(Session.SEARCH); 	// search세션을 지워라. null값이 되버림.
		return "redirect:/board/list";
	}
	
	@RequestMapping("/board/list")
	public ModelAndView viewBoardListPage(
			@ModelAttribute BoardSearchVO boardSearchVO					// 1. 얘한테 페이지번호를 줄거임
			, HttpServletRequest request, HttpSession session) {
		
		// 전체검색 or 상세 -> 목록 or 글쓰기
		if ( boardSearchVO.getSearchKeyword() == null ) {
			boardSearchVO = (BoardSearchVO) session.getAttribute(Session.SEARCH);	// Session.SEARCH
			if( boardSearchVO == null ) {	// 전체 페이지보여주기
				boardSearchVO = new BoardSearchVO();
				boardSearchVO.setPageNo(0);
			}
		}

		PageExplorer pageExplorer= this.boardService.readAllBoards(boardSearchVO);	// 2. List<BoardVO> boardVOList = this.boardService.readAllBoards();
		
		session.setAttribute(Session.SEARCH, boardSearchVO);
			
		ModelAndView view = new ModelAndView("board/list");
		view.addObject("boardVOList", pageExplorer.getList());	// 4. boardVOList);
		view.addObject("pagenation", pageExplorer.make()); 		// 5.
		view.addObject( "size", pageExplorer.getTotalCount() );	// 몇개의 게시글이 검색되었습니다.
		view.addObject("boardSearchVO", boardSearchVO	);		// 검색시 검색칸에 검색한 글자가 안사라지게 하기위해서
		return view;
	}	
	
	@GetMapping("/board/write")
	public String viewCreateOneBoardPage() {
		
		return  "board/write";
	} 
	
	
	@PostMapping("/board/write")
	public ModelAndView doCreateOneBoardAction( @Valid @ModelAttribute BoardVO boardVO
											, Errors errors
											, HttpSession session) {
		
		System.out.println("title= " + boardVO.getTitle());
		System.out.println("content= " + boardVO.getContent());
		System.out.println("picture= " + boardVO.getPicture());
		
		ModelAndView view = new ModelAndView("redirect:/board/list");
		
		// Validation Annotation이 실패했는지 체크( 실패하면, 다시 글쓰기페이지로 내용 유지해서 돌아감 )
		if ( errors.hasErrors() ) {
			view.setViewName("board/write");
			view.addObject("boardVO", boardVO);
			System.out.println("여기는 Errors입니다!");
			return view;
		}
		
			
		// boardVO에 있는 MultipartFile pictureFile변수를 가져옴
		MultipartFile uploadPictureFile = boardVO.getPictureFile();	
		
		if( !uploadPictureFile.isEmpty() ) {
			// 파일시스템에 저장될 파일의 이름(난수)
			String pictureFileName = UUID.randomUUID().toString();
			
			// 업로드될 폴더경로는 맨 위에서 uploadPath선언해줌, 폴더가 존재하지 않는다면 생성 
			File uploadDir = new File(uploadPath);
			if( !uploadDir.exists() ) {
				uploadDir.mkdirs();
			}
			
			//파일 업로드될 경로 지정
			File destPictureFile = new File(uploadPath, pictureFileName);
			
				// 업로드
				try {
					uploadPictureFile.transferTo(destPictureFile);
					boardVO.setPicture(pictureFileName);
				} catch (IllegalStateException | IOException e) {
					throw new RuntimeException(e.getMessage(), e);
				} 			
		}
		
		MemberVO loginMemberVO = (MemberVO) session.getAttribute("_USER_");	// 키값을 써주면된다. 혹은 (MemberVO) session.getAttribute(Session.USER); 이거 할려면 import
		String memberId = loginMemberVO.getMemberId();
		boardVO.setMemberVO(loginMemberVO);
		boardVO.setMemberId(memberId);
		
		boolean isSuccess = this.boardService.createOneBoard(boardVO);
		
		return  view;
	}


}
