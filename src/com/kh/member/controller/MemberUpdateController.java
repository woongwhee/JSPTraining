package com.kh.member.controller;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "update.me", value = "/update.me")
public class MemberUpdateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String userId=request.getParameter("userId");
        String userName=request.getParameter("userName");
        String phone=request.getParameter("phone");
        String email=request.getParameter("email");
        String address=request.getParameter("address");
        String[] interest_=request.getParameterValues("interest");//문자열로가공해줌
        String interest=interest_!=null?String.join(",",interest_):"";
        Member m=new Member(userId,userName,phone,email,address,interest);
        System.out.println(m);
        Member result=new MemberService().updateMember(m);
        System.out.println(result);
        if(result==null){
            request.setAttribute("errorMsg","회원정보 수정에 실패했습니다.");
            request.getRequestDispatcher("views/common/errorPage.jsp").forward(request,response);
        }else{
            HttpSession session=request.getSession();
            session.setAttribute("loginUser",result);
            session.setAttribute("alertMsg","회원가입에 성공했습니다.");
            response.sendRedirect(request.getContextPath());
        }





    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
