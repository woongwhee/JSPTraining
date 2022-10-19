package com.kh.board.controller;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.Category;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "updateForm.bo", value = "/updateForm.bo")
public class BoardUpdateFormController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BoardService bs=new BoardService();
        int boardNo=Integer.parseInt(request.getParameter("bno"));
        ArrayList<Category>list=bs.selectCategoryList();
        Board board=bs.selectBoard(boardNo);
        Attachment attachment=bs.selectAttachment(boardNo);

        request.setAttribute("list",list);
        request.setAttribute("board",board);
        request.setAttribute("attachment",attachment);
        request.getRequestDispatcher("views/board/boardUpdateForm.jsp").forward(request,response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
