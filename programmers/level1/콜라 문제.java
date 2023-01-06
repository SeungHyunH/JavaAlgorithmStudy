class Solution {
    public int solution(int a, int b, int n) {
        int answer = 0;
        while(n >= a){
            int cur = (n/a)*b;
            answer += cur;
            n = n%a+cur;
        }
        return answer;
    }
}