import java.util.Stack;

public class Solution {
    public int solution(int[] ingredient) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        int[] burger = {1,2,3,1};
        for(int i = 0; i < ingredient.length; i++) {
        	stack.add(ingredient[i]);
        	int curIndex = stack.size()-1;
        	int burgerIndex = 3;
        	if(stack.size() >= 4) {
        		for(int j = curIndex; j > curIndex-4; j--) {
            		if(burger[burgerIndex--] == stack.elementAt(j)) {
            			if(j == curIndex-3) {
                			answer+=1;
                			for(int k = 0; k < 4; k++) {
                				stack.pop();
                			}
                		}
            		}else {
            			break;
            		}
            	}
    		}
        }
        return answer;
    }
}
