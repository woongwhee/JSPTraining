package com.kh.notice.model.service;


import com.kh.common.JDBCTemplet;
import com.kh.notice.model.dao.NoticeDao;
import com.kh.notice.model.vo.Notice;

import java.sql.Connection;
import java.util.ArrayList;
import static com.kh.common.JDBCTemplet.*;//메소드를 그냥 가저다쓰겠음
public class NoticeService {
    public ArrayList<Notice> selectNoticeList(){
        Connection conn=getConnection();
        ArrayList<Notice>list=new NoticeDao().selectNoticeList(conn);
        close();
        return list;
    }
    public int increaseCount(int noticeNo){
        Connection conn=getConnection();
        int result=new NoticeDao().increaseCount(conn,noticeNo);
        if(result>0){
            commit();
        }else{
            rollback();
        }
        close();
        return result;
    }
    public Notice selectNotice(int noticeNo){
        Connection conn=getConnection();
        Notice n=new NoticeDao().selectNotice(conn,noticeNo);
        close();
        return n;
    }
    public int insertNotice(Notice n,int userNo){
        Connection conn=getConnection();
        int result=new NoticeDao().insertNotice(conn,n,userNo);
        if(result>0) {
            commit();
        }else{
            rollback();
        }
        close();
        return result;
    }
    public  int updateNotice(Notice n){
        Connection conn=getConnection();
        int result=new NoticeDao().updateNotice(conn,n);
        if(result>0) {
            commit();
        }else{
            rollback();
        }
        close();
        return result;
    }
    public  int deleteNotice(int nno){
        Connection conn=getConnection();
        int result=new NoticeDao().deleteNotice(conn,nno);
        if(result>0) {
            commit();
        }else{
            rollback();
        }
        close();
        return result;
    }
}
