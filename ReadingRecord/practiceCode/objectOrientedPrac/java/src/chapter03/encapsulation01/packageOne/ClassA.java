package chapter03.encapsulation01.packageOne;

public class ClassA {
    private int pri;
    int def;
    protected int pro;
    public int pub;

    private static int priStatic;
    static int defStatic;
    protected static int proStatic;
    public static int pubStatic;

    void runSomeThing() {
        pri = 1;
        def = 1;
        pro = 1;
        pub = 1;

        this.pri = 2;
        this.def = 2;
        this.pro = 2;
        this.pub = 2;

        // static
        priStatic = 1;
        defStatic = 1;
        proStatic = 1;
        pubStatic = 1;

        ClassA.priStatic = 2;
        ClassA.defStatic = 2;
        ClassA.proStatic = 2;
        ClassA.pubStatic = 2;

        this.priStatic = 3;
        this.defStatic = 3;
        this.proStatic = 3;
        this.pubStatic = 3;
    }

    static void runStaticThing() {
        // 객체를 생성하지 않고는 객체 멤버에 접근 불가능
//        pri = 1;
//        def = 1;
//        pro = 1;
//        pub = 1;
//
//        this.pri = 2;
//        this.def = 2;
//        this.pro = 2;
//        this.pub = 2;

        // 객체 생성 후 객체 참조 변수를 통해 접근 가능
        ClassA ca = new ClassA();
        ca.pri = 1;
        ca.def = 1;
        ca.pro = 1;
        ca.pub = 1;

        // static
        priStatic = 1;
        defStatic = 1;
        proStatic = 1;
        pubStatic = 1;

        ClassA.priStatic = 2;
        ClassA.defStatic = 2;
        ClassA.proStatic = 2;
        ClassA.pubStatic = 2;

    }
}
