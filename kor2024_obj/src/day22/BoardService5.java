/**
 * BoardService 5
 *  - BoardService4 모든 코드를 복사 후 진행
 *  - 기존코드 : 가변길이 구현
 *  - 수정조건 : 가변길이 배열 대신 ArrayList 컬렉션 프레임으로 수정하시오
 *
 *  - 컬렉션(수집) 프레임워크(미리 만들어진 구조) : (데이터)수집 관련 클래스들
 *      - 여러개의 데이터들을 하나의 객체에서 관리하기 위해서
 *          --> 배열은 고정길이라 가변길이로 구현하는 방법은  복잡하다.
 *          --> 자바 회사에서 배열을 이용한 가변길이의 배열 클래스를 이용하여 편리성과 기능을 제공한다
 *      - 대표 인터페이스 : List, Set, Map 컬렉션
 *      - ArrayList 클래스
 *          대표 함수
 *              .add( 새로운 객체 ) : 리스트 내 지정한 객체 저장
 *              .get( 인덱스 ) : 리스트 내 지정한 인덱스에 객체 반환 함수
 *              .size() : 리스트 내 저장한 총 객체 수 반환 함수
 *              .remove(인덱스) : 리스트 내 지정한 인덱스의 객체 삭제하는 함수
 */
package day22;

import java.util.ArrayList;
import java.util.Scanner;

public class BoardService5 {
    public static void main(String[] args) {
        // -입력 객체
        Scanner scan = new Scanner(System.in);
        // - 컬렉션 프레임워크 중 ArrayList 클래스를 이용한 배열타입 대체한다. (개발자에게 편의성 제공)
        // 배열(고정길이) vs 컬렉션 프레임워크(가변길이)
        ArrayList<Board> boardList = new ArrayList<>();
        // ArrayList<제네릭타입> : 리스트 객체에 저장할 여러개 데이터들의 타입

        while (true) {
            System.out.print("1. 글쓰기 2. 글 출력 : ");
            int choose = scan.nextInt();
            if (choose == 1) {
                // [1] 사용자로부터 저장할 데이터 입력 받는다.
                scan.nextLine(); // 의미없는 nextLine() 코드 작성 -> line.40
                System.out.print("내용 : "); String content = scan.nextLine();
                // .next() 문자열 입력, .nextLine() 문자열(공백/띄어쓰기 포함) 입력
                // .nextLine() 사용시 주의할 점 : .nextLine() 앞에 또 다른 .nextXX() 존재하면 의미없는 .nextLine()을 작성해준다.
                System.out.print("작성자 : "); String writer = scan.next();
                System.out.print("비밀번호 : "); int pwd = scan.nextInt();
                // [2] 입력받은 데이터로 게시물 객체 생성
                Board board = new Board(); // - 게시물 객체 생성
                board.content = content; board.writer = writer; board.pwd = pwd;
                // [3] 컬렉션 프레임워크인 리스트 객체에 게시물을 저장한다.
                boardList.add(board);
            } else if (choose == 2) {
                // 배열 내 존재하는 게시물 모두 출력하기
                for (int index = 0; index <= boardList.size() - 1; index++) { // 게시물이 존재하면
                        System.out.printf("내용 : %s, 작성자 : %s, 비밀번호 : %d\n",
                                boardList.get(index).content, boardList.get(index).writer, boardList.get(index).pwd);
                } // f end
            }
        } // w end
    } // m end
} // cls end
