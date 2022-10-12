package com.kh.member.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "enrollForm.me", value = "/enrollForm.me")
public class MemberEnrollFormController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //포워딩방식

        RequestDispatcher view=request.getRequestDispatcher("views/member/memberEnrollForm.jsp");
        view.forward(request,response);//포워딩방식으로하면 디렉토리구조가안나옴!

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
