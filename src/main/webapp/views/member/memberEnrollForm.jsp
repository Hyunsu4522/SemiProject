<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>



<style>
    .outer{
        background: white;
        color: black;
        width: 1000px;
        margin: auto;
        margin-top: 50px;
    }
	h2{
	    font-size: 28px;
    	font-weight: 700;
    }
    #mem-enroll-form table{margin: auto;}
    #mem-enroll-form input{
        margin: 10px;
        width: 384px;
    	height: 45px;
        border: 1px solid #ced4da;
        border-radius: 5px; 
        padding-left: 15px;
        
    } 

#joinMember{

    width: 384px; background: rgb(255, 111, 15);  

    border: 1px solid;
    border-radius: 5px; 
    color: white; height: 45px;
    

}

</style>

</head>
<body>
   <%@ include file="../common/header.jsp"%>

    <div class="outer">
        <br>
        <h2 align="center">회원가입</h2>

        <form action="<%=contextPath%>/insert.me" id="mem-enroll-form" >
            <table style="width:auto;">
                <tr>
                    <td>아이디 *  </td> 
                    <br>
                    <td><input type="text" name="userId" maxlength="12"  placeholder="아이디를 입력해주세요."required></td>
                    <!-- <td><button type="button" style="background-color: #FF8B3D;  border: 1px solid;
                        border-radius: 5px; 
                        color: white; height: 35px;">중복확인</button></td> -->
                </tr>
                <tr> 
                    <td>비밀번호*</td>
                    <td><input type="password" name="userPwd" maxlength="15" placeholder="비밀번호를 입력해주세요." required></td>
                    <td></td>
                </tr>
                <tr>
                    <td>비밀번호 확인*</td>
                    <td><input type="password" name="userPwdCheck" maxlength="15" placeholder="비밀번호를 한 번 더 입력해주세요." required></td>
                    <td></td>
                </tr>
                <tr>
                    <td>이름  *</td>
                    <td><input type="text" name="userName" maxlength="6" placeholder="이름을 입력해주세요." required></td>
                    <td></td>
                </tr>
                
                <tr>
                    <td> 주소</td>
                    <td><input type="text" name="address" placeholder="주소를 입력해주세요." ></td>
                    <td></td>
                </tr>
                
            </table>
            

            <br><br>

            <div align = "center"   >
                <button type="submit" id="joinMember" onclick="return checkPwd()">회원가입</button>
            	<button type = "reset">초기화</button>
            </div>

            <br><br>

        </form>


        <script>
            function checkPwd(){
                let pwdInpt = document.querySelector("#mem-enroll-form input[name=userPwd]");
                let pwdCheckInput = document.querySelector("#mem-enroll-form input[name=userPwdCheck]");
               
                if(pwdInpt.value !== pwdCheckInput.value){
                    alert("비밀번호가 일치하지않습니다.");
                    return false;
                }
            }
        </script>
    </div>
</body>
</html>