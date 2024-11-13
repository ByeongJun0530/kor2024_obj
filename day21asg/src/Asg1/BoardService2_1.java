package Asg1;

import java.util.Scanner;

public class BoardService2_1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] content = new String[100];
        String[] writer = new String[100];

        while (true) {
            System.out.println("1.게시물 쓰기 2.게시물 출력 선택: ");
            int choose = scan.nextInt();
            if (choose == 1) {
                System.out.print("새로운 게시물 내용 : "); String cont = scan.next();
                System.out.print("새로운 게시물 작성자 : "); String writ = scan.next();

                boolean save = false;
                for (int index = 0; index <= content.length - 1; index++) {
                    if (content[index] == null) {
                        content[index] = cont; writer[index] = writ;
                        save = true;
                        break;
                    } // if end
                } // for end
                if (save) {
                    System.out.println("게시물 쓰기 성공!");
                } else {
                    System.out.println("게시물 쓰기 실패! 빈 공간이 없습니다.");
                } // else end
            } // if end
            else if (choose == 2) {
                for (int index = 0; index <= content.length - 1; index++) {
                    if (content[index] != null) {
                        System.out.printf("내용 : %s, 작성자 : %s\n", content[index], writer[index]);
                    } // if end
                } // for end
            } // else if end
        } // w end
    } // m end
} // cls end
