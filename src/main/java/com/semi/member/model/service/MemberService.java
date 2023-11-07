package com.semi.member.model.service;


import static com.semi.common.JDBCTemplate.close;
import static com.semi.common.JDBCTemplate.commit;
import static com.semi.common.JDBCTemplate.getConnection;
import static com.semi.common.JDBCTemplate.rollback;

import java.sql.Connection;

import com.oreilly.servlet.MultipartRequest;
import com.semi.member.model.dao.MemberDao;
import com.semi.member.model.vo.Member;




public class MemberService {
	
	public Member loginMember(String userId, String userPwd) {
		
		Connection conn = getConnection();
		Member m = new MemberDao().loginMember(conn, userId, userPwd);
		
		close(conn);
		
		return m;
		
	}
	
	public int deleteMember(String userId,String userPwd) {
		Connection conn = getConnection();
		int result = new MemberDao().deleteMember(conn, userId,userPwd);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	public int insertMember(Member m) {
		Connection conn = getConnection();
		
		
		int result = new MemberDao().insertMember(conn, m);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	public Member updateIdMember (String userId, String updateId) {
	      Connection conn = getConnection();
	      
	      int result = new MemberDao().updateIdMember(conn, userId, updateId);
	      
	      Member updateIdMem = null;
	      
	      if(result > 0) {
	         commit(conn);
	         updateIdMem = new MemberDao().selectMemberId(conn, updateId);
	      }else {
	         rollback(conn);
	      }
	      close(conn);
	      
	      return updateIdMem;
	      
	   }
	   
	   public Member updatePwdMember(String userId, String userPwd, String updatePwd) {
	      Connection conn = getConnection();
	      int result = new MemberDao().updatePwdMember(conn, userId, userPwd, updatePwd);
	      
	      Member updateMem = null;
	      if (result > 0) {
	         commit(conn);
	         // 갱신된 회원 객체 다시 조회해오기
	         updateMem = new MemberDao().selectMember(conn, userId);
	      } else {
	         rollback(conn);
	      }
	      
	      close(conn);
	      
	      return updateMem;
	   }
	   
	   public Member updateAdMember (String userId, String address, String updateAddress) {
	      Connection conn = getConnection();
	      
	      int result = new MemberDao().updateAdMember(conn, userId, address, updateAddress);
	      
	      Member updateAdMem = null;
	      
	      if(result > 0) {
	         commit(conn);
	         updateAdMem = new MemberDao().selectMember(conn, userId);
	      }else {
	         rollback(conn);
	      }
	      close(conn);
	      
	      return updateAdMem;
	      
	   }
	   
	   
	      public Member insertMemberImage(int userNo, String filePath, MultipartRequest multiRequest ) {
	          Connection conn = getConnection();
	          
	          int result = new MemberDao().insertMemberImage(conn, userNo, filePath, multiRequest);
	          
	          Member updateMem = null;
	          if(result > 0) {
	                commit(conn);
	                
	                updateMem = new MemberDao().UpdateImgMember(conn, userNo);
	             }
	             close(conn);
	             
	             return updateMem;
	       }
	
}
