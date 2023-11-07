package com.semi.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.semi.board.model.service.BoardService;
import com.semi.board.model.vo.Board;

/**
 * Servlet implementation class BoardListController
 */
@WebServlet("/blist.bo")
public class AjaxBoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxBoardListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardWriter = Integer.parseInt(request.getParameter("bwriter"));
		ArrayList<Board> list = new BoardService().selectBoardList(boardWriter);
		//[{},{},{}] 이렇게 오고, 이거 쉽게 gson으로 꺼내라
		//한글이니까 형식도 바꿔라
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(list,response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
