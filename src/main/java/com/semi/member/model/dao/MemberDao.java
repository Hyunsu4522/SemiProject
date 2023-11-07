package com.semi.member.model.dao;
import static com.semi.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.oreilly.servlet.MultipartRequest;
import com.semi.member.model.vo.Member;


public class MemberDao {
private Properties prop = new Properties();

	public MemberDao() {
		String filePath = MemberDao.class.getResource("/db/sql/member-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {

			e.printStackTrace();
		}
		
	}
	
	public Member loginMember(Connection conn, String userId, String userPwd) {
	      Member m = null;
	      
	      PreparedStatement pstmt = null;
	      ResultSet rset = null;
	      
	      String sql = prop.getProperty("loginMember");
	      

	      try {
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, userId);
	         pstmt.setString(2, userPwd);
	         
	         rset = pstmt.executeQuery();
	         
	         if (rset.next()) {
	            m = new Member(
	                  rset.getInt("user_no"),
	                  rset.getString("user_id"),
	                  rset.getString("user_pwd"),
	                  rset.getString("user_name"),
	                  rset.getString("address"),
	                  rset.getDate("enroll_date"),
	                  rset.getDate("modify_date"),
	                  rset.getString("status"),
	                  rset.getString("profile_url")
	                  
	                  );
	         }

	         
	      } catch (SQLException e) {

	         e.printStackTrace();
	      } finally {
	         close(rset);
	         close(pstmt);
	      }
	      

	      return m;
	      
	      
	      
	   }

	public int deleteMember(Connection conn, String userId, String userPwd) {
		//update =>처리된 행수 => 트랜잭션 처리
			int result =0 ;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("deleteMember");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, userId);
				pstmt.setString(2, userPwd);
				
				result = pstmt.executeUpdate();
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally {
				close(pstmt);
			}
			return result;
	}
	
	
	
public int insertMember(Connection conn, Member m) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertMember");
		
	
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, m.getUserId());
				pstmt.setString(2, m.getUserPwd());
				pstmt.setString(3, m.getUserName());
				pstmt.setString(4, m.getAddress());
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(pstmt);	
			}
			return result;
			
		
	}
	
public Member selectMember(Connection conn, String userId) {
    Member m = null;
    
    PreparedStatement pstmt = null;
    ResultSet rset = null;
    
    String sql = prop.getProperty("selectMember");
    

    try {
       pstmt = conn.prepareStatement(sql);
       pstmt.setString(1, userId);
       
       rset = pstmt.executeQuery();
       
       if (rset.next()) {
          m = new Member(
                rset.getInt("user_no"),
                rset.getString("user_id"),
                rset.getString("user_pwd"),
                rset.getString("user_name"),
                rset.getString("address"),
                rset.getDate("enroll_date"),
                rset.getDate("modify_date"),
                rset.getString("status")
                );
       }

       
    } catch (SQLException e) {

       e.printStackTrace();
    } finally {
       close(rset);
       close(pstmt);
    }
    
    return m;
 }
 
 public Member selectMemberId(Connection conn, String updateId) {
    Member m = null;
    
    PreparedStatement pstmt = null;
    ResultSet rset = null;
    
    String sql = prop.getProperty("selectMember");
    

    try {
       pstmt = conn.prepareStatement(sql);
       pstmt.setString(1, updateId);
       
       rset = pstmt.executeQuery();
       
       if (rset.next()) {
          m = new Member(
                rset.getInt("user_no"),
                rset.getString("user_id"),
                rset.getString("user_pwd"),
                rset.getString("user_name"),
                rset.getString("address"),
                rset.getDate("enroll_date"),
                rset.getDate("modify_date"),
                rset.getString("status")
                );
       }

       
    } catch (SQLException e) {

       e.printStackTrace();
    } finally {
       close(rset);
       close(pstmt);
    }
    
    return m;
 }

 
 public int updateIdMember (Connection conn, String userId, String updateId) {
    int result =0 ;
    
    PreparedStatement pstmt = null;
    String sql = prop.getProperty("updateIdMember");
    
    try {
       pstmt = conn.prepareStatement(sql);
       
       pstmt.setString(1, updateId);
       pstmt.setString(2, userId);
       
       result = pstmt.executeUpdate();
       
       
    } catch (SQLException e) {
       
       e.printStackTrace();
    }finally {
       close(pstmt);
    }
    return result;
 }
 
 public int updatePwdMember(Connection conn, String userId,String userPwd,String updatePwd) {
    //update => 처리된 행수 => 트랜잭션 처리
    int result = 0;
    
    PreparedStatement pstmt = null;
    String sql = prop.getProperty("updatePwdMember");
    
    try {
       pstmt = conn.prepareStatement(sql); // 미완성 sql
       
       pstmt.setString(1, updatePwd);
       pstmt.setString(2, userId);
       pstmt.setString(3, userPwd);
       
       result = pstmt.executeUpdate();
       
    } catch (SQLException e) {
       e.printStackTrace();
    } finally {
       close(pstmt);
    }
    
    return result;
 }
 
 public int updateAdMember (Connection conn, String userId, String address, String updateAddress) {
    int result =0 ;
    
    PreparedStatement pstmt = null;
    String sql = prop.getProperty("updateAdMember");
    
    try {
       pstmt = conn.prepareStatement(sql);
       
       pstmt.setString(1, updateAddress);
       pstmt.setString(2, userId);
       pstmt.setString(3, address);
       
       result = pstmt.executeUpdate();
       
    } catch (SQLException e) {
       
       e.printStackTrace();
    }finally {
       close(pstmt);
    }
    return result;
 }
	
 public int insertMemberImage(Connection conn, int userNo, String filePath, MultipartRequest multiRequest ) {
     int result = 0;
       
       PreparedStatement pstmt = null;
       String sql = prop.getProperty("insertMemberImage");
       
          try {
                pstmt = conn.prepareStatement(sql);
               
                pstmt.setString(1, filePath + multiRequest.getFilesystemName("file"));
                pstmt.setInt(2, userNo);
                
                result = pstmt.executeUpdate();
             
          } catch (SQLException e) {
             e.printStackTrace();
           
          } finally {
             close(pstmt);
          }

      
          return result;
 }
 
 
 
  public Member UpdateImgMember(Connection conn, int userNo) {
        Member m = null;
        
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String sql = prop.getProperty("UpdateImgMember");
        

        try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setInt(1, userNo);
           
           rset = pstmt.executeQuery();
           
           if (rset.next()) {
              m = new Member(
                    rset.getInt("user_no"),
                    rset.getString("user_id"),
                    rset.getString("user_pwd"),
                    rset.getString("user_name"),
                    rset.getString("address"),
                    rset.getDate("enroll_date"),
                    rset.getDate("modify_date"),
                    rset.getString("status"),
                    rset.getString("PROFILE_URL")
                    );
           }

           
        } catch (SQLException e) {

           e.printStackTrace();
        } finally {
           close(rset);
           close(pstmt);
        }
        
        return m;
     }
 
 
}
