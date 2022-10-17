package com.kh.board.controller;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.PageInfo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "list.bo", value = "/list.bo")
public class BoardListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //페이징 처리 시작
        request.setCharacterEncoding("UTF-8");
        int listCount=new BoardService().selectListCount();
        String categoryName=request.getParameter("ctg");

        int currentPage =Integer.parseInt(request.getParameter("currentPage")==null?"1":request.getParameter("currentPage"));
        PageInfo pageInfo;
        if(categoryName==null||categoryName.equals("")){
        pageInfo=new PageInfo(listCount,currentPage);
        }else{
            pageInfo=new PageInfo(listCount,currentPage,categoryName);
        }
        //
        //페이징 처리 끝
        // 현재 일반게시판의 게시글들 가져오기
        ArrayList<Board> list=new BoardService().selectList(pageInfo);
        request.setAttribute("list",list);
        request.setAttribute("pageInfo",pageInfo);
        request.getRequestDispatcher("views/board/boardListView.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);


    }
}
