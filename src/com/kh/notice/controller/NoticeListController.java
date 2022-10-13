package com.kh.notice.controller;

import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "list.no", value = "/list.no")
public class NoticeListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //공지사항 전체 리스트 조회 한 후 조회 결과를 담아서 응답페이지로 포워딩.
        ArrayList<Notice> list=new NoticeService().selectNoticeList();
        System.out.println(list);
        request.setAttribute("list",list);
        request.getRequestDispatcher("views/notice/NoticeListView.jsp").forward(request,response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
