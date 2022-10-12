package com.kh.member.controller;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "insert.me", value = "/insert.me")
public class MemberInsertController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1)인코딩작업
        request.setCharacterEncoding("UTF-8");
        //2)요청시 전달값을 뽑아서 변수 및 객체에 기록하기.
        String userId=request.getParameter("userId");
        String userPwd=request.getParameter("userPwd");
        String userName=request.getParameter("userName");
        String phone=request.getParameter("phone");
        String email=request.getParameter("email");
        String address=request.getParameter("address");
        String[] interest_=request.getParameterValues("interest");//문자열로가공해줌
        String interest=interest_!=null?String.join(",",interest_):"";
        //매개변수 생성자를 이용해서 Member객체에담기
        Member m=new Member(userId,userPwd,userName,phone,email,address,interest);
        int result=new MemberService().insertMember(m);

        //처리결과를 가지고 사용자가 보게될 응답 뷰를 지정.
        if(result>0){//성공
            HttpSession session=request.getSession();
            session.setAttribute("alertMsg","회원가입에 성공했습니다.");
            response.sendRedirect(request.getContextPath());
        }else{//실패
            request.setAttribute("erorrMsg","회원가입에 실패했습니다.");
            request.getRequestDispatcher("views/common/errorPage").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
