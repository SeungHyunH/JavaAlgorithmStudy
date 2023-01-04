public class Solution {
	private static String[] validBabblings = {"aya", "ye", "woo", "ma"};
	private boolean checkExceptionBabbling(String babbling) {
		for(int i = 0; i < validBabblings.length; i++) {
			babbling = babbling.replaceAll(validBabblings[i], Integer.toString(i));
		}
		babbling = babbling.replaceAll("\\d", "");
		return babbling.length() == 0;
	}
	private boolean checkDuplicationBabbling(String babbling) {
		int curLength = babbling.length();
		for(int i = 0; i < validBabblings.length; i++) {
			babbling = babbling.replaceAll(validBabblings[i]+validBabblings[i],"");
			if(curLength != babbling.length()) {
				return false;
			}
		}
		return true;
	}
    public int solution(String[] babbling) {
        int answer = 0;
        for(int i = 0; i < babbling.length; i++) {
        	if(!checkExceptionBabbling(babbling[i])) {
        		continue;
        	}
        	if(!checkDuplicationBabbling(babbling[i])) {
        		continue;
        	}
        	answer+=1;
        }
        return answer;
    }
}
