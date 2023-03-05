# 문제

[9205번: 맥주 마시면서 걸어가기](https://www.acmicpc.net/problem/9205)

- 맥주를 마시며 축제에 가려고 한다. 50m당 한 병씩 마셔야 한다.
    - 맥주는 빈병을 포함하여 20병까지 들 수 있다.
    - 편의점에서 빈병을 버리고 새 맥주를 살 수 있다.
- 집, 축제, 편의점의 좌표가 주어졌을 때 축제에 도달할 수 있는지 판별하라

# 제한사항

### 입력

- 첫째 줄에 테스트 케이스의 개수 t가 주어진다. (t ≤ 50)
- 각 테스트 케이스의 첫째 줄에는 맥주를 파는 편의점의 개수 n이 주어진다. (0 ≤ n ≤ 100).
- 다음 n+2개 줄에는 상근이네 집, 편의점, 펜타포트 락 페스티벌 좌표가 주어진다. 각 좌표는 두 정수 x와 y로 이루어져 있다. (두 값 모두 미터, -32768 ≤ x, y ≤ 32767)
- 송도는 직사각형 모양으로 생긴 도시이다. 두 좌표 사이의 거리는 x 좌표의 차이 + y 좌표의 차이 이다. (맨해튼 거리)

### 출력

- 각 테스트 케이스에 대해서 상근이와 친구들이 행복하게 페스티벌에 갈 수 있으면 "happy", 중간에 맥주가 바닥나서 더 이동할 수 없으면 "sad"를 출력한다.

# 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int testCase = 0; testCase < T; testCase++){
			int n = Integer.parseInt(br.readLine());
			int[][] pos = new int[n+2][2];
			ArrayList<int[]>[] graph = new ArrayList[n+2];
			StringTokenizer stk;
			for(int i = 0; i <= n+1; i++){
				graph[i] = new ArrayList<>();
				stk = new StringTokenizer(br.readLine());
				pos[i][0] = Integer.parseInt(stk.nextToken());
				pos[i][1] = Integer.parseInt(stk.nextToken());
			}
			for(int i = 0; i <= n+1; i++){
				for(int j = i+1; j <= n+1; j++){
					int dist = Math.abs(pos[i][0]-pos[j][0])+Math.abs(pos[i][1]-pos[j][1]);
					if(dist > 1000) continue;
					graph[i].add(new int[] {j,dist});
					graph[j].add(new int[] {i,dist});
				}
			}
			
			PriorityQueue<int[]> que = new PriorityQueue<>((a,b)->a[1]-b[1]);
			int[] dist = new int[n+2];
			Arrays.fill(dist,Integer.MAX_VALUE);
			que.offer(new int[] {0,0});
			dist[0]=0;
			while(!que.isEmpty()) {
				int[] cur = que.poll();
				
				if(cur[1] > dist[cur[0]]) continue;
				
				for(int[] next : graph[cur[0]]) {
					if(dist[next[0]] > cur[1]+next[1]) {
						dist[next[0]] = cur[1]+next[1];
						que.offer(new int[] {next[0],dist[next[0]]});
					}
				}
			}
			sb.append(dist[n+1] == Integer.MAX_VALUE ? "sad" : "happy").append('\n');
		}
		System.out.println(sb.toString());
	}
}
```

# 풀이

- 사용 알고리즘 : Dijkstra
- 문제를 요약하면 집에서 축제까지 도달할 수 있는지 여부를 물어보는 문제다.
- 집, 편의점, 축제가 정점이라고 생각하면 그래프로 표현이 가능하고, 중간에 간선의 길이가 20*50 = 1000m이하라는 조건이 있는 것이다.
    - 모든 편의점을 들렸다가 축제로 도착하지 않아도 되고, 출발지와 도착지가 정해져 있기 때문에 다익스트라를 이용해 풀 수 있는 문제다.
