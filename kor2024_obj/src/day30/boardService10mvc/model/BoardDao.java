package day30.boardService10mvc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class BoardDao {

    // JDBC 인터페이스, import java.sql.
    private Connection conn; // 연동된 결과의 연동 객체를 조작할 인터페이스

    //싱글톤
    private static BoardDao boardDao = new BoardDao();

    // 생성자에서 DB연동 코드 작성
    private BoardDao(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb1125", "root", "1234");
            System.out.println("[BoardDao Connection ok]");
        }catch (ClassNotFoundException e){
            e.getMessage();
            System.out.println("[BoardDao Connection fail]");
            // 실패이유 1. 프로젝트 JDBC 라이브러리 등록 2. 오타(클래스 경로, DB서버 경로) 체크, 3. MYSQL 워크벤치에서 DB 존재 체크
        } catch (SQLException e){
            e.getMessage();
            System.out.println("[BoardDao Connection fail]");
        }


    }
    public static BoardDao getInstance(){ return boardDao; }

    // 여러개 게시물 저장하는 리스트
    ArrayList<BoardDto> boardDB = new ArrayList<>();

    //1.게시물 등록 접근함수
    public boolean boardWrite(BoardDto boardDto){
        try {
            // 1. SQL 작성 , SQL 그대로 작성하되 데이터가 들어가는 자리는 ?, ? ,? 로 작성
            String sql = "insert into board(content, writer, pwd) values ( ?, ?, ? )";
            // 2. 작성한 SQL 를 DB 연동객체에 기재한다. prepare : 준비하다, statement : 기재하다. => SQL 기재할 준비
            // - 연동된 객체로부터 SQL 기재해서 준비된 개체를 PreparedStatement 인터페이스에 대입한다.
            PreparedStatement ps = conn.prepareStatement(sql);
            // 3. 기재된 SQL 의 매개변수(?)에 값을 대입한다.
            // ps.setString( ?순서번호, 대입할 데이터 ) : ?에 대입할 데이터가 String 타입일 때 사용
            // ps.setInt( ?순서번호, 대입할 데이터 ) : ?에 대입할 데이터가 int 타입일 때 사용
            ps.setString(1, boardDto.getContent()); // 1: SQL 내 첫 번째 ? 뜻한다 : 첫번째 ?에 입력받은 게시물을 대입한다.
            ps.setString(2, boardDto.getWriter()); // 2: SQL 내 두 번째 ? 뜻한다 : 두번째 ?에 입력받은 작성자를 대입한다.
            ps.setInt(3, boardDto.getPwd()); // 3: SQL 내 세 번째 ? 뜻한다 : 세 번째 ?에 입력받은 게시물 비밀번호를 대입한다.
            // 4. 기재된 SQL 를 실행한다. execute : 실행하다. Update : 최신화하다 => SQL 실행 후 최신화한다.
            ps.executeUpdate();
            // 5. 성공했을 때 true 반환
            return true;
        }catch (SQLException e){
            e.getMessage();
            System.out.println("[게시물 등록시 예외 발생]");
        }
        // 5. 실패 또는 오류 발생시 false 반환
        return false;
    }

    //2.게시물 출력 접근 함수
    public ArrayList<BoardDto> boardPrint() {
        return boardDB;
    }
}
