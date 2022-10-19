<%@ page import="java.util.ArrayList" %>
<%@ page import="com.kh.board.model.vo.Board" %>
<%@ page import="com.kh.board.model.vo.PageInfo" %><%--
  Created by IntelliJ IDEA.
  User: jinunghwi
  Date: 2022/10/14
  Time: 4:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<Board> list= (ArrayList<Board>) request.getAttribute("list");
    PageInfo pageInfo=(PageInfo) request.getAttribute("pageInfo");
    String ctg= pageInfo.getCategoryName()==null?"":"&ctg="+pageInfo.getCategoryName();
    String pageTitle= pageInfo.getCategoryName()==null?"일반게시판":pageInfo.getCategoryName()+"게시판";
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title><%=pageTitle%>-B class</title>
    <style>
        table{
            width: 1000px;
        }
        #page{
            margin:auto;
            width: 700px;
        }
        #page th{
            margin-left: 5px;
        }
        #page td{
            margin: auto;
            width: 60px;
            border: 1px black    solid;
            text-align: center;
            text-decoration: none;
        }
        #page td:hover{
            color:yellow;
        }

    </style>
</head>
<body>
    <header>
    <%@ include file="../common/menubar.jsp"%>

    </header>
    <main class="outer" >
        <br><h2 style="text-align: center"><%=pageTitle%></h2>
        <%if(loginUser!=null){%>
            <div align="right" style="width:850px">
                <a href="<%=contextPath%>/enrollForm.bo" class="btn btn-sm btn-secondary">글작성</a>
                <br><br>
            </div>
        <%}%>
        <table id="boardList"class="table table-striped">
            <thead>
                <tr>
                    <th width="70">글 번호</th>
                    <th width="70">카테고리</th>
                    <th width="300">글 제목</th>
                    <th width="100">작성자</th>
                    <th width="50">조회수</th>
                    <th width="100">작성일</th>
                </tr>
            </thead>
            <tbody>
                <%if(list.isEmpty()){%>
                    <tr>
                        <td colspan="6">조회된 리스트가 없습니다.</td>
                    </tr>
                <%}else{
                for(Board b:list){%>
                <tr>
                    <td><%=b.getBoardNo()%></td>
                    <td><%=b.getCategory()%></td>
                    <td><%=b.getBoardTitle()%></td>
                    <td><%=b.getBoardWriter()%></td>
                    <td><%=b.getCount()%></td>
                    <td><%=b.getCreateDate()%></td>
                </tr>
                <script>
                    $(function (){
                        $("#boardList>tbody>tr").click(function (){
                                let bno=$(this).children().eq(0).text();
                                location.href='<%=contextPath%>/detail.bo?bno='+bno
                                    <%if(pageInfo.getCategoryName()!=null){%>+'<%=ctg%>'<%}%>;
                            })
                    })

                </script>
                <% }%>

            </tbody>
            <tfoot>

            </tfoot>
        </table>

        <% if(pageInfo.getCurrentPage()<=pageInfo.getMaxPage()){%>

        <table id="page">
            <th>
                <td><a href="<%=contextPath%>/list.bo"><<</a></td>
                <td><a href="<%=contextPath%>/list.bo?currentPage=<%=pageInfo.getStartPage()-pageInfo.getPageLimit()+ctg%>"><</a></td>
            <%for(int i=pageInfo.getStartPage();i<pageInfo.getEndPage();i++){%>
                <td>
                    <a href="<%=contextPath%>/list.bo?currentPage=<%=i+ctg%>"
                            <%if(pageInfo.getCurrentPage()==i){%>
                    style="color:red;"
                            <% }%>
                    ><%=i%></a>
                </td>
            <%}%>

                <%if(pageInfo.getCurrentPage()!=pageInfo.getMaxPage()){%>
                    <td><a href="<%=contextPath%>/list.bo?currentPage=<%=pageInfo.getEndPage()+1+ctg%>">></a>
                    </td>
                <td><a href="<%=contextPath%>/list.bo?currentPage=<%=pageInfo.getListCount()-1+ctg%>">>></a></td><%}%>
            </th>
        </table>
        <%}
        }%>
    </main>



    <footer>
        
    </footer>


</body>
</html>