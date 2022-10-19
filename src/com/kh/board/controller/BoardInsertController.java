package com.kh.board.controller;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.common.UploadUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
@WebServlet(name = "insert.bo", value = "/insert.bo")
@MultipartConfig(
        fileSizeThreshold = 1024*1024,
        maxFileSize = 1024*1024*20 //20메가
)
public class BoardInsertController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        BoardService bs=new BoardService();
        String title=request.getParameter("title");
        String content=request.getParameter("content");
        String category=request.getParameter("category");
        int userNo=Integer.parseInt(request.getParameter("userNo"));
        int BoardType=0;

        Part p=request.getPart("attachment");
        if(p.getSubmittedFileName().equals("")){
            BoardType=1;
        }else{
            BoardType=2;
        }

        Board b = new Board(BoardType,category, title, content,String.valueOf(userNo));
        int result = bs.insertBoard(b);//글번호를 반환하게
        if(result>0){
            if(!p.getSubmittedFileName().equals("")) {
                UploadUtil uploadUtil = UploadUtil.create(request.getServletContext());
                Attachment at = uploadUtil.saveFiles(p, uploadUtil.createFilePath(category));
                at.setRefNo(result);
                int atResult=bs.insertAttachment(at);
                if(atResult>0){
                    response.sendRedirect(request.getContextPath()+"/detail.bo?bno="+result);
                }else{
                    new File(at.getFilePath()+at.getChangeName()).delete();
                    request.setAttribute("errorMsg","첨부파일 업로드 실패.");
                    request.getRequestDispatcher("views/common/errorPage.jsp").forward(request,response);
                };
            }else{
                response.sendRedirect(request.getContextPath()+"/detail.bo?bno="+result);

            }
        }else{
            request.setAttribute("errorMsg","게시글 업로드 실패.");
            request.getRequestDispatcher("views/common/errorPage.jsp").forward(request,response);

        }
        //cos.jar
        //defalutFileRenamePolict 객체 (cos.jar에서 제공하는 객체)
        // 동일한 파일명이 존재할경우 뒤에 카운팅또는 숫자를 붙여 파일명 수정을 진행함.
        //ex)aaa.jpg, aaa1.jpg
        //-입맛대로 파일명이 절대 안겹치게끔 rename해볼꺼임
        //내입맛대로


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
