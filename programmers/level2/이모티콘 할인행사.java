public class Solution {
	private static int[] answer = {0,0};
	private static int[] discounts;
	private static int maxDiscount = 10;
	private static int minDiscount = 40;
	private static void dfs(int depth,int[] discount,int[][] users, int[] emoticons) {
		if(depth == emoticons.length) {
			int user = 0;
			int cost = 0;
			for(int i = 0; i < users.length; i++) {
				int curCost = 0;
				for(int j = 0; j < emoticons.length; j++) {
					if(users[i][0] <= discount[j]) {
						curCost += emoticons[j]*(100-discount[j])/100;
					}
				}
				if(users[i][1] <= curCost) {
					user++;
				}else {
					cost+=curCost;
				}
			}
			if(user > answer[0]) {
				answer[0] = user;
				answer[1] = cost;
			}else if(user == answer[0] && cost > answer[1]) {
				answer[1] = cost;
			}
			return;
		}
		for(int i = 0; i < discounts.length; i++) {
			discount[depth] = discounts[i];
			dfs(depth+1,discount,users,emoticons);
		}
	}
	
    public int[] solution(int[][] users, int[] emoticons) {
    	for(int i = 0; i < users.length; i++) {
    		int cur = ((int)Math.ceil(users[i][0]/10.0))*10;
    		if(cur < minDiscount) {
    			minDiscount = cur;
    		}
    		
    		if(cur > maxDiscount) {
    			maxDiscount = cur;
    		}
    	}
    	
    	discounts = new int[(maxDiscount-minDiscount)/10+1];
    	int cur = minDiscount;
    	for(int i = 0; i < discounts.length; i++) {
    		discounts[i] = cur;
    		cur+=10;
    	}
    	dfs(0,new int[emoticons.length],users,emoticons);
        return answer;
    }
}
