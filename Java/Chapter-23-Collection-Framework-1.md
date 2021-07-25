# 컬렉션 프레임워크 1

## 목차
1. [컬렉션 프레임워크의 이해](#1-컬렉션-프레임워크의-이해)  
   1.1 [컬렉션 프레임워크](#11-컬렉션-프레임워크)  

2. [List\<E> 인터페이스를 구현하는 컬렉션 클래스들](#2-liste-인터페이스를-구현하는-컬렉션-클래스들)  
   2.1 [List\<E> 인터페이스](#21-liste-인터페이스)  
   2.2 [ArrayList\<E> 클래스](#22-arrayliste-클래스)   
   2.3 [LinkedList\<E> 클래스](#23-linkedliste-클래스)  
   2.4 [ArrayList\<E> vs LinkedList\<E>](#24-arrayliste-vs-linkedliste)   
   2.5 [저장된 인스턴스의 순차적 접근 방법 1: enhanced for문의 사용](#25-저장된-인스턴스의-순차적-접근-방법-1-enhanced-for문의-사용)  
   2.6 [저장된 인스턴스의 순차적 접근 방법 2](#26-저장된-인스턴스의-순차적-접근-방법-2)  
   2.7 [Iterator 반복자의 세 가지 메소드](#27-iterator-반복자의-세-가지-메소드)  
   2.8 [배열보다는 컬렉션 인스턴스가 좋다: 컬렉션 변환 1](#28-배열보다는-컬렉션-인스턴스가-좋다-컬렉션-변환-1)  
   2.9 [배열보다는 컬렉션 인스턴스가 좋다: 컬렉션 변환 2](#29-배열보다는-컬렉션-인스턴스가-좋다-컬렉션-변환-2)    
   2.10 [배열 기반 리스트를 연결 기반 리스트로](#210-배열-기반-리스트를-연결-기반-리스트로)  
   2.11 [기본 자료형 데이터의 저장과 참조](#211-기본-자료형-데이터의-저장과-참조)  
   2.12 [리스트만 갖는 양방향 반복자](#212-리스트만-갖는-양방향-반복자)  
   2.13 [양방향 반복자의 예](#213-양방향-반복자의-예)  

3. []()
4. []()
5. []()

<br>

# 1. 컬렉션 프레임워크의 이해
- 인스턴스의 저장에 관련된 것이다.  
어떻게 저장할 것인가?   
어떻게 저장할 것인가를 고민하는 것이 아니라 저장하는 방법을 클래스로 정의를 해서 우리에게 제공해준다. 그렇게해서 만들어진 것이 **`컬렉션 프레임워크`** 이다.

- 저장을 했으면 경우에 따라서 참조, 삭제도 가능해야한다. 전반적으로 인스턴스의 저장, 참조, 삭제에 대한 것을 모아 놓은 것이 컬렉션 프레임워크이다.

- 인스턴스를 저장하는 데는 다양한 방법이 있다.   
이런 다양한 방법들을 모아놓은 학문을 자료구조 라고 한다.  
자바에서는 이 방법을 클래스별로 만들어놓았다.  
<br>

## 1.1 컬렉션 프레임워크
```text
      Collection<E>
            ↑               Map<K, V>
┌───────────┴──────────┐
Set<E>   List<E>   Queue<E>
```
- 컬렉션 프레임워크의 골격에 해당하는 **`인터페이스`** 들

- 자료구조 및 알고리즘을 구현해 놓은 일종의 라이브러리이다.  
제네릭 기반으로 구현이 되어 있다.

- 구현하는 인터페이스에 따라 **`사용방법`** 과 **`특성`** 이 결정된다.

- Set\<E> 이라는 것은 인스턴스의 저장, 삭제, 참조하는 방법의 하나이다.   
List\<E>도 마찬가지이다.   
"List\<E>, Set\<E>을 기반으로 인스턴스를 저장하고 삭제하겠다."  
List\<E>, Set\<E> 등은 자료구조의 이름이다.
<br>
<br>


# 2. List\<E> 인터페이스를 구현하는 컬렉션 클래스들
## 2.1 List\<E> 인터페이스
- List\<E> 인터페이스를 구현하는 대표적인 컬렉션 클래스 둘은 다음과 같다.
  - **`ArrayList<E>`**  
    **`배열 기반`** 자료구조, 배열을 이용하여 인스턴스 저장
  - **`LinkedList<E>`**  
    **`리스트 기반`** 자료구조, 리스트를 구성하여 인스턴스 저장

- List\<E> 인터페이스를 구현하는 컬렉션 클래스들의 공통 특성
  - 인스턴스 `저장 순서 유지`
    - 나란히 
  - 동일 인스턴스의 `중복 저장을 허용`한다.

- 저장이라는 기능을 기반으로 ArrayList\<E>와 LinkedList\<E>를 봤을 때는 차이가 없다.

- 배열을 기반으로, 리스트를 기반으로 한다는 의미는 무엇인가?  
    ```text
    리스트
    ┌────┐   ┌────┐    ┌────┐   ┌────┐
    │   ·┼───┼>  ·┼────┼>  ·┼───┼>   │
    └────┘   └────┘    └────┘   └────┘
    ```

    ```text
    배열
    ┌────┐┌────┐┌────┐┌────┐
    │    ││    ││    ││    │
    └────┘└────┘└────┘└────┘
    ```
    배열은 공간을 미리 만들어둔다.  
    리스트는 필요할 때마다 공간을 만든다. 그리고 줄로 다음 바구니와 연결해놓는다고 생각하자.  
    해당 바구니는 다음 바구니랑만 연결해놓으면 된다.

- 배열의 경우 한 칸만 덧붙이면 얼마나 좋을까?   
하지만 배열은 이렇게 덧붙여서 늘릴 수가 없다.  
배열은 뒤에 덧붙이는 자료구조가 아니다.  
이보다 더 긴 배열을 만들어서 데이터들을 새 배열에 옮겨야한다.  
더 긴 배열을 만들고 기존 배열에 있던 값들을 새로운 배열로 옮기고 기존 배열은 삭제해야 하는 일련의 과정을 거쳐야한다.

- 리스트는 필요할 때마다 늘리면 되기 때문에 공간을 신경쓰지 않아도 된다.  
링크드는 언제든지 바구니 하나만 추가하면 100개든 1000개든 늘릴 수 있다.

- 이처럼 상황에 따른 성능적 차이가 있다.  
내가 구현하는 프로그램에 맞도록 자료구조를 선택하면 된다.
<br>
<br>


## 2.2 ArrayList\<E> 클래스
```java
public static void main(String[] args) {
    List<String> list = new ArrayList<>();  // 컬렉션 인스턴스 생성

    // 컬렉션 인스턴스에 문자열 인스턴스 저장
    list.add("Toy");
    list.add("Box");
    list.add("Robot");

    // 저장된 문자열 인스턴스의 참조
    for (int i = 0; i < list.size(); i++) {
        System.out.print(list.get(i) + '\t');
    }
    System.out.println();

    list.remove(0); // 첫 번째 인스턴스 삭제

    // 첫 번째 인스턴스 삭제 후 나머지 인스턴스들을 참조
    for (int i = 0; i < list.size(); i++) {
        System.out.print(list.get(i) + '\t');
    }
    System.out.println();
}
```

```bash
Toy	Box	Robot	
Box	Robot
```
- 배열 기반 자료구조이지만 공간의 확보 및 확장은 ArrayList 인스턴스가 스스로 처리한다.

- List\<String> list = new ArrayList<>();  
실제로 대부분 ArrayList 클래스 중에서 List 인터페이스가 가지고 있는 메소드들만 호출을 한다.  

- list.remove(0);  
배열의 삭제 방법을 알아야한다.  
    ```
    ┌────┐┌────┐┌────┐┌────┐┌────┐
    │ 1  ││ 2  ││ 3  ││ 4  ││ 5  │
    └────┘└────┘└────┘└────┘└────┘
    ```
    3을 삭제해야한다고 했을 때, 3만 지우는 게 아니라 뒤에 있는 4, 5 를 앞으로 한 칸씩 당겨온다.
     ```
    ┌────┐┌────┐┌────┐┌────┐┌────┐
    │ 1  ││ 2  ││ 4  ││ 5  ││    │
    └────┘└────┘└────┘└────┘└────┘
    ```
    이렇게되면 인덱스의 값이 바뀐다.  
    삭제하기 전의 2번 인덱스에는 3이 저장되어있었지만, 3을 삭제 한 후 2번 인덱스를 출력해보면 4가 저장되어있는 걸 확인할 수 있다.  
<br>
<br>


## 2.3 LinkedList\<E> 클래스
```java
public static void main(String[] args) {
    List<String> list = new LinkedList<>();  // 유일한 변화

    // 컬렉션 인스턴스에 문자열 인스턴스 저장
    list.add("Toy");
    list.add("Box");
    list.add("Robot");

    // 저장된 문자열 인스턴스의 참조
    for (int i = 0; i < list.size(); i++) {
        System.out.print(list.get(i) + '\t');
    }
    System.out.println();

    list.remove(0); // 첫 번째 인스턴스 삭제

    // 첫 번째 인스턴스 삭제 후 나머지 인스턴스들을 참조
    for (int i = 0; i < list.size(); i++) {
        System.out.print(list.get(i) + '\t');
    }
    System.out.println();
}
```
- 리스트 기반 자료구조는 열차 칸을 더하고 빼는 형태의 자료구조이다.
  - 일반적으로 `리스트` 라함은 연결 리스트를 말한다.
  
- 인스턴스 저장:  
열차 칸을 하나 더한다.  

- 인스턴스 삭제:  
해당 열차 칸을 삭제한다.

```text
       ┌─────┐   ┌─────┐   ┌──────┐
list·──┼>Toy·┼───┼>Box·┼───┼>Robot│
       └─────┘   └─────┘   └──────┘
```
- Box를 삭제한다면?  
Box 뒤에 만개가 연결되어있다하더라도 Box를 통째로 빼주면 된다. (기차처럼)  
Toy와 Robot을 연결해주고 Box를 빼버리면 된다.  

- 배열이었다면?   
Box 뒤에 있는 만개를 하나씩 다 앞으로 이동시켜야한다.  
배열이 가지고 있는 취약점을 Linked에서는 해결하고 있다.  

- 데이터를 저장할 때는 배열이 더 빨리 저장할 수 있고 데이터를 삭제할 때는 리스트가 유리하다.
<br>
<br>


## 2.4 ArrayList\<E> vs LinkedList\<E>
### 2.4.1 ArrayList\<E>
- ArrayList\<E>의 단점  
  - 저장 공간을 늘리는 과정에서 시간이 비교적 많이 소요된다.
    - 그냥 아주 되게 많이 소요된다.
  - 인스턴스의 삭제 과정에서 많은 연산이 필요할 수 있다. 따라서 느릴 수 있다.
    - 엄청 많은 연산이 필요하고, 완전 많이 느려진다.

- ArrayList\<E>의 장점
  - 저장된 인스턴스의 참조가 빠르다.  
    - 저장된 데이터의 양이 100개이고, 50번째 값을 얻고자 한다면 50번째 값의 인덱스를 전달하면 된다.  
    반면 리스트라면?  
    리스트가 50번째 바구니를 잡고 있는 것이 아니다.  
    첫 번째 바구니에서부터 50번째 바구니까지 건너가야한다.
<br>

### 2.4.2 LinkedList\<E>
- LinkedList\<E>의 단점
  - 저장된 인스턴스의 참조 과정이 배열에 비해 복잡하다. 따라서 느릴 수 있다.  
    - 50번째 바구니를 찾기 위해서 첫 번째 바구니에서부터 건너가야 한다.   
    데이터의 양에 비례해서 느려질 수 있다.

- LinkedList\<E>의 장점
  - 저장 공간을 늘리는 과정이 간단하다.
  - 저장된 인스턴스의 삭제 과정이 단순하다.
<br>
<br>


## 2.5 저장된 인스턴스의 순차적 접근 방법 1: enhanced for문의 사용
```java
public static void main(String[] args) {
    List<String> list = new LinkedList<>(); 

    // 인스턴스 저장
    list.add("Toy");
    list.add("Box");
    list.add("Robot");

    // 전체 인스턴스의 참조
    for (String s : list) {
        System.out.print(s + '\t');
    }
    ...
}
```
- for-each문의 대상이 되기 위한 조건  
    Interable\<T> 인터페이스의 구현

- public interface Collection\<E> extends Iterable\<E>

- 반복자 Iterable 등장과 iterator() 메소드
<br>
<br>


## 2.6 저장된 인스턴스의 순차적 접근 방법 2
A에서 값을 꺼내오는 방법, B에서 값을 꺼내오는 방법, C에서 값을 꺼내오는 방법이 각각 다르다.  
사용자의 입장에서는 A,B,C에서 값을 꺼내오는 방법이 동일한 것이 좋은데, 이를 가능하게 해주는 것이 반복자이다.

```java
public static void main(String[] args) {
    List<String> list = new LinkedList<>();
    ...
    Iterator<String> itr = list.iterator();  // 반복자 획득. itr이 지팡이를 참조한다.

    // 반복자를 이용한 순차적 참조
    while (itr.hasNext()) {    // next 메소드가 반환할 대상이 있다면, 
        str = itr.next();      // next 메소드를 호출한다.
        ...
    }
}
```
- Iterator<String> itr = list.iterator();  
iterator() 반복자를 호출했다. 

- str = itr.next();  
반복자가 값을 하나씩 꺼내준다. 우리는 반복자가 어떤 방법을 써서 값을 꺼내오는지는 알 필요가 없다.  
next() 메소드를 사용하기만 하면 된다.  
반복자를 사용하는 방법만 일단 습득하면 대상이 누구이든지간에 순차적인 참조를 쉽게할 수 있다.

- itr.hasNext()  
반환받을 값이 있는지 반복자에게 물어보는 메소드이다.  
있다면 true를 반환해주고, 없다면 false를 반환해준다.  

```java
public interface Iterble<T> {
    Iterator<T> iterator();
    ...
}
```
<br>
<br>


## 2.7 Iterator 반복자의 세 가지 메소드
```java
E next()              다음 인스턴스의 참조 값을 반환  
boolean hasNext()     next 메소드 호출 시 참조 값 반환 가능 여부 확인
void remove()         next 메소드 호출을 통해 반환했던 인스턴스 삭제
```

```java
// 반복자를 이용한 참조 과정 중 인스턴스의 삭제
while (itr.hasNext()) {    
    str = itr.next();      
    if (str.equals("Box")) {
        itr.remove();   // 위에서 next 메소드가 반환한 인스턴스 삭제
    }
}
```
![Iterator](./img/Iterator.png)  
- str = itr.next();   
반복자가 지팡이를 가지고 있다.  
아무것도 참조하고 있지 않다가 next() 메소드를 호출함과 동시에 지팡이로 0번째 인덱스의 값을 가리키고, 그 값을 반환한다.  

- itr.remove();  
현재 가리키고 있는 대상을 삭제한다.
<br>
<br>

## 2.8 배열보다는 컬렉션 인스턴스가 좋다: 컬렉션 변환 1
```text
1. 인스턴스의 저장과 삭제가 편하다.
2. 반복자를 쓸 수 있다.
```
- 두 가지의 이유로 배열보다 ArrayList\<E>가 더 좋다.

- 하지만 배열처럼 선언과 동시에 초기화가 불가능하다.
<br>

```java
List<String> list = Arrays.asList("Toy", "Robot", "Box");
```
- 초기화를 위해 쓸 수 있는 방법

- 인자로 전달된 인스턴스들을 저장한 컬렉션 인스턴스의 생성 및 반환

- 이렇게 생성된 리스트 인스턴스는 Immutable 인스턴스이다.
<br>
<br>


## 2.9 배열보다는 컬렉션 인스턴스가 좋다: 컬렉션 변환 2
```java
public ArrayList(Collection<? extends E> c) {...}
```
- 생성자를 통해서 새로운 ArrayList 인스턴스 생성이 가능하다.

- Collection\<E>를 구현한 컬렉션 인스턴스를 인자로 전달받는다.  
그리고 E는 인스턴스 생성 과정에서 결정되므로 무엇이든 될 수 있다.  
덧붙여서 매개변수 c로 전달된 컬렉션 인스턴스에서는 참조만(꺼내기만) 가능하다.

- new ArrayList<>(list);    
그대로 값을 복사해와야 하기때문에 ArrayList 생성자에 set() 메소드가 들어가면 안되므로 와일드카드에 상한 제한을 뒀다.  
<br>

```java
public static void main(String[] args) {
    List<String> list = Arrays.asList("Toy", "Box", "Robot", "Box");

    // 생성자 public ArrayList(Collection<? extends E> c)를 통한 인스턴스 생성
    list = new ArrayList<>(list);
    ...
}
```
- list는 "Toy", "Box", "Robot", "Box"를 저장하고 있는 컬렉션 인스턴스를 참조한다.  
list가 참조하고 있는 인스턴스는 Immutable 인스턴스이므로 데이터를 변경 하지 못하므로, 컬렉션 인스턴스를 다시 만든다.

- new ArrayList<>(list);  
ArrayList를 생성하면서 list를 인자로 전달하면 이 인스턴스는 list가 가지고 있던 데이터를 그대로 복사하고 참조값을 반환한다.
<br>
<br>

## 2.10 배열 기반 리스트를 연결 기반 리스트로
```java
public ArrayList(Collection<? extends E> c)     // ArrayList<E> 생성자 중 하나
```
- 인자로 전달된 컬렉션 인스턴스로부터 ArrayList\<E> 인스턴스 생성
<br>

```java
public LinkedList(Collection<? extends E> c)     // LinkedList<E> 생성자 중 하나
```
- 인자로 전달된 인스턴스로부터 LinkedList\<E> 인스턴스 생성
<br>

```java
public static void main(String[] args) {
    List<String> list = Arrays.asList("Toy", "Box", "Robot", "Box");
    list = new ArrayList<>(list);

    ...   
    // ArrayList<E> 인스턴스 기반으로 LinkedList<E> 인스턴스 생성
    list = new LinkedList<>(list);
    ...

}
```
- ArrayList를 LinkedList로 바꾸었다.
LinkedList를 생성하면서 인자로 list를 넘기면 list가 참조하고 있던 값이 그대로 LinkedList로 복사된다.
<br>
<br>


## 2.11 기본 자료형 데이터의 저장과 참조
```java
public static void main(String[] args) {
    LinkedList<Integer> list = new LinkedList<>();
    list.add(10);   // 저장 과정에서 오토 박싱 진행
    list.add(20);
    list.add(30);
    
    int n;
    for (Iterator<Integer> itr = list.iterator(); itr.hasNext(); ) {
        n = itr.next();     // 오토 언박싱 진행
        System.out.print(n + "\t");
    }
    System.out.println();
}
```
- 오토 박싱과 오토 언박싱 덕분에 컬렉션 인스턴스에 기본 자료형의 값도 저장 가능하다.
<br>
<br>

## 2.12 리스트만 갖는 양방향 반복자
```java
public ListIterator<E> listIterator()   // List<E> 인터페이스의 메소드
```
- ListIterator\<E>는 Iterator\<E>을 상속한다.

```java
E next()                다음 인스턴스의 참조 값을 반환
boolean hasNext()       next 메소드 호출 시 참조 값 반환 가능 여부 확인
void remove()           next 메소드 호출을 통해 반환했던 인스턴스를 삭제

E previous()            next 메소드와 기능은 같고 방향만 반대
boolean hasPrevious()   hasNext 메소드와 기능은 같고 방향만 반대

void add(E e)           인스턴스의 추가
void set(E e)           인스턴스의 변경
```
- 지팡이를 반대쪽 방향으로도 가리킬 수 있는 반복자이다.
<br>
<br>


## 2.13 양방향 반복자의 예
```java
public static void main(String[] args) {
    List<String> list = Arrays.asList("Toy", "Box", "Robot", "Box");
    list = new ArrayList<>(list);

    ListIterator<String> litr = list.listIterator();    // 양방향 반복자 획득

    String str;
    while (litr.hasNext()) {    // 왼쪽에서 오른쪽으로 이동을 위한 반복문
        str = litr.next();
        System.out.print(str + '\t');
        if (str.equals("Toy")) {     // "Toy" 만나면 "Toy2" 저장
            litr.add("Toy2");
        }
    }
    System.out.println();

    while (litr.hasPrevious()) {    // 오른쪽에서 왼쪽으로 이동을 위한 반복문
        str = litr.previous();
        System.out.print(str + '\t');
        if (str.equals("Robot")) {  // "Robot" 만나면 "Robot2" 저장
            litr.add("Robot2");
        }
    }
    ...
}
```
- 양방향 반복자를 획득함으로써 왼쪽에서 오른쪽 뿐만 아니라 오른쪽에서 왼쪽으로도 가리킬 수 있게 되었다.
```bash
Toy	Box	Robot	Box	
Box	Robot	Robot2	Box	Toy2	Toy	
```
<br>
<br>


# 3. Set\<E> 인터페이스를 구현하는 컬렉션 클래스들