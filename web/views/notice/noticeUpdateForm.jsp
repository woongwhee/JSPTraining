<%@ page import="com.kh.notice.model.vo.Notice" %><%--
  Created by IntelliJ IDEA.
  User: jinunghwi
  Date: 2022/10/13
  Time: 5:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Notice n=(Notice)request.getAttribute("n");

%>
<html>
<head>
    <title>공지사항 작성</title>
</head>
<body>
<nav>
    <%@include file="../common/menubar.jsp"%>
</nav>
<main class="outer">
<form action="<%=contextPath%>/update.no" method="post">
    <input type="hidden" name="NoticeNo" value="<%=n.getNoticeNo()%>">
    <input type="text" name="title" class="form-control mt-4 mb-2"
           placeholder="제목을 입력해주세요." value="<%=n.getNoticeTitle()%>" required
    >
    <div class="form-group">
		<textarea name="content" class="form-control" rows="10"
                  placeholder="내용을 입력해주세요"  required
        ><%=n.getNoticeContent()%></textarea>
    </div>
    <button type="submit" class="btn btn-secondary mb-3">수정하기</button>
    <button type="button" class="btn btn-secondary mb-3" onclick="history.back()">뒤로가기</button>
</form>
</main>
</body>
</html>
