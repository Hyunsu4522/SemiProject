package com.semi.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.board.model.service.BoardService;
import com.semi.board.model.vo.Board;
import com.semi.common.model.vo.PageInfo;

/**
 * Servlet implementation class BoardListViewController
 */
@WebServlet("/boardlist.bo")
public class BoardListViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListViewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardListCount;  	//현재 총 게시글 수
		int currentPage; 	//현재 페이지(즉, 사용자가 요청한 페이지)
		int pageLimit;		//페이지 하단에 보여질 페이징바의 페이지 최대의 개수
		int boardLimit;		//한 페이지내에 보여질 게시글 최대갯수
		// 위 4개의 값을 기준으로 아래 3개의 값을 구할 것이다.
		
		int maxPage; 		//가장 마지막페이지(총 페이지의 수)
		int startPage;		//페이징바의 시작 수
		int endPage;		//페이징바의 끝수
		
		// *listCount : 총 게시글 수
		boardListCount = new BoardService().selectBoardListCount();
		
		// *currentPage : 현재 페이지(즉, 사용자가 요청한 페이지)
		currentPage = Integer.parseInt(request.getParameter("cpage"));
		
		// *pageLimit : 페이징바의 최대 갯수(단위)
		pageLimit = 10;
		
		// *boardLimit : 한 페이지내에 보여질 게시글 최대갯수(단위)
		boardLimit = 6;
		
		/**
		 *  *maxPage : 제일 마지막 페이지 수(총 페이지의 수)
		 *  
		 *  listCount, boardLimit에 영향을 받음
		 *  
		 *  ex) 게시글이 10개 단위 보여진다는 가정하에 
		 *  listCount	boardLimit		maxPage
		 *  	100			10	 =>10	  10
		 *  	101			10	 =>10.1	  11
		 *  
		 *  총 게시글수(실수) / boardLimit = 올림처리
		 */
		
		maxPage = (int)Math.ceil((double)boardListCount / boardLimit);
		
		/**
		 *  *startPage : 페이징바 시작 수
		 *  
		 *  pageLimit, currentPage에 영향을 받음
		 *  
		 *  ex) 페이징바의 목록이 10단위씩이라는 가정하에
		 *  	startPage : 1, 11, 21, 31 ...
		 *  			=> n*pageLimit +1
		 *  
		 *  currentPage		pageLimit
		 *  	1				1			=>	+ 1
		 */
							
		startPage = ((currentPage -1) / pageLimit)*pageLimit+1;
		
		/**
		 *  * endPage : 페이징바의 끝 수
		 *  
		 *  * startPage, pageLimit
		 *  
		 *  pageLimit : 10이라는 가정하에
		 *  
		 *  startPage : 1 => endPage : 10
		 *  startPage : 11 => endPage : 20
		 *  
		 */
		
		endPage = startPage + pageLimit -1;
		
		//startPage가 11이면 endPage는 20이 됨(만약 maxPage가 13이라면 ?)
		endPage = endPage > maxPage ? maxPage : endPage;
		
		PageInfo pi = new PageInfo(boardListCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		
		request.setAttribute("pi", pi);
		
		ArrayList<Board> list = new BoardService().selectAllBoardList(pi);
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("views/board/boardListView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
