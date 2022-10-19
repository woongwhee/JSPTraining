<%--
  Created by IntelliJ IDEA.
  User: jinunghwi
  Date: 2022/10/19
  Time: 4:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>사진 게시글작성</title>
</head>

<body>
  <nav>
    <%@include file="../common/menubar.jsp"%>
  </nav>
  <main class="outer">
    <form action="<%=contextPath%>/insert.th" id= "enroll-form" method="post" enctype="multipart/form-data">
      <input type="hidden" name="userNo" value="<%=loginUser.getUserNo()%>">
      <input type="text" name="title" class="form-control mt-4 mb-2" placeholder="제목을 입력해주세요." required>
      <div class="form-group">
          <textarea name="content" class="form-control" rows="10"
                    placeholder="내용을 입력해주세요" required></textarea>
      </div>
      <h2>대표 이미지</h2><!--미리보기 -->

      <table id="fileTable" class="table" >
        <tr>
          <td colspan="3" rowspan="2">
            <img id="titleImg"width="200"height="150">
          </td>
        </tr>
        <tr><td>
          <td></tr>
        <tr>
          <td><img id="contentImg2"width="200"height="150"></td>
          <td><img id="contentImg3"width="200"height="150"></td>
          <td><img id="contentImg4"width="200"height="150"></td>
        </tr>
        <tr>
        <style>
          .hiddenFileInput{
            display: none;
          }
        </style>
          <td>
            <input type="file"id="file1" name="file1"
                   class="form-control mt-4 mb-1" accept=".jpg,.jpeg,.gif,.png" onchange="checkSize(this,1)">
          </td>
          <td>
            <input type="file"id="file2" name="file2"
                   class="form-control mt-4 mb-1 hiddenFileInput" accept=".jpg,.jpeg,.gif,.png" onchange="checkSize(this,2)">
          </td>
          <td>
            <input type="file"id="file3" name="file3"
                     class="form-control mt-4 mb-1 hiddenFileInput" accept=".jpg,.jpeg,.gif,.png" onchange="checkSize(this,3)">
          </td>
          <td>
            <input type="file"id="file4" name="file3"
                     class="form-control mt-4 mb-1 hiddenFileInput" accept=".jpg,.jpeg,.gif,.png" onchange="checkSize(this,4)">
          </td>
      </tr>
      </table>
      <button type="submit" class="btn btn-secondary mb-3">작성하기</button>
      <button type="button" class="btn btn-secondary mb-3" onclick="history.back()">뒤로가기</button>
    </form>
    <script>
      function loadImg(input,index){
        //파일을 읽어드릴 객체가필요
        let Img;
        if(index>1){
          Img= document.getElementById("contentImg"+index);
        }else {
          Img= document.getElementById("titleImg");
          Img.style.display='block';
        }
        let inputFile=document.getElementById("file"+index);
        console.log(inputFile);
        if(inputFile.files.length!=0){
          //파일을 읽어드리는 객체
          let reader=new FileReader();
          //파일을 읽어드리는 메소드-> 해당파일을 읽어드려 고유한 url을부여한다.->url을 src로 제시
          reader.readAsDataURL(inputFile.files[0]);
          //파일읽기 완료시 실행할 함수 정의
          reader.onload=function(e){//e.target.result에 고유 url이 담김
            Img.src=e.target.result;
          }
        }
        displayNext(input,index);
      }

      function displayNext(input,index){
        if(index<4){
          let files=document.querySelectorAll('#fileTable input[type="file"]');
          files[index].style.display='block';
        }
      }
      function checkSize(input,index){
        if(input.files!=null&&input.files[0].size>1024*1024*10){
          alert("파일이10mb가 넘습니다.")
          input.value=null;
        }else{
          loadImg(input,index);
        }
      }
    </script>
  </main>

</body>
</html>
