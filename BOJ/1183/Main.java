import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		List<Integer> times = new LinkedList<>();
		StringTokenizer stk = null;
		for(int i = 0; i < n; i++){
			stk = new StringTokenizer(br.readLine());
			times.add(Integer.parseInt(stk.nextToken())-Integer.parseInt(stk.nextToken()));
			
		}
		if(n%2 == 1) {
			System.out.println(1);
		}else {
			Collections.sort(times);
			System.out.println(Math.abs(times.get(n/2)-times.get(n/2-1)+1));
		}
	}
}