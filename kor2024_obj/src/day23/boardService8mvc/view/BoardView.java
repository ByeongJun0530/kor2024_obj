package day23.boardService8mvc.view;

import day23.Board;
import day23.boardService8mvc.controller.BoardController;

import java.util.Scanner;

public class BoardView {
    Scanner scan = new Scanner(System.in); // 입력객체
    public void mainPage(){ // view 시작 함수
        while (true) {
            System.out.println("1.게시글 작성 2.게시글 출력 : ");
            int choose = scan.nextInt();
            if (choose == 1) {boardWrite();}
            else if (choose == 2) {boardPrint();}

        }//w end
    }//m end

    // view가 해야하는 역할 : 입력과 출력만 한다. (관례적인 약속)
    //1.게시글 등록 함수
    void boardWrite(){
        //1.입력
        scan.nextLine();
        System.out.print("내용 : "); String content = scan.nextLine();
        System.out.print("작성자 : "); String writer = scan.next();
        System.out.print("비밀번호 : "); int pwd = scan.nextInt();
        //2.입력받은 값을 컨트롤러에 전달
        BoardController boardController = new BoardController();
        boolean result = boardController.boardWrite(content, writer, pwd);
        //3.컨트롤러에 전달 후 결과를 받아 출력하기.
        if (result) {
            System.out.println("게시물 등록 성공");
        } else {
            System.out.println("게시물 등록 실패");
        }
    }
    //2.게시글 출력함수
    void boardPrint(){
        //1.컨트롤러에게 모든 게시물 정보를 요청한다.
        //2.컨트롤러에게 전달받은 결과를 출력한다.
    }

}//cls end
