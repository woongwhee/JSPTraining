package com.kh.board.controller;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Category;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "enrollForm.bo", value = "/enrollForm.bo")
public class BoardEnrollController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Category> list =new BoardService().selectCategoryList();
        request.setAttribute("list",list);
        request.getRequestDispatcher("views/board/boardEnrollForm.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
