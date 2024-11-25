package day26.toDoService.view;

import day26.toDoService.Controller.ToDoController;
import day26.toDoService.model.ToDoDto;

import java.util.ArrayList;
import java.util.Scanner;

public class ToDoView {
    private static ToDoView toDoView = new ToDoView();
    private ToDoView(){}
    public static ToDoView getInstance(){
        return toDoView;
    }

    // 입력객체
    Scanner scan = new Scanner(System.in);

    // 등록 메소드
    void toDoWrite(){
        System.out.print("할 일 내용 : "); String content = scan.next();
        System.out.print("마감기한 : "); String deadLine  = scan.next();
        System.out.print("상태 (완료 : true, 미완료 : false): "); boolean state = scan.nextBoolean();
        boolean result = ToDoController.getInstance().toDoWrite(content,deadLine,state);
        if (result) {
            System.out.println("등록 성공");
        } else {
            System.out.println("등록 실패");
        }
    }

    // 출력 메소드
    void toDoPrint(){
        ArrayList<ToDoDto> result = ToDoController.getInstance().toDoPrint();
        for (int index = 0; index <= result.size() - 1; index++){
            String stateStr = result.get(index).isState() ? "완료" : "미완료";
            System.out.println("할 일 내용 : " + result.get(index).getContent());
            System.out.println("마감기한 : " + result.get(index).getDeadLine());
            System.out.println("상태 : " + stateStr);
        }
    }

    // 메인 페이지
    public void mainPage(){
        while (true) {
            System.out.print("1.ToDoList 등록 2. ToDoList 출력 : ");
            int choose = scan.nextInt();
            if (choose == 1){toDoWrite();}
            else if (choose==2) {toDoPrint();}
        }
    }
}
