# 문제

[14395번: 4연산](https://www.acmicpc.net/problem/14395)

- 정수 s가 주어진다. 정수 s의 값을 t로 바꾸는 최소 연산 횟수를 구하는 프로그램을 작성하시오.
    1. `s = s + s; (출력: +)`
    2. `s = s - s; (출력: -)`
    3. `s = s * s; (출력: *)`
    4. `s = s / s; (출력: /) (s가 0이 아닐때만 사용 가능)`

# 제한사항

### 입력

- 첫째 줄에 s와 t가 주어진다. (1 ≤ s, t ≤ $10^9$)

### 출력

- 첫째 줄에 정수 s를 t로 바꾸는 방법을 출력한다.
    - s와 t가 같은 경우에는 0을, 바꿀 수 없는 경우에는 -1을 출력한다.
    - 가능한 방법이 여러 가지라면, 사전 순으로 앞서는 것을 출력한다.
- 연산의 아스키 코드 순서는 '*', '+', '-', '/' 이다.

# 코드

```java
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer stk = new StringTokenizer(br.readLine());
    	int s = Integer.parseInt(stk.nextToken());
    	int t = Integer.parseInt(stk.nextToken());
    	if(s == t) {
    		System.out.println(0);
    		return;
    	}
    	ArrayDeque<node> que = new ArrayDeque<>();
    	Set<Long> visited = new HashSet<>();
    	que.offer(new node(s,""));
    	visited.add((long) s);
    	StringBuilder sb = null;
    	while(!que.isEmpty()) {//최소연산 -> BFS
    		node cur = que.poll();
    		if(cur.index==t){//s가 t로 바뀌었다면 출력하고 종료
    			System.out.println(cur.oper);
    			return;
    		}
    		
    		long next = cur.index*cur.index;//사전순으로 연산하여 탐색
    		if(next<=1000000000 && !visited.contains(next)) {//최대값보다 작고, 탐색하지 않은 경우일때만 진행
    			visited.add(next);
    			sb = new StringBuilder();
    			sb.append(cur.oper).append("*");
    			que.offer(new node(next,sb.toString()));
    		}
    		
    		next = cur.index+cur.index;
    		if(next<=1000000000 && !visited.contains(next)) {
    			visited.add(next);
    			sb = new StringBuilder();
    			sb.append(cur.oper).append("+");
    			que.offer(new node(next,sb.toString()));
    		}
    		
    		
    		next = cur.index-cur.index;
    		if(!visited.contains(next)) {
    			visited.add(next);
    			sb = new StringBuilder();
    			sb.append(cur.oper).append("-");
    			que.offer(new node(next,sb.toString()));
    		}
    		
    		if(cur.index == 0) continue;//0은 나눗셈 X
    		next = cur.index/cur.index;
    		if(!visited.contains(next)) {
    			visited.add(next);
    			sb = new StringBuilder();
    			sb.append(cur.oper).append("/");
    			que.offer(new node(next,sb.toString()));
    		}
    	}
    	System.out.println(-1);
    }
    static class node{
    	long index;
    	String oper;
		public node(long index, String oper) {
			super();
			this.index = index;
			this.oper = oper;
		}
    }
}
```

# 풀이

- 사용 알고리즘 : BFS
- 최소 연산 횟수를 구해야 하기 때문에 BFS를 사용한다.
- visited의 경우 배열을 통해 해결할 수도 있지만 최대값이 10^9이기 때문에 그냥 Set을 이용해 해결했다.
- 사전순으로 출력하기 위해 *+-/순으로 탐색하여 큐에 삽입한다.