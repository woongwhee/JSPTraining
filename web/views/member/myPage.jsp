<%--
  Created by IntelliJ IDEA.
  User: jinunghwi
  Date: 2022/10/07
  Time: 7:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/menubar.jsp"%>
<%
    String userId = loginUser.getUserId();
    String userName = loginUser.getUserName();
    String email = loginUser.getEmail()==null?"":loginUser.getEmail();
    String phone = loginUser.getPhone()==null?"": loginUser.getPhone();
    String address = loginUser.getAddress()==null?"":loginUser.getAddress();
    String interest=loginUser.getInterest()==null?"":loginUser.getInterest();
%>
<html>
<head>
    <title>Title</title>
    <style>
        .outer{
            background-color: yellowgreen;
            color:antiquewhite;
            width: 1000px;
            margin: auto;
            margin-top: 50px;
        }
        #mypage-form{
            margin:auto;
        }
    </style>
</head>
<body>

    <div class="outer">
        <br>
        <h2 style="text-align: center">마이페이지</h2>
        <form id="mypage-form" action="<%=contextPath%>/update.me" method="post">
            <table>
                <tr>
                    <td>* 아이디</td>
                    <td><input type="text" name="userId" value="<%=userId%>" readonly></td>
                    <td></td>
                </tr>
                <tr>
                    <td>&nbsp;이름</td>
                    <td><input type="text" name="userName" value="<%=userName%>" maxlength="6"></td>
                    <td></td>
                <tr>
                    <td>&nbsp;전화번호</td>
                    <td><input type="text" name="phone" value="<%=phone%>" placeholder="- 포함해서 입력"></td>
                    <td></td>
                </tr>
                <tr>
                    <td>&nbsp;이메일</td>
                    <td><input type="email" name="email" value="<%=email%>"></td>
                    <td></td>
                </tr>
                <tr>
                    <td>&nbsp;주소</td>
                    <td><input type="text" name="address" value="<%=address%>"></td>
                    <td></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;관심분야</td>
                    <td colspan="2">
                        <!-- 운동,등산-->
                        <input type="checkbox" name="interest" id="sports" value="운동"><label for="sports">운동</label>
                        <input type="checkbox" name="interest" id="hiking" value="등산"><label for="hiking">등산</label>
                        <input type="checkbox" name="interest" id="fishing" value="낚시"><label for="fishing">낚시</label>
                        <input type="checkbox" name="interest" id="cooking" value="요리"><label for="cooking">요리</label>
                        <input type="checkbox" name="interest" id="game" value="게임"><label for="game">게임</label>
                        <input type="checkbox" name="interest" id="movie" value="영화"><label for="movie">영화</label>
                    </td>
                </tr>
            </table>
            <script>
                $(function() {
                    let interests="<%=interest%>";
                    $("input[type=checkbox]").each(function () {
                        if(interests.search($(this).val())!=-1){
                            $(this).attr("checked",true);
                        }
                    })
                })

            </script>
            <div align="center">
                <button type="submit" class="btn btn-secondary btn-sm">정보변경</button>
                <button type="button" class="btn btn-warning btn-sm" data-toggle="modal" data-target="#updatePwdForm">
                    비밀번호 변경</button>
                <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#deleteForm">회원탈퇴
                </button >
            </div>

        </form>
    </div>
    <div class="modal" id="updatePwdForm" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">비밀번호 변경</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="<%=contextPath%>/updatePwd.me" method="post">
                    <input type="hidden" name="userId" value="<%=userId%>">
                    <table>
                        <tr>
                            <td>현재비밀번호</td>
                            <td><input type="password" name="userPwd" required></td>
                        </tr>
                        <tr>
                            <td>변경할 비밀번호</td>
                            <td><input type="password" name="updatePwd" required></td>
                        </tr>
                        <tr>
                            <td>변경할 비밀번호</td>
                            <td><input type="password" name="checkPwd" required></td>
                        </tr>
                    </table>
                    <br>
                    <button type="submit" class="btn btn-secondary btn-sm" onclick="return validatePwd()">비밀번호 변경</button>
                    </form>
                    <script>
                        function validatePwd(){
                            if($("input[name=updatePwd]").val()!=$("input[name=checkPwd]").val()){
                                alert("비밀번호가 일치하지 않네요");
                                return false;
                            }

                        }
                    </script>
                </div>
                </div>
                <div class="modal-footer">
            </div>
        </div>
        </div>
        <div class="modal" id="deleteForm" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">회원탈퇴</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body" align="center">
                        <h4>정말로 회원탈퇴하시겠습니까?</h4>
                        <form action="<%=contextPath%>/deleteMember.me" method="post">
                            <tabel>
                                <th>
                                    <td>현재비밀번호</td>
                                    <td><input type="password" name="userPwd" required></td>
                                </th>
                            </tabel>
                            <button type="submit" class="btn btn-danger btn-sm" >회원탈퇴</button>
                        </form>
                    </div>
                    <div class="modal-footer">
                    </div>
                </div>
            </div>
        </div>
</body>
</html>
