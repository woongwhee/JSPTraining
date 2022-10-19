package com.kh.board.model.dao;

import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.Category;
import com.kh.board.model.vo.PageInfo;
import com.kh.common.JDBCTemplet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static com.kh.common.JDBCTemplet.close;

public class BoardDao {
    private enum BoardType{Detail,List,Thumb}
    private Properties prop;
    public BoardDao(){
        prop=new Properties();
        String fileName=BoardDao.class.getResource("/sql/board/Board-mapper.xml").getPath();
        try {
            prop.loadFromXML(new FileInputStream(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Board> selectList(Connection conn){
        ArrayList<Board> list=new ArrayList<>();
        PreparedStatement pstm=null;
        ResultSet rset=null;
        String sql=prop.getProperty("selectListAll");
        try{
            pstm=conn.prepareStatement(sql);
            rset=pstm.executeQuery();
            Board b=null;
            while((b=rsetToBoard(rset,BoardType.List))!=null){
                list.add(b);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rset);
            close(pstm);
        }
        return list;
    }

    public ArrayList<Board> selectList(Connection conn, PageInfo PI){
        ArrayList<Board> list=new ArrayList<>();
        PreparedStatement pstm=null;
        ResultSet rset=null;
        String allSql=prop.getProperty("selectAllList");
        String cateSql=prop.getProperty("selectByCategoryName");
        int startNum=(PI.getCurrentPage()-1)*PI.getBoardLimit()+1;
        int endNum=(PI.getCurrentPage())*PI.getBoardLimit();

        try{
            if(PI.getCategoryName()==null){
                pstm=conn.prepareStatement(allSql);
                pstm.setInt(1,startNum);
                pstm.setInt(2,endNum);
            }else{
                pstm=conn.prepareStatement(cateSql);
                pstm.setString(1, PI.getCategoryName());
                pstm.setInt(2,startNum);
                pstm.setInt(3,endNum);
            }
            rset=pstm.executeQuery();
            Board b=null;
            while((b=rsetToBoard(rset,BoardType.List))!=null){
                list.add(b);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstm);
        }
        return list;
    }
    public ArrayList<Board> selectThumbnailList(Connection conn){
        ArrayList<Board> list=new ArrayList<>();
        PreparedStatement psmt=null;
        ResultSet rset=null;
        String sql=prop.getProperty("selectThumbnailList");
        try{
            psmt=conn.prepareStatement(sql);
            rset=psmt.executeQuery();
            Board b=null;
            while((b=rsetToBoard(rset,BoardType.Thumb))!=null){
                list.add(b);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rset);
            close(psmt);
        }
        return list;

    }
//    public int selectThumnailListCount(Connection conn){
//
//
//    }
    public int selectListCount(Connection conn){
        PreparedStatement psmt=null;
        ResultSet rset=null;
        int result=0;
        String sql=prop.getProperty("selectListCount");
        try {
            psmt=conn.prepareStatement(sql);
            rset= psmt.executeQuery();
            rset.next();
            result=rset.getInt("cnt");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rset);
            close(psmt);
        }

        return result;
    }

    public int increaseCount(Connection conn,int boardNo){
        PreparedStatement psmt=null;
        String sql=prop.getProperty("increaseCount");
        int result=0;
        try {
            psmt=conn.prepareStatement(sql);
            psmt.setInt(1,boardNo);
            result=psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {

            close(psmt);

        }

        return result;
    };
    public Board selectBoard(Connection conn,int boardNo){
        PreparedStatement psmt=null;
        String sql=prop.getProperty("selectBoard");
        ResultSet rset=null;
        Board b=null;
        try {
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, boardNo);
            rset=psmt.executeQuery();
            b=rsetToBoard(rset,BoardType.Detail);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rset);
            close(psmt);
        }
        return b;
    };
    public Attachment selectAttachment(Connection conn, int boardNo){
        PreparedStatement psmt=null;
        String sql=prop.getProperty("selectAttachment");
        ResultSet rset=null;
        Attachment at=null;
        try {
            psmt=conn.prepareStatement(sql);
            psmt.setInt(1,boardNo);
            rset=psmt.executeQuery();
            at=rsetToAttachment(rset);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rset);
            close(psmt);
        }
        return at;
    };
    public int updateBoard(Connection conn,Board b){
        PreparedStatement psmt=null;
        int result=0;
        String sql=prop.getProperty("updateBoard");
        try {
            psmt=conn.prepareStatement(sql);
            psmt.setString(1,b.getBoardTitle());
            psmt.setInt(2,b.getBoardType());
            psmt.setString(3,b.getCategory());
            psmt.setString(4,b.getBoardContent());
            psmt.setInt(5,b.getBoardNo());
            psmt.setString(6,b.getBoardWriter());
            result=psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(psmt);
        }
        return result;
    }


    public ArrayList<Category> selectCategoryList(Connection conn){
        PreparedStatement psmt=null;
        ResultSet rset=null;
        ArrayList<Category> list=new ArrayList<>();
        String sql=prop.getProperty("selectCategoryList");
        try {
            psmt=conn.prepareStatement(sql);
            rset= psmt.executeQuery();
            Category c=null;
            while((c=rsetToCategory(rset))!=null){
                list.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            close(rset);
            close(psmt);
        }
        return list;

    }
    public int insertBoard(Connection conn,Board b){
        PreparedStatement psmt=null;
        PreparedStatement psmt2=null;
        int result=0;
        ResultSet rset=null;
        String insert=prop.getProperty("insertBoard");
        String getbNo=prop.getProperty("getbNo");
        try{
            psmt = conn.prepareStatement(insert);
            psmt.setInt(1,b.getBoardType());
            psmt.setString(2, b.getCategory());
            psmt.setString(3,b.getBoardTitle());
            psmt.setString(4,b.getBoardContent());
            psmt.setString(5,b.getBoardWriter());
           synchronized(this) {
                result=psmt.executeUpdate();
                if(result>0){
                    psmt2=conn.prepareStatement(getbNo);
                    rset= psmt2.executeQuery();
                }
            }
            if(rset.next()) {
                result=rset.getInt("sc");}
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            close(rset);
            close(psmt2);
            close(psmt);
        }
        return result;
    }
    public int insertAttachment(Connection conn,Attachment at){
        PreparedStatement pstm=null;
        int result=0;
        String sql=prop.getProperty("insertAttachment");
        try {
            pstm=conn.prepareStatement(sql);
            pstm.setInt(1,at.getRefNo());
            pstm.setString(2,at.getOriginName());
            pstm.setString(3,at.getChangeName());
            pstm.setString(4,at.getFilePath());
            result=pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(pstm);
        }
        return result;
    }

    public int deleteBoard(Connection conn,int boardNo,int userNo){
        PreparedStatement psmt=null;
        int result=0;
        String sql=prop.getProperty("deleteBoard");
        try {
            psmt=conn.prepareStatement(sql);
            psmt.setInt(1,boardNo);
            psmt.setInt(2,userNo);
            result=psmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(psmt);
        }

        return result;
    }
    public int deleteAttachment(Connection conn,int atNo){
        PreparedStatement psmt=null;
        int result=0;
        String sql=prop.getProperty("deleteAttachment");
        try {
            psmt=conn.prepareStatement(sql);
            psmt.setInt(1,atNo);
            result=psmt.executeUpdate();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }finally {
            close(psmt);
        }


        return result;
    }
    public int deleteAttachmentByBNO(Connection conn,int bno){
        PreparedStatement psmt=null;
        int result=0;
        String sql=prop.getProperty("deleteAttachmentByBNO");
        try {
            psmt=conn.prepareStatement(sql);
            psmt.setInt(1,bno);
            result=psmt.executeUpdate();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }finally {
            close(psmt);
        }


        return result;
    }
    private Category rsetToCategory(ResultSet rset)throws SQLException{
        Category c=null;
        if(rset.next()){
            c=new Category(rset.getInt("CATEGORY_NO"),rset.getString("CATEGORY_NAME"));
        }
        return c;
    }
    private Attachment rsetToAttachment (ResultSet rset)throws SQLException{
        Attachment at=null;
        if(rset.next()){
            at=new Attachment(rset.getInt("FILE_NO"),
                    rset.getString("ORIGIN_NAME"),
                    rset.getString("CHANGE_NAME"),
                    rset.getString("FILE_PATH"));
        }
        return at;
    }
    private Board rsetToBoard(ResultSet rset,BoardType type)throws SQLException{
        Board b=null;
        if(type==BoardType.List){
            if(rset.next()){
            b=new Board(rset.getInt("board_no"),
                    rset.getString("category_name"),
                    rset.getString("board_title"),
                    rset.getString("user_id"),
                    rset.getInt("count"),
                    rset.getDate("create_date"));
            };
        }else if(type==BoardType.Detail){
           if(rset.next()){b=new Board(rset.getInt("board_no"),
                    rset.getString("category_name"),
                    rset.getString("board_title"),
                    rset.getString("board_content"),
                    rset.getString("user_id"),
                    rset.getDate("create_date"));}
        }else if(type==BoardType.Thumb){
            if(rset.next()){b=new Board(rset.getInt(1),
                    rset.getString(2),
                    rset.getInt(3),
                    rset.getString(4));
            }}
        return b;
    }

}
