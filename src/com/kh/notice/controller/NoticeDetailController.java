package com.kh.notice.controller;

import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "detail.no", value = "/detail.no")
public class NoticeDetailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");생략가능한것
        //클릭시 공지사항 글번호 nno

        int noticeNo=Integer.parseInt(request.getParameter("nno"));

        int result=new NoticeService().increaseCount(noticeNo);
        if(result>0){
            Notice n=new NoticeService().selectNotice(noticeNo);
            request.setAttribute("n",n);
            request.getRequestDispatcher("views/notice/noticeDetailView.jsp").forward(request,response);

        }else{
            request.setAttribute("errorMsg","공지사항 조회 실패");
            request.getRequestDispatcher("views/common/errorPage.jsp").forward(request,response);
        }


        //조회수 증가용 서비스 호출.





    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
