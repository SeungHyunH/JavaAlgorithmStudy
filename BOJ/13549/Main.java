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
