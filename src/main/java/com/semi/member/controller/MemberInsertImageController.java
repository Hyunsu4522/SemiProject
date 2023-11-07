package com.semi.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.semi.common.MyFileRenamePolicy;
import com.semi.member.model.service.MemberService;
import com.semi.member.model.vo.Member;

/**
 * Servlet implementation class MemberUpdateImageController
 */
@WebServlet("/updateImg.me")
public class MemberInsertImageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertImageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (ServletFileUpload.isMultipartContent(request)) {
		
		//1_1)파일용량제한
		int maxSize = 10 * 1024 * 1024;
		
		//1_2)전달된 파일을 저장시킬 폴더의 경로 알아내기
		String savePath = request.getSession().getServletContext().getRealPath("/resources/member_upfile/");
		
		System.out.println(savePath);
		
		//2 전달된 파일 업로드(request 변환)
		MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
		
		int userNo = Integer.parseInt(multiRequest.getParameter("userNo"));
		//System.out.println(userNo);
		String filePath = multiRequest.getParameter("filePath");
		System.out.println(filePath);
		Member updateMem = new MemberService().insertMemberImage(userNo, filePath, multiRequest );
		
		if (updateMem == null) {
			request.setAttribute("errorMsg", "사진등록에 실패하였습니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			
		} else {
			request.getSession().setAttribute("alertMsg", "성공적으로 등록되었습니다");
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", updateMem);
			response.sendRedirect((request.getContextPath() + "/myPage.me"));
		}
		
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
