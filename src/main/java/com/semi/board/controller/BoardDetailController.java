package com.semi.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.board.model.service.BoardService;
import com.semi.board.model.vo.Board;
import com.semi.common.model.vo.Attachment;

/**
 * Servlet implementation class BoardDetailController
 */
@WebServlet("/detailPage.bo")
public class BoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		
		BoardService bService = new BoardService();
		//조회수 1 증가시키고 디테일페이지에 보여줄 board객체를 가져와라
		Board b = bService.increaseCount(boardNo);
		
		if (b != null) {
			Attachment at = bService.selectAttachment(boardNo);
			
			request.setAttribute("b", b);
			request.setAttribute("at", at);
			
			request.getRequestDispatcher("views/board/detailPage.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMsg", "게시글 조회 실패");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
