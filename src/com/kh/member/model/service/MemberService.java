package com.kh.member.model.service;

import com.kh.common.JDBCTemplet;
import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;

import java.sql.Connection;

public class MemberService {


    public Member loginMember(String userId, String userPwd){
        Connection conn= JDBCTemplet.getConnection();
        Member m=null;
        m=new MemberDao().login(userId,userPwd,conn);
        JDBCTemplet.close();
        return m;
    }

    /**
     * 회원가입용서비스
     * @param m => 회원가입할 정보를 담을 member객체
     * @return => 처리된 행의 갯수(int)
     */
    public int insertMember(Member m){
        int result=0;
        Connection conn=JDBCTemplet.getConnection();
        result=new MemberDao().insertMember(m,conn);
        if(result>0){
            JDBCTemplet.commit();
        }else{
            JDBCTemplet.rollback();
        }
        JDBCTemplet.close();
        return result;
    }
    public Member updateMember(Member m){
        Member updateMember=null;
        Connection conn=JDBCTemplet.getConnection();
        int result=new MemberDao().updateMember(m,conn);
        if(result>0){
            JDBCTemplet.commit();
            updateMember=new MemberDao().selectMember(m.getUserId(),conn);
        }else{
            JDBCTemplet.rollback();
        }
        return updateMember;
    }

    /**
     *
     * @param userId 수정할회원의아이디
     * @param userPwd 수정할 회원의 비밀번호
     * @param updatePwd 수정할 새로운 비밀번호
     * @return 수정된 회원의 갱신된 정보
     */
    public Member updatePwdMember(String userId,String userPwd,String updatePwd){
        Member updateMember=null;
        Connection conn=JDBCTemplet.getConnection();
        System.out.println(" "+userId+" "+userPwd+" "+updatePwd);
        int result=new MemberDao().updatePwdMember(userId,userPwd,updatePwd,conn);
        if(result>0){
            updateMember=new MemberDao().selectMember(userId,conn);
            JDBCTemplet.commit();
        }else{
            JDBCTemplet.rollback();
        }
        JDBCTemplet.close();
        return updateMember;
    }
}
