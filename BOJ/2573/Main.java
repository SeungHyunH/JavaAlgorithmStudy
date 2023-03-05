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
