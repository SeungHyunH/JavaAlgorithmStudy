# 문제

[2573번: 빙산](https://www.acmicpc.net/problem/2573)

- 빙산의 정보가 2차원 배열로 주어진다. 각각의 값은 빙산의 높이이고, 0은 바다이다.
- 매년 각각의 빙산은 동시에 녹으며, 동/서/남/북에 맞닿아 있는 바다의 수만큼 녹는다.
    - 빙산이 녹으면 빙산의 높이는 맞닿아 있는 바다의 수 만큼 낮아진다.
- 빙산이 두 덩어리이상으로 분리되는 최초의 시간을 구하시오.

# 제한사항

### 입력

- 첫 줄에는 이차원 배열의 행의 개수와 열의 개수를 나타내는 두 정수 N과 M이 한 개의 빈칸을 사이에 두고 주어진다.
    - N과 M은 3 이상 300 이하이다.
- 그 다음 N개의 줄에는 각 줄마다 배열의 각 행을 나타내는 M개의 정수가 한 개의 빈 칸을 사이에 두고 주어진다.
    - 각 칸에 들어가는 값은 0 이상 10 이하이다.
    - 배열에서 빙산이 차지하는 칸의 개수, 즉, 1 이상의 정수가 들어가는 칸의 개수는 10,000 개 이하이다.
    - 배열의 첫 번째 행과 열, 마지막 행과 열에는 항상 0으로 채워진다.

### 출력

- 첫 줄에 빙산이 분리되는 최초의 시간(년)을 출력한다.
    - 만일 빙산이 다 녹을 때까지 분리되지 않으면 0을 출력한다.

# 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	static int n,m;
	static int[][]map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		map = new int[n][m];

		for(int i = 0; i < n; i++){
			stk = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++){
				map[i][j]=Integer.parseInt(stk.nextToken());
			}
		}
		
		int year = 0;
		while(true) {
			int res = countIsland();
			
			if(res >= 2) {
				break;
			}else if(res == 0) {
				year = 0;
				break;
			}
			
			bfs();
			year++;
		}
		System.out.println(year);
	}
	
	static int countIsland() {
		boolean[][] visited = new boolean[n][m];
		int cnt = 0;
		for(int i = 0; i < n; i++){
			for(int j = 0; j < m; j++){
				if(!visited[i][j] && map[i][j] > 0) {
					dfs(i,j,visited);
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	static void dfs(int y,int x,boolean[][] visited) {
		visited[y][x]=true;
		if(y-1 >= 0 && !visited[y-1][x] && map[y-1][x] > 0) dfs(y-1,x,visited);
		if(y+1 < n && !visited[y+1][x] && map[y+1][x] > 0) dfs(y+1,x,visited);
		if(x-1 >= 0 && !visited[y][x-1] && map[y][x-1] > 0) dfs(y,x-1,visited);
		if(x+1 < m && !visited[y][x+1] && map[y][x+1] > 0) dfs(y,x+1,visited);
	}
	
	static void bfs() {
		ArrayDeque<int[]> que = new ArrayDeque<>();
		boolean[][] visited = new boolean[n][m];
		
		for(int i = 0; i < n; i++){
			for(int j = 0; j < m; j++){
				if(map[i][j] > 0) {
					que.offer(new int[] {i,j});
					visited[i][j]=true;
				}
			}
		}
		
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			
			int cnt = 0;
			if(cur[0]-1 >= 0 && !visited[cur[0]-1][cur[1]] && map[cur[0]-1][cur[1]] == 0)cnt++;
			if(cur[0]+1 < n && !visited[cur[0]+1][cur[1]] && map[cur[0]+1][cur[1]] == 0) cnt++;
			if(cur[1]-1 >= 0 && !visited[cur[0]][cur[1]-1] && map[cur[0]][cur[1]-1] == 0) cnt++;
			if(cur[1]+1 < m && !visited[cur[0]][cur[1]+1] && map[cur[0]][cur[1]+1] == 0) cnt++;
			
			map[cur[0]][cur[1]]-=cnt;
			if(map[cur[0]][cur[1]] < 0) map[cur[0]][cur[1]]=0;
		}
		
	}
}
```

# 풀이

- 사용 알고리즘 : 시뮬레이션 (DFS+BFS)
- 우선 전체적인 구조는 다음과 같다.
    1.  DFS를 이용해 빙산의 덩어리 수를 카운팅한다.(BFS로 구현해도 상관은 없다.)
    2. 덩어리가 1개 이하면 BFS를 이용해 빙산을 녹인다.
        1. 동시에 녹는 것을 표현하기 위해 visited배열을 사용해 빙산이었다가 녹아서 바다가 된 것은 탐색을 제외해준다.
- 간단한 구조이지만, 시뮬레이션인 만큼 생각보다 디버깅이 어려운 문제이므로 천천히 접근할 필요성이 있다.
