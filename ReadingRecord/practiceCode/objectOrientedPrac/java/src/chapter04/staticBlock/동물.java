package chapter04.staticBlock;

public class 동물 {
    static {
        System.out.println("동물 클래스 레디 온");
    }

    public 동물() {
        System.out.println("동물 생성자 레디 온");
    }

}
