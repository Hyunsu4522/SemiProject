package com.semi.board.model.dao;

import static com.semi.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.semi.board.model.vo.Board;
import com.semi.board.model.vo.Reply;
import com.semi.common.model.vo.Attachment;
import com.semi.common.model.vo.PageInfo;
import com.semi.member.model.vo.Member;

public class BoardDao{
	private Properties prop = new Properties();
	
	public BoardDao() {		//기본생성자
		String filePath = BoardDao.class.getResource("/db/sql/board-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	
	public int selectSellListCount(Connection conn,int bwriter) {

		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectSellListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				listCount = rset.getInt("count");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		//System.out.println(listCount);
		return listCount;
		
		
	}
	
	public ArrayList<Board> selecSellDetailList(Connection conn, PageInfo pi, int bwriter){
		ArrayList<Board> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selecSellDetailList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			//System.out.println(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() -1;
			
			pstmt.setInt(1, bwriter);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			//System.out.println(startRow);
			//System.out.println(endRow);
			rset = pstmt.executeQuery();
			//System.out.println(rset);
			//System.out.println(rset.next());
			while(rset.next()) {
				list.add(new Board(rset.getInt("board_no"),
						rset.getString("board_title"),
						rset.getString("board_content"),
						rset.getString("user_id"),
						rset.getString("create_date"),
						rset.getInt("count"),
						rset.getString("status"),
						rset.getInt("amount"),
						rset.getString("title_img"),
						rset.getString("address")
						));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int selectListCount(Connection conn,int bwriter) {

		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bwriter);
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				listCount = rset.getInt("count");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
		
		
	}
	public ArrayList<Board> selectMyBoardList (Connection conn, PageInfo pi,int bwriter){
		//select 문 => ResultSet(여러행) => ArrayList<Board>
		ArrayList<Board> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectMyBoardList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			/**
			 * currentPage : 1 => 1 ~ 10
			 * currentPage : 2 => 11~ 20
			 * currentPage : 3 => 21 ~30
			 * 시작값 : (currentPage - 1) * boardLimit + 1
			 * 끝값 : 시작값 + boardLimit - 1
			 */
			
			int startRow = (pi.getCurrentPage() -1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() -1;
			
			pstmt.setInt(1, bwriter);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Board(
							rset.getInt("BOARD_NO"),
							rset.getString("BOARD_TITLE"),
							rset.getString("USER_ID"),
							rset.getString("CREATE_DATE"),
							rset.getInt("COUNT"),
							rset.getInt("AMOUNT"),
							rset.getString("TITLE_IMG")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	public ArrayList<Board> selectBoardList(Connection conn, int boardWriter){
		ArrayList<Board> list = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql =prop.getProperty("selectBoardList");
		
		try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, boardWriter);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					list.add(new Board(rset.getInt("board_no"),
										rset.getString("board_title"),
										rset.getString("board_content"),
										rset.getString("user_id"),
										rset.getString("create_date"),
										rset.getInt("count"),
										rset.getString("status"),
										rset.getInt("amount"),
										rset.getString("title_img"),
										rset.getString("address")
							));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	public ArrayList<Board> selectSellBoardList(Connection conn, int boardWriter){
		ArrayList<Board> list = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql =prop.getProperty("selectSellBoardList");
		
		try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, boardWriter);
	
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					list.add(new Board(rset.getInt("board_no"),
							rset.getString("board_title"),
							rset.getString("board_content"),
							rset.getString("user_id"),
							rset.getString("create_date"),
							rset.getInt("count"),
							rset.getString("status"),
							rset.getInt("amount"),
							rset.getString("title_img"),
							rset.getString("address")
							));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public int insertBoard(Connection conn, Board b) {
        int result = 0;
        
        PreparedStatement pstmt = null;
        String sql = prop.getProperty("insertBoard");
        
        try {
           pstmt = conn.prepareStatement(sql);
           
           pstmt.setString(1, b.getBoardTitle());
           pstmt.setString(2, b.getBoardContent());
           pstmt.setInt(3, Integer.parseInt(b.getBoardWriter()));

           pstmt.setInt(4, b.getAmount());

           
           result = pstmt.executeUpdate();
        } catch (SQLException e) {
           e.printStackTrace();
        } finally {
           close(pstmt);
        }
        
        return result;
        
     }
	
	public int insertAttachmentList(Connection conn, ArrayList<Attachment> list) {
        int result = 0;
        
        PreparedStatement pstmt = null;
        String sql = prop.getProperty("insertAttachmentList");
        
           try {
              
              for (Attachment at : list) {
                 pstmt = conn.prepareStatement(sql);
                 pstmt.setString(1, at.getOriginName());
                 pstmt.setString(2, at.getChangeName());
                 pstmt.setString(3, at.getFilePath());
                 pstmt.setInt(4, at.getFileLevel());
                 
                 result = pstmt.executeUpdate();
              }
              
           } catch (SQLException e) {
              e.printStackTrace();
           } finally {
              close(pstmt);
           }
           
        return result;
     }
	
	public ArrayList<Board> selectAllBoardList(Connection conn, PageInfo pi) {
	      Board b = null;
	      ArrayList<Board> list = new ArrayList<>();
	      
	      PreparedStatement pstmt = null;
	      ResultSet rset = null;
	      
	      String sql = prop.getProperty("selectAllBoardList");
	      
	      try {
	         pstmt = conn.prepareStatement(sql);
	         //System.out.println(sql);
	         
	         int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
	         int endRow = startRow + pi.getBoardLimit() -1;
	         
	         pstmt.setInt(1, startRow);
	         pstmt.setInt(2, endRow);
	         //System.out.println(startRow);
	         //System.out.println(endRow);
	         rset = pstmt.executeQuery();
	         //System.out.println(rset);
	         //System.out.println(rset.next());
	         while(rset.next()) {
	            b = new Board();
	            b.setBoardNo(rset.getInt("board_no"));
	            b.setBoardTitle(rset.getString("board_title"));
	            b.setCreateDate(  rset.getString("create_date"));
	            b.setCount(   rset.getInt("count"));
	            b.setAmount(rset.getInt("amount"));
	            b.setTitleImg(  rset.getString("TITLE_IMG"));
	            
	            list.add(b);
	         }
	         
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close(rset);
	         close(pstmt);
	      }
	      
	      //System.out.println(list.size());
	      
	      return list;
	   }
	
	public int selectBoardListCount(Connection conn) {

	      int boardListCount = 0;
	      
	      PreparedStatement pstmt = null;
	      ResultSet rset = null;
	      
	      String sql = prop.getProperty("selectBoardListCount");
	      
	      try {
	         pstmt = conn.prepareStatement(sql);
	         rset = pstmt.executeQuery();
	         
	         if (rset.next()) {
	            boardListCount = rset.getInt("count");
	         }
	      } catch (SQLException e) {
	         
	         e.printStackTrace();
	      } finally {
	         close(rset);
	         close(pstmt);
	      }
	      //System.out.println(listCount);
	      return boardListCount;
	      
	      
	   }
	
	public ArrayList<Board> selectBuyBoardList(Connection conn, int buyer){
		ArrayList<Board> list = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql =prop.getProperty("selectBuyBoardList");
		
		try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, buyer);
	
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					list.add(new Board(rset.getInt("board_no"),
							rset.getString("board_title"),
							rset.getString("board_content"),
							rset.getString("user_id"),
							rset.getString("create_date"),
							rset.getInt("count"),
							rset.getString("status"),
							rset.getInt("amount"),
							rset.getString("title_img"),
							rset.getString("address")
							));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	public int selectBuyListCount(Connection conn, int buyer) {

		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectBuyListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, buyer);
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				listCount = rset.getInt("count");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		//System.out.println(listCount);
		return listCount;
		
		
	}
	public ArrayList<Board> selectMyBuyDetailList(Connection conn, PageInfo pi, int buyer){
		ArrayList<Board> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectMyBuyDetailList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			//System.out.println(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() -1;
			
			pstmt.setInt(1, buyer);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			//System.out.println(startRow);
			//System.out.println(endRow);
			rset = pstmt.executeQuery();
			//System.out.println(rset);
			//System.out.println(rset.next());
			while(rset.next()) {
				list.add(new Board(rset.getInt("board_no"),
						rset.getString("board_title"),
						rset.getString("board_content"),
						rset.getString("user_id"),
						rset.getString("create_date"),
						rset.getInt("count"),
						rset.getInt("amount"),
						rset.getString("title_img"),
						rset.getString("address")
						));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public ArrayList<Reply> selectReplyList(Connection conn, int boardNO) {
        ArrayList<Reply> list = new ArrayList<>();
        
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String sql = prop.getProperty("selectReplyList");
        
        try {
           pstmt = conn.prepareStatement(sql);
           
           pstmt.setInt(1, boardNO);
          
           rset = pstmt.executeQuery();
           
           while(rset.next()) {
             list.add(new Reply(
                    rset.getInt("reply_no"),
                    rset.getString("reply_content"),
                    rset.getString("user_id"),
                    rset.getString("create_Date")
                   ));
           }
           
           
        } catch (SQLException e) {
           e.printStackTrace();
        } finally {
           close(rset);
           close(pstmt);
        }
        
        return list;
     }
  
  public int insertReply(Connection conn,Reply r) {
     int result = 0;
     
     PreparedStatement pstmt = null;
     String sql = prop.getProperty("insertReply");
     
     try {
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, r.getReplyContent());
        pstmt.setInt(2, r.getRefBoardNo());
        pstmt.setInt(3, Integer.parseInt(r.getReplyWriter()));
        
        result = pstmt.executeUpdate();
        
     } catch (SQLException e) {
        e.printStackTrace();
     } finally {
        close(pstmt);
     }
     
     return result;
  }
  
  
  public int increaseCount(Connection conn, int BoardNo) {
		//update => 처리된행수 => 트랜잭션 처리
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("increaseCount");
		
		try {
			pstmt = conn.prepareStatement(sql); //미완성sql
			pstmt.setInt(1, BoardNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
  
	  public Board selectBoard(Connection conn, int boardNo) {
			//select => ResultSet(한행) => Board객체
			
			ResultSet rset = null;
			Board b = null;
			
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("selectBoard");
			
			try {
				pstmt = conn.prepareStatement(sql); // 미완성
				pstmt.setInt(1, boardNo);
				
				rset = pstmt.executeQuery();
				if(rset.next()) {
					b = new Board(
								rset.getInt("board_no"),
								rset.getString("board_title"),
								rset.getString("board_content"),
								rset.getString("user_id"),
								rset.getString("create_date")
							);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
//			System.out.println(b);
			
			return b;
			
		}
	  
	  public Attachment selectAttachment(Connection conn, int boardNo) {
			//select => ResultSet => Attachment
			
			Attachment at = null;
			ResultSet rset = null;
			
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("selectAttachment");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, boardNo);
				
				rset = pstmt.executeQuery();
				
				if (rset.next()) {
					at = new Attachment();
					at.setFileNo(rset.getInt("file_no"));
					at.setOriginName(rset.getString("origin_name"));
					at.setChangeName(rset.getString("change_name"));
					at.setFilePath(rset.getString("file_path"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			
			return at;
		}
	  //board에서 sale_yn Y로 바꿈
	  public int saleYnAlter(Connection conn,Member m,int boardNo) {
	     int result = 0;
	     
	     PreparedStatement pstmt = null;
	     String sql = prop.getProperty("saleYnAlter");
	     
	     try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, boardNo);
	        
	        result = pstmt.executeUpdate();
	        
	     } catch (SQLException e) {
	        e.printStackTrace();
	     } finally {
	        close(pstmt);
	     }
	     
	     return result;
	  }
	  
	  //sale_log 에 insert문 날려서 거래정보담기
	  public int insertSaleLog(Connection conn,Member m,int boardNo, int rWriter) {
		  int result = 0;
		     
		     PreparedStatement pstmt = null;
		     String sql = prop.getProperty("insertSaleLog");
		     
		     try {
		        pstmt = conn.prepareStatement(sql);
		        pstmt.setInt(1, m.getUserNo());
		        pstmt.setInt(2, boardNo);
		        pstmt.setInt(3, rWriter);
		        
		        result = pstmt.executeUpdate();
		        
		     } catch (SQLException e) {
		        e.printStackTrace();
		     } finally {
		        close(pstmt);
		     }
		     
		     return result;
	  }
	  public int deleteReply(Connection conn,int replyNo) {
			
			int result = 0;
			
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("deleteReply");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, replyNo);
				
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			return result;
		}
}
