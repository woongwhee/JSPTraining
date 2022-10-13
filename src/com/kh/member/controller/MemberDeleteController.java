package com.kh.member.controller;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "deleteMember.me", value = "/deleteMember.me")
public class MemberDeleteController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //방법1 input type hidden으로 요청시 해당값으 숨겨전달받기
        //방법2 session 영역에서 담겨있는 회원객체로 붙어 뽑기?
        String userPwd=request.getParameter("userPwd");
        HttpSession session=request.getSession();
        Member m=((Member)session.getAttribute("loginUser"));
        String userId=m.getUserId();
        int result=0;
        if(userPwd.equals(m.getPassword())){
        result=new MemberService().deleteMember(userId,userPwd);
        } else{
            result=0;
        }
        if(result>0){
            session.setAttribute("alertMsg","성공적으로 회원탈퇴 되었습니다. 그 동안 이용해주셔서 감사합니다.");
            //invalidate() : 세션이 만료되어 alertMsg를 사용 못하게됨.
            //removeAttribute(키값)을 이용해서 로그인한 사용자의 정보를 지워주는 방식으로 로그아웃처리함.
            session.removeAttribute("loginUser");
            response.sendRedirect(request.getContextPath());
        }else{//실패-->에러페이지
            request.setAttribute("errorMsg","회원 탈퇴에 실패했습니다.");
            request.getRequestDispatcher("views/common/errorPage.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
