package com.kh.member.controller;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name="login.me", value="/login.me")
public class LoginController extends HttpServlet {
    private MemberService ms=new MemberService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* <HttpServletRequest 객체와 HttpServletResponse 객체>
        *   -request:서버로 요쳥할 때의 정보들이 담겨있음(요청시 전달값 , 요청전송 방식등)
        *   -response: 요청에대해 응답할때 필요한 객체
        * <GET방식과 POST방식의 차이>
        *       사용자가 입력한 값들이 url 데이터길이제한 즐겨찾기
        * - get:    o               o           o
        * - post:   x               x           x(timeOut이 존재)
        */
        //1)전달값에 한글이 있을경우 인코딩처리해줘야함(Post방식일 경우)
        request.setCharacterEncoding("UTF-8");
        //2)요청시 전달값을 꺼내서 변수 또는 객체에 기록하기
        String userId=request.getParameter("userId");
        String userPwd=request.getParameter("userPwd");
        //3)해당요청을 처리하는 서비스의 메소드 메소드 호출
        Member loginUser = ms.loginMember(userId,userPwd);
        //4)처리된결과를 가지고 사용자가 보게될 뷰 지정
        /*
            -응답페이지에 전달할 값이 있을 경우 값을 어띤가에 담아야함.(담아줄수 있는 Servlet scope 내장객체종류)
            1) application : application 에 담은 데이터는 웹 애플리케이션 전역에서 다 꺼내 쓸수 있음.
            2) session : session에 담은 데이터는 모든 jsp와 servlet에서 꺼내쓸수 있음.
                        한번 담은 데이터는 내가 지우기 전까지 서버가 멈추기 전까지 브라우저 종료전까지 접근가능.
            3) request : request에 담은 데이터는 해당 request를 포워딩한 응답 jsp에서만 꺼내 쓸 수 있음.
            4) page : 해당 jsp페이지에서만 꺼내쓸수 있음.
            자주 쓰는건 request와 session
         */
        if(loginUser==null){//로그인 실패=>에러페이지
            request.setAttribute("errorMsg","로그인에 실패했습니다.");
            //응답페이지 jsp에 위임시 필요한 객체(requestDispatcher)
            RequestDispatcher view=request.getRequestDispatcher("views/common/errorPage.jsp");
            view.forward(request,response);//포워딩 방식 : 해당경로로 선택된 뷰가 보여질뿐 url은 변하지 않음.
        }else{//로그인성공=> index페이지 응답.
            //로그인 회원의 정보를 로그아웃 하기 전까지 계속 가져다 쓸것이기 때문에 session에 담기.
            HttpSession session=request.getSession();
            session.setAttribute("loginUser",loginUser);
            session.setAttribute("alertMsg","성공적으로 로그인이 되었습니다.");
            /*
                1. 포워딩 방식 응답 뷰 출력하기
                RequestDispatcher view=request.getRequestDispatcher("index.jsp");
                view,forward(request,response);
                해당 선택된 jsp가 보여질 뿐이고, url에는 여전히 현재 이 서블릿 매핑값이 남아있음.
                localhost:8001/jsp/login.me
                2.url 재요청방식(sendRedirect방식)
                    localhost:8001/jsp/login.me
             */

            response.sendRedirect(request.getContextPath());

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);

    }
}
