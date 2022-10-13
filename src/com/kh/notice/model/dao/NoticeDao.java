package com.kh.notice.model.dao;

import com.kh.common.JDBCTemplet;
import com.kh.notice.model.vo.Notice;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static com.kh.common.JDBCTemplet.*;

public class NoticeDao {
    private Properties prop=new Properties();

    public NoticeDao(){
        String fileName=NoticeDao.class.getResource("/sql/notice/Notice-mapper.xml").getPath();
        try {
            prop.loadFromXML(new FileInputStream(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public ArrayList<Notice> selectNoticeList(Connection conn){
        PreparedStatement psmt=null;
        ResultSet rset=null;
        ArrayList<Notice> list=new ArrayList<>();
        String sql=prop.getProperty("selectNoticeList");
        System.out.println(sql);
        try {
            psmt=conn.prepareStatement(sql);
            rset=psmt.executeQuery();
            Notice N=null;
            while((N=resultToNotice(rset,NoticeType.List))!=null){
                list.add(N);
            };
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rset);
            close(psmt);
        }
        return list;
    }
    public int increaseCount(Connection conn,int noticeNo){
        PreparedStatement psmt=null;
        int result=0;
        String sql=prop.getProperty("increaseCount");
        try {
            psmt= conn.prepareStatement(sql);
            psmt.setInt(1,noticeNo);
            result=psmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(psmt);
        }

        return result;
    }

    public Notice selectNotice(Connection conn,int noticeNo){
        PreparedStatement psmt=null;
        ResultSet rset=null;
        String sql=prop.getProperty("selectNotice");
        Notice n=null;

        try {
            psmt=conn.prepareStatement(sql);
            psmt.setInt(1,noticeNo);
            rset=psmt.executeQuery();
            n=resultToNotice(rset,NoticeType.Detail);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rset);
            close(psmt);
        }

        return n;

    }
    public int insertNotice(Connection conn,Notice n,int userNo){
        PreparedStatement psmt=null;
        int result=0;
        String sql=prop.getProperty("insertNotice");
        try {
            psmt= conn.prepareStatement(sql);
            psmt.setString(1,n.getNoticeTitle());
            psmt.setString(2,n.getNoticeContent());
            psmt.setInt(3,userNo);
            result=psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(psmt);
        }

        return result;
    }
    public int updateNotice(Connection conn,Notice n){
        PreparedStatement psmt=null;
        int result=0;
        String sql=prop.getProperty("updateNotice");
        try {
            psmt=conn.prepareStatement(sql);
            psmt.setString(1,n.getNoticeTitle());
            psmt.setString(2,n.getNoticeContent());
            psmt.setInt(3,n.getNoticeNo());
            result= psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(psmt);
        }
        return result;
    }
    public int deleteNotice(Connection conn,int nno){
        PreparedStatement psmt=null;
        int result=0;
        String sql=prop.getProperty("deleteNotice");
        try{
            psmt=conn.prepareStatement(sql);
            psmt.setInt(1,nno);
            result= psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(psmt);
        }
        return result;

    }
    private enum NoticeType{
        Detail,List
    }
    private Notice resultToNotice(ResultSet rset,NoticeType T)throws SQLException{
        Notice n=null;
        if(T==NoticeType.Detail) {
            if (rset.next()) {
                n = new Notice(rset.getInt("NOTICE_NO"),
                        rset.getString("NOTICE_TITLE"),
                        rset.getString("NOTICE_CONTENT"),
                        rset.getString("USER_ID"),
                        rset.getDate("CREATE_DATE"));
            }
        }else if(T==NoticeType.List){
        if(rset.next()){
            n=new Notice(rset.getInt("NOTICE_NO"),
                    rset.getString("NOTICE_TITLE"),
                    rset.getString("USER_ID"),
                    rset.getInt("COUNT"),
                    rset.getDate("CREATE_DATE"));
            }
        }
        return n;
    }
}
