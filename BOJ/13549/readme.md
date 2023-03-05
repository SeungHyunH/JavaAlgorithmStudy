# 문제

[13549번: 숨바꼭질 3](https://www.acmicpc.net/problem/13549)

- 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다.
    - 수빈이는 걷거나 순간이동을 할 수 있다.
    - 만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다.
    - 순간이동을 하는 경우에는 0초 후에 2*X의 위치로 이동하게 된다.
- 수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오.

# 제한사항

### 입력

- 첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다.
    - N과 K는 정수이다.

### 출력

- 수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.

# 코드

```java
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer stk = new StringTokenizer(br.readLine());
    	int n = Integer.parseInt(stk.nextToken());
    	int k = Integer.parseInt(stk.nextToken());
    	int[] visited = new int[100000+1];
    	Arrays.fill(visited,-1);
    	ArrayDeque<Integer> que = new ArrayDeque<>();
    	que.offer(n);
    	visited[n]=0;
    	while(!que.isEmpty()) {
    		int cur = que.poll();
    		if(cur == k) {
    			System.out.println(visited[k]);
    			break;
    		}
    		if(cur+1 <= 100000 && (visited[cur+1]==-1 || visited[cur+1] > visited[cur]+1)) {
    			visited[cur+1]=visited[cur]+1;
    			que.offer(cur+1);
    		}
    		if(cur-1 >= 0 && (visited[cur-1]==-1 || visited[cur-1] > visited[cur]+1 )) {
    			visited[cur-1]=visited[cur]+1;
    			que.offer(cur-1);
    		}
    		if(cur*2 <= 100000 && (visited[cur*2]==-1 || visited[cur*2] > visited[cur])) {
    			visited[cur*2]=visited[cur];
    			que.offer(cur*2);
    		}
    	}
    }
}
```

# 풀이

- 사용 알고리즘 : BFS
- 동생을 찾을 수 있는 최단시간을 구하는 문제다.  순간이동 시 시간이 소요가 되지 않기 때문에 visited를 이용해 최단시간을 갱신하며 구한다.
    - 이미 동생을 찾았었더라도 순간이동하면서 더 빠른 시간에 탐색했을 수도 있다. 따라서 메모제이션을 활용해 더 빨리 탐색한 시간을 저장한다.
