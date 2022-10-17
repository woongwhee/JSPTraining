package com.kh.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCTemplet{
    private static Connection conn=null;
    private JDBCTemplet(){
    }
    //1. Connection 객체 생성(DB접속)한 후 해당 Connection을 반환하는 메소드
    public static Connection getConnection(){
        Properties prop=new Properties();
        String fileName=JDBCTemplet.class.getResource("/sql/driver/driver.properties").getPath();
        //getResource메소드의 맨처음 /는 classes폴더를 의미한다.
        try {
            prop.load(new FileInputStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            Class.forName(prop.getProperty("oracle"));
            if(conn==null){
                synchronized (JDBCTemplet.class){
                    if(conn==null){
                        conn= DriverManager.getConnection
                                (prop.getProperty("url"),prop.getProperty("username"),prop.getProperty("password"));
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    //2. 전달받은 Connection 객체를 가지고 Commit 해주는 메소드
    public static void commit(){
        try{
            if(conn!=null&&!conn.isClosed()){
                conn.commit();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }


    }

    //3. 전달받은 Connection 객체를 가지고 RollBack해주는 메소드
    public static void rollback(){
        try{
            if(conn!=null&&!conn.isClosed()){
                conn.rollback();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void rollback(Connection conn){
        try{
            if(conn!=null&&!conn.isClosed()){
                conn.rollback();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //4. 전달받은 Connection 객체를 가지고 반납시켜주는 메소드
    //5. 전달받은 Statement 객체를 받납시켜주 메소드
    //6. 전달받은 ResultSet 객체를 받납시켜주 메소드
    public static void close(){
        try {
            if(conn!=null&&!conn.isClosed()){
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        conn=null;
    }
    public static void close(ResultSet rset){
        try{
            if(rset!=null&&!rset.isClosed()){
                rset.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void close(Statement stat){
        try{
            if(stat!=null&&!stat.isClosed()){
                stat.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }


}
