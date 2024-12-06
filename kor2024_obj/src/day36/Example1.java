package day36;

public class Example1 {//cls start

    int 멤버변수 = 40;
    static int 전역변수 = 20;

    public static void main(String[] args) {



        // [1] 지역 변수
        if(10 > 3){ // if 지역 =========================
            int 지역변수 = 10;
        } // ==========================================

        //System.out.println(지역변수); // 오류발생
        System.out.println(전역변수); // 오류없음
        임의함수(3); // 3 : 인자/인수 값
            // int 매개변수1 = 3;
        임의함수(10);
            // int 매개변수1 = 10;
        //System.out.println(멤버변수); // 오류발생
        Example1 객체 = new Example1();
        System.out.println(객체.멤버변수); // 객체를 이용한 멤버변수 호출/접근

        Example1 변수 = new Example1(); // 변수가 가지고 있는 자료는 몇개일까요? 1개

    }//m end

    // 임의함수
    public static void 임의함수(int 매개변수1){

    }
}//cls end
