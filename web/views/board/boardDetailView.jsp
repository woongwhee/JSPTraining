<%@ page import="java.sql.Date" %>
<%@ page import="com.kh.board.model.vo.Attachment" %>
<%@ page import="com.kh.board.model.vo.Board" %>
<%--
  Created by IntelliJ IDEA.
  User: jinunghwi
  Date: 2022/10/13
  Time: 4:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Attachment at=(Attachment)request.getAttribute("attachment");
    Board b=(Board) request.getAttribute("board");
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title><%=b.getBoardTitle()%>-Bclass</title>
</head>
<body>
    <%@include file="../common/menubar.jsp" %>
    <div class="outer" style=" background-color:beige;height: 500px">
        <br><h2 style="text-align: center;">일반게시판 상세보기</h2><br>
        <table id="detail-area" align="center" border="1">
            <thead>
            <tr>
                <td width="70">카테고리</td>
                <td width="70" ><%=b.getCategory()%></td>
                <td width="70">제목</td>
                <td width="350" colspan="2"><%=b.getBoardTitle()%></td>
            </tr>
            <tr>
                <th>작성자</th>
                <td><%=b.getBoardWriter()%></td>
                <th>작성일</th>
                <td><%=b.getCreateDate()%></td>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <th>내용</th>
                    <td colspan="3">
                        <pre style=""><%=b.getBoardContent()%></pre>
                        <% if(at!=null){ %>
                            <img src="<%=contextPath%>/<%=at.getFilePath()%>/<%=at.getChangeName()%>">
                        <% }%>
                    </td>
                </tr>
                <tr>
                    <th>첨부파일</th>
                    <td colspan="3">
                        <%if(at==null){%>
                            첨부파일이없습니다.
                        <%}else{%>
                            <a href="<%=contextPath%>/<%=at.getFilePath()%>/<%=at.getChangeName()%>"
                            download="<%=at.getOriginName()%>">
                                <%=at.getOriginName()%></a>
                        <%}%>
                    </td>
                    <td></td>
                </tr>
            </tbody>
        </table>
        <br><br>
        <div align="center">
            <a href="/list.bo" class="btn btn-secondary btn-sm">
                목록으로
            </a>
            <%if(loginUser!=null&&loginUser.getUserId().equals(b.getBoardWriter())){%>
            <%--로그인한사용자가 해당글을 작성한 작성자일경우에만 보여진다--%>
            <a href="<%=contextPath%>/updateForm.bo?bno=<%=b.getBoardNo()%>" class="btn btn-secondary btn-sm" >수정하기</a>
            <a href="<%=contextPath%>/delete.dbo?bno=<%=b.getBoardNo()%>"class="btn btn-warring btn-sm" >삭제하기</a>
            <%}%>

        </div>
        <br><br>
        <div id="reply-area">
            <table border="1" align="center">
                <thead>

                </thead>
                <tbody>

                </tbody>
                <tfood>
                    <%if(loginUser!=null){ %>
                    <tr>
                        <th>댓글작성</th>
                        <td>
                            <textarea id="replyContent" cols="50" rows="3" style="resize:none;"></textarea>
                        </td>
                        <td>
                            <button onclick="">댓글등록</button></td>
                    </tr>
                    <%}else {%>
                    <tr>
                        <th>댓글작성</th>
                        <td>
                            <textarea cols="50" rows="3" style="resize: none;" readonly> 로그인 후 이용가능한 서비스 입니다.</textarea>
                        </td>
                        <td>
                            <button disabled>댓글등록</button>
                        </td>
                    </tr>
                    <%}%>
                </tfood>
            </table>
        </div>


    </div>
</body>
</html>