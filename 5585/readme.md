# 문제

[5585번: 거스름돈](https://www.acmicpc.net/problem/5585)

- 거스름돈을 주려고 한다. 잔돈으로  500엔, 100엔, 50엔, 10엔, 5엔, 1엔이 충분히 있고  언제나 거스름돈 개수가 가장 적게 잔돈을 준다.
- 1000원을 냈을 때 받을 잔돈의 개수를 구하시오.

# 제한사항

### 입력

- 입력은 한줄로 이루어져있고, 타로가 지불할 돈(1 이상 1000미만의 정수) 1개가 쓰여져있다.

### 출력

- 제출할 출력 파일은 1행으로만 되어 있다. 잔돈에 포함된 매수를 출력하시오.

# 코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int money = 1000-Integer.parseInt(br.readLine());
		int answer = 0;
		if(money >= 500) {
			answer += 1;
			money -= 500;
		}
		
		if(money >= 100) {
			answer += money/100;
			money -= (money/100)*100;
		}
		
		if(money >= 50) {
			answer += money/50;
			money -= (money/50)*50;
		}
		
		if(money >= 10) {
			answer += money/10;
			money -= (money/10)*10;
		}
		
		if(money >= 5) {
			answer += money/5;
			money -= (money/5)*5;
		}
		
		answer += money;//남은 돈은 1원짜리로 거슬러줌 
		System.out.println(answer);
	}
}
```

# 풀이

- 사용 알고리즘 : 그리디
- 최소 개수로 거스름돈을 주려면 큰 돈부터 거슬러 주면 된다. 따라서 500, 100, 10, 5, 1원 순서로 거슬러 준다.
    - 최대 금액부터 거스름돈을 주기 시작하면 이미 거슬러준 최대금액에 대해서는 신경안써도 되기 때문에 그리디로 풀 수 있다.