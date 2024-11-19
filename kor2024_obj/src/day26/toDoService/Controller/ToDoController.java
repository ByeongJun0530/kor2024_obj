package day26.toDoService.Controller;

import day26.toDoService.model.ToDoDao;
import day26.toDoService.model.ToDoDto;

import java.util.ArrayList;

public class ToDoController {
    private static ToDoController toDoController = new ToDoController();
    private ToDoController(){}
    public static ToDoController getInstance(){
        return toDoController;
    }

    // 할 일 등록 제어
    public boolean toDoWrite(String content, String deadLine, boolean state){
        ToDoDto toDoDto = new ToDoDto(content, deadLine, state);
        return ToDoDao.getInstance().toDoWrite(toDoDto);
    }

    // 할 일 출력 제어
    public ArrayList<ToDoDto> toDoPrint(){
        ArrayList<ToDoDto> result = ToDoDao.getInstance().toDoPrint();
        return result;
    }
}
