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
    #done-button{
        margin-left: 400px;
        background-color: #ff6f0f;
        border: none;
        border-radius: 3px 3px;
        height: 30px;
        width: 100px;
        font-weight: bolder;

    }
</style>
</head>
<body>
	<%@ include file="../common/header.jsp"%>
    <main class="container">
        <!-- contents -->
        <div class="contents" id="contents">
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
                                <div class="name"><%=b.getBoardWriter()%></div>
                                <div class="adress">서울특별시 강남구 역삼동</div>
                            </div>                     
                        </div>
                    </a>
                </section>
                <section class="prd-detail">
                    <h1 class="prd-title"><%=b.getBoardTitle()%></h1>
                    <button id="done-button" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#openModalBtn">
					    거래완료
					</button>
                    <p class="category">
                        <span><%=b.getCreateDate()%></span>
                    </p>
                    <p class="prd-price">
                        <%=b.getAmount()%>
                    </p>
                    <p class="detail"><%=b.getBoardContent()%>
                    </p>
                    <span class="counts">조회수<span>3</span></span>
                </section>
                <section class="comment">
                    <h2>댓글<span>(2)</span></h2>
                   		<!-- 댓글이 그려지는곳 -->
	                    <div id="reply-area" class="profile-detail-info comment">
	                        <ul>
	                        	 
	                        </ul>
	                    </div>
	                  
                        <fieldset class="form comment-form">
							<legend>댓글 쓰기</legend>
							<%if (loginUser != null) { %>
								<div class="form-control">
									<textarea name="reply-content" id="reply-content" rows="3" title="댓글 입력" placeholder="댓글을 입력해주세요." onkeyup="(event.keyCode === 13 ? insertReply() : false)"></textarea>
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
                    				console.log(res)
                    				if(res.length === 0){
                    					document.querySelector("#reply-area ul").innerHTML = "<p style='padding: 50px 0; text-align: center;'>등록된 댓글이 없습니다.</p>";
                    				} else {
                    					let str = "";
                        				for (let reply of res) {
                        					str += "<li>"
    	                    						+ "<div class='flex'>"
    	                    							+"<div class='profile-image'>"
    	                    								+ "<img src='./resources/images/icon/profile_sample_img.png' alt=''>"
    	                    							+ "</div>"
    		                    						+ "<div class='profile-left'>"
    		                    							+ "<div class='name'>" + reply.replyWriter + "</div>"
    		                    							+ "<p class='category'><span>" + reply.createDate + "</span></p>"
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
                                        modalStart();
                    				}
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
                                	console.log(res)
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
    <script>
    	function Done(){
    		const done = document.getElementsByClassName("done-reply").value;
            console.log(done);
    	}
    </script>
    
    
<!-- The Modal -->
<div class="modal" id="openModalBtn">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">회원 선택</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

        <ul id="authorList">
            <!-- 댓글 작성자 목록을 여기에 추가 -->
        </ul>
        <button id="modalSubmit">선택</button>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>

<script>
	function modalStart() {
	    const authors = document.getElementsByClassName("done-reply");
	    const uniqueAuthors = new Set();
	    
	    
	
	    for (let i = 0; i < authors.length; i++) {
	    
	        uniqueAuthors.add(authors[i].value);
	    	
	    }
	
	    const authorList = document.getElementById("authorList");
	
	    // 중복을 제거한 고유한 작성자 목록을 순회하며 리스트에 추가
	    uniqueAuthors.forEach(author => {
	        const listItem = document.createElement("li");
	        listItem.textContent = author;		//author : 중복체크 된 댓글작성자
	        authorList.appendChild(listItem);
	        
	        listItem.addEventListener("click", function() {
	            const selectedUser = this.textContent;
	            window.location.href = "trade.bo?bno=${b.boardNo}&rwriter=selectedUser";
	            
	        });	            
        
	    });
	}
</script>



</body>
</html>