package com.semi.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.semi.board.model.service.BoardService;
import com.semi.board.model.vo.Board;
import com.semi.common.MyFileRenamePolicy;
import com.semi.common.model.vo.Attachment;

/**
 * Servlet implementation class insertBoardController
 */
@WebServlet("/insert.bo")
public class InsertBoardController extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertBoardController() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("UTF-8");
      
      HttpSession session = request.getSession();
      
      if(session.getAttribute("loginUser") == null) {
         session.setAttribute("alertMsg", "로그인 후 이용가능한 서비스입니다.");
         response.sendRedirect(request.getContextPath());
         
      }else {

         if (ServletFileUpload.isMultipartContent(request)) {
         
            int maxSize = 10*1024*1024;
            
         
            String savePath = request.getSession().getServletContext().getRealPath("/resources/thumbnail_upfile/");
            
         
            MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
            
            
            Board b = new Board();
            b.setBoardWriter(multiRequest.getParameter("userNo"));
            b.setBoardTitle(multiRequest.getParameter("title"));
            b.setBoardContent(multiRequest.getParameter("content"));
            b.setAmount(Integer.parseInt(multiRequest.getParameter("amount")));

      
            ArrayList<Attachment> list = new ArrayList<>();
            for(int i = 1; i <= 4; i++) {
               String key = "file" + i;
               if (multiRequest.getOriginalFileName(key) != null) {
                  
                  
                  Attachment at = new Attachment();
                  at.setOriginName(multiRequest.getOriginalFileName(key));
                  at.setChangeName(multiRequest.getFilesystemName(key));
                  at.setFilePath("resources/thumbnail_upfile/");
                  
                  if(i == 1) { //대표이미지
                     at.setFileLevel(1);
                  } else { // 상세이미지
                     at.setFileLevel(2);
                  }
                  
                  list.add(at);
               }
            }
            
            int result = new BoardService().insertBoard(b, list);
            System.out.println(result);
            if (result > 0) { 
               request.getSession().setAttribute("alertMsg", "성공적으로 게시글 등록하였습니다");
               response.sendRedirect(request.getContextPath());
               
            } else { //실패 => 에러페이지 
               request.setAttribute("errorMsg", "게시글 작성 실패");
               request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
            }
            
            
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