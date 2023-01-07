import java.util.Stack;

public class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        Stack<Integer> dStack = new Stack<>();
        Stack<Integer> pStack = new Stack<>();
        for(int i = 0; i < n; i++) {
        	if(deliveries[i] > 0) {
        		dStack.add(i);
        	}
        	if(pickups[i] > 0) {
        		pStack.add(i);
        	}
        }
        
        
        
        while(!dStack.empty() && !pStack.empty()) {
        	answer += Math.max((dStack.peek()+1)*2, (pStack.peek()+1)*2);
        	
        	int box = 0;
        	while(!dStack.empty() && box <= cap) {
        		if(deliveries[dStack.peek()]+box <= cap) {
        			box += deliveries[dStack.peek()];
        		}else {
        			deliveries[dStack.peek()] -= (cap-box);
        			break;
        		}
        		dStack.pop();
        	}
        	
        	box = 0;
        	while(!pStack.empty() && box <= cap) {
        		if(pickups[pStack.peek()]+box <= cap) {
        			box += pickups[pStack.peek()];
        		}else {
        			pickups[pStack.peek()] -= (cap-box);
        			break;
        		}
        		pStack.pop();
        	}
        }
        
        while(!dStack.empty()) {
        	answer += (dStack.peek()+1)*2;
        	
        	int box = 0;
        	while(!dStack.empty() && box <= cap) {
        		if(deliveries[dStack.peek()]+box <= cap) {
        			box += deliveries[dStack.peek()];
        		}else {
        			deliveries[dStack.peek()] -= (cap-box);
        			break;
        		}
        		dStack.pop();
        	}
        }
        
        while(!pStack.empty()) {
        	answer += (pStack.peek()+1)*2;
        	
        	int box = 0;
        	while(!pStack.empty() && box <= cap) {
        		if(pickups[pStack.peek()]+box <= cap) {
        			box += pickups[pStack.peek()];
        		}else {
        			pickups[pStack.peek()] -= (cap-box);
        			break;
        		}
        		pStack.pop();
        	}
        }
        return answer;
    }
}
