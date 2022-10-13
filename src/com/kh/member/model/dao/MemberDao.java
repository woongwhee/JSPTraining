package com.kh.member.model.dao;

import com.kh.common.JDBCTemplet;
import com.kh.member.model.vo.Member;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class MemberDao {
    private Properties prop;
    public MemberDao(){
        prop=new Properties();
        String fileName= JDBCTemplet.class.getResource("/sql/member/Member-mapper.xml").getPath();
//        String fileName="/sql/member/Member-mapper";
        try {
            prop.loadFromXML(new FileInputStream(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public Member login(String userId, String userPwd, Connection conn){

        Member m=null;
        PreparedStatement psmt=null;
        ResultSet rset=null;
        String sql=prop.getProperty("loginMember");
        try{
            psmt=conn.prepareStatement(sql);
            psmt.setString(1,userId);
            psmt.setString(2,userPwd);
            rset=psmt.executeQuery();
            m=resultToMember(rset);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCTemplet.close(rset);
            JDBCTemplet.close(psmt);
        }

        return m;

    }
    public int insertMember(Member m, Connection conn){
        int result=0;
        String sql=prop.getProperty("insertMember");
        PreparedStatement psmt=null;
        try{
            psmt=conn.prepareStatement(sql);
            psmt.setString(1,m.getUserId());
            psmt.setString(2,m.getPassword());
            psmt.setString(3,m.getUserName());
            psmt.setString(4,m.getPhone());
            psmt.setString(5,m.getEmail());
            psmt.setString(6,m.getAddress());
            psmt.setString(7,m.getInterest());
            result=psmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBCTemplet.close(psmt);
        }
        return result;
    }
    public int updateMember(Member m,Connection conn){
        PreparedStatement psmt=null;
        int result=0;
        String sql=prop.getProperty("updateMember");
        try{
            psmt=conn.prepareStatement(sql);
            psmt.setString(1,m.getUserName());
            psmt.setString(2,m.getPhone());
            psmt.setString(3,m.getEmail());
            psmt.setString(4,m.getAddress());
            psmt.setString(5,m.getInterest());
            psmt.setString(6,m.getUserId());
            result=psmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            JDBCTemplet.close(psmt);
        }
        return result;
    }

    public int updatePwdMember(String userId,String userPwd,String updatePwd,Connection conn){
        PreparedStatement psmt=null;
        int result=0;
        String sql=prop.getProperty("updatePwdMember");
        try {
            psmt=conn.prepareStatement(sql);
            psmt.setString(1,updatePwd);
            psmt.setString(2,userId);
            psmt.setString(3,userPwd);
            result=psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCTemplet.close(psmt);
        }

        return result;
    }
    public Member selectMember(String userId,Connection conn){
        PreparedStatement psmt=null;
        ResultSet rset=null;
        Member updateMember=null;
        String sql=prop.getProperty("refreshMember");
        try{
            psmt=conn.prepareStatement(sql);
            psmt.setString(1,userId);
            rset=psmt.executeQuery();
            updateMember=resultToMember(rset);
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            JDBCTemplet.close(rset);
            JDBCTemplet.close(psmt);
        }
        return updateMember;
    }
    public int deleteMember(String userId,String userPwd,Connection conn){
        PreparedStatement psmt=null;
        int result=0;
        String sql=prop.getProperty("deleteMember");
        try {
            psmt=conn.prepareStatement(sql);
            psmt.setString(1,userId);
            psmt.setString(2,userPwd);
            result=psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCTemplet.close(psmt);
        }
        return result;

    }
    private Member resultToMember(ResultSet rset) throws SQLException {
        Member m=null;
        if(rset.next()) {
            m=new Member(
                    rset.getInt("USER_NO"),
                    rset.getString("USER_ID"),
                    rset.getString("USER_PWD"),
                    rset.getString("USER_NAME"),
                    rset.getString("PHONE"),
                    rset.getString("EMAIL"),
                    rset.getString("ADDRESS"),
                    rset.getString("INTEREST"),
                    rset.getDate("ENROLL_DATE"),
                    rset.getDate("MODIFY_DATE"),
                    rset.getString("STATUS")
            );
        }
        return m;
    }

}
