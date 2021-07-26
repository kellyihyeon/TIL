# 컬렉션 프레임워크 2

## 목차
1. [컬렉션 기반 알고리즘](#1-컬렉션-기반-알고리즘)    
   1.1 [정렬](#11-정렬)  
   1.2 [리스트 대상 정렬의 예](#12-리스트-대상-정렬의-예)  
   1.3 [<T extends Comparable\<T>> 아니고 <T extends Comparable\<? super T>>](#13-t-extends-comparablet-아니고-t-extends-comparable-super-t)  
   1.4 [<T extends Comparable\<? super T>>의 이해 1](#14-t-extends-comparable-super-t의-이해-1)  
   1.5 [<T extends Comparable\<? super T>>의 이해 2](#15-t-extends-comparable-super-t의-이해-2)  
   1.6 [<T extends Comparable\<? super T>>의 이해 3](#16-t-extends-comparable-super-t의-이해-3)  
   1.7 [정렬: Comparator\<T> 기반](#17-정렬-comparatort-기반)  
   1.8 [찾기](#18-찾기)  
   1.9 [찾기의 예](#19-찾기의-예)  
   1.10 [찾기: Comparator\<T> 기반](#110-찾기-comparatort-기반)  
   1.11 [찾기: Comparator\<T> 기반의 예](#111-찾기-comparatort-기반의-예)  
   1.12 [복사하기](#112-복사하기)  

<br>

# 1. 컬렉션 기반 알고리즘
## 1.1 정렬
- List\<E>를 구현한 클래스들은 저장된 인스턴스를 정렬된 상태로 유지하지 않는다.  
대신에 정렬을 해야 한다면 다음 메소드를 사용할 수 있다. 

```java
public static <T extends Comparable<T>> void sort(List<T> list)
```
- Collections 클래스에 정의되어 있는 제네릭 메소드
- 인자로 List\<T>의 인스턴스는 모두 전달 가능하다.  
단, T는 Comparable\<T> 인터페이스를 구현한 상태이어야 한다.
<br>
<br>

## 1.2 리스트 대상 정렬의 예
```java
public static void main(String[] args) {
    List<String> list = Arrays.asList("Toy", "Box", "Robot", "Weapon");
    list = new ArrayList<>(list);

    // 정렬 이전 출력
    for (Iterator<String> itr = list.iterator(); itr.hasNext(); ) {
        System.out.print(itr.next() + '\t');
    }
    System.out.println();

    // 정렬
    Collections.sort(list);

    // 정렬 이후 출력
    for (Iterator<String> itr = list.iterator(); itr.hasNext(); ) {
        System.out.print(itr.next() + '\t');
    }
    System.out.println();
}
```
```bash
Toy	Box	Robot	Weapon	
Box	Robot	Toy	Weapon	
```
- Collections.sort(list);  
sort 메소드의 인자로 list를 전달했다. (List\<String> list)  
T가 String 으로 결정됐으니, String 클래스를 보자.  

- class **`String`** extends Object implements **`Comparable<String>`**  
T의 제약 사항은 <T extends Comparable\<T>> 였으므로 extends Comparable\<String> T는 String이 된다.  
String 클래스는 Comparable\<String>를 구현하고 있으니 T의 조건에 부합이된다.  
<br>
<br>


## 1.3 <T extends Comparable\<T>> 아니고 <T extends Comparable\<? super T>>
- public static <T extends Comparable\<T>> void sort(List\<T> list)  
위에서는 이해를 쉽게 하기 위해 임시로 이러한 메소드를 정의했다. 실제 있는 메소드가 아니다.

- public static <T extends Comparable\<? super T>> void sort(List\<T> list)    
실제로는 이 메소드이다.  
<br>
<br>

## 1.4 <T extends Comparable\<? super T>>의 이해 1
```java
class Car implements Comparable<Car> {
    private int disp;   // 배기량

    public Car(int d) {
        disp = d;
    }
    

    @Override
    public int compareTo(Car o) {
        return disp - o.disp;
    }
}
```

```java
public static void main(String[] args) {
    List<Car> list = new ArrayList<>();
    list.add(new Car(1200));
    list.add(new Car(3000));
    list.add(new Car(1800));
    Collections.sort(list); // 정렬

    for (Iterator<Car> itr = list.iterator(); itr.hasNext(); ) {
        System.out.print(itr.next().toString() + '\t');
    }
    System.out.println();
}

```
- sort 메소드가 다음과 같다고 가정하자.  
public static <T extends Comparable\<T>> void sort(List\<T> list)  

<br>
<br>

## 1.5 <T extends Comparable\<? super T>>의 이해 2
```java
class Car implements Comparable<Car> {...}

class ECar extends Car {...}    // Ecar는 Comparable<Car>를 간접 구현

public static void main(String[] args) {
    List<ECar> list = new ArrayList<>();
    ...
    Collections.sort(list);
    ...
}
```
- Collections.sort(list);  
이 메소드 호출이 성공할 수 있을까?  
상식적으로 보면 이 메소드 호출은 성공해야 한다.  
ECar가 Car를 상속하고 있고, Car가 Comparable\<Car>를 구현하고 있기 때문이다.  

- public static <Ecar extends Comparable\<Ecar>> void sort(List\<Ecar> list)  
메소드의 정의에 따라 ECar를 대입하였다.  
이 메소드를 보면 ECar는 Comparable\<Ecar>를 구현해야 한다.  
하지만 ECar는 Comparable\<Car>는 구현하지만 Comparable\<Ecar>는 구현하고 있지 않다.

- sort 메소드 호출이 허용이 되게 해야 하는데, 이 상황에선 호출을 할 수가 없다.  
<br>
<br>


## 1.6 <T extends Comparable\<? super T>>의 이해 3
```java
class Car implements Comparable<Car> {...}

class ECar extends Car {...}    // Ecar는 Comparable<Car>를 간접 구현

public static void main(String[] args) {
    List<ECar> list = new ArrayList<>();
    ...
    Collections.sort(list);
    ...
}
```
- <T extends Comparable\<? super T>>  
실제 메소드의 제네릭 선언 부분이다.  
여기에 Ecar를 대입해보자.  

- Collections.sort(list);  
이 메소드는 호출이 가능하다.  
이제는 왜 Camparable 타입을 이렇게 정의했는지 이해할 수 있다.

- **`public static <T extends Comparable<? super T>> void sort(List<T> list)`**  
실제 메소드의 정의를 살펴보자.  
-> public static <Ecar extends Comparable\<? super Ecar>> void sort(List\<Ecar> list)
<br>
<br>

## 1.7 정렬: Comparator\<T> 기반
- Collections 클래스에는 호출 시 정렬의 기준을 결정할 수 있는 다음 메소드가 정의되어 있다.  
```java
public static <T> void sort(List<T> list, Comparator<? super T> c)
```

```java
class Car {...}

// Car의 정렬을 위한 클래스
class CarComp implements Comparator<Car> {
    ...
}

class ECar extends Car {...}

public static void main(String[] args) {
    List<Car> clist = new ArrayList<>();
    clist.add(new Car(1800));
    clist.add(new Car(1200));

    List<ECar> elist = new ArrayList<>();
    elist.add(new ECar(3000, 55));
    elist.add(new ECar(1000, 87));

    CarComp comp = new CarComp();
    
    // 각각 정렬
    Collections.sort(clist, comp);
    Collections.sort(elist, comp);
}
```
- Collections.sort(elist, comp);  
sort 메소드의 Comparator<? super T> 부분이 Comparator\<T>였다면 clist를 전달 할 때는 문제 없었지만 elist를 전달하려 할 때에는 CarComp가 Comparator\<Ecar>를 구현하고 있지 않기 때문에 인자로 전달할 수 없다.  
<br>
<br>

## 1.8 찾기
```java
public static <T> int binarySearch(List<? extends Comparable<? super T>> list, T key)
```
- list에서 Key를 찾아 그 인덱스 값 반환, 못 찾으면 음의 정수 반환

- step 1   
public static\<T> int binarySearch(**`List<?>`** list, T key)

- step 2  
(**`List<? extends Comparable<T>>`** list, T key)

- step 3  
(**`List<? extends Comparable<? super T>>`** list, T key)
<br>
<br>

## 1.9 찾기의 예
```java
class StringBinarySearch {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Box");
        list.add("Robot");
        list.add("Apple");
        Collections.sort(list); // 정렬이 먼저다

        int idx = Collections.binarySearch(list, "Robot");
        System.out.println(list.get(idx));  // 탐색의 결과 출력
    }
}
```
```bash
Robot
```
- Collections.binarySearch(list, "Robot");  
앞의 예시였던 Car와 ECar 관계를 떠올리면 이해가 쉽다.
<br>
<br>

## 1.10 찾기: Comparator\<T> 기반
```java
public static <T> int binarySearch(List<? extends T> list, T key, Comparator<? super T> c)
```
- list에서 key를 찾는데 c의 기준을 적용하여 찾는다.

- step 1  
public static \<T> int binarySearch(List **`<T>`** list, T key, Comparator **`<? super T>`** c)

- step 2  
(List **`<? extends T>`** list, T key, Comparator **`<? super T>`** c)
<br>
<br>

## 1.11 찾기: Comparator\<T> 기반의 예
```java
class StrComp implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {
        return s1.compareToIgnoreCase(s2);  // 대문자, 소문자 구분 없이 비교
    }
}

class StringComparator {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("ROBOT");
        list.add("APPLE");
        list.add("BOX");

        StrComp cmp = new StrComp();    // 정렬과 탐색의 기준
        Collections.sort(list, cmp);    // 정렬
        int idx = Collections.binarySearch(list, "Robot", cmp);// 탐색
        System.out.println(list.get(idx));  // 탐색 결과 출력
    }
}
```
```
ROBOT
```
- 주의할 것:  
정렬 기준과 탐색 기준이 같기 때문에 sort 할 때의 정렬 기준과, Search 할 때의 정렬 기준이 다르면 의외의 결과를 얻을 수 있다. 
<br>
<br>

## 1.12 복사하기
```java
public static <T> void copy(List<? super T> dest, List<? extends T> src)
```
- src의 내용을 dest로 복사

- List\<T> dest가 아닌 List<? super T> dest인 이유는?   
deest에 T형 인스턴스를 넣는 것만 허용하겠다는 의미이다. 꺼내면 컴파일 에러.

- List\<T> src 가 아닌 List<? extends T> src 인 이유는?  
src로부터 T형 인스턴스를 꺼내는 것만 허용하겠다는 의미이다. 넣으면 컴파일 에러.
<br>
<br>