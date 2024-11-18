package day25.boardService9mvc.view;

import day25.boardService9mvc.controller.BoardController;
import day25.boardService9mvc.model.BoardDto;

import java.util.ArrayList;
import java.util.Scanner;

public class BoardView {
    private static BoardView boardView = new BoardView();
    private BoardView(){}
    public static BoardView getInstance(){
        return boardView;
    }

    Scanner scan = new Scanner(System.in); // 입력객체
    public void mainPage(){ // view 시작 함수
        while (true) {
            System.out.print("1.게시글 작성 2.게시글 출력 : ");
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
        //BoardController boardController = new BoardController();
        boolean result = BoardController.getInstance().boardWrite(content, writer, pwd); // 싱글톤
        //3.컨트롤러에 전달 후 결과를 받아 출력하기.
        if (result) {
            System.out.println("게시물 등록 성공");
        } else {
            System.out.println("게시물 등록 실패");
        }
    }
    //2.게시글 출력함수
    void boardPrint(){
        //1.컨트롤러에게 모든 게시물(Board)객체 정보를 요청한다.
            // 다른 클래스의 메소드를 호출하기 위해서는 객체가 필요하다.
        //BoardController boardController = new BoardController();
        //2.컨트롤러에게 전달받은 결과를 출력한다.
        ArrayList<BoardDto> result = BoardController.getInstance().boardPrint(); // 게시물 출력 함수를 호출 // 싱글톤
        //출력
        for (int index = 0; index <= result.size() - 1; index++){
            System.out.print("게시물 내용 : " + result.get(index).getContent());
            System.out.println("작성자 : " + result.get(index).getWriter());
        }//f end
    }//m end
    /*
        게시물 1개 = Board 객체 1개
        게시물 여러개 = Board[] 배열 또는 컬렉션 프레임워크 ArrayList<Board>
     */

}//cls end
