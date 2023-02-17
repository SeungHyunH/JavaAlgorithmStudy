# 문제

[1461번: 도서관](https://www.acmicpc.net/problem/1461)

- 책을 옮기려 한다. 현재 위치는 0이며 각 책의 좌표는 음수도 있고, 양수의 좌표도 있다.
- 한번에 M권의 책을 들 수 있다.
- 한걸음에 1칸씩 움직인다.
- 모든 책은 0의 위치에 있다.
- 마지막 책을 옮긴 후에는 0의 위치로 올 필요가 없다.
- 가장 최소로 움직인 횟수를 반환하시오.

# 제한사항

### 입력

- 첫째 줄에 책의 개수 N과, 세준이가 한 번에 들 수 있는 책의 개수 M이 주어진다.
- 둘째 줄에는 책의 위치가 주어진다.
    - N과 M은 50보다 작거나 같은 자연수이다.
    - 책의 위치는 0이 아니며, 절댓값은 10,000보다 작거나 같은 정수이다.

### 출력

- 첫째 줄에 정답을 출력한다.

# 코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(stk.nextToken());
		int m = Integer.parseInt(stk.nextToken());
		stk = new StringTokenizer(br.readLine());
		PriorityQueue<Integer> minusBooks = new PriorityQueue<>((a,b)->-(a-b));
		PriorityQueue<Integer> plusBooks = new PriorityQueue<>((a,b)->-(a-b));
		for(int i = 0; i < n; i++){
			int cur = Integer.parseInt(stk.nextToken()); 
			if(cur > 0) plusBooks.offer(cur);
			else minusBooks.offer(-cur);
		}
		int answer = 0;
		
		//가장 멀리 있는 책은 0으로 돌아오지 않아도 되므로 미리 빼줌
		if(plusBooks.isEmpty()) {
			answer -= minusBooks.peek();
		}else if(minusBooks.isEmpty()) {
			answer -= plusBooks.peek();
		}else {
			if(plusBooks.peek() < minusBooks.peek()) {
				answer -= minusBooks.peek();
			}else {
				answer -= plusBooks.peek();
			}
		}
		
		//양수좌표에 있는 책들을 옮김
		while(!plusBooks.isEmpty()) {
			int cur = plusBooks.poll();//현재 옮겨야 하는 책 중 가장 멀리 있는 책
			for(int i = 1; !plusBooks.isEmpty()&&i < m; i++){//손에 들 수 있는 책의 개수만큼 빼기
				plusBooks.poll();
			}
			answer += cur*2; //(손에 들고 있는 책들 중)가장 가까운 책+(손에 들고 있는 책들)책간의 거리 = (손에 들고 있는 책들 중)가장 멀리있는 책의 거리
		}
		
		//음수좌표에 있는 책들을 옮김
		while(!minusBooks.isEmpty()) {
			int cur = minusBooks.poll();//현재 옮겨야 하는 책 중 가장 멀리 있는 책
			for(int i = 1; !minusBooks.isEmpty()&&i < m; i++){//손에 들 수 있는 책의 개수만큼 빼기
				minusBooks.poll();
			}
			answer += cur*2;//(손에 들고 있는 책들 중)가장 가까운 책+(손에 들고 있는 책들)책간의 거리 = (손에 들고 있는 책들 중)가장 멀리있는 책의 거리
		}
		
		System.out.println(answer);
	}
}
```

```java
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
```

# 풀이

- 사용 알고리즘 : 그리디
- 음수의 위치에 책이 있다는 점을 활용해 함정을 파놓은 문제다. 단순하게 가까이 있는 책부터 집어서 움직인다면 양수 → 음수 → 양수 → 음수 이런식으로 이상하게 움직일 수도 있다.
    - 0의 위치에 간다면 책을 새롭게 들수있는데 위의 경우는 부호가 바뀌는 과정에서 0을 거치게 되고, 이 과정에서 m권의 책을 한번에 나를 수 있음에도 그러지 못하게 된다.
- 위의 경우때문에 양수와 음수의 좌표를 분리할 필요가 있다. 어차피 양수와 음수를 번갈아갔을 때 0의 좌표로 돌아오게 된다면 처음부터 따로따로 진행하면 된다.
- 모든 책이 0의 위치에 있다는 것은 책을 나르고 난 뒤에는 다시 책을  집기위해 0으로 돌아와야 한다는 것을 의미한다.
- 가장 멀리 있는 책은 다시 0으로 돌아올 필요가 없으므로 그리디로 탐색하기 전에 미리 최대 거리만큼 빼면 된다.
- 거리를 더할 때 손에 들고 있는 책들 중 가장 멀리 있는 책의 거리 x 2만큼 더하면 된다.
    - 가장 멀리 있는 책까지 움직인 뒤 가장 가까운 책으로 이동하고 그 뒤 0의 좌표로 돌아오는 거리와 손에 들고 있는 책들 중 가장 멀리 있는 책의 거리 x 2는 동일하기 때문이다.
- 처음의 코드를 제출하고 효율적으로 개선할려고 생각해보니 그냥 m권마다 거리를 더하면 된다.
    - 0,m,m*2, … 등의 index만 더하는 것과 위의 코드는 동일하다
    - 물론 이경우도 음수와 양수는 분리해야 한다