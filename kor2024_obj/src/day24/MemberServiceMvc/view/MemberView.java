package day24.MemberServiceMvc.view;

import MemberServiceMvc.controller.MemberController;

import java.util.Scanner;

public class MemberView {
    //싱글톤
    private static MemberView memberView = new MemberView();
    private MemberView(){};
    public static MemberView getInstance(){
        return memberView;
    }

    Scanner scan = new Scanner(System.in); // 입력 객체

    //회원가입 메소드
    void memberRegister(){
        System.out.print("id 를 입력하시오 : "); String id = scan.next();
        System.out.print("pwd 를 입력하시오 : "); String pwd = scan.next();
        boolean result = MemberController.getInstance().memberRegister(id, pwd);
        if (result) {
            System.out.println("회원 등록 성공");
        } else {
            System.out.println("회원 등록 실패");
        }
    }

    //로그인 메소드
    void memberLogin(){
        System.out.print("id 를 입력하시오 : "); String id = scan.next();
        System.out.print("pwd 를 입력하시오 : "); String pwd = scan.next();
        boolean result = MemberController.getInstance().memberLogin(id, pwd);
        if (result) {
            System.out.println("로그인 성공");
        } else {
            System.out.println("로그인 실패");
        }
    }

    //메인페이지
    public void mainPage(){
        while (true) {
            System.out.print("1.회원가입 2.로그인 : ");
            int choose = scan.nextInt();
            if (choose == 1){
                memberRegister();
            } else if (choose == 2) {
                memberLogin();
            }
        }//w end
    }
}//cls end
