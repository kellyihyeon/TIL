package chapter03.reference;

public class CallByReference2 {
    public static void main(String[] args) {
        Animal ref_a = new Animal();
        Animal ref_b = ref_a;

        System.out.println(ref_a);
        System.out.println(ref_b);
    }
}
