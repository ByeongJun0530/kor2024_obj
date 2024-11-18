package day24.MemberServiceMvc.controller;

import day24.MemberServiceMvc.model.MemberDao;
import day24.MemberServiceMvc.model.MemberDto;

import java.util.ArrayList;

public class MemberController {
    //싱글톤
    private static MemberController memberController = new MemberController();
    public MemberController(){}
    public static MemberController getInstance(){
        return memberController;
    }

    //회원 등록 접근
    public boolean memberRegister(String id, String pwd){
        MemberDto memberDto = new MemberDto(id, pwd);
        return MemberDao.getInstance().memberRegister(memberDto);
    }

    //로그인 접근
    public boolean memberLogin(String id, String pwd){
        ArrayList<MemberDto> members = MemberDao.getInstance().memberLogin();
        for (MemberDto member : members) {
            if (member.getId().equals(id) && member.getPwd().equals(pwd)) {
                return true;
            }
        }
        return false;
    }
}//cls end
