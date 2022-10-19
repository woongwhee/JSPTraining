package com.kh.board.controller;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "detail.th", value = "/detail.th")
public class ThumbnailDetailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int boardNo=Integer.parseInt(request.getParameter("bno"));
        BoardService bs=new BoardService();
        int result=bs.increaseCount(boardNo);
        if(result==0){
            errorPage(request,response,"게시글 조회실패");
        }
        Board b=new BoardService().selectBoard(boardNo);
        ArrayList<Attachment> list=new BoardService().selectAttachmentList(boardNo);
        request.setAttribute("b",b);
        request.setAttribute("list",list);
        request.getRequestDispatcher("views/board/thumbnailDetailView.jsp").forward(request,response);
    }
    private void errorPage(HttpServletRequest request, HttpServletResponse response,String msg)throws ServletException, IOException{
        request.setAttribute("errorMsg",msg);
        request.getRequestDispatcher("views/common/errorPage.jsp").forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
