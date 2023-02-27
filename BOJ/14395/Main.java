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
    	while(!que.isEmpty()) {//BFS, 사전순으로 출력 = 최단거리 -> BFS
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