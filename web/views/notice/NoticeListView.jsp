<%@ page import="java.util.ArrayList" %>
<%@ page import="com.kh.notice.model.vo.Notice" %>
<%--
  Created by IntelliJ IDEA.
  User: jinunghwi
  Date: 2022/10/12
  Time: 8:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%
    ArrayList<Notice>list=(ArrayList<Notice>)request.getAttribute("list");


%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <style>

        .list-area{
            border: 1px solid white;
            text-align: center;
        }
        .list-area>tbody>tr>hover{
            background-color: palegoldenrod;
            cursor: pointer;
        }
    </style>
</head>
    <%@include file="../common/menubar.jsp"%>
    <div class="outer">
        <br>
        <h2 style="text-align: center">공지사항</h2>
        <br>

        <%--공지사항은 관리자만 작성가능--%>
        <% if(loginUser!=null&&loginUser.getUserNo()==1){%>
            <div align="right" style="width:850px">
            <%--a태그를 버튼모양으로 만들수있네?(부트스트랩에서 제ㅐ공하는 btn클래스)--%>
                <a class="btn btn-secondary" href="<%=contextPath%>/enroll.no">글작성</a>
            </div>
        <% }%>
        <table class="table table-hover list-area">
            <thead>
                <tr>
                    <th>글번호</th>
                    <th>글제목</th>
                    <th>작성자</th>
                    <th>조회수</th>
                </tr>
            </thead>
            <tbody>
                <% if(list.isEmpty()){%>
                <tr>
                    <td colspan="5">존재하는 공지사항이 없습니다.</td>
                </tr>
                <% }else{
                    for(Notice N:list){%>
                <tr>
                    <td ><%=N.getNoticeNo()%></td>
                    <td><%=N.getNoticeTitle()%></td>
                    <td><%=N.getNoticeWriter()%></td>
                    <td><%=N.getCount()%></td>
                    <td><%=N.getCreateDate()%></td>
                </tr>
                <% }
                }%>
            </tbody>
            <tfood>

            </tfood>
        </table>
    </div>
    <script>
        $(function (){
            $(".list-area>tbody>tr").click(function (){
                let nno=$(this).children().eq(0).text();
                location.href='<%=contextPath%>/detail.no?nno='+nno;
            }
            )



        })

    </script>
<body>

</body>
</html>