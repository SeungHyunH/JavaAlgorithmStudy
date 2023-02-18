# 문제

- 마법사 N명이 머글과 약속을 잡았다. 각 마법사는 한 명의 머글을 만날예정이다.
- 각 마법사는 기다리는 시간을 최소화 하기 위해 모든 약속 시간을 T씩 미루려고 한다.
    - 기다리는 시간은 먼저 도착한 사람이 늦게 도착한 사람이 도착할 때까지 기다리는 시간을 의미한다.
    - T는 음수일수도, 양수일수도 있다.
- 마법사의 약속 시간은 A1, A2, ..., AN이고, 도착 시간은 B1, B2, ..., BN이다.
- 약속 시간을 T만큼 미루면, 기다리는 시간의 합은 |Ai + T - Bi|의 합과 같다.
- 기다리는 시간의 합이 최소가 되는 서로 다른 정수 T의 개수를 구하시오.

# 제한사항

### 입력

- 첫째 줄에 N이 주어진다. 다음 N개의 줄에 Ai, Bi가 주어진다.

### 출력

- 첫째 줄에 기다리는 시간의 합이 최소인 서로 다른 정수 T의 개수를 출력한다.

# 코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		List<Integer> times = new LinkedList<>();
		StringTokenizer stk = null;
		for(int i = 0; i < n; i++){
			stk = new StringTokenizer(br.readLine());
			times.add(Integer.parseInt(stk.nextToken())-Integer.parseInt(stk.nextToken()));
			
		}
		if(n%2 == 1) {
			System.out.println(1);
		}else {
			Collections.sort(times);
			System.out.println(Math.abs(times.get(n/2)-times.get(n/2-1)+1));
		}
	}
}
```

# 풀이

- 사용 알고리즘 : 그리디
- 이 문제를 정리하면 |Ai + T - Bi |의 합이 최소가 되는 T의 개수를 찾는 것이다.
    - 정리하면 SUM ( ABS ( Ai - Bi + T ) )의 최소값을 구하는 것이다.
    - T = - (Ai - Bi)라고 가정했을 때, 결국 중간 값을 찾아야 한다는 것을 알 수 있다.
    - 홀수일 때는 무조건 중간 값이 1개일 수 밖에 없지만 짝수일때는 n/2 -1 ~ n/2 까지의 수가 중간값이 된다.
- 찾아보니 |Ai + T - Bi |의 합이 최소가 되는 T를 구하는 것은 수학적으로 증명이 되어있다고 한다. 자세한 증명이 궁금하다면 구글링을 해보자.