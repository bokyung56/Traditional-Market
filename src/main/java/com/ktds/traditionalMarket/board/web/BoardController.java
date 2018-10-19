package com.ktds.traditionalmarket.board.web;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.traditionalmarket.board.reply.service.BoardReplyService;
import com.ktds.traditionalmarket.board.reply.vo.BoardReplyVO;
import com.ktds.traditionalmarket.board.service.BoardService;
import com.ktds.traditionalmarket.board.vo.BoardSearchVO;
import com.ktds.traditionalmarket.board.vo.BoardVO;
import com.ktds.traditionalmarket.common.session.Session;
import com.ktds.traditionalmarket.common.utils.DownloadUtil;
import com.ktds.traditionalmarket.member.vo.MemberVO;
import com.nhncorp.lucy.security.xss.XssFilter;

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
		session.removeAttribute(Session.SEARCH); 						// search세션을 지워라. null값이 되버림.
		return "redirect:/board/list";
	}
	
	
	// 여러 게시글 가져오기(게시판)
	@RequestMapping("/board/list")
	public ModelAndView viewBoardListPage(
			@ModelAttribute BoardSearchVO boardSearchVO								// 1. 얘한테 페이지번호를 줄거임
			, HttpServletRequest request, HttpSession session) {
		
		// 전체검색 or 상세 -> 목록 or 글쓰기
		if ( boardSearchVO.getSearchKeyword() == null ) {
			boardSearchVO = (BoardSearchVO) session.getAttribute(Session.SEARCH);	// Session.SEARCH
			if( boardSearchVO == null ) {	// 전체 페이지보여주기
				boardSearchVO = new BoardSearchVO();
				boardSearchVO.setPageNo(0);
			}
		}

		PageExplorer pageExplorer = this.boardService.readAllBoards(boardSearchVO);	// 2. List<BoardVO> boardVOList = this.boardService.readAllBoards();
		
		session.setAttribute(Session.SEARCH, boardSearchVO);
			
		ModelAndView view = new ModelAndView("board/list");
		
		
		if(pageExplorer != null) {
			// XSS  방어하기: 게시판의 제목과 내용에 대해 XSS HTML 인코딩
     		XssFilter filter = XssFilter.getInstance("lucy-xss-superset.xml");
			for ( Object boardVO : pageExplorer.getList() ){		// pageExplorer.getList()가 return이 object타입이라서
				BoardVO board = (BoardVO) boardVO;
				board.setTitle( filter.doFilter(board.getTitle()) );
				board.setContent( filter.doFilter(board.getContent()) );
			}
			
			view.addObject("boardVOList", pageExplorer.getList());	// 4. boardVOList);
			view.addObject("pagenation", pageExplorer.make()); 		// 5.
			view.addObject( "size", pageExplorer.getTotalCount() );	// 몇개의 게시글이 검색되었습니다.
			view.addObject("boardSearchVO", boardSearchVO	);		// 검색시 검색칸에 검색한 글자가 안사라지게 하기위해서
		}
	
		return view;
	}	
	
	
	
	// 글 작성하기 (Get)
	@GetMapping("/board/write")
	public String viewCreateOneBoardPage() {
		
		return  "board/write";
	} 
	
	// 글 작성하기 (Post)
	@PostMapping("/board/write")
	public ModelAndView doCreateOneBoardAction( @Valid @ModelAttribute BoardVO boardVO
											, Errors errors
											, HttpSession session
											, MultipartHttpServletRequest multipartRequest) {
		
		ModelAndView view = new ModelAndView("redirect:/board/list");
		
		// CSRF 방어하기
		String sessionToken = (String)session.getAttribute(Session.CSRF_TOKEN);
		if ( !boardVO.getToken().equals(sessionToken) ){
			throw new RuntimeException("잘못된 접근입니다.");
		}
		
		// Validation Annotation이 실패했는지 체크( 실패하면, 다시 글쓰기페이지로 내용 유지해서 돌아감 )
		if ( errors.hasErrors() ) {
			view.setViewName("board/write");
			view.addObject("boardVO", boardVO);
			return view;
		}
		
		
		// <파일업로드하기>
		// boardVO에 있는 MultipartFile pictureFile변수를 가져옴
		MultipartFile uploadPictureFile = boardVO.getPictureFile();	
		//List<MultipartFile> uploadPictureFileList = multipartRequest.getFiles("pictureFiles");
		
		 
		if( !uploadPictureFile.isEmpty() ) {
			
			//for( MultipartFile mf : uploadPictureFileList ) {
				
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
			//}
						
		}
		
		MemberVO loginMemberVO = (MemberVO) session.getAttribute("_USER_");	// 키값을 써주면된다. 혹은 (MemberVO) session.getAttribute(Session.USER); 이거 할려면 import
		String memberId = loginMemberVO.getMemberId();
		boardVO.setMemberVO(loginMemberVO);
		boardVO.setMemberId(memberId);
		
		boolean isSuccess = this.boardService.createOneBoard(boardVO);
		
		// XSS  방어하기: 게시글 작성시 제목과 내용에 대해 XSS HTML 인코딩 ( 파일은 이미 업로드 시, 이름난수화 시키고 jsp페이지에서 확장자 지정해주었으므로 ) 
		XssFilter filter = XssFilter.getInstance("lucy-xss-superset.xml");
		boardVO.setTitle( filter.doFilter(boardVO.getTitle()) );
		boardVO.setContent( filter.doFilter(boardVO.getContent()) );
		
		return  view;
	}
	
	
	// 게시글 추천 증가시키기
	@PostMapping("/board/recommend")
	@ResponseBody
	public Map<String, Object> recommendUp(@RequestParam String boardId		
									      , @RequestParam String token, HttpSession session) {
		
		// CSRF 방어하기
		String sessionToken = (String)session.getAttribute(Session.CSRF_TOKEN);
		if ( !sessionToken.equals(token) ){
			throw new RuntimeException("잘못된 접근입니다.");
		} 
		
		MemberVO loginMemberVO = (MemberVO) session.getAttribute("_USER_");	// 키값을 써주면된다. 혹은 (MemberVO) session.getAttribute(Session.USER); 이거 할려면 import
		String memberId = loginMemberVO.getMemberId();
		
		
		boolean isSuccess = this.boardService.createOneBoardRecommend(boardId, memberId);	// 한 게시글당 추천 누른 회원정보 추가
		int recommendCnt = this.boardService.readOneBoardRecommendCount(boardId);			// 한 게시글당 추천 수
	
		Map<String, Object> result = new HashMap<>();
		result.put("isSuccess", isSuccess);
		result.put("recommendCnt", recommendCnt);
		
		return result;		
	}
	
	// 게시글 추천 감소시키기
	@PostMapping("board/recommend/cancel")
	@ResponseBody
	public Map<String, Object> recommendDown(@RequestParam String boardId		
											, @RequestParam String token, HttpSession session) {
		
		// CSRF 방어하기
		String sessionToken = (String)session.getAttribute(Session.CSRF_TOKEN);
		if ( !sessionToken.equals(token) ){
			throw new RuntimeException("잘못된 접근입니다.");
		} 
		
		MemberVO loginMemberVO = (MemberVO) session.getAttribute("_USER_");	// 키값을 써주면된다. 혹은 (MemberVO) session.getAttribute(Session.USER); 이거 할려면 import
		String memberId = loginMemberVO.getMemberId();
		
		boolean isSuccess = this.boardService.deleteOneBoardRecommend(boardId, memberId);	// 한 게시글당 추천 누른 회원정보 삭제
		int recommendCnt = this.boardService.readOneBoardRecommendCount(boardId);			// 한 게시글당 추천 수
		
		Map<String, Object> result = new HashMap<>();
		result.put("isSuccess", isSuccess);
		result.put("recommendCnt", recommendCnt);
		
		return result;		
	}
	
	// 게시글 하나 읽기
	@GetMapping("/board/detail")
	public ModelAndView viewBoardDetailPage(@RequestParam String boardId){
		
		BoardVO boardVO  = this.boardService.readOneBoard(boardId);		
				
		ModelAndView view = new ModelAndView("board/detail");
		// view.addObject("boardVO", boardVO);
		
		// XSS  방어하기: 게시글 읽을 시 제목과 내용에 대해 XSS HTML 인코딩 ( 파일은 이미 업로드 시, 이름난수화 시키고 jsp페이지에서 확장자 지정해주었으므로 ) 
		XssFilter filter = XssFilter.getInstance("lucy-xss-superset.xml");
		boardVO.setTitle( filter.doFilter(boardVO.getTitle()) );
		boardVO.setContent( filter.doFilter(boardVO.getContent()) );
		
		view.addObject("boardVO", boardVO);
		
		// XSS  방어하기: 댓글 읽을 시
		for ( Object boardReplyVO : boardVO.getReplyList() ) {
			BoardReplyVO replyVO = (BoardReplyVO) boardReplyVO;
			replyVO.setReply( filter.doFilter( replyVO.getReply()) );
		}
		
		//view.addObject("boardVO.getReplyList()", boardVO.getReplyList());
		
		return view;
	}
	
	
	// 파일 다운로드
	@RequestMapping("/board/download/{boardId}")
	public void fileDownload( 
			@PathVariable String boardId
			, HttpServletRequest request
			, HttpServletResponse response
			
		) {
	
	BoardVO boardVO = this.boardService.readOneBoard(boardId);

	// String originFileName = boardVO.getOriginFileName();
	 String pictureName = boardVO.getPicture();

	
	// Windows \
	// Unix/Linux/macos /
	
	try {
		new DownloadUtil(this.uploadPath + File.separator + pictureName)
				.download(request, response, pictureName);
	} catch (UnsupportedEncodingException e) {
		throw new RuntimeException(e.getMessage(), e);
	}
}
	
	
	// 게시글 삭제하기
	@GetMapping("/board/delete/{boardId}")
	public String doBoardDeleteAction(@PathVariable String boardId) {
		System.out.println("--------------doBoardDeleteAction");
		BoardVO boardVO = this.boardService.readOneBoard(boardId);
		System.out.println("--------------delete하기전 값= "+boardVO.getDeleteBoard());
		
		boolean isSuccess = this.boardService.updateDeleteOneBoard(boardVO.getBoardId());
		
		System.out.println("--------------delete하기후 값= " + boardVO.getDeleteBoard());
		System.out.println("--------------isSuccess= " + isSuccess);
		
		//boolean isSuccess = this.boardService.deleteOneBoard(boardId); 게시글 자체를 삭제(안에 있는 댓글들도)		
		
		return "redirect:/board/detail?boardId="+boardId;
	}
	
	
	// 게시글 수정하기
	@GetMapping("/board/modify/{boardId}")
	public ModelAndView viewModifyOneBoardPage(@PathVariable String boardId) {
			
		ModelAndView view = new ModelAndView("redirect:/board/modify");
			
		BoardVO boardVO = this.boardService.readOneBoard(boardId);
		view.setViewName("board/modify");
		view.addObject("boardVO", boardVO);
						
		return  view;
	} 
	
	
	// 게시글 수정하기
	@RequestMapping("/board/modify")
	public ModelAndView doModifyOneBoardAction( @Valid @ModelAttribute BoardVO boardVO
												,  @SessionAttribute(Session.USER) MemberVO memberVO
												, Errors errors
												, HttpSession session) {
					
		ModelAndView view = new ModelAndView("redirect:/board/list");
					
		// Validation Annotation이 실패했는지 체크( 실패하면, 다시 글쓰기페이지로 내용 유지해서 돌아감 )
		if ( errors.hasErrors() ) {
			view.setViewName("board/modify");
			view.addObject("boardVO", boardVO);
						
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
					
		boolean isSuccess = this.boardService.updateOneBoard(boardVO);
		
		// XSS: 게시글 수정시 제목과 내용에 대해 XSS HTML 인코딩 ( 파일은 이미 업로드 시, 이름난수화 시키고 jsp페이지에서 확장자 지정해주었으므로 ) 
		XssFilter filter = XssFilter.getInstance("lucy-xss-superset.xml");
		boardVO.setTitle( filter.doFilter(boardVO.getTitle()) );
		boardVO.setContent( filter.doFilter(boardVO.getContent()) );
					
		return  view;
	}	
	
}
