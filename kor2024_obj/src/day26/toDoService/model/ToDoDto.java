package day26.toDoService.model;

public class ToDoDto {
    //필드
    private String content;
    private String deadLine;
    private boolean state;

    //생성자
    public ToDoDto(String content, String deadLine, boolean state) {
        this.content = content;
        this.deadLine = deadLine;
        this.state = state;
    }

    //메서드
    public String getContent() {return content;}
    public void setContent(String content) {this.content = content;}
    public String getDeadLine() {return deadLine;}
    public void setDeadLine(String deadLine) {this.deadLine = deadLine;}
    public boolean isState() {return state;}
    public void setState(boolean state) {this.state = state;}

    @Override
    public String toString() {
        return "ToDoDto{" +
                "content='" + content + '\'' +
                ", deadLine='" + deadLine + '\'' +
                ", state=" + state +
                '}';
    }
}//cls end
