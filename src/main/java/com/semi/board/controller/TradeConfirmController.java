package com.semi.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.board.model.service.BoardService;
import com.semi.member.model.vo.Member;

/**
 * Servlet implementation class TradeConfirmController
 */
@WebServlet("/trade.bo")
public class TradeConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TradeConfirmController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//Map<String, Integer> map = new HashMap<String, Integer>();
		//Map.put("bno", Integer.parseInt(request.getParameter("bno")));
		
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		String rWriter = request.getParameter("rWriter");
		Member m = ((Member)request.getSession().getAttribute("loginUser"));
		
		BoardService bService = new BoardService();// 여러번쓰기위해 생성해놓고 씀
		
		int result = bService.saleYnAlter(m,boardNo,rWriter);
		
		//응답뷰요청
		if(result > 0) {//성공시에는 목록으로 가야함(jsp/list.bo?cpage=1)
			request.getSession().setAttribute("alertMsg", "거래완료 설정에 성공하였습니다.");
			response.sendRedirect(request.getContextPath() +"/detailPage.bo?bno=" + boardNo);
			
		}else {//실패시 => 업로드된 파일 삭제해주고 에러페이지
			request.setAttribute("errorMsg", "거래 완료설정에 실패하였습니다.");
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
