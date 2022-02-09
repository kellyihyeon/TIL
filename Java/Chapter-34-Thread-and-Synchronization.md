# 쓰레드 그리고 동기화

## 목차
1. [쓰레드의 이해와 쓰레드의 생성](#1-쓰레드의-이해와-쓰레드의-생성)  
   1.1 [쓰레드의 이해](#11-쓰레드의-이해)  
   1.2 [쓰레드를 생성하는 방법](#12-쓰레드를-생성하는-방법)   
   1.3 [둘 이상의 쓰레드를 생성한 예](#13-둘-이상의-쓰레드를-생성한-예)
   1.4 [쓰레드를 생성하는 두 번째 방법](#14-쓰레드를-생성하는-두-번째-방법)
   
2. []()
3. []()
<br>

# 1. 쓰레드의 이해와 쓰레드의 생성
## 1.1 쓰레드의 이해
- 실행 중인 프로그램을 가리켜 `프로세스` 라고 한다. 쓰레드는 프로세스 내에서 `또 다른 실행의 흐름을 형성하는 주체`를 의미한다.

- 하나의 프로세스 안에서 여러 실행 흐름을 가지게 할 수 있다.  
하나의 프로세스 안에 3개의 쓰레드가 있다면 3개의 쓰레드가 각각의 실행 흐름을 가지고 동시에 실행된다는 의미이다.

- 우리가 만든 쓰레드는 어떤 것이 있을까?  
우리는 만든 적이 없다. 메인 메소드의 호출과 실행을 담당하는 쓰레드가 사실은 자동적으로 생성이 되었던 것이다.

- 하나의 프로세스가 형성되고 그 안에 하나의 쓰레드가 만들어지고 이 쓰레드에 의해서 메인메소드가 실행되어져 왔다.  
(메인 메소드가 실행 된다 = 하나의 실행 흐름)

- 멀티 쓰레드?  
하나의 프로세스 안에 둘 이상의 쓰레드가 존재하는 것을 말한다. 멀티 쓰레드는 직접 쓰레드 생성을 명령 해야 한다. 
<br>
<br>

### 1.1.1 쓰레드 코드
```java
public class CurrentThreadName {

    public static void main(String[] args) {
        Thread ct = Thread.currentThread();
        String name = ct.getName();
        System.out.println(name);
    }
}
```
```java
main

Process finished with exit code 0
```
- main (main 메소드를 실행하기 때문): 우리는 쓰레드의 이름을 지정해 준 적이 없다.  
우리가 만든 게 아니라 자동으로 생성된 것이기 때문이다.

- main 메소드를 실행하는 쓰레드를 main 쓰레드라고 한다.
<br>
<br>

## 1.2 쓰레드를 생성하는 방법
- 1단계: Runnable을 구현한 인스턴스 생성  
  2단계: Thread 인스턴스 생성  
  3단계: start 메소드 호출  

```java
public static void main(String[] args) {
        Runnable task = () -> {
            int n1 = 10;
            int n2 = 20;
            String name = Thread.currentThread().getName();
            System.out.println(name + ": " + (n1 + n2));
        };

        Thread t = new Thread(task);
        // 쓰레드 생성 및 실행
        t.start();
        System.out.println("End = " + Thread.currentThread().getName());
    }
```
```java
End = main
Thread-0: 30

Process finished with exit code 0
```
- t.start();  
start() 메소드를 호출해야 `쓰레드가 생성` 되고 실행이 된다.
- 쓰레드가 동시에 실행되고 있지만 누가 먼저 끝날지는 모른다. (보장하지 못한다)

```text
쓰레드 흐름

main
├────────┐
│        │
v        v
```
<br>
<br>

## 1.3 둘 이상의 쓰레드를 생성한 예
```java
public static void main(String[] args) {
    Runnable task1 = () -> {
        try {
            for (int i = 0; i < 20; i++) {
                if (i % 2 == 0) {
                    System.out.print(i + " ");
                }
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };

    Runnable task2 = () -> {
        try {
            for (int i = 0; i < 20; i++) {
                if (i % 2 == 1) {
                    System.out.print(i + " ");
                }
            }
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };

    Thread t1 = new Thread(task1);
    Thread t2 = new Thread(task2);

    t1.start();
    t2.start();
}
```
```java
1 3 5 7 9 0 11 13 15 17 19 2 4 6 8 10 12 14 16 18 
Process finished with exit code 0
```
```text
쓰레드 흐름

t1  main   t2
┌─────┼─────┐
│     │     │
v     v     v
```
- main 메소드가 끝나면 프로그램이 종료 되는 것이 아니라, 쓰레드가 각각의 흐름을 가지기 때문에 마지막 쓰레드가 종료가 되어야 전체 프로세가 종료 된다.

- t1이 먼저 실행되고 t2가 실행이 될 것이라 짐작할 수 있다. 정말 짐작한대로 실행 될 수 있지만 결과를 보면 예측이 안되는 걸 알 수 있다.
- (t2의 전력질주...)
<br>
<br>

## 1.4 쓰레드를 생성하는 두 번째 방법
- 1단계: Thread를 상속하는 클래스의 정의와 인스턴스 생성  
2단계: Start 메소드 호출

```java
public class Task extends Thread {

    @Override
    public void run() {
        int n1 = 10;
        int n2 = 20;
        String name = Thread.currentThread().getName();
        System.out.println(name + ": " + (n1 + n2));
    }

}
```
```java
public class MakeThreadDemo2 {

    public static void main(String[] args) {
        Task t1 = new Task();
        Task t2 = new Task();

        t1.start();
        t2.start();
        System.out.println("End " + Thread.currentThread().getName());
    }
}
```
```java
End main
Thread-1: 30
Thread-0: 30

Process finished with exit code 0
```