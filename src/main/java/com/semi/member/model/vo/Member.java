package com.semi.member.model.vo;

import java.sql.Date;

public class Member {
   private int userNo;
   private String userId;
   private String userPwd;
   private String userName;
   private String address;
   private Date enrollDate;
   private Date modifyDate;
   private String status;
   private String profileUrl;
   
   public Member() {}
   
   

   public Member(String userId, String userPwd, String userName, String address) {
      super();
      this.userId = userId;
      this.userPwd = userPwd;
      this.userName = userName;
      this.address = address;
   }



   public Member(int userNo, String userId, String userPwd, String userName, String address, Date enrollDate,
         Date modifyDate, String status, String profileUrl) {
      super();
      this.userNo = userNo;
      this.userId = userId;
      this.userPwd = userPwd;
      this.userName = userName;
      this.address = address;
      this.enrollDate = enrollDate;
      this.modifyDate = modifyDate;
      this.status = status;
      this.profileUrl = profileUrl;
   }
   
   
   

   public Member(int userNo, String userId, String userPwd, String userName, String address, Date enrollDate,
         Date modifyDate, String status) {
      super();
      this.userNo = userNo;
      this.userId = userId;
      this.userPwd = userPwd;
      this.userName = userName;
      this.address = address;
      this.enrollDate = enrollDate;
      this.modifyDate = modifyDate;
      this.status = status;
   }

   public int getUserNo() {
      return userNo;
   }

   public void setUserNo(int userNo) {
      this.userNo = userNo;
   }

   public String getUserId() {
      return userId;
   }

   public void setUserId(String userId) {
      this.userId = userId;
   }

   public String getUserPwd() {
      return userPwd;
   }

   public void setUserPwd(String userPwd) {
      this.userPwd = userPwd;
   }

   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public Date getEnrollDate() {
      return enrollDate;
   }

   public void setEnrollDate(Date enrollDate) {
      this.enrollDate = enrollDate;
   }

   public Date getModifyDate() {
      return modifyDate;
   }

   public void setModifyDate(Date modifyDate) {
      this.modifyDate = modifyDate;
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String getProfileUrl() {
      return profileUrl;
   }

   public void setProfileUrl(String profileUrl) {
      this.profileUrl = profileUrl;
   }

   @Override
   public String toString() {
      return "Member [userNo=" + userNo + ", userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName
            + ", address=" + address + ", enrollDate=" + enrollDate + ", modifyDate=" + modifyDate + ", status="
            + status + ", profileUrl=" + profileUrl + "]";
   }
   
   

   


}