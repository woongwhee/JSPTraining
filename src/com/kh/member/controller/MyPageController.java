package com.kh.member.controller;

import com.kh.member.model.vo.Member;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "myPage.me", value = "/myPage.me")
public class MyPageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //url로 직접 요청도 가능하기 때문에
        //로그인 전 요청시 -=>메인페이지로
        //로그인후 요청시 ->마이페이지로 포워딩.
        request.setCharacterEncoding("UTF-8");

        HttpSession session=request.getSession();
        Member loginUser=(Member)session.getAttribute("loginUser");
        if(loginUser==null){
            session.setAttribute("alertMsg","로그인후 이용가능한 서비스 입니다.");
            response.sendRedirect(request.getContextPath());
        }else{
        request.getRequestDispatcher("views/member/myPage.jsp").forward(request,response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
