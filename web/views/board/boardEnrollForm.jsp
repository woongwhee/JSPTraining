<%@ page import="java.util.ArrayList" %>
<%@ page import="com.kh.board.model.vo.Category" %><%--
  Created by IntelliJ IDEA.
  User: jinunghwi
  Date: 2022/10/17
  Time: 3:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<Category> list=(ArrayList<Category>) request.getAttribute("list");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%@include file="../common/menubar.jsp"%>
    <div class="outer">
        <form action="insert.bo" method="post" enctype="multipart/form-data">
                <input type="text" name="userNo" value="<%=loginUser.getUserNo()%>" hidden>
            <label  for="category">카테고리</label>
                <select name="category" id="category" class="form-control" style="width: 100px; display: inline-block">
                    <%--                        <option value="10">공통</option>--%>
                    <%--                        <option value="20">운동</option>--%>
                    <%--                        <option value="30">등산</option>--%>
                    <%--                        <option value="40">게임</option>--%>
                    <%--                        <option value="50">낚시</option>--%>
                    <%--                        <option value="60">요리</option>--%>
                    <%--                        <option value="70">기타</option>--%>
                    <%for (Category c:list) { %>
                    <option value="<%=c.getCategoryNo()%>"><%=c.getCategoryName()%></option>
                    <% } %>
                </select>
                <input type="text" name="title" class="form-control mt-4 mb-2"
                               placeholder="제목을 입력해주세요." value="" required>
                    <div class="form-group">
                      <textarea name="content" class="form-control" rows="10"
                            placeholder="내용을 입력해주세요"  required
                      ></textarea>
                        <input type="file" name="attachment" accept=".gif, .jpg, .png" onchange="checkSize(this)">
                    </div>

            <button type="submit" class="btn btn-secondary mb-3">작성하기</button>
            <button type="button" class="btn btn-secondary mb-3" onclick="history.back()">뒤로가기</button>
        </form>
    </div>
    <script>
        function checkSize(input) {
            if (input.files && input.files[0].size > (20 * 1024 * 1024)) {
                alert("파일 사이즈가 20mb 를 넘습니다.");
                input.value = null;
            }
        }
    </script>
</body>
</html>
