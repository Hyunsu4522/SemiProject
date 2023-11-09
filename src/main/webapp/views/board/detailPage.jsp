<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.semi.board.model.vo.Board, com.semi.board.model.vo.Reply, java.util.ArrayList, com.semi.common.model.vo.Attachment"%>
    
<%
	Board b = (Board)request.getAttribute("b");
	ArrayList<Reply> list = (ArrayList<Reply>)request.getAttribute("list");
	Attachment at = (Attachment)request.getAttribute("at");
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세페이지</title>
<!-- 231105 박수현 jquery 추가 -->
<!-- jquery 3.7.1 -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
    integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<!--// 231105 박수현 jquery 추가 -->
<!-- Swiper CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
<!-- Swiper JS -->
<script src="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.js"></script>
<!-- 23110_임동건 추가 -->
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
<style>
    .contents-wrap {
        padding-top: 24px;
    }
    .swiper-images {
        position: relative;
        width: 729px;
        margin: 0 auto;
    }
    .swiper {
      width: 100%;
      height: 100%;
    }

    .swiper-slide {
      text-align: center;
      font-size: 18px;
      background: #fff;
      display: flex;
      justify-content: center;
      align-items: center;
      position: relative;
        width: 677px;
        margin: 0 auto;
        height: 500px;
        border-radius: 8px;
        overflow: hidden;
    }

    .swiper-slide img {
      width: 668px;
      display: block;
      height: 100%;
      object-fit: cover;
      border-radius: 8px;
    }

    .swiper {
      margin-left: auto;
      margin-right: auto;
    }
    .swiper-button-next {
        justify-content: flex-end;
    }
    .swiper-button-prev{
        justify-content: flex-start;
    }
    .swiper-button-next:after, .swiper-button-prev:after{
        font-size: 21px;
        color: #000;
    }
    .swiper-pagination-bullet-active {
        background-color: #fff;
        opacity: 0.8;
    }
    .swiper-horizontal>.swiper-pagination-bullets, .swiper-pagination-bullets.swiper-pagination-horizontal{
        position: absolute;
        bottom: 0px;
        left: 31px;
        right: 26px;
        width: 668px;
        padding-top: 16px;
        padding-bottom: 16px;
        border-radius: 8px;
        list-style: none;
        text-align: center;
        background-image: linear-gradient(to top, rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0));
        background-blend-mode: multiply;
    }
    .swiper-pagination-bullet{
        background: #fff;
    }

    .profile{
        width: 677px;
        margin: 0 auto;
    }
    .profile-detail-info {
        text-decoration: none;
        display: block;
        margin-top: 25px;
        padding-bottom: 23px;
        position: relative;
        border-bottom: 1px solid #e9ecef;
        display: flex;
        gap: 8px;
        align-items: center;
        justify-content: start;
    }
    .profile-detail-info .profile-image img {
        width: 40px;
        height: 40px;
        -o-object-fit: cover;
        object-fit: cover;
        border-radius: 50%;
        -webkit-border-radius: 50%;
        -moz-border-radius: 50%;
    }

    .profile-detail-info .profile-left .name{
        font-size: 15px;
        font-weight: 600;
        line-height: 1.5;
        letter-spacing: -0.6px;
        color: #212529;
    }
    .profile-detail-info .profile-left .adress{
        font-size: 13px;
        line-height: 1.46;
        letter-spacing: -0.6px;
        color: #212529;
    }
    .prd-detail{
        padding: 32px 0;
        width: 677px;
        margin: 0 auto;
        border-bottom: 1px solid #e9ecef;
    }
    .prd-detail .prd-title{
        margin-top: 0;
        font-size: 20px;
        font-weight: 600;
        line-height: 1.5;
        letter-spacing: -0.6px;
    }
    .category{
        margin-top: 4px;
        font-size: 13px;
        line-height: 1.46;
        letter-spacing: -0.6px;
        color: #868e96;
    }
    .comment-contents {
		padding: 8px 24px 24px 0px;
		display: flex;
	    justify-content: space-between;
	    align-items: center;
	}
	.comment-contents .btns {
		display: flex;
    	column-gap: 12px;
    	color: #666;
    	font-weight: 600;
	}
    .prd-detail .prd-price{
        margin-top: 4px;
        font-size: 18px;
        font-weight: bold;
        line-height: 1.76;
        letter-spacing: -0.6px;
    }
    .detail {
        font-size: 17px;
        line-height: 1.6;
        letter-spacing: -0.6px;
        margin: 16px 0;
        word-break: break-all;
    }
    .prd-detail .counts {
        font-size: 13px;
        line-height: 1.46;
        letter-spacing: -0.6px;
        color: #868e96;
    }



    section.comment{
        width: 677px;
        margin: 0 auto;
    }
    section.comment h2{
        padding-top: 25px;
    }
    .profile-detail-info.comment {
        display: block;
    }
    .profile-detail-info.comment  .flex{
        display: flex;
        gap: 8px;
        align-items: center;
        justify-content: start;
    }
    .detail.comment{
        margin-left: 48px;
    }
    .recomment{
        margin-left: 48px;
    }
    
    .comment-form{
    	padding: 24px;
   		border-top: 1px solid #dedede;
    }
    .form-control{
    	position: relative;
	    font-size: 14px;
	    border: 1px solid #dedede;
    }
    .form-control>textarea{
   	    display: block;
	    height: 103px;
	    padding: 14px;
	    border: 0px;
	    background-color: rgb(255, 255, 255);
	    line-height: 1.5;
	    color: rgb(51, 51, 51);
	    font-weight: 600;
	    width: 623px;
	    resize: none;
	    outline: none;
    }
    .btns-right{
    	justify-content: right;
	    display: flex;
	    margin-top: 16px;
	    align-items: center;
    }
    .comment-form button + button:before, .comment-form .form-check + button:before {
	    content: '';
	    display: inline-block;
	    width: 1px;
	    height: 16px;
	    margin: 0 16px;
	    background-color: #dedede;
	    vertical-align: middle;
	}
	em{
		color:#ff6f0f;
		font-weight: 600;
	}
</style>
</head>
<body style=" width: 100%;
    min-width: 1200px;
    min-height: 100%;">
	<%@ include file="/views/common/header.jsp" %>
    <main class="container">
        <!-- contents -->
        <div class="contents" id="contents">
        <input type="hidden" name="bno" value="<%=b.getBoardNo()%>" >
            <div class="contents-wrap">
                <h1 class="sr-only">루이비통 카드지갑</h1>
                <section class="swiper-images">
                    <!-- Swiper -->
                    <div class="swiper mySwiper">
                        <div class="swiper-wrapper">
                            <div class="swiper-slide"><a href="#none"><img src="./resources/images/img/prd_sample_img.jpg" alt="루이비통 카드지갑의 여성잡화 구월동 1"></a></div>
                            <div class="swiper-slide"><a href="#none"><img src="./resources/images/img/prd_sample_img.jpg" alt="루이비통 카드지갑의 여성잡화 구월동 1"></a></div>
                            <div class="swiper-slide"><a href="#none"><img src="./resources/images/img/prd_sample_img.jpg" alt="루이비통 카드지갑의 여성잡화 구월동 1"></a></div>
                        </div>
                        <div class="swiper-button-next"></div>
                        <div class="swiper-button-prev"></div>
                        <div class="swiper-pagination"></div>
                    </div>
                </section>
                 
                <section class="profile">
                    <a href="#none">
                        <h3 class="sr-only">프로필</h3>
                        <div class="profile-detail-info">
                            <div class="profile-image">
                                <img src="./resources/images/icon/profile_sample_img.png" alt="" >
                            </div>
                            <div class="profile-left">
                                <div class="name">닉네임</div>
                                <div class="adress">서울특별시 강남구 역삼동</div>
                            </div>                     
                        </div>
                    </a>
                </section>
                <section class="prd-detail">
                    <h1 class="prd-title">루이비통 카드지갑</h1>
                    <p class="category">
                        여성잡화 ∙<span> 4일전</span>
                    </p>
                    <p class="prd-price">
                        18,000원
                    </p>
                    <p class="detail">공홈에서 구매하고 인천롯백에서 픽업한 정품입니다!
                        <br>사용감 많아요!!!
                        <br>사진 꼭 확인하시고 꼭 구매하실 분만 연락주세요!
                    </p>
                    <span class="counts">채팅<span>26</span></span>
                </section>
                <section class="comment">
                    <h2>댓글<span>(2)</span></h2>
                    <%if(list != null){ %>
                    	<p style="padding: 50px 0; text-align: center;">등록된 댓글이 없습니다.</p>
                    <%} else {%>
                    <div id="reply-area" class="profile-detail-info comment">
                        <ul>
                            <!--<li>
                                <div class="flex">
                                    <div class="profile-image">
                                        <img src="./resources/images/icon/profile_sample_img.png" alt="">
                                    </div>
                                    <div class="profile-left">
                                        <div class="name">닉네임</div>
                                        <p class="category">
                                            여성잡화 ∙<span> 4일전 </span>
                                        </p>
                                    </div>
                                </div>
                                <div class="comment-contents">
                                	<p class="detail comment">
	                                    댓글입니다만...
	                                </p>
		                                <div class="btns">
											<button type="button">수정</button>
											<button type="button">삭제</button>
										</div>
                                </div>
                                <ul class="recomment">
                                    <li>
                                        <div class="flex">
                                            <div class="profile-image">
                                                <img src="./resources/images/icon/profile_sample_img.png" alt="">
                                            </div>
                                            <div class="profile-left">
                                                <div class="name">닉네임</div>
                                                <p class="category">
                                                    여성잡화 ∙<span> 3일전 </span>
                                                </p>
                                            </div>
                                        </div>
                                        <div class="comment-contents">
		                                	<p class="detail comment">
			                                    대댓글입니다만...
			                                </p>
				                                <div class="btns">
													<button type="button">수정</button>
													<button type="button">삭제</button>
												</div>
		                                </div>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                      	<ul>
                            <li>
                                <div class="flex">
                                    <div class="profile-image">
                                        <img src="./resources/images/icon/profile_sample_img.png" alt="">
                                    </div>
                                    <div class="profile-left">
                                        <div class="name">닉네임</div>
                                        <p class="category">
                                            여성잡화 ∙<span> 2일전 </span>
                                        </p>
                                    </div>
                                </div>
                                <div class="comment-contents">
                                	<p class="detail comment">
	                                   답글달아죠...
	                                </p>
		                                <div class="btns">
											<button type="button">수정</button>
											<button type="button">삭제</button>
										</div>
                                </div>
                            </li>-->
                            <!--   + "<div class='btns'>"
        						+ "<button type='button'>수정</button>"
        						+ "<button type='button'>삭제</button>"
       						+ "</div>"-->
                        </ul>
                         <%} %>
                        <fieldset class="form comment-form">
							<legend>댓글 쓰기</legend>
							<%if (loginUser != null) { %>
								<div class="form-control">
									<textarea name="reply-content" id="reply-content" rows="3" title="댓글 입력" placeholder="댓글을 입력해주세요."></textarea>
								</div> 
								<div class="btns-right">
									<button type="button"><em>취소</em></button>
									<button type="button" onclick="insertReply()"><em>등록</em></button>
								</div>
							<%} else { %>
								<div class="form-control">
									<textarea name="reply-content" id="reply-content" rows="3" title="댓글 입력" placeholder="로그인 후 댓글작성 가능합니다." readonly></textarea>
								</div> 
							<%} %>
						</fieldset>
                    </div>
                    <script>
                    	window.onload = function(){
                    		//댓글 가져와서 그려주기
                    		selectReplyList();
                    	}
                    	function selectReplyList(){
                    		$.ajax({
                    			url: "rlist.bo",
                    			data: {
                    				bno: <%=b.getBoardNo()%>
                    			},
                    			success: function (res) {
                    				let str = "";
                    				for (let reply of res) {
                    					str += "<li>"
	                    						+ "<div class='flex'>"
	                    							+"<div class='profile-image'>"
	                    								+ "<img src='./resources/images/icon/profile_sample_img.png' alt=''>"
	                    							+ "</div>"
		                    						+ "<div class='profile-left'>"
		                    							+ "<div class='name'>" + reply.replyWriter + "</div>"
		                    							+ "<p class='category'>"
		                    								+ "여성잡화 ∙ <span>2일전</span>"
		                    							+ "</p>"
		                    						+ "</div>"
	                    						+ "</div>"
	                    						+ "<div class='comment-contents'>"
	                    							+ "<p class='detail comment'>"
	                    								+ reply.replyContent
	                    							+ "</p>"
	                    						+ "</div>"                   						
                    						+ "</li>";
                    				}
                    				document.querySelector("#reply-area ul").innerHTML = str;
                    			},
                    			error: function () {
                    				console.log("댓글목록 조회중 ajax 통신 실패");
                    			}
                    		})
                    	}
                    	
                    	 function insertReply(){
                             $.ajax({
                                url : "rinsert.bo",
                                data : {
                                    content: document.getElementById("reply-content").value,
                                    bno: <%=b.getBoardNo()%>
                                },
                                type:"post",
                                success:function(res){
                                    if (res > 0) {//댓글작성 성공
                                    	document.getElementById("reply-content").value = "";
                                    	selectReplyList();
                                    }
                                },
                                error:function(){
        							console.log("댓글 작성중 ajax통신 실패")
                                }
                            })
                        }
                    </script>
                </section>
            </div>
        </div>
    </main>
    <script>
        var swiper = new Swiper(".mySwiper", {
        slidesPerView: 1,
        spaceBetween: 30,
        loop: true,
        pagination: {
            el: ".swiper-pagination",
            clickable: true,
        },
        navigation: {
            nextEl: ".swiper-button-next",
            prevEl: ".swiper-button-prev",
        },
        });
    </script>
</body>
</html>