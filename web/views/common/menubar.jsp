<%--
  Created by IntelliJ IDEA.
  User: jinunghwi
  Date: 2022/10/06
  Time: 5:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="com.kh.member.model.vo.Member" pageEncoding="UTF-8" %>
<%
    String contextPath=request.getContextPath();
    Member loginUser=(Member)session.getAttribute("loginUser");
    String alertMsg=(String)session.getAttribute("alertMsg");
    //요청전: null
    //dycjdgn : alert로 띄워줄 메세지 문구;

    //로그인하기전or 실패시 : null
    //로그인 성공후 : 로그인한 회원의 정보가 담긴 member객체

%>
<!DOCTYPE html>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <title>Welcome B Class</title>
    <style>

        #login-form,#user-info{
            float: right;
        }
        #user-info a{
            text-decoration: none;
            color:black;
            font-size:12px;
        }
        .nav-area{
            background-color: yellowgreen;
        }
        .menu{
            display:table-cell;
            width: 150px;
            height: 50px;
        /*    인라인요소처럼 배치해줌*/
        }
        h1>a{
            text-decoration: none;
            font-size:3em;
            color: yellowgreen;
        }
        .menu a{
            text-decoration: none;
            color:white;
            font-size:20px;
            display:block;
            font-weight: bold;
            width: 100%;
            height: 100%;
            line-height: 50px;
        }
        .menu a:hover{
            background-color: palegoldenrod;
        }
        .outer{
            background-color: yellowgreen;
            color:black;
            width: 1000px;
            margin: auto;
            margin-top: 50px;
        }
    </style>
</head>
<body>
    <script>
            let msg='<%=alertMsg%>';//성공적으로 로그인이 되었습니다.
            if(msg!='null'){
                 alert(msg);//페이지 로딩시 메세지
            }
            <%session.removeAttribute("alertMsg");%>

    </script>
    <h1 style="text-align: center"><a href="<%=contextPath%>">Wellcom B class</a></h1>
    <div id="loginArea">
      <% if(loginUser==null){%>
    <form id="login-form" action="<%=contextPath%>/login.me" method="get">
        <table>
            <tr>
                <th>아이디 : </th>
                <td><input type="text" name="userId"></td>

            </tr>
            <tr>
                <th>비밀번호</th>
                <td><input type="text" name="userPwd"></td>
            </tr>
            <tr>
                <th>
                    <button type="submit">로그인</button>
                    <button type="button" onclick="ennolPage()">회원가입</button>
                </th>
            </tr>
        </table>
    </form>
  </div>

    <script>
        function ennolPage(){
            //location.href = "/jsp/views/member/memberEnrollForm.jsp;//좋은방법이아님 디렉토리 구조가 노출된다.
            //웹어플리케이션의 디렉토리 구조가 url에 노출되면 보안에 취약하다.
            // 정적인 페이지라도 sevlet을거처가기.
            location.href="<%=contextPath%>/enrollForm.me";
        }
    </script>
    <%}else {%>
    <div id="user-info">
        <b><%=loginUser.getUserName()%></b>님 환영합니다.<br><br>
        <div style="text-align:center;">
            <a href="<%=contextPath%>/myPage.me">마이페이지</a>
            <a href="<%=contextPath%>/logout.me">로그아웃</a>

        </div>
    </div>
    <%}%>

    <br clear="both">
    <br>
    <div class="nav-area" style="text-align: center">
        <div class="menu"><a href="<%=contextPath%>">menu</a></div>
        <div class="menu"><a href="<%=contextPath%>/list.no">공지사항</a></div>
        <div class="menu"><a href="<%=contextPath%>/list.do?currentPage">일반게시판</a></div>
        <div class="menu"><a href="<%=contextPath%>/list.th">사진게시판</a></div>
    </div>

</body>
</html>
