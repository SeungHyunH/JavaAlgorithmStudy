import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] times = new int[n][2];
		StringTokenizer stk = null;
		for(int i = 0; i < n; i++){
			stk = new StringTokenizer(br.readLine());
			times[i][0] = Integer.parseInt(stk.nextToken());
			times[i][1] = Integer.parseInt(stk.nextToken());
		}
		Arrays.sort(times,(a,b)->a[1]==b[1] ? a[0]-b[0] : a[1]-b[1]);
		int answer = 0;
		int end = 0;
		for(int[] time : times) {
			if(time[0] >= end) {
				answer++;
				end = time[1];
			}
		}
		System.out.println(answer);
	}
}