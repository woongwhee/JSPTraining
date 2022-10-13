package com.kh.notice.controller;

import com.kh.member.model.vo.Member;
import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "update.no", value = "/update.no")
public class NoticeUpdateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!(request.getSession().getAttribute("loginUser")!=null&&((Member)request.getSession().getAttribute("loginUser")).getUserId().equals("admin"))){
            request.setAttribute("errorMsg","공지사항 수정권한이 없습니다.");
            request.getRequestDispatcher("views/common/errorPage.jsp").forward(request,response);
        }

        request.setCharacterEncoding("UTF-8");
        int nno=Integer.parseInt(request.getParameter("NoticeNo"));
        String title=request.getParameter("title");
        String content=request.getParameter("content");
        Notice n=new Notice(nno,title,content);
        int result=new NoticeService().updateNotice(n);
        if(result >0){
            request.getSession().setAttribute("alertMsg","공지사항이 성공적으로 수정 되었습니다.");
            response.sendRedirect("detail.no?nno="+nno);
        }else{
            request.setAttribute("errorMsg","공지사항 수정 실패");
            request.getRequestDispatcher("views/common/errorPage.jsp").forward(request,response);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
