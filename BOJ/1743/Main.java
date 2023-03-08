import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk  = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(stk.nextToken());
		int m = Integer.parseInt(stk.nextToken());
		int k = Integer.parseInt(stk.nextToken());
		
		boolean[][] map = new boolean[n][m];
		for(int i = 0; i < k; i++){
			stk = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(stk.nextToken())-1;
			int x = Integer.parseInt(stk.nextToken())-1;
			map[y][x]=true;
		}
		
		int answer = 0;
		ArrayDeque<int[]> que = new ArrayDeque<>();
		for(int i = 0; i < n; i++){
			for(int j = 0; j < m; j++) {
				if(!map[i][j]) continue;
				que.clear();
				que.offer(new int[] {i,j});
				map[i][j]=false;
				int cnt = 1;
				while(!que.isEmpty()) {
					int[] cur = que.poll();
					if(cur[0]-1 >= 0 && map[cur[0]-1][cur[1]]) {//상
						que.offer(new int[] {cur[0]-1,cur[1]});
						map[cur[0]-1][cur[1]]=false;
						cnt++;
					}
					
					if(cur[0]+1 < n && map[cur[0]+1][cur[1]]) {//하
						que.offer(new int[] {cur[0]+1,cur[1]});
						map[cur[0]+1][cur[1]]=false;
						cnt++;
					}
					
					if(cur[1]-1 >= 0 && map[cur[0]][cur[1]-1]) {//좌
						que.offer(new int[] {cur[0],cur[1]-1});
						map[cur[0]][cur[1]-1]=false;
						cnt++;
					}
					
					if(cur[1]+1 < m && map[cur[0]][cur[1]+1]) {//우
						que.offer(new int[] {cur[0],cur[1]+1});
						map[cur[0]][cur[1]+1]=false;
						cnt++;
					}
				}
				answer = Math.max(answer,cnt);
			}
		}
		System.out.println(answer);
	}
}
