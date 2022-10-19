<%@ page import="com.kh.notice.model.vo.Notice" %>
<%@ page import="java.sql.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: jinunghwi
  Date: 2022/10/13
  Time: 4:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Notice n= (Notice)request.getAttribute("n");
    int noticeNo=n.getNoticeNo();
    String title=n.getNoticeTitle();
    String content=n.getNoticeContent();
    String writer=n.getNoticeWriter();
    Date createDate = n.getCreateDate();

%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title><%=title%>-Bclass 공지사항</title>
</head>
<body>
    <%@include file="../common/menubar.jsp"%>>
    <div class="outer" style=" background-color:beige;height: 500px">
        <br><h2 style="text-align: center">공지사항 상세보기</h2><br>
        <table id="detail-area" align="center" border="1">
            <thead>
            <tr>
                <td width="70">제목</td>
                <td width="350" colspan="3"><%=title%></td>
            </tr>
            <tr>
                <th>작성자</th>
                <td><%=writer%></td>
                <th>작성일</th>
                <td><%=createDate%></td>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <th>내용</th>
                    <td colspan="3">
                        <pre style=""><%=content%></pre>
                    </td>
                </tr>
            </tbody>
        </table>
        <br><br>
        <div align="center">
            <a href="<%=contextPath%>/list.no" class="btn btn-secondary btn-sm">
                목록으로
            </a>
            <%if(loginUser!=null&&loginUser.getUserId().equals(writer)){%>
            <%--로그인한사용자가 해당글을 작성한 작성자일경우에만 보여진다--%>
            <a href="<%=contextPath%>/updateForm.bo?nno=<%=noticeNo%>" class="btn btn-info btn-sm" >수정하기</a>
            <a href="<%=contextPath%>/delete.bo?nno=<%=noticeNo%>"class="btn btn-warning btn-sm" >삭제하기</a>
            <%}%>

        </div>
    </div>
</body>
</html>