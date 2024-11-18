package day24.MemberServiceMvc.model;

public class MemberDto {
    //필드
    private String id;
    private String pwd;

    //생성자
    public MemberDto(String id, String pwd){
        this.id = id;
        this.pwd = pwd;
    }

    //메소드
    public String getId() {return id;}
    public void setId(String id) {this.id = id;}
    public String getPwd() {return pwd;}
    public void setPwd(String pwd) {this.pwd = pwd;}

    @Override
    public String toString() {
        return "MemberDto{" +
                "id='" + id + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}//cls end
