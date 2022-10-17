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
        commitAndRollback(result);
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
        commitAndRollback(result);
        close();
        return result;
    }
    public int insertAttachment(Attachment at){
        Connection conn=getConnection();
        int result=new BoardDao().insertAttachment(conn,at);
        commitAndRollback(result);
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
    private void commitAndRollback(int result){
        if(result>0){
            commit();
        }else{
            rollback();
        }
        close();
    }
}
