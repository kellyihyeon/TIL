# 스트림 2

## 목차
1. [스트림의 생성과 연결](#1-스트림의-생성과-연결)  
   1.1 [스트림의 생성: 스트림 생성에 필요한 데이터를 직접 전달](#11-스트림의-생성-스트림-생성에-필요한-데이터를-직접-전달)  
   1.2 [스트림의 생성: 다양한 of 메소드들](#12-스트림의-생성-다양한-of-메소드들)  
   1.3 [병렬 스트림으로 변경](#13-병렬-스트림으로-변경)  
   1.4 [스트림의 연결](#14-스트림의-연결)  

2. [스트림의 중간연산](#2-스트림의-중간연산)  
   2.1 [맵핑(Mappting)에 대한 추가 정리](#21-맵핑mappting에-대한-추가-정리)  
   2.2 [맵핑(Mapping)에 대한 추가 정리: 예제 1](#22-맵핑mapping에-대한-추가-정리-예제-1)  
   2.3 [맵핑(Mapping)에 대한 추가 정리: 예제 2](#23-맵핑mapping에-대한-추가-정리-예제-2)  
   2.4 [정렬](#24-정렬)  
   2.5 [IntStream, LongStream, DoubleStream의 정렬](#25-intstream-longstream-doublestream의-정렬)  
   2.6 [루핑(Looping)](#26-루핑looping)  

3. [스트림의 최종 연산](#3-스트림의-최종-연산)  
   3.1 [sum(), count(), average(), min(), max()](#31-sum-count-average-min-max)  
   3.2 [forEach](#32-foreach)  
   3.3 [allMatch, anyMatch, noneMatch](#33-allmatch-anymatch-nonematch)  
   3.4 [collect: 스트림에 있는 데이터를 모아라](#34-collect-스트림에-있는-데이터를-모아라)  
   3.5 [병렬 스트림에서의 collect](#35-병렬-스트림에서의-collect)  

<br>

# 1. 스트림의 생성과 연결
## 1.1 스트림의 생성: 스트림 생성에 필요한 데이터를 직접 전달
```java
static <T> Stream<T> of(T t)
static <T> Stream<T> of(T...values)

public static void main(String[] args) {
    // 네 개의 값으로 이뤄진 스트림 생성
    Stream.of(11, 22, 33, 44)
            .forEach(n -> System.out.print(n + "\t"));
    System.out.println();

    // 하나의 String 인스턴스로 이뤄진 스트림 생성
    Stream.of("So Simple")
            .forEach(n -> System.out.print(n + "\t"));
    System.out.println();

    List<String> s1 = Arrays.asList("Lumpy", "Bubblegum", "Marcelline");
    // 하나의 컬렉션 인스턴스로 이뤄진 스트림 생성
    Stream.of(s1)
            .forEach(w -> System.out.print(w + "\t"));
    System.out.println();
}
```
```bash
11	22	33	44	
So Simple	
[Lumpy, Bubblegum, Marcelline]	
```
<br>
<br>

## 1.2 스트림의 생성: 다양한 of 메소드들
```java
static DoubleStream of(double...values)     // DoubleStream의 메소드

static DoubleStream of(double t)    // DoubleStream의 메소드

static IntStream of(int ...values)   // IntStream의 메소드

static IntStream of(int t)   // IntStream의 메소드

static LongStream of(long...values)     // LongStream의 메소드

static LongStream of(long t)     // LongStream의 메소드

static IntStream range(int startInclusive, int endExclusive) // IntStream의 메소드

static IntStream rangeClosed(int startInclusive, int endInclusive) // IntStream의 메소드

static LongStream range(Long startInclusive, Long endExclusive) // LongStream 메소드

static LongStream rangeClosed(Long startInclusive, Long endInclusive) // LongStream 메소드
```
<br>
<br>

## 1.3 병렬 스트림으로 변경
```java
Stream<T> parallel();

DoubleStream parallel();

IntStream parallel();

LongStream parallel();
```
```java
public static void main(String[] args) {
    List<String> ls = Arrays.asList("Lumpy", "Bubblegum", "Marcelline");
    Stream<String> ss = ls.stream();

    BinaryOperator<String> lc = (s1, s2) -> {
        if (s1.length() > s2.length()) {
            return s1;
        } else {
            return s2;
        }
    };

    String str = ss.parallel()
            .reduce("", lc);
    System.out.println(str);
}
```
<br>
<br>

## 1.4 스트림의 연결
```java
static <T> Stream<T> concat(Stream<? extends T> a, Stream<? extends T> b)

static DoubleStream concat(DoubleStream a, DoubleStream b)

static IntStream concat(IntStream a, IntStream b)

static LongStream concat(LongStream a, LongStream b)
```
```java
public static void main(String[] args) {
    Stream<String> ss1 = Stream.of("Cake", "Milk");
    Stream<String> ss2 = Stream.of("Lemon", "Jelly");
    
    // 스트림을 하나로 묶은 후 출력
    Stream.concat(ss1, ss2)
            .forEach(s -> System.out.println(s));
}
```
<br>
<br>

# 2. 스트림의 중간연산
## 2.1 맵핑(Mappting)에 대한 추가 정리
[Stream\<T>의 map 시리즈 메소드들] 1:1 맵핑

```java
<R> Stream<R> map(Function<T, R> mapper)

IntSream mapToInt(ToIntFunction<T> mapper)

LongStream mapToLong(ToLongFunction<T> mapper)

DoubleStream mapToDouble(ToDoubleFunction<T> mapper)
```
- 하나의 데이터를 하나의 데이터로 바꿔준다.
<br>

[Stream\<T>의 flatMap 시리즈 메소드들] 1:* 맵핑
```java
<R> Stream<R> flatMap(Function<T, Stream<R>> mapper)

IntSream flatMapToInt(ToIntFunction<T, IntStream> mapper)

LongStream flatMapToLong(ToLongFunction<T, LongStream> mapper)

DoubleStream flatMapToDouble(ToDoubleFunction<T, DoubleStream> mapper)
```
- flatMap에 전달할 람다식에서는 '스트림을 생성하고 이를 반환'

- 하나의 데이터가 여러개의 데이터로 매핑이 된다.
<br>
<br>

## 2.2 맵핑(Mapping)에 대한 추가 정리: 예제 1
```java
public static void main(String[] args) {
    Stream<String> ss1 = Stream.of("MY_AGE", "YOUR_LIFE");

    // 아래 람다식에서 스트림을 생성
    Stream<String> ss2 = ss1.flatMap(s -> Arrays.stream(s.split("_")));
    ss2.forEach(s -> System.out.print(s + "\t"));
    System.out.println();
}
```
```bash
MY  AGE  YOUR  LIFE	
```
<br>
<br>

## 2.3 맵핑(Mapping)에 대한 추가 정리: 예제 2
```java
class ReportCard {
    private int kor;
    private int eng;
    private int math;

    public ReportCard(int kor, int eng, int math) {
        this.kor = kor;
        this.eng = eng;
        this.math = math;
    }

    public int getKor() {
        return kor;
    }

    public int getEng() {
        return eng;
    }

    public int getMath() {
        return math;
    }
}

public static void main(String[] args) {
    ReportCard[] cards = {
            new ReportCard(70, 80, 90),
            new ReportCard(90, 80, 70),
            new ReportCard(80, 80, 80)
    };

    // ReportCard 인스턴스로 이뤄진 스트림 생성
    Stream<ReportCard> sr = Arrays.stream(cards);

    // 학생들의 점수 정보로 이뤄진 스트림 생성
    IntStream si = sr.flatMapToInt(
            r -> IntStream.of(r.getKor(), r.getEng(), r.getMath())
    );

    // 평균을 구하기 위한 최종 연산 average 진행
    double avg = si.average().getAsDouble();
    System.out.println("avg = " + avg);
}
```
```bash
avg = 80.0
```
- ReportCard 인스턴스에서 국어, 영어, 수학 점수를 꺼낸 후 이것을 하나의 스트림으로 생성한다. (1:*)
<br>
<br>

## 2.4 정렬
```java
Stream<T> sorted(Comparator<? super T> comparator)    // Stream<T>의 메소드

Stream<T> sorted()

IntStream<T> sorted()

LongStream<T> sorted()

DoubleStream<T> sorted()

public static void main(String[] args) {
    Stream.of("Box", "Apple", "Robot")
            .sorted()
            .forEach(s -> System.out.print(s + "\t"));
    System.out.println();

    Stream.of("Box", "Apple", "Rabbit")
            .sorted((s1, s2) -> s1.length() - s2.length())
            .forEach(s -> System.out.print(s + "\t"));
    System.out.println();
}
```
```bash
Apple	Box	    Robot	
Box	    Apple	Rabbit
```
<br>
<br>

## 2.5 IntStream, LongStream, DoubleStream의 정렬
```java
public static void main(String[] args) {
    IntStream.of(3, 9, 4, 2)
            .sorted()
            .forEach(d -> System.out.print(d + "\t"));
    System.out.println();

    DoubleStream.of(3.3, 6.2, 1.5, 8.3)
            .sorted()
            .forEach(d -> System.out.print(d + "\t"));
    System.out.println();
}
```
```bash
2	3	4	9	
1.5	3.3	6.2	8.3	
```
- 기본적으로 오름차순 정렬.
<br>
<br>

## 2.6 루핑(Looping)
대표적인 루핑 연산으로는 forEach가 있다. 이는 '최종 연산'으로 사용한다.  
반면 다음 메소드들은 '중간 연산'으로 루핑 연산을 한다.

```java
Stream<T> peek(Consumer<? super T> action)

IntStream peek(IntConsumer action)

LongStream peek(LongConsumer action)

DoubleStream peek(DoubleConsumer action)

public static void main(String[] args) {
    // 최종 연산이 생략된 스트림의 파이프라인
    IntStream.of(1, 3, 5)
            .peek(d -> System.out.print(d + "\t"));
    System.out.println();

    // 최종 연산이 존재하는 스트림의 파이프라인
    IntStream.of(5, 3, 1)
            .peek(d -> System.out.print(d + "\t"))
            .sum();
    System.out.println();
}
```
```bash
5	3	1	
```
- sum의 결과를 가지고 아무것도 하지 않았지만, sum처럼 최종 연산을 하고 안하고의 차이는 중간 연산이 실행 되냐 안되느냐로 귀결이 된다.
<br>
<br>

# 3. 스트림의 최종 연산
## 3.1 sum(), count(), average(), min(), max()
[IntStream의 메소드들] long, double에 대해서도 정의되어 있음
```java
int sum()

long count()

OptionalDouble average()

OptionalInt min()

OptionInt max()
```
```java
public static void main(String[] args) {
    int sum = IntStream.of(1, 3, 5, 7, 9)
            .sum();
    System.out.println("sum = " + sum);

    long cnt = IntStream.of(1, 3, 5, 7, 9)
            .count();
    System.out.println("cnt = " + cnt);

    IntStream.of(1, 3, 5, 7, 9)
            .average()
            .ifPresent(av -> System.out.println("avg = " + av));

    IntStream.of(1, 3, 5, 7, 9)
            .min()
            .ifPresent(mn -> System.out.println("min = " + mn));

    IntStream.of(1, 3, 5, 7, 9)
            .max()
            .ifPresent(mx -> System.out.println("max = " + mx));
}
```
```bash
sum = 25
cnt = 5
avg = 5.0
min = 1
max = 9
```
<br>
<br>

## 3.2 forEach
```java
void forEach(Consumer<? super T> action)

void forEach(IntConsumer action)

void forEach(LongConsumer action)

void forEach(DoubleConsumer action)
```
<br>
<br>

## 3.3 allMatch, anyMatch, noneMatch
[Stream\<T> 의 메소드들]  
IntStream, LongStream, DoubleStream에도 정의된 메소드들

```text
boolean allMatch(Predicate<? super T> predicate)
-> 스트림의 데이터가 조건을 모두 만족하는가?


boolean anyMatch(Predicate<? super T> predicate)
-> 스트림의 데이터가 조건을 하나라도 만족하는가?

boolean noneMatch(Predicate<? super T> predicate)
-> 스트림의 데이터가 조건을 하나도 만족하지 않는가?
```

```java
public static void main(String[] args) {
    boolean b = IntStream.of(1, 2, 3, 4, 5)
            .allMatch(n -> n % 2 == 0);
    System.out.println("모두 짝수이다. " + b);

    b = IntStream.of(1, 2, 3, 4, 5)
            .anyMatch(n -> n % 2 == 0);
    System.out.println("짝수가 하나는 있다. " + b);

    b = IntStream.of(1, 2, 3, 4, 5)
            .noneMatch(n -> n % 2 == 0);
    System.out.println("짝수가 하나도 없다 " + b);
}
```
```bash
모두 짝수이다. false
짝수가 하나는 있다. true
짝수가 하나도 없다. false
```
<br>
<br>

## 3.4 collect: 스트림에 있는 데이터를 모아라
[Stream\<T>의 메소드] 
IntStream, LongSteam, DoubleStream에도 정의된 메소드
```java
<R> R collect(Supplier<R> supplier,
              BiConsumer<R, ? super T> accumulator,
              BiConsumer<R, R> combiner);
```

```java
public static void main(String[] args) {
    String[] words = {"Hello", "Box", "Robot", "Toy"};
    Stream<String> ss = Arrays.stream(words);

    List<String> ls = ss.filter(s -> s.length() < 5)
                        .collect(() -> new ArrayList<>(),
                                (c, s) -> c.add(s),
                                (lst1, lst2) -> lst1.addAll(lst2));
    System.out.println(ls);
}
```

```bash
[Box, Toy]
```
- filter 연산을 거친 후 살아남은 데이터들을 따로 모으고 싶을 때, 따로 모아서 출력이 아니라 다른 일을 해보고 싶을 때 collect를 사용한다.

- stream을 통과한 데이터를 따로 모으기 위해서는 첫 번째 인자를 통해서 어디에 담을 것인지를, 두 번째 인자를 통해서 어떤 방법으로 담을 것인지를 전달한다.  
세 번째 인자는 아래에서 설명.

- (lst1, lst2) -> lst1.addAll(lst2));  
순차 스트림에서는 의미 없다.  
그러나 병렬 스트림을 고려하여 병렬 스트림에 의미 있는 문장을 작성해야 한다. null 전달 시 예외가 발생한다. 
<br>
<br>

## 3.5 병렬 스트림에서의 collect
```java
public static void main(String[] args) {
    String[] words = {"Hello", "Box", "Robot", "Toy"};
    Stream<String> ss = Arrays.stream(words);

    List<String> ls = ss.parallel()
                        .filter(s -> s.length() < 5)
                        .collect(() -> new ArrayList<>(),
                                (c, s) -> c.add(s),
                                (lst1, lst2) -> lst1.addAll(lst2));
    System.out.println(ls);
}
```
```bash
[Box, Toy]
```
- 병렬 스트림 생성을 통한 성능의 향상은 실행 결과를 기반으로 평가해야 한다.

- 서로 공유하지 않는 형태의 병렬처리가 이루어진다. 
코어가 각자 ArrayList를 만들어서 담는다. (3개의 컬렉션 인스턴스)

- (lst1, lst2) -> lst1.addAll(lst2));  
3개의 컬렉션 인스턴스를 취합해서 반환해야 하므로 어떤 식으로 취합을 할지 제시를 해줘야 한다.  
이 람다식을 기반으로 데이터를 취합한다. 