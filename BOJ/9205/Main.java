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
