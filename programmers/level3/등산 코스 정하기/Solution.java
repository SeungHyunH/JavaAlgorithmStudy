import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        HashMap<Integer,ArrayList<int[]>> graph = new HashMap<>();
        ArrayDeque<int[]> que = new ArrayDeque<>();
        Set<Integer> isGate = new HashSet<>();
        int[] intensitys = new int[n+1];
        Arrays.fill(intensitys,Integer.MAX_VALUE);
        for(int gate : gates) {
        	isGate.add(gate);
        	que.offer(new int[] {gate,0});
        	intensitys[gate]=0;
        }
        Set<Integer> isSummits = new HashSet<>();
        for(int summit : summits) {
        	isSummits.add(summit);
        }
        for(int[] path : paths) {
        	if(!graph.containsKey(path[0]))
        		graph.put(path[0],new ArrayList<>());
        	if(!graph.containsKey(path[1]))
        		graph.put(path[1],new ArrayList<>());
        	
        	if(isGate.contains(path[0])||isSummits.contains(path[1])) {
        		graph.get(path[0]).add(new int[] {path[1],path[2]});
        	}else if(isGate.contains(path[1])||isSummits.contains(path[0])) {
        		graph.get(path[1]).add(new int[] {path[0],path[2]});
        	}else {
        		graph.get(path[0]).add(new int[] {path[1],path[2]});
        		graph.get(path[1]).add(new int[] {path[0],path[2]});
        	}
        }
        while(!que.isEmpty()) {
        	int[] cur = que.poll();
        	if(intensitys[cur[0]] < cur[1]) continue;
        	
        	for(int[] next : graph.get(cur[0])) {
        		int intensity = Math.max(intensitys[cur[0]],next[1]);
        		if(intensitys[next[0]] > intensity) {
        			intensitys[next[0]] = intensity;
        			que.offer(new int[] {next[0],intensity});
        		}
        	}
        }
        int[] answer = {-1,Integer.MAX_VALUE};
        Arrays.sort(summits);
        for(int summit : summits) {
        	if(intensitys[summit] < answer[1]) {
        		answer[0]=summit;
        		answer[1]=intensitys[summit];
        	}
        }
        return answer;
    }
}