package com.kh.board.controller;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.PageInfo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "list.th", value = "/list.th")
public class ThumbnallListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //사진게시판 목록 데이터 가져오기
//        String tno_=request.getParameter("tno")!=null?request.getParameter("tno"):"1";
//        int tno=Integer.parseInt(tno_);
//        int listCount=new BoardService().selectThumbnailListCount();
//        PageInfo PI=new PageInfo(listCount,tno);
//        ArrayList<Board> list= new BoardService().selectThumbnailList(tno);
        ArrayList<Board> list= new BoardService().selectThumbnailList();
        System.out.println(list);
        request.setAttribute("list",list);
        request.getRequestDispatcher("views/board/thumbnailListView.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
