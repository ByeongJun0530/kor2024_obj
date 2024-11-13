package Asg1;

import java.util.Scanner;

public class BoardService2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] content = new String[100];
        String[] writer = new String[100];
        while (true) {
            System.out.println("1.게시물 쓰기 2.게시물 출력 선택 : ");
            int choose = scan.nextInt();
            if (choose == 1) {
                System.out.print("새로운 게시물 내용 : "); String cont = scan.next();
                System.out.print("새로운 게시물 작성자 : "); String writ = scan.next();
                /**
                 *  boolean save = false; 를 나타내는 이유
                 *  "게시물이 저장되지 않았다"는 초기 상태를 나타내고, 게시물이 저장되면
                 *  true 로 변경되어 게시물 저장의 성공 여부를 추적하는 역할
                 */
                boolean save = false;
                for (int index = 0; index <= content.length -1; index++) {
                    if (content[index] == null) {
                        content[index] = cont;
                        writer[index] = writ;
                        save = true;
                        break;
                    } // if end
                } // for end
                if (save) {
                    /**
                     * (save == true)를 사용하지 않는 이유
                     * 1. 불리언 값은 이미 참(true) 또는 거짓(false)만 가질 수 있기 때문에
                     * 2. 불리언 값은 조건문에서 직접 사용할 수 있다
                     * 3. 코드 간결화 및 가독성 향상
                     * 4. 불리언의 반대 조건 - save == false 대신 !save를 사용하는 것이 더 자연스럽고 가독성이 좋다.
                     */
                    System.out.println("게시물 쓰기 성공!");
                } // if end
                else {
                    System.out.println("게시물 쓰기 실패! 빈 공간이 없습니다.");
                } // else end
            } // if end
            if (choose == 2) {
                for (int index = 0; index <= content.length - 1; index++) {
                    if (content[index] != null) {
                        System.out.printf("내용 : %s , 작성자 : %s\n", content[index], writer[index]);
                    } // if end
                } // for end
            } // if end
        } // w end
    } // m end
} // cls end
