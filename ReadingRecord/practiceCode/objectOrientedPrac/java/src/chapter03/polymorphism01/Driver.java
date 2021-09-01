package chapter03.polymorphism01;

public class Driver {
    public static void main(String[] args) {
        Penguin pororo = new Penguin();

        pororo.name = "뽀로로";
        pororo.habitat = "남극";

        pororo.showName();  // 제 이름은 알아서 뭐하시게요?
        pororo.showName("초보람보");    // 초보람보 안녕, 나는 뽀로로라고 해
        pororo.showHabitat();   // 뽀로로는 남극에 살아

        Animal pingu = new Penguin();

        pingu.name = "핑구";
        pingu.showName();   // 제 이름은 알아서 뭐하시게요?
    }
}