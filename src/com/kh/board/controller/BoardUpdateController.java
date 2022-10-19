package com.kh.board.controller;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.common.UploadUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
@WebServlet(name = "update.bo", value = "/update.bo")
@MultipartConfig(
        fileSizeThreshold = 1024*1024,
        maxFileSize = 1024*1024*20 //20메가
)
public class BoardUpdateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        BoardService bs = new BoardService();
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String category = request.getParameter("category");
        String delete = request.getParameter("deleteFile");
        System.out.println(delete);
        int userNo = Integer.parseInt(request.getParameter("userNo"));
        int boardNo = Integer.parseInt(request.getParameter("boardNo"));
        int boardType = 1;
        Part p = request.getPart("upfile");
        if (delete == null || delete.equals("false")) {
            if (p.getSubmittedFileName().equals("")) {
                boardType = 1;
            } else {
                boardType = 2;
            }
        }
        Board b = new Board(boardNo, category, title, content, String.valueOf(userNo));
        b.setBoardType(boardType);
        int result = bs.updateBoard(b);
        if (result==0) {
            request.setAttribute("errorMsg", "게시글 수정실패.");
            request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
            return;
        }
        if (p.getSubmittedFileName().equals("")) {//새로 업로드된 파일이 없다.
            request.setAttribute("alertMsg", "게시글 수정완료.");
            response.sendRedirect(request.getContextPath() + "detail.bo?bno=" + boardNo);
            return;
        }
        //새로 업로드된 파일이 있다.
        UploadUtil uplodUtil=UploadUtil.create(request.getServletContext());

        if (delete != null && delete.equals("delete")) {//삭제된 파일이 있는경우
            int originFileNo =Integer.parseInt(request.getParameter("originFileNo"));
            Attachment at = bs.selectAttachment(boardNo);
            if (at.getFileNo()==originFileNo);
            if(uplodUtil.deleteFile(at)){
                System.out.println("삭제됨");
                bs.deleteAttachment(originFileNo);
            }
        }
        Attachment at = uplodUtil.saveFiles(p,uplodUtil.createFilePath(category));
        at.setRefNo(boardNo);
        result = bs.insertAttachment(at);
        if(result>0) {
        request.setAttribute("alertMsg", "게시글 수정완료.");
        response.sendRedirect(request.getContextPath() + "/detail.bo?bno="+boardNo);
        }else{
            request.setAttribute("errorMsg", "첨부파일 수정실패.");
            request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
        }


}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
