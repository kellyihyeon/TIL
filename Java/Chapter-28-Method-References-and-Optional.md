# 메소드 참조와 Optional

## 목차
1. [메소드 참조](#1-메소드-참조)  
   1.1 [메소드 참조의 4가지 유형과 메소드 참조의 장점](#11-메소드-참조의-4가지-유형과-메소드-참조의-장점)  
   1.2 [static 메소드의 참조: 람다식 기반 예제](#12-static-메소드의-참조-람다식-기반-예제)  
   1.3 [static 메소드의 참조: 메소드 참조 기반으로 수정](#13-static-메소드의-참조-메소드-참조-기반으로-수정)  
   1.4 [인스턴스 메소드 참조 1: effectively final](#14-인스턴스-메소드-참조-1-effectively-final)  
   1.5 [인스턴스 메소드 참조 1: 인스턴스 존재 상황에서 참조](#15-인스턴스-메소드-참조-1-인스턴스-존재-상황에서-참조)    
   1.6 [인스턴스 메소드 참조 1: forEach 메소드](#16-인스턴스-메소드-참조-1-foreach-메소드)  
   1.7 [인스턴스 메소드 참조 2: 인스턴스 없이 인스턴스 메소드 참조](#17-인스턴스-메소드-참조-2-인스턴스-없이-인스턴스-메소드-참조)  
   1.8 [생성자 참조](#18-생성자-참조)  

2. [Optional 클래스](#2-optional-클래스)  
   2.1 [NullPointerException 예외의 발생 상황 1](#21-nullpointerexception-예외의-발생-상황-1)  
   2.2 [NullPointerException 예외의 발생 상황 2](#22-nullpointerexception-예외의-발생-상황-2)    

3. []()

<br>

# 1. 메소드 참조
## 1.1 메소드 참조의 4가지 유형과 메소드 참조의 장점
- ① static 메소드의 참조

- ② 참조변수를 통한 인스턴스 메소드 참조

- ③ 클래스 이름을 통한 인스턴스 메소드 참조

- ④ 생성자 참조

- 기본적으로 람다식보다 조금 더 코드를 단순하게 할 수 있다는 장점이 있다.  
일부 람다식을 메소드 참조로 대신하게 할 수 있다.
<br>
<br>

## 1.2 static 메소드의 참조: 람다식 기반 예제
```java
public interface Consumer<T> {
    void accept(T t);
    ...
}

class ArrangeList {
    public static void main(String[] args) {
        List<Integer> ls = Arrays.asList(1, 3, 5, 7, 9);
        ls = new ArrayList<>(ls);

        Consumer<List<Integer>> c = l -> Collections.reverse(l);    // reverse 메소드 호출 중심의 람다식
        c.accept(ls);   // 순서 뒤집기 진행
        System.out.println(ls);
    }
}
```
```bash
[9, 7, 5, 3, 1]
```
- Collections 클래스의 reverse 메소드 기반 예제  
public static void reverse(List<?> list)     
저장 순서를 뒤집는다.

- 코드를 직접 만들어보자.  
    ```java
    Consumer<List<Integer>> c = new Consumer<List<Integer>>() {
            @Override
            public void accept(List<Integer> int) {
                Collections.reverse(int));
            }
        };
    ```
    reverse() 메소드는 인자를 하나 전달받는데 이 메소드로 전달되는 인자는 accept()메소드에 전달된 인자이다.(약속)  
    그리고 accept() 메소드에 전달된 인자는 무엇인지 Consumer\<List<Integer>>를 보면 유추 가능하다.

- 약속을 토대로 c.accept(ls)에 전달된 매개변수 ls는 reverse(l)에 전달된 매개변수 l이다.
<br>
<br>

## 1.3 static 메소드의 참조: 메소드 참조 기반으로 수정
```java
Consumer<T>     
    void accept(T t)

Consumer<List<Integer>> c = l -> Collections.reverse(l);    // 람다식 
                            ↓
Consumer<List<Integer>> c = Collections::reverse;   // 메소드 참조
```
- accept 메소드 호출 시 전달되는 인자를 reverse 메소드를 호출하면서 그대로 전달한다는 약속에 근거한 수정  

- void accept(T t)  
  void reverse(List<?> list)  
  accept로 전달 된 것 reverse로 그대로
<br>
<br>

## 1.4 인스턴스 메소드 참조 1: effectively final
```java
Consumer<T>     
    void accept(T t)

class JustSort {
    public void sort(List<?> lst) { // 인스턴스 메소드
        Collections.reverse(lst);
    }
}

class ArrangeList3 {
    public static void main(String[] args) {
        List<Integer> ls = Arrays.asList(1, 3, 5, 7, 9);
        ls = new ArrayList<>(ls);
        JustSort js = new JustSort();   // js 는 effectively final

        Consumer<List<Integer>> c = e -> js.sort(e);    // 람다식 기반
        c.accept(ls);
        System.out.println(ls);     

        // js.sort(ls);
        // System.out.println("그냥 바로 " + ls);  // 굳이 왜 저렇게 부르는 거지   
    }
}
```
```bash
[9, 7, 5, 3, 1]
```
- Consumer<List<Integer>> c = e -> js.sort(e);  
메소드 참조 방식으로 바꾸면 `js::sort`  

- js::sort  
여기서 js는 JustSort 인스턴스를 참조하는 참조변수이므로 참조변수 js의 sort 메소드를 참조한다는 의미이다.  
위의 예제에서 봤던 Collections::reverse 은 Collections 클래스가 왔으므로 Collections 클래스의 static 메소드 reverse를 참조한다는 의미가 된다. 
<br>

### 1.4.1 effectively final
- JustSort js = new JustSort();  
js.sort(e) 는 새로운 인스턴스 생성으로 이어지고 이 인스턴스는 main 메소드와는 전혀 다른 곳에 존재하는데 js라는 참조변수는 main 메소드 내에 존재하는 지역 변수이다.  
main 이라는 지역을 벗어난 다른 인스턴스에 존재하는 것이 개념을 벗어난 것으로 치부될 수 있다. 그래서 제약 조건을 하나 건다.
    
- effectively final 이라는 제약 조건을 걸었다.  
final은 상수를 의미하는데, **`상수`** 란 js 가 참조하고 있는 JustSort 인스턴스의 내부가 바뀌지 않는 것을 의미하는 것이 아니라 **`js가 이 인스턴스를 참조한다는 사실이 바뀌지 않는다는 것`** 을 의미한다.  

- 우리가 아는 상수는 final 선언을 해야만 상수가 된다.  
그런데 js에는 final 선언이 되어있지 않다.  
하지만 예제를 보면 js가 참조하는 인스턴스가 결코 바뀌지 않는다는 것을 알 수 있다.   
따라서 js는 사실상 상수라고 할 수 있고 이러한 참조변수 즉, 한 번 참조하는 대상을 전혀 바꾸지 않는 참조변수를 가리켜 effectively final이라고 말한다.  
이런 effectively final인 참조변수에 한하여 람다식 작성을 허용해주고 있다.  

- 위험성 때문에 제약 조건을 걸어 허용해주고 있다.   
js.sort(e)는 인스턴스 생성으로 이어진다.   
생성된 인스턴스 내에서 sort 메소드가 호출될 때 이 인스턴스는 sort 메소드가 일정한 조건을 가지고 일정한 결과를 제공해줄 것을 기대한다. 
그런데 main 메소드에서 참조하는 대상이 바뀌어 버리면 이 때 호출하는 sort 메소드의 결과도 달라질 수 있다.    
즉 코드가 예측 불가능하게 된다. 따라서 안정성을 위해서 effectively final인 경우에만 허용한다.
<br>
<br>

## 1.5 인스턴스 메소드 참조 1: 인스턴스 존재 상황에서 참조
```java
Consumer<T>     
    void accept(T t)

class JustSort {
    public void sort(List<?> lst) { 
        Collections.reverse(lst);
    }
}

class ArrangeList3 {
    public static void main(String[] args) {
        List<Integer> ls = Arrays.asList(1, 3, 5, 7, 9);
        ls = new ArrayList<>(ls);
        JustSort js = new JustSort();  

        Consumer<List<Integer>> c = e -> js::sort; 
        ...
    }
}
```
- 프로그램이 종료되기 바로 직전에 js = null; 이라는 코드를 작성하면 어떻게 될까?  
컴파일이 안된다. 프로그램이 종료될 때까지 js는 저 JustSort 인스턴스를 참조해야지만 effectively final이다.  
<br>
<br>

## 1.6 인스턴스 메소드 참조 1: forEach 메소드
```java
// Iterable\<T>의 디폴트 메소드
default void forEach(Consumer<? super T> action) {
    for (T t : this) {  // this는 이 메소드가 속한 컬렉션 인스턴스를 의미한다.
        action.accept(t);   // 모든 저장된 데이터들에 대해 이 문장을 반복한다.
    }
}
```

```java
class ForEachDemo {
    public static void main(String[] args) {
        List<String> ls = Arrays.asList("Box", "Robot");
        ls.forEach(s -> System.out.println(s)); // 람다식 기반
        ls.forEach(System.out::println);    // 메소드 참조 기반
    }
}
```
- System.out::println  
전달되는 인자를 println으로 흘려보내는 구나 라고 판단할 수 있다.  
그리고나서 forEach 메소드의 참조 변수 선언을 보고 Consumer의 추상 메소드 accept를 보고 이 안에 println 메소드가 들어간다고 생각하면 된다. (메소드 참조의 약속)

- **`Consumer<? super T> action = System.out::println`**
<br>
<br>

## 1.7 인스턴스 메소드 참조 2: 인스턴스 없이 인스턴스 메소드 참조
```java
public interface ToIntBiFunction<T, U> {
    int applyAsInt(T t, U u);
}
```
- 두개의 인자를 전달받고 int 형 값을 반환해주면 된다.

```java
int applyAsInt(T t, U u){
    t.method(U u);
}
```
- 어떠한 메소드든 마음대로 정의할 수 있지만, 이런 메소드를 정의하고 싶다고 가정하자.    
첫 번째 인자를 대상으로해서 첫 번째 인자가 가지고 있는 메소드를 호출하고, 이 메소드의 인자로 두 번째 인자를 흘려보내는 상황이다.  
이 상황에서는 메소드 정보만 남기자 라고 약속을 한다.  
<br>

```java
class IBox {
    private int n;

    
    public IBox(int i) {
        n = i;
    }

    public int larger(IBox b) {
        if (n > b.n) {
            return n;
        } else {
            return b.n;
        }
    }

    public static void main(String[] args) {
        IBox ib1 = new IBox(5);
        IBox ib2 = new IBox(7);
        
        // 두 상자에 저장된 값 비교하여 더 큰 값 반환
        ToIntBiFunction<IBox, IBox> bf = (b1, b2) -> b1.larger(b2);
        int bigNum = bf.applyAsInt(ib1, ib2);
        System.out.println(bigNum);
    }
}
```
- ToIntBiFunction<IBox, IBox> bf = (b1, b2) -> b1.larger(b2);  
-> **`ToIntBiFunction<IBox, IBox> bf = IBox::larger;`**  
약속에 근거한 줄인 표현  
IBox 클래스를 찾아봐야한다. larger가 static 메소드가 아니다. 인스턴스 메소드이다.  
첫 번째 약속에 해당하는 경우가 아니다. 그렇다면 첫 번째 인자를 대상으로 메소드를 호출하면서 두 번째 혹은 그 이후에 등장하는 인자를 larger 메소드의 인자로 흘려보낸다 라는 약속을 떠올려야 한다.  

- 약속을 근거로 해서 문장을 해석할 수 있어야 한다.  
applyAsInt 메소드의 몸체를 IBox::larger 로 채웠다.
<br>
<br>

## 1.8 생성자 참조
```java
Function<T, R> {
    R apply(T t)
}


class StringMaker {
    public static void main(String[] args) {
        Function<char[], String> f = ar -> {
            return new String(ar);
        };
        char[] src = {'R', 'o', 'b', 'o', 't'};
        String str = f.apply(src);
        System.out.println(str);
    }
}
```
- 람다식을 작성 시 인스턴스 생성 후 이의 참조 값을 반환하는 경우가 있다.  
이 경우 메소드 참조 방식을 쓸 수 있다.  

```java
Function<char[], String> f = ar -> {
    return new String(ar);
};
                        ↓
Function<char[], String> f = ar -> new String(ar);
                        ↓
Function<char[], String> f = String::new;
```
- String::new  
클래스의 이름을 남겨라.  
나는 이 String을 new 하겠다 (=생성하겠다)라는 정보만 남겨라.  
이런식으로 클래스의 이름 정보만 남기라는 것이 세번째 약속이다. 

- new를 보고서는 직감해야 한다. 왼쪽에 있는 String이 생성자 정보라는 것을.  
String을 생성하겠다라는 것이라고 판단하고 new String()을 떠올린다.  
인자로 정보를 받아야 하므로 Function을 보고 Function의 첫번째 인자 char[]를 그대로 new String의 인자에 전달하겠다라는 것을 판단해서 이제 apply라는 몸체를 채운다.  
String apply\<char[] t>의 몸체는 new String(t) 라고 정의할 수 있다.
<br>
<br>


# 2. Optional 클래스
- if ~ else 문으로 인해 코드의 흐름이 나뉜다는 것은 Reading 하는 입장에서는 불편하다.  
없앨 수 있으면 없애는 것이 좋은데 어떻게 없앨 수가 있을까?

- if ~ else문 자체를 없애지는 못하는데, 내가 작성해야 될 if ~ else에 대한 책임을 누군가에게 떠넘길 수는 있다.  
우리가 작성하는 코드는 if ~ else가 안보이게 할 수 있다. 다른 코드 영역으로 떠넘겨서 안보이게끔 할 수 있다.  

- 그렇다면 우리 코드에서 if ~ else가 등장해야 될 때 누구에게 떠넘길 수 있을까?  
자바에서 제공하는 **`Optional`** 클래스에게 떠넘길 수 있다. (100%는 아님)  
우리 코드 라인에서는 if ~ else 가 사라진다.

- if ~ elae 문의 구성을 Optional 클래스에게 대신 떠넘김으로 인해서 코드의 흐름을 단순화 시킬 수가 있다.  
그렇다면 어떠한 경우에 대체할 수 있는지 알아보자.
<br>

## 2.1 NullPointerException 예외의 발생 상황 1
```java
class Friend {
    String name;
    Company cmp;    // null 일 수 있음

    public Friend(String n, Company c) {
        name = n;
        cmp = c;
    }

    public String getName() {
        return name;
    }

    public Company getCmp() {
        return cmp;
    }
}

class Company {
    String cName;
    ContInfo cInfo; // null 일 수 있음

    public Company(String cName, ContInfo ci) {
        this.cName = cName;
        this.cInfo = ci;
    }

    public String getCName() {
        return cName;
    }

    public ContInfo getCInfo() {
        return cInfo;
    }
}

class ContInfo {    // 회사 정보에 속하는 회사 연락처
    String phone;   // null 일 수 있음
    String adrs;    // null 일 수 있음

    public ContInfo(String ph, String ad) {
        this.phone = ph;
        this.adrs = ad;
    }

    public String getPhone() {
        return phone;
    }

    public String getAdrs() {
        return adrs;
    }
}
```
- 가장 좋은 것은 null로 초기화 할 인스턴스 변수가 없는 것이다.   
그러나 허용해야 하는 경우도 있다.
<br>
<br>

## 2.2 NullPointerException 예외의 발생 상황 2
```java
public static void showComAddr(Friend f) {
    String addr = null;
    
    if (f != null) {    // 인자로 전달된 것이 null 일 수 있으니
        Company com = f.getCmp();
        
        if (com != null) {  // 회사 정보가 없을 수도 있으니  
            ContInfo info = com.getCInfo();

            if (info != null) {     // 회사의 연락처 정보가 없을 수도 있으니
                addr = info.getAdrs();
            }
        }

        if (addr != null) { // 위의 코드에서 주소 정보를 얻지 못했을 수 있으니
            System.out.println(addr);
        } else {
            System.out.println("There's no address information.");
        }
    }
}
```
- null 가능성에 대비하는 코드의 작성은 번거롭고 그 코드 스타일도 만족스러운 편은 아니다.  
이를 해결하기 위해 등장한 것이 Optional 클래스이다.  
<br>
<br>

## 2.3 Optional 클래스의 기본적인 사용 방법 1
```java
public final class Optional<T> extends Object{
    private final T value;
    ...
```
- private final T value;  
이 참조변수를 통해 저장을 하는 일종의 래퍼 클래스
<br>

```java
public static void main(String[] args) {
    Optional<String> os1 = Optional.of(new String("Toy1")); // of 는 null 을 허용하지 않음
    Optional<String> os2 = Optional.ofNullable(new String("Toy2")); // of 는 null 을 허용하지 않음

    if (os1.isPresent()) {
        System.out.println(os1.get());
    }

    if (os2.isPresent()) {
        System.out.println(os2.get());
    }
}
```
```bash
Toy1
Toy2
```
<br>
<br>

## 2.4 Optional 클래스의 기본적인 사용 방법 2
```java
public void ifPresent(Consumer<? super T> consumer)
    Consumer<T> 
        void accept(T t)

class StringOptional2 {
    public static void main(String[] args) {
        Optional<String> os1 = Optional.of(new String("Toy1"));
        Optional<String> os2 = Optional.ofNullable(new String("Toy2"));
        os1.ifPresent(s -> System.out.println(s));  // 람다식 버전
        os2.ifPresent(System.out::println); // 메소드 참조 버전
    }
}
```
```bash
Toy1
Toy2
```
<br>
<br>

## 2.5 Optional 클래스를 사용하면 if ~ else 문을 대신할 수 있다.
```java
class ContInfo {    
    String phone;   // null 일 수 있음(가정)
    String adrs;    // null 일 수 있음(가정)

    public ContInfo(String ph, String ad) {
        this.phone = ph;
        this.adrs = ad;
    }

    public String getPhone() {
        return phone;
    }

    public String getAdrs() {
        return adrs;
    }
}
```

```java
public static void main(String[] args) {
        ContInfo ci = new ContInfo(null, "Republic of Korea");
        String phone;
        String addr;

        if (ci.phone != null) {
            phone = ci.getPhone();
        } else {
            phone = "There is no phone number";
        }

        if (ci.adrs != null) {
            addr = ci.getAdrs();
        } else {
            addr = "There is no address";
        }

        System.out.println(phone);
        System.out.println(addr);
    }
```
```bash
There is no phone number
Republic of Korea
```
<br>
<br>

## 2.6 Optional 클래스를 사용하면 if ~ else 문을 대신할 수 있다: map 메소드의 소개
```java
public<U> Optional<U> map(Function<? super T, ? extends U> mapper)

public interface Function<T, R> {
    R apply(T t);
    ...
}
```

```java
public static void main(String[] args) {
    Optional<String> os1 = Optional.of("Optional String");
    Optional<String> os2 = os1.map(s -> s.toUpperCase());
    System.out.println(os2.get());

    Optional<String> os3 = os1.map(s -> s.replace(' ', '_'))
                                .map(s -> s.toLowerCase());
    System.out.println(os3.get());
}
```
```bash
OPTIONAL STRING
optional_string
```
- map 메소드는 apply 메소드가 반환하는 대상을 Optional 인스턴스에 담아서 반환한다.
<br>
<br>

## 2.7 Optional 클래스를 사용하면 if ~ else 문을 대신할 수 있다: orElse 메소드의 소개
```java
public static void main(String[] args) {
    Optional<String> os1 = Optional.empty();
    Optional<String> os2 = Optional.of("So Basic"); // null 이 아니라는 확신 아니야?

    String s1 = os1.map(s -> s.toString())
                    .orElse("Empty");

    String s2 = os2.map(s -> s.toString())
                    .orElse("Empty");
    System.out.println(s1);
    System.out.println(s2);
}
```
```bash
Empty
So Basic
```
- Optional 인스턴스를 대상으로 orElse 메소드를 호출하면 orElse를 호출하면서 전달된 인스턴스가 대신 반환된다.
<br>
<br>

## 2.8 Optional 클래스를 사용하면 if ~ else 문을 대신할 수 있다: 최종 결론
### 2.8.1 이전 코드
```java
public static void main(String[] args) {
    ContInfo ci = new ContInfo(null, "Republic of Korea");
    String phone;
    String addr;

    if (ci.phone != null) {
        phone = ci.getPhone();
    } else {
        phone = "There is no phone number";
    }

    if (ci.adrs != null) {
        addr = ci.getAdrs();
    } else {
        addr = "There is no address";
    }

    System.out.println(phone);
    System.out.println(addr);
}
```
<br>

### 2.8.2 Optional 클래스 사용한 코드
```java
public static void main(String[] args) {
        Optional<ContInfo> ci = Optional.of(new ContInfo(null, "Republic of Korea"));

        String phone = ci.map(c -> c.getPhone())
                         .orElse("There is no phone number");

        String addr = ci.map(c -> c.getAdrs())
                        .orElse("There is no address");

        System.out.println(phone);
        System.out.println(addr);
    }
```
<br>
<br>

## 2.9 showCompAddr 메소드의 개선 결과
```java
public<U> Optional<U> map(Function<? super T, ? extends U> mapper)

public interface Function<T, R> {
    R apply(T t);
    ...
}
```
### 2.9.1 개선 전
```java
public static void showComAddr(Friend f) {
    String addr = null;

    if (f != null) {    
        Company com = f.getCmp();

        if (com != null) {
            ContInfo info = com.getCInfo();

            if (info != null) {    
                addr = info.getAdrs();
            }
        }

        if (addr != null) { 
            System.out.println(addr);
        } else {
            System.out.println("There's no address information.");
        }

    }
}
```
<br>

### 2.9.2 개선 후
```java
public static void showComAddr(Optional<Friend> f) {
    String addr = f.map(Friend::getCmp)
                   .map(Company::getCInfo)
                   .map(ContInfo::getAdrs)
                   .orElse("There is no...");
    System.out.println(addr);
}

public static void main(String[] args) {
    ContInfo ci = new ContInfo("321-444-577", "Republic of Korea");

    Company cp = new Company("Kelly Co.", ci);
    Friend frn = new Friend("Kim DangBo", cp);
    showComAddr(Optional.of(frn));
}
```
<br>
<br>

## 2.10 Optional 클래스의 flatMap 메소드: Optional 클래스를 코드 전반에 사용하기 1
```java
public static void main(String[] args) {
    Optional<String> os1 = Optional.of("Optional String");
    Optional<String> os2 = os1.map(s -> s.toUpperCase());
    System.out.println(os2.get());

    Optional<String> os3 = os1.flatMap(s -> Optional.of(s.toLowerCase()));
    System.out.println(os3.get());
}
```
- map은 람다식이 반환하는 내용물을 Optional로 감싸서 반환한다.  
flatMap은 그냥 반환한다. 따라서 필요하면 직접 Optional로 감싸야 한다. 
<br>
<br>

## 2.11 Optional 클래스의 flatMap 메소드: Optional 클래스를 코드 전반에 사용하기 2
```java
class ContInfo {    
    Optional<String> phone;   // null 일 수 있음
    Optional<String> adrs;    // null 일 수 있음

    public ContInfo(Optional<String> ph, Optional<String> ad) {
        this.phone = ph;
        this.adrs = ad;
    }

    public Optional<String> getPhone() {
        return phone;
    }

    public Optional<String> getAdrs() {
        return adrs;
    }
}


public static void main(String[] args) {
    Optional<ContInfo> ci = Optional.of(
                new ContInfo(Optional.ofNullable(null), Optional.of("Korea"))
    );
        
    String phone = ci.flatMap(c -> c.getPhone())
                        .orElse("There is no phone number");

    String addr = ci.flatMap(c -> c.getAdrs())
                    .orElse("There is no address");

    System.out.println(phone);
    System.out.println(addr);
}
```
<br>
<br>

## 2.12 Optional 클래스의 flatMap 메소드: Optional 클래스를 코드 전반에 사용하기 3

<br>
<br>


# 3. OptionalInt, OptionalLong, OptionalDouble 클래스
## 3.1 Optional과 OptionalXXX와의 차이점
### 3.1.1 Optional
```java
public static void main(String[] args) {
    Optional<Integer> oi1 = Optional.of(3);
    Optional<Integer> oi2 = Optional.empty();

    System.out.print("[Step 1.] : ");
    oi1.ifPresent(i -> System.out.print(i + "\t"));
    oi2.ifPresent(i -> System.out.print(i));
    System.out.println();

    System.out.print("[Step 2.] : ");
    System.out.print(oi1.orElse(100) + "\t");      
    System.out.print(oi2.orElse(100) + "\t");       
    System.out.println();
}
```
<br>

### 3.1.2 OptionalXXX
```java
public static void main(String[] args) {
    OptionalInt oi1 = OptionalInt.of(3);
    OptionalInt oi2 = OptionalInt.empty();

    System.out.print("[Step 1.] : ");
    oi1.ifPresent(i -> System.out.print(i + "\t"));
    oi2.ifPresent(i -> System.out.print(i));
    System.out.println();

    System.out.print("[Step 2.] : ");
    System.out.print(oi1.orElse(100) + "\t");       
    System.out.print(oi2.orElse(100) + "\t");  
    System.out.println();
}
```
- OptionalXXX 클래스들은 Optional 클래스보다 그 기능이 제한적이다.  