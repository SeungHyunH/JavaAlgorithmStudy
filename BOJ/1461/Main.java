import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		List<Integer> minusBooks = new LinkedList<>();
		List<Integer> plusBooks = new LinkedList<>();
		int answer = 0;
		int n = Integer.parseInt(stk.nextToken());
		int m = Integer.parseInt(stk.nextToken());
		stk = new StringTokenizer(br.readLine());
		while (stk.hasMoreTokens()) {
			int cur = Integer.parseInt(stk.nextToken());
			if (cur < 0) {
				minusBooks.add(-cur);
			} else {
				plusBooks.add(cur);
			}
		}

		Collections.sort(minusBooks,Collections.reverseOrder());
		Collections.sort(plusBooks,Collections.reverseOrder());
		
		for (int i = 0; i < minusBooks.size(); i = i + m) {
			answer += minusBooks.get(i) * 2;
		}

		for (int i = 0; i < plusBooks.size(); i = i + m) {
			answer += plusBooks.get(i) * 2;
		}

		if ((!minusBooks.isEmpty()) && (!plusBooks.isEmpty())) {
			answer -= minusBooks.get(0) > plusBooks.get(0) ? minusBooks.get(0) : plusBooks.get(0);
		}
		else if(minusBooks.isEmpty()) {
			answer -= plusBooks.get(0);
		}
		else {
			answer -= minusBooks.get(0);
		}
		System.out.println(answer);
	}
}
