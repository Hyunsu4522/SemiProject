package com.semi.board.model.service;


import static com.semi.common.JDBCTemplate.close;
import static com.semi.common.JDBCTemplate.commit;
import static com.semi.common.JDBCTemplate.getConnection;
import static com.semi.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.semi.board.model.dao.BoardDao;
import com.semi.board.model.vo.Board;
import com.semi.board.model.vo.Reply;
import com.semi.common.model.vo.Attachment;
import com.semi.common.model.vo.PageInfo;

public class BoardService {
	public int selectSellListCount(int bwriter) {
		Connection conn = getConnection();
		
		int listCount = new BoardDao().selectSellListCount(conn,bwriter);
		
		close(conn);
		
		return listCount;
	}
	
	public ArrayList<Board> selecSellDetailList(PageInfo pi, int bwriter){
		Connection conn = getConnection();
		
		ArrayList<Board> list = new BoardDao().selecSellDetailList(conn, pi,bwriter);
		
		close(conn);
		
		return list;
	}
	
	public int selectListCount(int bwriter) {
		Connection conn = getConnection();
		
		int listCount = new BoardDao().selectListCount(conn,bwriter);
		
		close(conn);
		
		return listCount;
	}
	
	public ArrayList<Board> selectMyBoardList(PageInfo pi, int bwriter) {
		Connection conn = getConnection();
		ArrayList<Board> list = new BoardDao().selectMyBoardList(conn ,pi,bwriter);
		
		close(conn);
		return list;
	}
	public ArrayList<Board> selectBoardList(int boardWriter){
		Connection conn = getConnection();
		ArrayList<Board> list = new BoardDao().selectBoardList(conn,boardWriter);
		close(conn);
		return list;
	}
	public ArrayList<Board> selectSellBoardList(int boardWriter){
		Connection conn = getConnection();
		ArrayList<Board> list = new BoardDao().selectSellBoardList(conn,boardWriter);
		close(conn);
		return list;
	}

	public int insertBoard(Board b, ArrayList<Attachment> list) {
	      Connection conn = getConnection();
	      
	      int result1 = new BoardDao().insertBoard(conn, b);
	      int result2 = new BoardDao().insertAttachmentList(conn, list);
	      
	      
//	      System.out.println(result1);
//	      System.out.println(result2);
	      if (result1 > 0 && result2 > 0) {
	         commit(conn);
	      } else {
	         rollback(conn);
	      }
	      
	      close(conn);
	      
	      return result1 * result2;
	   }
	
	public ArrayList<Board> selectAllBoardList(PageInfo pi){
	      Connection conn = getConnection();
	      
	      ArrayList<Board> list = new BoardDao().selectAllBoardList(conn, pi);
	      
	      close(conn);
	      System.out.println(list);
	      return list;
	   }
	
	  public int selectBoardListCount() {
	      Connection conn = getConnection();
	      
	      int boardListCount = new BoardDao().selectBoardListCount(conn);
	      
	      close(conn);
	      
	      return boardListCount;
	   }
	  
	  public ArrayList<Board> selectBuyBoardList(int buyer){
		Connection conn = getConnection();
		ArrayList<Board> list = new BoardDao().selectBuyBoardList(conn,buyer);
		close(conn);
		return list;
	  }
	  
		public int selectBuyListCount(int buyer) {
			Connection conn = getConnection();
	
			int listCount = new BoardDao().selectBuyListCount(conn,buyer);
	
			close(conn);
	
			return listCount;
		}
		
		public ArrayList<Board> selectMyBuyDetailList(PageInfo pi, int buyer){
			Connection conn = getConnection();
			
			ArrayList<Board> list = new BoardDao().selectMyBuyDetailList(conn, pi,buyer);
			
			close(conn);
			
			return list;
		}
		
		public ArrayList<Reply> selectReplyList(int boardNo){
		       Connection conn = getConnection();
		       
		       ArrayList<Reply> list = new BoardDao().selectReplyList(conn, boardNo);
		       close(conn);
		       
		       return list;
		    }
		    public int insertReply(Reply r) {
		      Connection conn = getConnection();
		      int result = new BoardDao().insertReply(conn, r);
		      
		      if (result > 0) {
		         commit(conn);
		      } else {
		         rollback(conn);
		      }
		      
		      close(conn);
		      
		      return result;
		   } 
}