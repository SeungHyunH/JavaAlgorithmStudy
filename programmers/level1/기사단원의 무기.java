public class Solution {
	private int getDiviserCount(int number) {
		int count = 0;
		for(int i = 1; i*i <= number; i++) {
			if(i*i == number) {count++;}
			else if(number%i == 0) {count+=2;}
		}
		
		return count;
	}
    public int solution(int number, int limit, int power) {
        int answer = 0;
        for(int i = 1; i <= number; i++) {
        	int curPower = getDiviserCount(i);
        	
        	if(curPower > limit) {
        		answer+=power;
        	}else {
        		answer+=curPower;
        	}
        }
        return answer;
    }
}