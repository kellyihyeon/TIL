# 쓰레드 그리고 동기화

## 목차
1. [쓰레드의 이해와 쓰레드의 생성](#1-쓰레드의-이해와-쓰레드의-생성)  
   1.1 [쓰레드의 이해](#11-쓰레드의-이해)  
   1.2 [쓰레드를 생성하는 방법](#12-쓰레드를-생성하는-방법)   
   1.3 [둘 이상의 쓰레드를 생성한 예](#13-둘-이상의-쓰레드를-생성한-예)  
   1.4 [쓰레드를 생성하는 두 번째 방법](#14-쓰레드를-생성하는-두-번째-방법)
   
2. [쓰레드의 동기화](#2-쓰레드의-동기화)  
   2.1 [쓰레드의 메모리 접근 방식과 그에 따른 문제점](#21-쓰레드의-메모리-접근-방식과-그에-따른-문제점)  
   2.2 [동일한 메모리 공간에 접근하는 것이 왜 문제가 되는가?](#22-동일한-메모리-공간에-접근하는-것이-왜-문제가-되는가)  
   2.3 [동기화(Synchronization) 메소드](#23-동기화synchronization-메소드)  
   2.4 [동기화(Synchronization) 블록](#24-동기화synchronization-블록)  

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
<br>
<br>

# 2. 쓰레드의 동기화
- 동기화가 왜 필요한 것인가?
- 쓰레드가 가지고 있는 문제점이 무엇인가?

## 2.1 쓰레드의 메모리 접근 방식과 그에 따른 문제점
```java
public class Counter {

    // 공유되는 변수
    int count = 0;

    public void increment() {
        count++;
    }

    public void decrement() {
        count--;
    }

    public int getCount() {
        return count;
    }
}

```
```java
public class MutualAccess {

    public static Counter counter = new Counter();

    public static void main(String[] args) throws InterruptedException {
        // count 값을 1 증가
        Runnable task1 = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        };

        // count 값을 1 감소
        Runnable task2 = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.decrement();
            }
        };

        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println(counter.getCount());

    }
}
```
```java
-62

Process finished with exit code 0
```
- join():  
쓰레드의 종료를 기다리고 있다.   
마지막 출력문의 count 의 개수는 쓰레드 t1과 t2가 종료되고 난 후의 데이터이다.  
(이해가 가지 않으면 join() 메서드를 주석으로 처리하고 실행 결과를 보면 된다.)  

- 출력 결과는 실행할 때마다 다르다.

<br>
<br>

## 2.2 동일한 메모리 공간에 접근하는 것이 왜 문제가 되는가?
### 2.2.1 문제 상황 제시
- 변수에 저장된 값을 1씩 증가시키는 연산을 두 쓰레드가 동시에 진행한다고 가정하자.

![Thread1](./Img/Thread-Before.png) ![Thread2](./Img/Thread-After.png)
<br>
<br>

### 2.2.2 thread1 이 num++ 을 하면 100이 된다.  
이런식이면 문제가 없어 보이지만 `값의 증가` 라는 작업임을 다시 생각해보자.  
값의 증가는 코어를 통한 연산 작업이 필요한 작업이다.   
연산 작업을 하면서 단계를 거치게 되는데 ⓐ쓰레드는 변수 num 에 저장된 99를 가져온다. ⓑ코어에서 num + 1 을 계산하고 값을 내놓는다. ⓒ결과 100을 변수 num 에 가져다 놓는다. 이 과정을 거치면 num = 100이 된다.
<br>
<br>

### 2.2.3 thread1 과 thread2가 num++ 을 한다면 값은 얼마가 될까?  
thread1도 thread2도 num +1을 연산하기 위해 위 작업을 거친다. 두 쓰레드에서 num 에 저장된 값 99를 가져갈 때 시차를 두고 가져갈 수도 있고 코어가 여러개라면 동시에 가져갈 수도 있다.   
두 쓰레드에서 각각 99 를 가져가고 각 연산 작업(num + 1)을 한 후 각각 num 에 100을 가져다 놓는다면 변수 num의 값은 101이 아닌 100이 된다.  
두 쓰레드 모두 99를 가져가서 99 + 1을 한 100을 가져다 놓았기 때문이다.
<br>
<br>

### 2.2.4 쓰레드의 동기화
- 둘 이상의 쓰레드가 동일한 변수에 동시에 접근해서 생긴 문제이므로 한순간에 한 쓰레드만 변수에 접근하도록 제한한다.

- thread1이 작업을 진행 하면 thread2는 thread1의 작업이 종료될 때까지 기다린다.   thread1이 작업을 종료하면 thread2가 작업을 시작한다.
<br>
<br>

## 2.3 동기화(Synchronization) 메소드
```java
public class Counter {

    // 공유되는 변수
    int count = 0;

    synchronized public void increment() {
        count++;
    }

    synchronized public void decrement() {
        count--;
    }

    public int getCount() {
        return count;
    }
}
```
- synchronized  
한순간에 한 쓰레드의 접근만을 허용하겠다. (메소드 전체에 적용)

- 동기화 되었다.  
t1 메소드가 메소드를 실행하면 메소드가 시작되고 종료될 때까지 다른 쓰레드인 t2 의 접근을 막겠다. 

- 두 쓰레드가 각기 다른 메소드에 접근한다면?  
t1은 increment() 메소드만 실행하고 t2는 decrement() 메소드만 실행 하는데 문제가 발생하는가?  
두 메소드 모두 동일한 변수 count 를 공유하고 있기 때문에 문제가 생긴다.

- t1이 increament() 를 호출했을 때 t2는 decrement()를 실행하지 못하고 t1의 작업이 끝날 때까지 기다려야한다.

- increment() 메소드 내용이 간단하지 않다면?  
코드가 count++; 앞에 10줄, 후에 30줄이 있다면 41개의 코드가 실행될 때까지 다른 쓰레드는 기다려야 한다. 이는 성능을 극단적으로 낮추는 결과로 이어질 수 있다.
<br>  
<br>  

## 2.4 동기화(Synchronization) 블록
```java
public void increment() {
        synchronized (this) {
            count++;
        }
    }
    
public void decrement() {
    synchronized (this) {
        count--;
    }
}
```
- 특정 블록을 지정해서 블록 안에 있는 코드에만 접근을 막는 것이다.
  
- 메소드보다 더 작은 단위인 문장 단위로 동기화를 시킬 수 있다.
  
- this 는 뭐지?  
이 인스턴스를 대상으로 동기화를 하겠다는 의미이다. -> ???
- [ ] 무슨 말인지 이해가 안되므로 더 찾아보고 설명 추가 할 것