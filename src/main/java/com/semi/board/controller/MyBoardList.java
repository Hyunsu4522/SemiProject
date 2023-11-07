package com.semi.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.board.model.service.BoardService;
import com.semi.board.model.vo.Board;
import com.semi.common.model.vo.PageInfo;
import com.semi.member.model.vo.Member;

/**
 * Servlet implementation class MyBoardList
 */
@WebServlet("/detail.bo")
public class MyBoardList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyBoardList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//----------------------페이징 처리-----------------
		int listCount;//현재 총 게시글 수
		int currentPage;//현재 페이지 (즉,사용자가 요청한 페이지)
		int pageLimit; // 페이지 하단에 보여질 페이징바의 페이지 최대의 개수
		int boardLimit; // 한 페이지내에 보여질 게시글 최대 갯수
		//위의4개의 값을 기준으로 아래 3개의 값을 구할 것이다.
		
		int maxPage; // 가장 마지막 페이지(총 페이지의 수)
		int startPage; // 페이징바의 시작수 
		int endPage; //페이징바의 끝수
		
		//로그인정보 가져와서 작성자 필터해야함
		HttpSession session = request.getSession();
		Member m = (Member)session.getAttribute("loginUser");
		int bwriter = m.getUserNo();
		//*listCount : 총 게시글 수
		listCount = new BoardService().selectListCount(bwriter);
		
		// *currentPage : 현재페이지(즉, 사용자가 요청한 페이지)
		currentPage = Integer.parseInt(request.getParameter("cpage"));

		
		
		//* pageLimit : 페이징바의 최대 개수(단위)
		pageLimit = 10;
		
		//*boardLimit : 한 페이지 내에 보여질 게시글 최대 갯수(단위)
		boardLimit =6;
		
		/**
		 * maxPage 구하는법 : 제일 마지막 페이지 수(총 페이지의 수)
		 * 
		 * listCount, boardLimit에 영향을 받음
		 * ex)게시글이 10개 단위 보여진다는 가정하에 
		 * listCount	boardLimit		maxPage
		 * 		100.0			10		=>		10
		 * 		101.0			10		=>		11
		 * 		105.0			10		=>		11
		 * (실수)listCount 를 (실수)boardLimit으로 나눈 나머지에 올림처리 하면 그게 maxPage
		 *  
		 */
		
		maxPage = (int)Math.ceil((double)listCount / boardLimit);
		
		/**
		 * startPage : 페이징바 시작수
		 * 
		 * pageLimit, currentPage에 영향을 받음 
		 * 
		 * ex) 페이징바의 목록이 10단위씩이라는 가정하에
		 * 		startPage : 1, 11 ,21, 31 ...
		 * 				=>	n*10 + 1
		 * 				=> 	n*pageLimit + 1
		 * 
		 * 1~10 => n =0
		 * 11~20 => n=1		
		 * 21~30 => n=2
		 * ...
		 * 0~9
		 * 10~19
		 * 20~29
		 */
		
		startPage = ((currentPage - 1) / pageLimit) * pageLimit + 1;
		
		/**
		 * endPage : 페이징바의 끝 수
		 * startPage, pageLimit
		 * 
		 */
		
		endPage = startPage + pageLimit -1;
		
		//startPage가 11이면 endPage는 20이 됨(만약 maxPage가 13이라면?)
		endPage = endPage > maxPage ? maxPage : endPage;
		
		PageInfo pi = new PageInfo(listCount,currentPage,pageLimit,boardLimit,maxPage,startPage,endPage);
		
		ArrayList<Board> list = new BoardService().selectMyBoardList(pi,bwriter);
		
		request.setAttribute("pi", pi);
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("views/board/myBoardList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
