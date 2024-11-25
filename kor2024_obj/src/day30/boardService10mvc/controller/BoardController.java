package day30.boardService10mvc.controller;

import day30.boardService10mvc.model.BoardDao;
import day30.boardService10mvc.model.BoardDto;

import java.util.ArrayList;

public class BoardController {
    // 싱글톤
    private static BoardController boardController = new BoardController();
    private BoardController(){}
    public static BoardController getInstance(){
        return boardController;
    }

    //1.게시물 등록 제어 함수
    public boolean boardWrite(String content, String writer, int pwd){
        //1.다양한 데이터 검사=유효성 검사, 오늘은 생략
        //2.유효성 검사 통화했으면 서로다른 데이터를 하나(객체)로 만들기
        BoardDto boardDto = new BoardDto(content, writer, pwd);
        //3.입력받은 객체를 저장하기 위해 Dao 전달하고 결과를 응답받기
        //BoardDao boardDao = new BoardDao();
        return BoardDao.getInstance().boardWrite(boardDto); // 싱글톤
    }
    //2.게시물 출력 제어 함수
    public ArrayList<BoardDto> boardPrint(){
        // -void 대신 여러개의 게시물을 반환받고 싶어서
        // void --> ArrayList<>
        // * dao 에게 게시물 전체 출력 함수를 호출해서 결과를 받는다.
        // 1. BoardDao 의 메소드를 호출하기 위해 객체 샏성
        //BoardDao boardDao = new BoardDao(); //
        // 2. dao 에게 메소드를 호출하고 응답(모든 게시물) 받기
        ArrayList<BoardDto> result = BoardDao.getInstance().boardPrint(); // 싱글톤
        // 3. dao 에게 응답받은 결과를 view 응답하기
        return result; // - return 현재 메소드가 종료되면서 반환되는 값
    }
}
