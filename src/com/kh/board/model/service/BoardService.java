package com.kh.board.model.service;

import com.kh.board.model.dao.BoardDao;
import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.Category;
import com.kh.board.model.vo.PageInfo;

import java.sql.Connection;
import java.util.ArrayList;

import static com.kh.common.JDBCTemplet.*;

public class BoardService {
    public ArrayList<Board> selectList(PageInfo PI){
        Connection conn= getConnection();
        ArrayList<Board> list=new BoardDao().selectList(conn,PI);
        close();
        return list;
    }
    public int selectListCount(){
        Connection conn= getConnection();
        int result=new BoardDao().selectListCount(conn);
        close();
        return result;
    }

    public int increaseCount(int boardNo){
        Connection conn=getConnection();
        int result=new BoardDao().increaseCount(conn,boardNo);
        commitOrRollback(result);
        return  result;
    };
    public Board selectBoard(int boardNo){
        Connection conn=getConnection();
        Board borad=new BoardDao().selectBoard(conn,boardNo);
        close();
        return borad;
    }
    public int insertBoard(Board b){
        Connection conn=getConnection();
        int result=new BoardDao().insertBoard(conn,b);
        commitOrRollback(result);
        close();
        return result;
    }
    public int insertAttachment(Attachment at){
        Connection conn=getConnection();
        int result=new BoardDao().insertAttachment(conn,at);
        commitOrRollback(result);
        close();
        return result;
    }

    public Attachment selectAttachment(int boardNo){
        Connection conn=getConnection();
        Attachment at=new BoardDao().selectAttachment(conn,boardNo);
        close();
        return at;
    }

    public ArrayList<Category> selectCategoryList(){
        Connection conn=getConnection();
        ArrayList<Category> list=new BoardDao().selectCategoryList(conn);
        close();
        return list;
    }

    public int updateBoard(Board b){
        Connection conn=getConnection();
        int result=new BoardDao().updateBoard(conn,b);
        commitOrRollback(result);
        close();
        return result;
    }
    public int deleteAttachment(int fileNo){
        Connection conn=getConnection();
        int result=new BoardDao().deleteAttachment(conn,fileNo);
        commitOrRollback(result);

        close();
        return result;
    }
    public int deleteBoard(int boardNo,int userNo){
        Connection conn=getConnection();
        int result=new BoardDao().deleteBoard(conn,boardNo,userNo);
        if(result>0){
            new BoardDao().deleteAttachmentByBNO(conn,boardNo);
        }
        commitOrRollback(result);
        close();
        return result;
    }
    public ArrayList<Board> selectThumbnailList(){
        Connection conn=getConnection();
        ArrayList<Board> list=new BoardDao().selectThumbnailList(conn);
        close();
        return list;
    }

    private void commitOrRollback(int result){
        if(result>0){
            commit();
        }else{
            rollback();
        }
        close();
    }
}
