# 형변환 방법 

# 1. double형과 float형
```java
public class InputOutput12 {

    // 1023
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Double doubleNum = sc.nextDouble();

        String str = String.valueOf(doubleNum);
        String[] arr = str.split("\\.");

        int num1 = Integer.parseInt(arr[0]);
        int num2 = Integer.parseInt(arr[1]);

        System.out.print(num1 +"\n" +num2);
    }
}
```
*[기초 100제] 1023번 문제*

- [정답] 자료형을 double형으로 지정했을 때:
  ```bash
  > 123456.56789

    123456
    56789
  ```
- [오답] 자료형을 float형으로 지정했을 때:
   ```bash
   >123456.56789

    123456
    57
   ```
<br>


# 2. char -> int 형 변환하는 방법
*[기초 100제] 1025번 문제*

```java
import java.util.Scanner;

public class InputOutput14 {

    // 1025
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int num = sc.nextInt();
        String str = String.valueOf(num);

        int arr[] = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {    //7 5 2 5 4
            arr[i] = Integer.parseInt(String.valueOf(str.charAt(i)));

            // data[i]=s.charAt(i)-'0';
        }

        int[] numArr = new int[5];
        numArr[0] = arr[0] * 10000;
        numArr[1] = arr[1] * 1000;
        numArr[2] = arr[2] * 100;
        numArr[3] = arr[3] * 10;
        numArr[4] = arr[4];

        for (int i = 0; i < 5; i++) {
            System.out.println("[" + numArr[i] + "]");
        }

    } //main
}
```

- 아스키코드를 알 필요 없이 String or Char 형을 int형으로 변환하는 방법은 charAt() - '0'을 사용하는 것이다.