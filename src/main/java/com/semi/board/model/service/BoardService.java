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
import com.semi.member.model.vo.Member;

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
		    
		    public Board increaseCount(int boardNo) {
				Connection conn = getConnection();
				
				BoardDao bDao = new BoardDao();
				int result = bDao.increaseCount(conn, boardNo);
				
				Board b = null;
				if (result > 0) {
					commit(conn);
					//정보조회
					b = bDao.selectBoard(conn, boardNo);
				} else {
					rollback(conn);
				}
				
				close(conn);
				
				return b;
			}
		    
		    public Attachment selectAttachment(int boardNO) {
				Connection conn = getConnection();
				Attachment at = new BoardDao().selectAttachment(conn, boardNO);
				
				close(conn);
				
				return at;
			}
		    
		    public Board selectBoard(int boardNo) {
				Connection conn = getConnection();
				Board b = new BoardDao().selectBoard(conn, boardNo);
				
				close(conn);
				return b;
			}
		    public int saleYnAlter(Member m,int boardNo,int rWriter) {
		    	Connection conn = getConnection();
				
				BoardDao bDao = new BoardDao();
				int result1 = bDao.saleYnAlter(conn, m, boardNo);
				int result2 = bDao.insertSaleLog(conn, m, boardNo, rWriter);
				
				if(result1 > 0 && result2 > 0) {
					commit(conn);
				} else {
					rollback(conn);
				}
				close(conn);
				
				return result1 * result2;
				
		    }
		    
		    public int deleteReply(int replyNo) {
				Connection conn = getConnection();
				int result = new BoardDao().deleteReply(conn, replyNo);
				
				if (result > 0) {
					commit(conn);
				} else {
					rollback(conn);
				}
				
				close(conn);
				
				return result;
			}
}
