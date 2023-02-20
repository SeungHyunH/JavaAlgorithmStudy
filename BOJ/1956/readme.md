# 문제

[1956번: 운동](https://www.acmicpc.net/problem/1956)

- V개의 마을과 E개의 도로로 구성되어 있는 도시가 있다.
    - 마을은 1번부터 V번까지 있다.
    - 일방도로이다.
- 도로의 정보가 주어졌을 때 도로길이의 합이 가장 작은 사이클의 길이를 구하시오.
    - 두 마을을 왕복하는 것도 사이클을 이루는 것이다.

# 제한사항

### 입력

- 첫째 줄에 V와 E가 빈칸을 사이에 두고 주어진다. (2 ≤ V ≤ 400, 0 ≤ E ≤ V(V-1))
- 다음 E개의 줄에는 각각 세 개의 정수 a, b, c가 주어진다
    - a번 마을에서 b번 마을로 가는 거리가 c인 도로가 있다는 의미이다. (a → b임에 주의)
    - 거리는 10,000 이하의 자연수이다.
    - (a, b) 쌍이 같은 도로가 여러 번 주어지지 않는다.

### 출력

- 첫째 줄에 최소 사이클의 도로 길이의 합을 출력한다.
- 운동 경로를 찾는 것이 불가능한 경우에는 -1을 출력한다.

# 코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int v = Integer.parseInt(stk.nextToken());
		int e = Integer.parseInt(stk.nextToken());
		int[][] dp = new int[v+1][v+1];
		int MAXVALUE = 10001*400;
		for(int i = 1; i <= v; i++){
			Arrays.fill(dp[i],MAXVALUE);
		}
		for(int i = 0; i < e; i++){
			stk = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(stk.nextToken());
			int b = Integer.parseInt(stk.nextToken());
			int c = Integer.parseInt(stk.nextToken());
			
			dp[a][b]=c;
		}
		
		for(int i = 1; i <= v; i++){
			for(int j = 1; j <= v; j++){
				if(i == j) continue;
				for(int k = 1; k <= v; k++) {
					if(k == i || k == j) continue;
					dp[i][j] = Math.min(dp[i][j],dp[i][k]+dp[k][j]);
				}
			}
		}
		
		int answer = MAXVALUE;
		for(int i = 1; i <= v; i++){
			for(int j = i+1; j <= v; j++){
				answer = Math.min(answer, dp[i][j]+dp[j][i]);
			}
		}
		
		if(answer == MAXVALUE)
			System.out.println(-1);
		else
			System.out.println(answer);
	}
}
```

# 풀이

- 사용 알고리즘 : 플로이드 와샬
- 전형적인 플로이드 와샬 문제다. 각 마을로 이동할 때의 최소거리를 플로이드 와샬로 구한 뒤, 왕복할 때 가장 짧은 거리를 찾으면 된다.
    - 다익스트라는 출발지점부터 모든 점까지의 최소거리를 구하는 알고리즘이다.
    - 플로이드와샬은 모든 지점으로부터 모든 점까지의 최소거리를 구하는 알고리즘이다.
- 최대 도로이동 거리의 합은 모든 마을을 거쳐서 왕복하는 경우이다. 따라서 최대 도로이동 거리의 합 = 400*최대도로의 길이 이다.