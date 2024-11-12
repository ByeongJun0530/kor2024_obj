/*
    BoardService1
        - 내용 과 작성자로 구성된 게시물을 최대 100개 까지 저장하는 서비스 구축
        - 조건 : main 1개 와 배열은 최대 2개까지 사용해서 구현
        - 구축 : 게시물 쓰기와 게시물 출력 기능 구현
 */
package day21;

import java.util.Arrays;
import java.util.Scanner;

public class BoardService2 { // cls start
    public static void main(String[] args) { // main start
        Scanner scan = new Scanner(System.in);

        // BoardService1 에서 변수를 사용했는데 배열 변수를 사용하는 이유
            // - 여러개의 변수에 있는 데이터를 배열로 사용하면 관리가 편하다.
        // 배열이란? 여러개의 동일한 타입의 데이터들을 하나의 변수에 저장할 수 있는 타입
        // 인덱스란? 배열 내 저장된 데이터들의 저장 순서 번호, 0 ~ 최대길이
            // 반복문 활용 : 시작값 부터 끝 값까지 반복
        String[] content = new String[100]; // 배열 선언 방법 : 타입[] 변수명 = new 타입[갯수];
        String[] writer = new String[100]; // String 데이터 100개를 저장할 수 있는 배열 선언

        while (true) { // while start
            System.out.print("1.게시물 쓰기 2.게시물 출력 선택 : ");
            int choose = scan.nextInt();
            if (choose == 1) {
                System.out.print("새로운 게시물 제목 : ");
                String cont = scan.next();
                System.out.print("새로운 게시물 작성자 : ");
                String writ = scan.next();
                // 만약에 게시물이 비어 있으면, 게시물이 100개이면 if 100개 검사?? X
                // 인덱스 0 부터 마지막 인덱스 99까지 1씩 증가
                // - 스위치 변수 = 상태를 저장하는 변수
                boolean save = false; // 처음에는 false 해서 저장 실패 했다는 뜻
                for (int index = 0; index <= content.length - 1; index++) {
                    if (content[index] == null) { // 만약에 index 번째 게시물이 비어 있으면
                        content[index] = cont;
                        writer[index] = writ; // 비어있는 게시물에 입력받은 내용물 저장
                        save = true; // 만약에 저장 성공했으면 save 변수에 ture 값으로 변경
                        break; // 만약에 저장을 했으면 1개만 저장해야 하므로 break 해서 반복문 종료.
                    } // if end
                } // for end
                // for 종료 후에 save 변수값이 false 이면 출력
                if (save) { System.out.println("게시물 쓰기 성공");}
                else {System.out.println("게시물 쓰기 실패 : 빈 공간이 없습니다.");}
            } // if end

            if (choose == 2){
                for (int index = 0; index <= content.length - 1; index++ ){ // 0 ~ 99 까지
                    if (content[index] != null){ // 만약에 index 번째 게시물이 존재하면
                        System.out.printf("작성자 : %s, 내용 : %s\n", writer[index], content[index]);
                    } // if end
                } // for end
            } // if end
        } // while end
    } // main end
 } // cls end
