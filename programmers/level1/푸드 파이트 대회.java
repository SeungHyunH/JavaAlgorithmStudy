class Solution {
    public String solution(int[] food) {
        String answer = "0";
        for(int i = food.length-1; i >= 0; i--) {
        	String curFood = Integer.toString(i);
        	int cnt = food[i]/2;
        	answer = curFood.repeat(cnt) + answer + curFood.repeat(cnt);
        }
        return answer;
    }
}