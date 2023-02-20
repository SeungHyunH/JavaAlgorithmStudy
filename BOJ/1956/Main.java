import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int v = Integer.parseInt(stk.nextToken());
		int e = Integer.parseInt(stk.nextToken());
		int[][] dp = new int[v+1][v+1];
		int MAXVALUE = 10001*400;
		for(int i = 1; i <= v; i++){
			Arrays.fill(dp[i],MAXVALUE);
		}
		for(int i = 0; i < e; i++){
			stk = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(stk.nextToken());
			int b = Integer.parseInt(stk.nextToken());
			int c = Integer.parseInt(stk.nextToken());
			
			dp[a][b]=c;
		}
		
		for(int i = 1; i <= v; i++){
			for(int j = 1; j <= v; j++){
				if(i == j) continue;
				for(int k = 1; k <= v; k++) {
					if(k == i || k == j) continue;
					dp[i][j] = Math.min(dp[i][j],dp[i][k]+dp[k][j]);
				}
			}
		}
		
		int answer = MAXVALUE;
		for(int i = 1; i <= v; i++){
			for(int j = i+1; j <= v; j++){
				answer = Math.min(answer, dp[i][j]+dp[j][i]);
			}
		}
		
		if(answer == MAXVALUE)
			System.out.println(-1);
		else
			System.out.println(answer);
	}
}
