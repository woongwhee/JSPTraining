package com.kh.notice.controller;

import com.kh.member.model.vo.Member;
import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "insert.no", value = "/insert.no")
public class NoticeInsertController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session=request.getSession();
        String title=request.getParameter("title");
        String content=request.getParameter("content");
        int userNo=Integer.parseInt(request.getParameter("userNo"));
        Notice N=new Notice(title,content);
        int result=new NoticeService().insertNotice(N,userNo);
        if(result>0){
            response.sendRedirect(request.getContextPath()+"/list.no");
        }else{
            request.setAttribute("errorMsg","공지사항 작성 실패");
            request.getRequestDispatcher("views/common/errorPage.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
