package com.kh.member.controller;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "updatePwd.me", value = "/updatePwd.me")
public class MemberUpdatePwdController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String userId=request.getParameter("userId");
        String userPwd=request.getParameter("userPwd");
        String updatePwd=request.getParameter("updatePwd");
        Member result=new MemberService().updatePwdMember(userId,userPwd,updatePwd);
        HttpSession session=request.getSession();
        if(result==null){
            session.setAttribute("alertMsg","비밀번호 변경에 실패했습니다.");
        }else{
            session.setAttribute("alertMsg","성공적으로 비밀번호가 변경되었습니다.");
            session.setAttribute("loginUser",result);
        }
        response.sendRedirect(request.getContextPath()+"/myPage.me");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
