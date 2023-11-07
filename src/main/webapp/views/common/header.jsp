<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.semi.member.model.vo.Member"%>
<%

	String contextPath = request.getContextPath();
	Member loginUser = (Member)session.getAttribute("loginUser");
	String alertMsg = (String)session.getAttribute("alertMsg");


%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no, viewport-fit=cover">
    <meta name="description" content="">
    <meta name="keyword" content="">
    <title>당신 근처의 당근</title>
    <link rel="stylesheet" href="./resources/css/common.css">
    <link rel="stylesheet" href="./resources/css/content.css">
    <link rel="stylesheet" href="./resources/css/font.css">
    
</head>
<body>
 	<% if(alertMsg != null){ %>
		<script>
			alert("<%=alertMsg%>");
		</script>
		<% session.removeAttribute("alertMsg");%>
	<% }%>
    <!-- header -->
    
    <header class="header" id="header">
        <div class="inner-wrap">
            <div class="gnb">
                <nav class="nav">
                    <h1><a href="#none"><img src="resources/images/icon/daangn_logo.png" alt="daangn logo"><span class="sr-only">당근마켓<span></a></h1>
                    <ul class="menu">
                        <li><a href="<%=contextPath %>/boardlist.bo?cpage=1">중고거래</a></li>
                    </ul>
                    <div class="search">
                        <form action="">
                            <input type="text" class="search" placeholder="물품이나 동네를 검색해보세요">
                        </form>
                        <button>검색하기</button>
                    </div>
                    <% if(loginUser == null) { %>
                    <div class="utill">
                        <div><a href="<%=contextPath%>/loginForm.me">로그인</a></div>
                        <div><a href="<%=contextPath%>/enrollForm.me">회원가입</a></div>
                    </div>
                    <% } else { %>
		            <!-- case2. 로그인 후 -->
		            <div>
		            	<b><%=loginUser.getUserName()%></b>님</a>
		            	<a href="<%=contextPath %>/myPage.me">마이페이지</a>
		            </div>
		            <div class="utill">
	                    <div><a href="<%=contextPath %>/logout.me">로그아웃</a></div>
	                    <div><a href="<%=contextPath %>/enrollForm.bo">글 작성</a></div>       
		            </div>
		            <% } %>
		            </nav>
		     	 </div>
            </div>
        </div>
    </header>
    <!--// header  -->   
</body>
</html>