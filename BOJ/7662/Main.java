import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer stk = null;
		for(int testCase = 1; testCase <= T; testCase++){
			int k = Integer.parseInt(br.readLine());
			PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b)-> a < b ? 1 : -1);
			PriorityQueue<Integer> minHeap = new PriorityQueue<>();
			HashMap<Integer,Integer> heapCounter = new HashMap<>();
			for(int commandCount = 0; commandCount < k; commandCount++){
				stk = new StringTokenizer(br.readLine());
				if(stk.nextToken().equals("I")) {
					int cur = Integer.parseInt(stk.nextToken());
					maxHeap.offer(cur);
					minHeap.offer(cur);
					heapCounter.put(cur,heapCounter.getOrDefault(cur, 0)+1);
				}else {
					if(Integer.parseInt(stk.nextToken()) == -1) {
						while(minHeap.size()!=0 && heapCounter.get(minHeap.peek())==0) {
							minHeap.poll();
						}
						if(minHeap.size()!=0) {
							heapCounter.put(minHeap.peek(),heapCounter.get(minHeap.peek())-1);
							minHeap.poll();
						}
					}else {
						while(maxHeap.size()!=0 && heapCounter.get(maxHeap.peek())==0) {
							maxHeap.poll();
						}
						if(maxHeap.size()!=0) {
							heapCounter.put(maxHeap.peek(),heapCounter.get(maxHeap.peek())-1);
							maxHeap.poll();
						}
					}
				}
			}
			while(minHeap.size()!=0 && heapCounter.get(minHeap.peek())==0) {
				minHeap.poll();
			}
			while(maxHeap.size()!=0 && heapCounter.get(maxHeap.peek())==0) {
				maxHeap.poll();
			}
			
			if(maxHeap.size() == 0 ||minHeap.size() == 0) {
				sb.append("EMPTY\n");
			}else {
				sb.append(maxHeap.peek()).append(" ").append(minHeap.peek()).append("\n");
			}
		}
		System.out.println(sb.toString());
	}
}
