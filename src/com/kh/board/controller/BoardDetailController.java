package com.kh.board.controller;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "detail.bo", value = "/detail.bo")
public class BoardDetailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String boardNo_=request.getParameter("bno");

        if(boardNo_==null){
            request.setAttribute("errorMsg","삭제되었거 존재하지 않는 페이지 입니다.");
            request.getRequestDispatcher("views/common/errorPage.jsp").forward(request,response);
            return;
        }
        int boardNo=Integer.parseInt(boardNo_);
        BoardService bs=new BoardService();
        int result=bs.increaseCount(boardNo);
        if(result>0){
            Board b = bs.selectBoard(boardNo);
            if(b!=null){
            Attachment at=bs.selectAttachment(boardNo);
            request.setAttribute("board",b);
            request.setAttribute("attachment",at);
            request.getRequestDispatcher("views/board/boardDetailView.jsp").forward(request,response);
            return;
            }

            request.setAttribute("errorMsg","삭제되었거 존재하지 않는 페이지 입니다.");
            request.getRequestDispatcher("views/common/errorPage.jsp").forward(request,response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
