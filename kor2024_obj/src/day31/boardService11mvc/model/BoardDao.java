package day31.boardService11mvc.model;

import java.sql.*;
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
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb1126", "root", "1234");
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

    //2. 전체 게시물 출력 접근 함수
    public ArrayList<BoardDto> boardPrint() {
        ArrayList<BoardDto> list = new ArrayList<>(); // -- 조회된 레코드들을 객체화 해서 저장할 리스트객체
        try {
            // 1. SQL 작성
            String sql = "select* from board";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql); // 기재할 SQL 준비
            // 3. SQL 조작
            // 4. SQL 실행 , executeQuery() : sql 실행 결과 조회된 SQL 결과 조작하는  resultSet 객체 반환
            ResultSet rs = ps.executeQuery();
            // 5. SQL 결과
            while (rs.next()){// while(조건){} : 반복문, rs.next() : 조회 결과에서 다음 레코드로 이동 함수,
                // 만약에 결과 레코드가 3개 존재하면 rs.next() 3번 실행 된다.
                // [해석] 조회 결과 첫 번째 레코드부터 마지막 레코드까지 하나씩 레코드 이동)
                // 6. 각 레코드를 읽어서 각 필드별 데이터를 호출 , rs.gerXXX("필드명") : 지정한 필드명의 값을 타입에 맞게 값 반환
                int num = rs.getInt("num");               // 현재 조회중인 레코드의 게시물 번호(num) 값 호출
                String content = rs.getString("content");        // 현재 조회중인 레코드의 게시물 내용(content) 값 호출
                String writer = rs.getString("writer");         // 현재 조회중인 레코드의 게시물 작성자(writer) 값 호출
                // int pwd = rs.getInt("pwd");               // 현재 조회중인 레코드의 게시물 비밀번호(pwd) 값 호출

                // 7. 각 레코드의 호출된 필드값들을 객체화 --> DTO 생성
                BoardDto boardDto = new BoardDto(num, content, writer );

                // 8. 1개의 레코드를 dto 객체로 변환 dto 를 리스트에 저장
                list.add(boardDto);
            }// w end // - 반복문 1번 실행에 레코드 1개를 dto 로 변환
        } catch (SQLException e) {
            e.getMessage();
            System.out.println("[게시물 출력시 예외 발생]");
        }
            // 9. 구성한 리스트 객체를 반환
            return list;
    }

    // 3. 게시물 삭제 함수
    public boolean boardDelete(int deleteNum){
        try {
            //1. SQL 작성
            String sql = "delete from board where num = ?";
            //2. SQL 기재 준비
            PreparedStatement ps = conn.prepareStatement(sql);
            //3. 기재된 SQL 조작, 기재된 SQL 매개변수 대입
            ps.setInt(1, deleteNum); // 기재된 sql 내 첫번째 ? 'deleteNum' 삭제할 게시물번호 대입한다.
            //4. 기재된 SQL 실행, //5. 실행결과
            int result = ps.executeUpdate(); // delete 실행 후 delete 레코드 갯수를 반환, result(삭제된 레코드 수)
            // 6. 메소드 반환
            if (result == 1) {
                return true; // 삭제 성공
            }
        } catch (SQLException e) {e.getMessage();}
            return false; // 삭제 실패 : sql 오류 또는 삭제할 번호가 없을 때 오류
    }//m end

    // 4. 게시물 수정 dao 함수
    public boolean boardUpdate(BoardDto updateDto) {
        try {
            // 1. sql 작성
            String sql = "update board set content = ? where num = ?";
            // 2. sql 기재 준비get
            PreparedStatement ps = conn.prepareStatement(sql);
            // 3. 기재된 sql 조작
            ps.setString(1, updateDto.getContent());
            ps.setInt(2, updateDto.getNum());
            // 4. 기재된 sql 실행, // 5. 실행결과
            int result = ps.executeUpdate(); // 수정 sql 결과 변화가 있는 레코드 수 반환
            // 6. 메소드 반환
            if (result == 1) {
                return true;
            } // 만약에 레코드 변화가 1개 있으면 수정 성공
        }catch (SQLException e){e.getMessage();}
        return false;
    }//m end
}//cls end









