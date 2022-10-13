package com.kh.notice.controller;

import com.kh.member.model.vo.Member;
import com.kh.notice.model.service.NoticeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "delete.no", value = "/delete.no")
public class NoticeDeleteController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!(request.getSession().getAttribute("loginUser")!=null&&((Member)request.getSession().getAttribute("loginUser")).getUserId().equals("admin"))){
            request.setAttribute("errorMsg","공지사항 삭제권한이 없습니다.");
            request.getRequestDispatcher("views/common/errorPage.jsp").forward(request,response);
        }
        request.setCharacterEncoding("UTF-8");
        int nno=Integer.parseInt(request.getParameter("nno"));
        int result=new NoticeService().deleteNotice(nno);
        HttpSession session=request.getSession();
        if(result>0){
            request.setAttribute("alert","공지사항이 성공적으로 삭제되었습니다.");
            response.sendRedirect(request.getContextPath()+"/list.no");
        }else{
            request.setAttribute("error","공지사항 삭제에 실패했습니다.");
            request.getRequestDispatcher("views/common/errorPage.jsp").forward(request,response);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
