
# 부동 소수점(floating point)

- 흔히 float나 double 연산 시 오차가 발생하여 숫자에 민감한 금융 거래 등에는 BigDecimal을 사용해야 한다고 설명한다. 
- 보통 그 이유를 컴퓨터는 "정확히" 실수 표현이 불가능해 근사 과정에서 연산과정 중 오차가 쌓임에 따라 최종적으로 가시적인 오차가 발생한다고 말한다.


## float와 double의 오차
- float, double 오차 표시 코드

```java


import java.math.BigDecimal;

public class Test {
public static void main(String[] args) {
double doubleValue = 0.0d;
for (int i = 0; i < 100; i++) {
doubleValue += 0.1d;
}
double doubleResult = 0.1d * 100;
System.out.println("expected value is : " + doubleResult + " result is : " + doubleValue);
float floatValue = 0.0f;
for (int i = 0; i < 100; i++) {
floatValue += 0.1f;
}
float floatResult = 0.1f * 100;
System.out.println("expected value is : " + floatResult + " result is : " + floatValue);

    }

}
``` 
- 연산 결과
```shell
expected value is : 10.0 result is : 9.99999999999998
expected value is : 10.0 result is : 10.000002
```

위 코드의 결과처럼 소수점 연산은 반복하다보면 원하던 결과와 사뭇 다른 값을 얻게 된다. 이러한 정확성 손실로 인해 금전 거래 시 조심해야 함을 항상 말한다. 물론 소수 연산이 필요없다면 정수 자료형으로 연산하겠으나 금융거래는 %연산이 빈번히 이루어지니 소수를 항상 고려해야 한다.

## 실수 자료형의 오차 - 부동소수점

실수 표현에서의 오차는 부동소수점 표현에 기인한다. 자리수가 굉장히 크거나 무한 소수 처리에 있어 적절한 표기를 위해 표준에 맞춰서 근사값을 다루기 때문이다. 일례로 10진수 기반일 경우 분모의 원소가 2, 5로만 구성되면 유한소수이나 나머지 인수가 들어가면 무한소수가 되어 표기를 위해 적절히 반올림 처리를 했다. 이는 컴퓨터에서도 마찬가지로 2진수 기반인 경우 분모에 2를 제외한 인수가 포함되면 무한소수가 된다.

예를 들어 0.1f를 표현하려면 BigDecimal 표기 기준 0.100000001490116119384765625을 근사하고 있는데 이걸 비트로 표현하면 1100이 무한히 반복된다. 적당히 잘라서 표현하면 (e = −4 s = 110011001100110011001101)이 된다.

## BigDecimal, BigInteger
BigDecimal은 double보다 범위가 약 2배 이상 길어 좀 더 정밀한 연산이 가능하다. 그리고 근사값이 아니라 유효숫자와 소수점 숫자를 기준으로 표현한다. 따라서 소수 연산이나 금융 거래 시 BigDecimal이나 BigInteger를 써야한다.

### BigDecimal 사용 시 주의할 점
소수부 표현이 정확하다고 BigDecimal foo = new BigDecimal(0.1);을 넣어버리면 생성자 내부에 자바가 0.1을 근사한 값을 넣어버려 실제로는 0.1과는 완전히 다른 값인 0.1000000000000000055511151231257827021181583404541015625을 저장해버린다. 따라서 BigDecimal을 사용해 소수연산을 할 때는 반드시 String으로 변환하여 근사할 여지가 없게끔 처리해야 한다.

```java

import java.math.BigDecimal;

public class Test {
    public static void main(String[] args) {
        BigDecimal bigDouble = new BigDecimal(0.1);
        BigDecimal bigString = new BigDecimal("0.1");
        System.out.println("BigDecimal(0.1) value is : " + bigDouble);
        System.out.println("BigDecimal(\"0.1\") value is : " + bigString);
    }
}
```

```shell
BigDecimal(0.1) value is : 0.1000000000000000055511151231257827021181583404541015625
BigDecimal("0.1") value is : 0.1
```

## precision과 scale
precision은 유효숫자 개수를 의미하며 scale은 소수 자리의 개수를 의미한다. 즉, 예를 들어 12.345는 precision이 5이고 scale이 3이 된다.

```shell
19/100 = 0.19 // integer=19, scale=2
21/110 = 0.190 // integer=190, scale=3
```


## 부동 소수점 IEEE 754 표현 방식
- 부호부 (Sign) : 1비트. 숫자의 부호를 나타내며, 양수일 때 0, 음수일 때1이 됩니다.
- 지수부 (Exponent) : 8비트. 지수를 나타냅니다.
- 가수부 (Mantissa) : 23비트. 가수 또는 유효숫자를 나타냅니다.

#### 참고

- https://8iggy.tistory.com/232
- https://velog.io/@mjk3136/%EB%B6%80%EB%8F%99%EC%86%8C%EC%88%98%EC%A0%90%EC%97%90-%EB%8C%80%ED%95%B4-%EC%95%8C%EA%B8%B0
- https://steemit.com/kr/@modolee/floating-point