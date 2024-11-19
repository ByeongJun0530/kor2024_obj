package day25.boardService9mvc.model;

import java.io.*;
import java.util.ArrayList;

public class BoardDao {
    /*
        boardService9mvc
            - boardService8mvc 패키지를 복사해서 boardService9mvc 패키지명 변경 후 진행
            - 조건 : 파일 입출력을 활용하여 프로그래밍 종료되고 다시 실행 했을 때 영구 저장 되도록 하시오.

            [생각해보기]
            1. txt 메모장은 문자열만 저장되는 윈도우 프로그램의 확장자 , txt(객체지향 x)
            2. 게시물들(ArrayList<BoardDto>) 저장을 하기 위해서는 변환이 필요하다. , java(객체지향 o)
            문제점 발견 : 서로 다른 언어/프로그램 들 간의 데이터를 주고 받으려면 형식/타입이 같으면 좋을텐데.
            - 관례적으로 사용되는 타입 : CSV, JSON, XML 파일 타입 주로 사용한다.
            --> 서로 다른 언어/프로그램 들 간의 공통된 형식/타입을 사용하므로 데이터 통신 목적!

            [CSV]
            1. 객체 내 쉼표로 구분된 문자열로 구성된 파일 형식
            2. 객체간의 구분 \n 백슬래시 + n 이용한 줄바꿈 처리를 한다.
            2. .csv 확장자 가진다.

            [게시물 객체 CSV 형식으로 변환]
            1. boardDto( content = "안녕하세요", writer = "유재석", pwd = 1234) : 자바 객체를 임의로 시각화
            2. 하나의 문자열로 필드명을 제외한 필드값 들을, 쉼표로 구분하여 문자열로 반환 : "안녕하세요, 유재석, 1234"

            [여러개 게시물 객체가 존재 했을 때]
            point1 : 객체 내 필드간의 구분을 ,쉼표로 한다,
            point2 : 객체 간의 구분을 \n으로 한다.
                예] "안녕하세요, 유재석, 1234\n그래 안녕, 강호동, 4567"
     */

    private static BoardDao boardDao = new BoardDao();
    private BoardDao(){
        // 만약에 파일을 로드 하는데.. 파일이 존재하지 않으면?
            //[1] 파일 경로에 따른 파일 객체화
        File file = new File("./src/day25/boardService9mvc/data.txt");
            //[2] 객체화한 파일이 존재하는지 확인
        if (file.exists()){ // - 지정한 경로에 파일이 있다.
            // - 싱글톤(static) 이 생성될 때 ( 프로그램이 실행 될 때 )
            fileLoad(); // 파일 로드
        } else { // - 지정한 경로의 파일이 없다?
            // .createNewFile() : 파일 생성
            try {
                file.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        } //else end
    }//생성자 end
    public static BoardDao getInstance(){
        return boardDao;
    }

    ArrayList<BoardDto> boardDB = new ArrayList<>();


    public boolean boardWrite(BoardDto boardDto){
        boardDB.add(boardDto);
        // 만약에 리스트에 게시물 객체를 추가했다면, 파일에도 추가된 게시물을 쓰기
        fileSave();
        return true;
    }

    public ArrayList<BoardDto> boardPrint() {
        return boardDB;
    }

    // =================== 영구저장을 위한 게시물들을 파일에 저장 기능 ==================== //
    public void fileSave(){ // (게시물 등록을 성공했을 때 지정한 함수 사용/호출)
        // 여러개의 게시물들을  [ArrayList<BoardDto>] boardDB 하나의 문자열[String/CSV]로 표현하는 방법
            String outStr = ""; // 1.임의의 문자열 변수 선언
            // 2. 반복문을 이용한 모든 게시물들을 순회/반복
            for (int index = 0; index <= boardDB.size() -1; index++){// - 리스트 객체 내 0번 인덱스부터 마지막 인덱스까지 반복
                BoardDto boardDto = boardDB.get(index);// 3. index 번째의 게시물
                // 4. index 번째의 게시물 객체 내 필드값을 CSV 형식으로 변환, 필드값 출력 후 사이사이에 ,쉼표 이용한 CSV 형식 만들기
                outStr += boardDto.getContent()+","+boardDto.getWriter()+","+boardDto.getPwd();
                // += 복합대입 연산자 : 오른쪽 값을 왼쪽 변수 값과 더한 후 결과를 왼쪽 변수에 대입한다.
                outStr += "\n"; // 5. [객체 구분]
            } System.out.println(outStr); // 확인용
        // - try{}catch(){} : try{} 예상치 못한 예외가 발생했을 때 지정된 catch 코드로 흐름을 이동하는 문법
        try {
            // [1] 파일 출력 객체를 생성한다.
            FileOutputStream outputStream = new FileOutputStream("./src/day25/boardService9mvc/data.txt");
            // [2] 파일 출력 객체를 이용한 바이트 쓰기/내보내기, 여러개 게시물을 하나의 문자열로 표현하는 방법
            outputStream.write( outStr.getBytes() ); // 위에서 객체들을 모두 CSV 형식으로 변환된 문자열을 바이트로 변환
            // [3] 안내 메시지
            System.out.println("[파일 저장 성공]");

        }catch (FileNotFoundException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}
    }
    // =================== 영구 저장된 파일의 게시물들을 가져오는 기능 ==================== //
    public void fileLoad(){ // (프로그래밍이 실행되었을 때 1번만 실행) // DAO 객체(싱글톤) 생성 될 때 메모장(txt) 불러오기를 한다.

        try {
            // [1] 파일 입력 객체 생성
            FileInputStream inputStream = new FileInputStream("./src/day25/boardService9mvc/data.txt");
            // [2] 파일 용량만큼 바이트 배열 선언
            File file = new File("./src/day25/boardService9mvc/data.txt");
            byte[] bytes = new byte[(int) file.length()];
            // [3] 파일 입력 객체를 이용한 파일 읽어서 바이트 배열 저장
            inputStream.read(bytes);
            // [4] 읽어온 바이트 배열을 문자열(String)으로 변환
            String inStr = new String(bytes);
            // 활용과제 : 파일로부터 읽어온 문자열의 게시물 정보들을 다시 ArrayList<BoardDto> boardDB 에 저장하시오.
            // 목표 : 파일로 가져온 문자열 내 저장된 여러개의 게시물들을 객체화하고 게시물 객체를 리스트에 담자.
                // "안녕하세요, 유재석, 1234\n그래 안녕, 강호동, 4567"
            // [1] 객체 구분 문자(\n 임의로), 문자열 분해 , "문자열".split("기준문자") : 문자열 내 기준문자 기준으로 분해 후 배열로 반환
                // inStr = "안녕하세요, 유재석, 1234\n그래 안녕, 강호동, 4567"
                // inStr.split("\n") -> ["안녕하세요, 유재석,1234","그래 안녕,강호동,4567"]
                String[] objStr = inStr.split("\n");
            // [2] 객체 내 필드 구분 문자(, : 쉼표)
            for(int i = 0; i <= objStr.length - 1; i++){ // 마지막 줄 제외를 하기 위해 -1
                // [3] 1개의 객체 필드 값들 가져오기
                String obj = objStr[i];
                // objStr = ["안녕하세요, 유재석,1234","그래 안녕,강호동,4567"]
                // objStr[0] = "안녕하세요, 유재석, 1234"
                // objStr[0].split(",") --> ["안녕하세요", "유재석", "1234"]
                // field = ["안녕하세요", "유재석", "1234]"
                // field[0] = "안녕하세요"
                // [4] 문자열로 된 필드값들을 객체로 가져오기
                String[] field = obj.split(",");
                String content = field[0];
                String writer = field[1];
                int pwd = Integer.parseInt(field[2]); // String 타입을 int 으로 변환하는 방법, Integer.parseInt("문자열")
                BoardDto boardDto = new BoardDto(content, writer, pwd);
                // [5] 리스트에 담기
                boardDB.add(boardDto);
            }

        }catch (FileNotFoundException e){e.printStackTrace();}
        catch (IOException e){e.printStackTrace();}

    }
}
