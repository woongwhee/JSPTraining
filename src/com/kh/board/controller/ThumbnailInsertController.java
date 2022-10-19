package com.kh.board.controller;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.common.MyFileRenamePolicy;
import com.kh.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "insert.th", value = "/insert.th")
@MultipartConfig(
        fileSizeThreshold = 1024*1024,
        maxFileSize = 1024*1024*10
)
public class ThumbnailInsertController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            if(ServletFileUpload.isMultipartContent(request)){
            int maxSize=10*1024*1024;
            String savePath=request.getServletContext().getRealPath("/resources/thumbnail_upfiles");
            MultipartRequest multipartRequest=new MultipartRequest(request,savePath,maxSize,"UTF-8",new MyFileRenamePolicy());
            Board b=new Board();
            b.setBoardWriter(String.valueOf(((Member)request.getSession().getAttribute("loginUser")).getUserNo()));
            b.setBoardTitle(multipartRequest.getParameter("title"));
            b.setBoardContent(multipartRequest.getParameter("content"));
            ArrayList<Attachment> list=new ArrayList<>();
            System.out.println("?");
            for(int i=1; i<=4; i++){//파일은 최대 4개 인덱스는 1부터
                String key="file"+i;
                if(multipartRequest.getOriginalFileName(key)!=null&&!multipartRequest.getOriginalFileName(key).equals("")){
                    //첨부파일이 있는경우
                    //Attachment 객체를 생성 + 원본명,수정명, 파일경로를 저장 + fileLevel
                    //index1 은 썸네일 아닌건 일반
                    Attachment at=new Attachment();
                    at.setOriginName(multipartRequest.getOriginalFileName(key));
                    at.setChangeName(multipartRequest.getFilesystemName(key));
                    at.setFilePath("/resources/thumbnail_upfiles");
                    at.setFileLevel(i);
                    list.add(at);
                }
            }
            int result= new BoardService().insertThumbnailBoard(b,list);
            if(result>0){
                request.getSession().setAttribute("alertMsg","게시글등록에 성공하셨습니다.");
                response.sendRedirect(request.getContextPath()+"/list.th");
            }else{
                request.setAttribute("errorMsg","게시글 등록에 실패 하셨습니다.");
                request.getRequestDispatcher("views/common/errorPage.jsp");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
