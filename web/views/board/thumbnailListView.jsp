<%@ page import="java.util.ArrayList" %>
<%@ page import="com.kh.board.model.vo.Board" %><%--
  Created by IntelliJ IDEA.
  User: jinunghwi
  Date: 2022/10/19
  Time: 3:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<Board> list=(ArrayList<Board>) request.getAttribute("list");
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>사진게시판</title>
    <style>
        .list-area{
            width: 760px;
            margin:auto;
        }
    </style>
</head>
<body>
    <%@include file="../common/menubar.jsp"%>
        <div class="outer" >
            
           <b> <h2 align="center">사진게시판</h2></b>
            <%if(loginUser!=null){%>
                <div align="right" style="width: 800px"><a href="<%=contextPath%>/enrollForm.th" class="btn btn-secondary btn-sm">글 작성</a></div>
            <br><br>
            <%}%>
            <div class="list-area" style="height: 1000px;">
        <%if(!list.isEmpty()){
            for(Board b:list){%>
                <div class="thumbnail" align="center">
                    <input type="hidden" value="<%=b.getBoardNo()%>">
                    <img src="<%=contextPath%><%=b.getTitleImg()%>" alt="" style="width: 200px;height: 150px">
                    <p>
                        No.<%=b.getBoardNo()%><%=b.getBoardTitle()%><br>
                                            조회수 : <%=b.getCount()%>
                    </p>
                </div>
        <%}
        }else{%>
                <h2 align="center" style="color: yellow">등록된 게시글이없습니다.</h2>
                <%}%>
        </div>
            <script>
                    $(function (){
                        $(".thumbnail").click(function (){
                            let index=$(this).children().eq(0).val();
                            location.href="<%=contextPath%>/detail.th?bno="+index;
                        })
                    })
            </script>
</body>
</html>