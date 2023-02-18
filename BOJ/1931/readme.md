# 문제

[1931번: 회의실 배정](https://www.acmicpc.net/problem/1931)

- 회의실 한 개가 있는데 N개의 회의를 하기 위해 배정표를 만들려고 한다.
    - 각각의 회의는 시작시간과 종료시간이 주어진다.
    - 각각의 회의는 중간에 중단할 수 없다.
    - 회의의 종료와 동시에 다른 회의를 시작할 수 있다.
    - 회의 시작과 종료시간이 같다면 시작하자마자 끝난 것으로 간주한다.
- 최대 회의의 수를 구하시오

# 제한사항

### 입력

- 첫째 줄에 회의의 수 N(1 ≤ N ≤ 100,000)이 주어진다.
- 둘째 줄부터 N+1 줄까지 각 회의의 정보가 주어지는데 이것은 공백을 사이에 두고 회의의 시작시간과 끝나는 시간이 주어진다.
    - 시작 시간과 끝나는 시간은 $2^{31}-1$보다 작거나 같은 자연수 또는 0이다.

### 출력

- 첫째 줄에 최대 사용할 수 있는 회의의 최대 개수를 출력한다.

# 코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] times = new int[n][2];
		StringTokenizer stk = null;
		for(int i = 0; i < n; i++){
			stk = new StringTokenizer(br.readLine());
			times[i][0] = Integer.parseInt(stk.nextToken());
			times[i][1] = Integer.parseInt(stk.nextToken());
		}
		Arrays.sort(times,(a,b)->a[1]==b[1] ? a[0]-b[0] : a[1]-b[1]);
		int answer = 0;
		int end = 0;
		for(int[] time : times) {
			if(time[0] >= end) {
				answer++;
				end = time[1];
			}
		}
		System.out.println(answer);
	}
}
```

# 풀이

- 사용 알고리즘 : 그리디
- 이 문제의 경우 시작시간을 기준으로 푸는 것과 종료시간을 기준으로 푸는 아이디어를 생각할 수 있다.
    - 시작시간을 기준으로 정렬해 푼다면 생기는 문제는 종료시간이 매우 길어서 n이 걸린다고 하면 중간에 다른 회의들은 못하고 한 개의 회의만 할 수 있다.
    - 종료시간을 기준으로 한다면 최적의 방법으로 할 수 있는데, 종료시간을 기준으로 우선적으로 정렬하되 시작시간도 정렬되어 있어야  한다. 그렇게 되면 종료시간보다 나중에 시작하는 회의만 세면 된다.