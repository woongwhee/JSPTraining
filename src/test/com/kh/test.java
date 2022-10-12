package test.com.kh;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

public class test {
    public static void main(String[] args) {
        Member m= new Member("ㄴㅇㅁㄹ","김김김","010-3333-3333","dndsndn@gmail.com","서울시 목동동","운동,등산");
        System.out.println(new MemberService().updateMember(m));
    }

}
