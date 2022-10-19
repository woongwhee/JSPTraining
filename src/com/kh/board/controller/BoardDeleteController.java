package com.kh.board.controller;

import com.kh.board.model.dao.BoardDao;
import com.kh.board.model.service.BoardService;
import com.kh.member.model.vo.Member;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "delete.dbo", value = "/delete.dbo")
public class BoardDeleteController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int boardNo=Integer.parseInt(request.getParameter("bno"));
        HttpSession session=request.getSession();
        int userNo=session.getAttribute("loginUser")==null?-1:((Member)session.getAttribute("loginUser")).getUserNo();
        int result=new BoardService().deleteBoard(boardNo,userNo);
        if(result>0){
            request.setAttribute("alert","게시글이 성공적으로 삭제되었습니다.");
            response.sendRedirect(request.getContextPath()+"/list.bo");
        }else{
            request.setAttribute("error","게시글 삭제에 실패했습니다.");
            request.getRequestDispatcher("views/common/errorPage.jsp").forward(request,response);
        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
