/**
 * BoardService6
 *  - BoardService5 모든 코드를 복사 후 진행
 *  - 추가 조건 : NewBoard 클래스 객체들을 캡슐화(접근제한자) 하시오.
 *      - 1. Board는 앞전 Board 와 동일하게 내용[String타입] 과 작성자[String타입] 과 비밀번호[int타입] 로 구성된 게시물 클래스를
 *      - 2. Board의 모든 필드(멤버변수)는 private로 선언한다.
 *      - 3. 외부에서 객체 생성시 생성자를 사용한다.
 *      - 4. getter(필드 값 호출 함수), setter(필드값 대입 함수), toString(객체 정보 함수) 함수들을 이용하여 모든 필드의 데이터를 간접 접근한다.
 *
 *      public : 공개용, 모든 클래스/패키지 내에서 접근 가능
 *      private : 비공개용, 현재 클래스 내에서 접근 가능
 *          - 이유 : 객체의 자료는 중요하기 때문에 쉽게 저장/변경 되거나 하면 안된다. ( 유효성 검사 - 1.원하는 데이터인지 2.안전한 데이터인지)
 *          - 객체를 통해 필드 직접 접근을 차단하고 간접 접근을 이용한 유효성 검사를 시행한다.
 *      protected : 동일한 패키지 내에서만 접근 가능, 상속관계이면 다른 패키지에서도 접근 가능하다.
 *      (default) : 동일한 패키지 내에서 접근 가능, 위에 3가지를 작성 안했을 때 기본적으로 적용되는 접근 제한자
 */

package day23;

import java.util.ArrayList;
import java.util.Scanner;

public class BoardService6 {
    public static void main(String[] args) {

        // 1. toString 오버라이딩 하기 전
        //Board b1 = new Board();
        //System.out.println( b1.toString() ); // 객체의 (힙 영역) 주소값

        // 2. toString() 오버라이딩 했을 때, toString() 함수는 생략하능
        Board b1 = new Board();
        System.out.println(b1.toString()); // 객체의 (힙영역) 필드값

        // - 구현 BoardService5
        Scanner scan = new Scanner(System.in);
        ArrayList<Board> boardList = new ArrayList<>();
        while (true) {
            System.out.print("1. 글쓰기 2. 글 출력 : ");
            int choose = scan.nextInt();
            if (choose == 1) {
                System.out.print("내용 : "); String content = scan.nextLine();
                System.out.print("작성자 : "); String writer = scan.next();
                System.out.print("비밀번호 : "); int pwd = scan.nextInt();
                Board board = new Board(content, writer, pwd);
                boardList.add( board );
            } else if (choose == 2) {
                for (int index = 0; index <= boardList.size() - 1; index++) { // 게시물이 존재하면
                    System.out.printf("내용 : %s, 작성자 : %s, 비밀번호 : %d\n",
                            boardList.get(index).getContent(),
                            boardList.get(index).getWriter(),
                            boardList.get(index).getPwd()
                    );
                } // f end
            }
        } // w end
    }//m end
}//cls end
