<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.Usedtrade{
		height: 315px;
    	padding: 0 16px 0 16px;
		background-color: #FFF1AA;
	}
	.Usedtrade .content{
		padding-top: 50px;
		max-width: 768px;
		margin: 0 auto;
		position: relative;
		height: 100%;
		box-sizing: border-box;
	}
	.Usedtrade .content h1{
		font-weight: 700;
		line-height: 1.5;
		font-size: 34px;
		letter-spacing: -0.32px;
		text-align: right;
	}
	.Usedtrade .content span{
		line-height: 1.32;
		font-size: 18px;
		letter-spacing: -0.18px;
		margin-top: 16px;
		display: block;
		text-align: right;
	}
</style>
</head>
<body>
	<%@ include file="views/common/header.jsp" %>
	<%-- <%@ include file="views/board/boardEnrollForm.jsp" %> --%>
<%--  	<%@ include file="views/board/detailPage.jsp" %> --%>
	<%--<%@ include file="views/member/memberenrollForm.jsp" %>--%>
	<%--<%@ include file="views/board/boardByList.jsp" %>
	<%--<%@ include file="views/member/myPage.jsp" %> --%>
	<%--<%@ include file="views/member/memberLogin.jsp" %>--%>
	<section class="Usedtrade">
		<div class="content">
			<h1 class="title">믿을만한<br>이웃 간 중고거래</h1>
			<span class="detail">이곳에서 거래하며<br>즐거운 시간 보내세요!</span>
		</div>
	</section>

</body>
</html>