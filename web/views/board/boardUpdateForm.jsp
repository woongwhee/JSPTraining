
<%@ page import="com.kh.notice.model.vo.Notice" %>
<%@ page import="java.sql.Date" %>
<%@ page import="com.kh.board.model.vo.Board" %>
<%@ page import="com.kh.board.model.vo.Attachment" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.kh.board.model.vo.Category" %>
<%--
  Created by IntelliJ IDEA.
  User: jinunghwi
  Date: 2022/10/13
  Time: 4:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<Category> list=(ArrayList<Category>)request.getAttribute("list");
    Board b= (Board)request.getAttribute("board");
    Attachment at=(Attachment)request.getAttribute("attachment");

%>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>게시글 수정</title>
    </head>
    <body>
        <nav>
            <%@include file="../common/menubar.jsp"%>
        </nav>
        <main class="outer" style="border-radius:3px;padding: 5px">
            <form action="<%=contextPath%>/update.bo" method="post" enctype="multipart/form-data">
                <input type="hidden" name="boardNo" value="<%=b.getBoardNo()%>">
                <input type="hidden" name="userNo" value="<%=loginUser.getUserNo()%>">
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
                    <option value="<%=c.getCategoryNo()%>"
<%--                        <%if(c.getCategoryName().equals(b.getCategory())){%>selected="seleted"<%}%>--%>
                    ><%=c.getCategoryName()%></option>
                    <% } %>
                </select>
                <script>
                    $(function (){
                        let selectedName='<%=b.getCategory()%>'; //쌍따옴표를 명시적으로 표시해줘야된다.
                        $('#category').children().each(function (){
                            //현재 반복진행중인 option text값과 db에서 가저온 카테고리 name값이 일치하는 경우 선택되도록
                            if($(this).text()==selectedName){
                                $(this).attr('selected',true);
                            }
                        })
                    })
                </script>
                <input type="text" name="title" class="form-control mt-4 mb-2"
                       placeholder="제목을 입력해주세요." value="<%=b.getBoardTitle()%>" required
                >
                <div class="form-group">
                <textarea name="content" class="form-control" rows="10" placeholder="내용을 입력해주세요"  required><%=b.getBoardContent()%></textarea>
                </div>
                <div class="from-group">
                    <p>첨부파일</p>
                    <%if(at!=null){%>
                    <div id="originFile" name="originFile"><%= at.getOriginName()%>
                        <input type="hidden" value="none" id="deleteFile" name="deleteFile">
                        <button type="button" id="deletebtn" onclick="deleteFiles()"
                                class="btn btn-danger btn-sm" style="border-radius:25px">X</button>
                    </div>
                    <input type="hidden" name="originFileNo" value="<%=at.getFileNo()%>">
                    <input type="hidden" name="originFileName" value="<%=at.getOriginName()%>">
                    <input type="file" id="upfile" name="upfile" accept=".gif, .jpg, .png" onchange="checkSize(this)" style="display: none">
                    <%}else{%>
                    <input type="hidden" id="deleteFile" value="none">
                    <input type="file" name="upfile" accept=".gif, .jpg, .png" onchange="checkSize(this)">
                    <%}%>
                </input>
                <button type="submit" class="btn btn-secondary mb-3">수정하기</button>
                <button type="button" class="btn btn-secondary mb-3" onclick="history.back()">뒤로가기</button>
            </form>

            <script >
                function deleteFiles(){
                    let file=document.getElementById('originFile');
                    let deletefile=document.getElementById('deleteFile');
                    // $('#originFile').css('display')
                    let btn=document.getElementById("deletebtn");
                    let upfile=document.getElementById("upfile");
                    file.style.display="none";
                    btn.style.display="none"
                    deletefile.value='delete';
                    upfile.style.display="block"
                }
                function checkSize(input) {
                    if (input.files && input.files[0].size > (20 * 1024 * 1024)) {
                        alert("파일 사이즈가 20mb 를 넘습니다.");
                        input.value = null;
                    }
                }
            </script>
        </main>
    </body>
</html>