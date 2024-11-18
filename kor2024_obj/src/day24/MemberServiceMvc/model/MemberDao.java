package day24.MemberServiceMvc.model;

import java.util.ArrayList;

public class MemberDao {
     //싱글톤
     private static MemberDao memberDao = new MemberDao();
     private MemberDao(){};
     public static MemberDao getInstance(){
         return memberDao;
     }

     //모든 회원정보를 담는 리스트 생성
     ArrayList<MemberDto> memberDB = new ArrayList<>();

     //회원가입 접근
     public boolean memberRegister(MemberDto memberDto){
        memberDB.add(memberDto);
        return true;
     }

     //로그인 접근
     public ArrayList<MemberDto> memberLogin(){
        return memberDB;
     }
}//cls end
