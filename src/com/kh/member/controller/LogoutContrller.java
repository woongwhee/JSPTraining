package com.kh.member.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "logout.me", value = "/logout.me")
public class LogoutContrller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 로그아웃 요청 처리=> session에 저장되었는 loginUser정보를 만료시키기.(세션을 무효화)
        request.setCharacterEncoding("UTF-8");

        HttpSession session=request.getSession();
        session.invalidate();
        //응답페이지 -> /jsp
        //url 재요청 방식=>index.jsp 페이지
        //response.sendRedirect("/jsp");(x)
        //response.sendRedirect(request.getContextPath());(x)

        response.sendRedirect(request.getContextPath());

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
