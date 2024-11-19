package day26.toDoService.model;

import java.io.*;
import java.util.ArrayList;

public class ToDoDao {
    private static ToDoDao toDoDao = new ToDoDao();
    private ToDoDao(){
        File file = new File("./src/day26/toDoService/toDo.txt");
        if (file.exists()){
            toDoLoad();
        } else {
            try {
                file.createNewFile();
            } catch (FileNotFoundException e){e.printStackTrace();}
            catch (IOException e){e.printStackTrace();}
        }
    }
    public static ToDoDao getInstance(){
        return toDoDao;
    }

    ArrayList<ToDoDto> todoDB = new ArrayList<>();

    // 할 일 등록 접근
    public boolean toDoWrite(ToDoDto toDoDto){
        todoDB.add(toDoDto);
        toDoSave();
        return true;
    }
    // 할 일 출력 접근
    public ArrayList<ToDoDto> toDoPrint(){
        return todoDB;
    }

    // 영구 저장 영역
    // 저장
    public void toDoSave(){
        String outStr = "";
        for (int index = 0; index <= todoDB.size() - 1; index++){
            ToDoDto toDoDto = todoDB.get(index);
            outStr += toDoDto.getContent() + "," + toDoDto.getDeadLine() + "," + toDoDto.isState();
            outStr += "\n";
        }
        System.out.println(outStr);

        try {
            FileOutputStream outputStream = new FileOutputStream("./src/day26/toDoService/todo.txt");
            outputStream.write(outStr.getBytes());
            System.out.println("[저장 성공]");
        } catch (FileNotFoundException e){e.printStackTrace();}
        catch (IOException e){e.printStackTrace();}
    }

    // 호출
    public void toDoLoad(){
        try {
            FileInputStream inputStream = new FileInputStream("./src/day26/toDoService/todo.txt");
            File file = new File("./src/day26/toDoService/todo.txt");
            byte[] bytes = new byte[(int) file.length()];
            inputStream.read(bytes);
            String inStr = new String(bytes);
            String[] objStr = inStr.split("\n");

            for (int i = 0; i <= objStr.length - 1; i++) {
                String obj = objStr[i];
                String[] field = obj.split(",");
                String content = field[0];
                String deadLine = field[1];
                boolean state = Boolean.parseBoolean(field[2]);
                ToDoDto toDoDto = new ToDoDto(content, deadLine, state);
                todoDB.add(toDoDto);
            }
        }catch (FileNotFoundException e){e.printStackTrace();}
        catch (IOException e){e.printStackTrace();}
    }
}
