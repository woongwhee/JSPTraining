<%--
  Created by IntelliJ IDEA.
  User: jinunghwi
  Date: 2022/10/07
  Time: 4:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>B class 회원가입</title>
  <style>
    .outer{
      background-color: yellowgreen;
      color:antiquewhite;
      width: 1000px;
      margin: auto;
      margin-top: 50px;
    }

    #enroll-form table{
      margin: auto;
      width: 60%;
    }
    #enroll-form input{
      margin: 4px;
    }
  </style>
</head>
<body>
  <%@ include file="../common/menubar.jsp"%>
  <div class="outer">
    <br>
    <h2 style="text-align: center" >회원가입</h2>
    <form action="<%=contextPath%>/insert.me" id="enroll-form" method="post">
        <table>
          <tr>
            <td>* 아이디 </td>
            <td><input type="text" name="userId" maxlength="12" required="required">   </td>
            <td><button type="button" onclick="idCheck()">중복확인</button></td>
          </tr>
          <tr>
            <td>* 비밀번호확인</td>
            <td><input type="password" name="userPwd" maxlength="12" required></td>
            <td></td>
          </tr>
          <tr>
            <td>* 비밀번호 확인</td>
            <td><input type="password" name="userPwd2" maxlength="12" required></td>
            <td></td>
          </tr>
          <tr>
            <td>* 이름</td>
            <td><input type="text" name="userName" maxlength="6"></td>
            <td></td>
          </tr>
          <tr>
            <td>&nbsp;&nbsp;전화번호</td>
            <td><input type="text" name="phone" placeholder="-포함해서 입력"></td>
            <td></td>
          </tr>
          <tr>
            <td>&nbsp;&nbsp;이메일</td>
            <td><input type="email" name="email" ></td>
            <td></td>
          </tr>
          <tr>
            <td>&nbsp;&nbsp;주소</td>
            <td><input type="text" name="address"></td>
            <td></td>
          </tr>
          <tr>
            <td>&nbsp;&nbsp;관심분야</td>
            <td colspan="2">
              <input type="checkbox" name="interest" id="sports" value="운동"><label for="sports">운동</label>
              <input type="checkbox" name="interest" id="hiking" value="등산"><label for="hiking">등산</label>
              <input type="checkbox" name="interest" id="fishing" value="낚시"><label for="fishing">낚시</label>
              <input type="checkbox" name="interest" id="cooking" value="요리"><label for="cooking">요리</label>
              <input type="checkbox" name="interest" id="game" value="게임"><label for="game">게임</label> 
              <input type="checkbox" name="interest" id="movie" value="영화"><label for="movie">영화</label>
            </td>
          </tr>
        </table>
        <div align="center">
          <button type="submit" disabled>회원가입</button>
          <button type="reset">초기화</button>
        </div>
        <script>
          function idCheck(){
            let $userId=$("#enroll-form input[name='userId']");//
            $("#enroll-form :submit").removeAttr("disabled");
            $userId.attr("readonly",true);
          }
        </script>
    </form>
  </div>

</body>
</html>