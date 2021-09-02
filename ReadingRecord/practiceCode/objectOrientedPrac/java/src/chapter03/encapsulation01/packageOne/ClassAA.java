package chapter03.encapsulation01.packageOne;

public class ClassAA extends ClassA {

    void runSomething() {
//        pri = 1;
        def = 1;
        pro = 1;
        pub = 1;

//        this.pri = 1;
        this.def = 2;
        this.pro = 2;
        this.pub = 2;

        // static
//        priStatic = 1;
        defStatic = 1;
        proStatic = 1;
        pubStatic = 1;

//        ClassA.priStatic = 2;
        ClassA.defStatic = 2;
        ClassA.proStatic = 2;
        ClassA.pubStatic = 2;

//        this.priStatic = 3;
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
        ClassAA caa = new ClassAA();
//        caa.pri = 1;
        caa.def = 1;
        caa.pro = 1;
        caa.pub = 1;

        // static
//        priStatic = 1;
        defStatic = 1;
        proStatic = 1;
        pubStatic = 1;

//        ClassA.priStatic = 2;
        ClassA.defStatic = 2;
        ClassA.proStatic = 2;
        ClassA.pubStatic = 2;

//        this.priStatic = 3;
//        this.defStatic = 3;
//        this.proStatic = 3;
//        this.pubStatic = 3;

        // 객체 참조 변수를 통해 정적 멤버도 접근 가능하지만 권장하지 않음
        // 왜? 이유 설명하기
        //caa.priStatic = 1;
        caa.defStatic = 1;
        caa.proStatic = 1;
        caa.pubStatic = 1;
    }
}
