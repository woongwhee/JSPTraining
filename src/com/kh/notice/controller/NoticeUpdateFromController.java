package com.kh.notice.controller;

import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "updateForm.no", value = "/updateForm.no")
public class NoticeUpdateFromController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int nno=Integer.parseInt(request.getParameter("nno"));
        Notice n=new NoticeService().selectNotice(nno);
        request.setAttribute("n",n);
        RequestDispatcher view=request.getRequestDispatcher("views/notice/noticeUpdateForm.jsp");
        view.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
