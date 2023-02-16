# 문제

[1700번: 멀티탭 스케줄링](https://www.acmicpc.net/problem/1700)

- 멀티탭에 전기용품을 k개를 꽂아서 사용하려고 한다.
    - 현재 멀티탭에 꽂은 전기용품의 개수가 n개라면 전기용품을 뽑고 꽂아야 한다.
- 전기용품의 사용순서가 주어졌을 때, 가장 적게 뽑은 횟수는 무엇인가?

# 제한사항

### 입력

- 첫 줄에는 멀티탭 구멍의 개수 N (1 ≤ N ≤ 100)과 전기 용품의 총 사용횟수 K (1 ≤ K ≤ 100)가 정수로 주어진다.
- 두 번째 줄에는 전기용품의 이름이 K 이하의 자연수로 사용 순서대로 주어진다.
    - 각 줄의 모든 정수 사이는 공백문자로 구분되어 있다.

### 출력

- 하나씩 플러그를 빼는 최소의 횟수를 출력하시오.

# 코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(stk.nextToken());
		int k = Integer.parseInt(stk.nextToken());
		int answer = 0;
		stk = new StringTokenizer(br.readLine());
		HashSet<Integer> set = new HashSet<>();
		HashMap<Integer,ArrayDeque<Integer>> map = new HashMap<>();
		int[] input = new int[k];
		for(int i = 0; i < k; i++){
			input[i] = Integer.parseInt(stk.nextToken());
			if(map.containsKey(input[i])) {
				map.get(input[i]).offerLast(i);
			}else {
				ArrayDeque<Integer> temp = new ArrayDeque<>();
				temp.offerLast(i);
				map.put(input[i],temp);
			}
		}
		for(int i = 0; i < k; i++){
			map.get(input[i]).pollFirst();
			if(set.size() < n) set.add(input[i]); //콘센트가 비어있을 때
			else if(set.contains(input[i])) continue; //이미 사용하고 있을 때
			else {
				boolean find = false;
				int maxIdx = 0;
				for(int key : set) {
					if(map.get(key).size() == 0) {//현재 사용중인 전기용품이 앞으로 사용하지 않을 때
						find = true;
						answer++;
						set.remove(key);
						set.add(input[i]);
						break;
					}
					maxIdx = Math.max(maxIdx, map.get(key).peekFirst()); //현재 사용중인 전기용품 중 가장 늦게 사용하는 것을 선택
				}
				if(find) continue;//이미 콘센트를 뽑은 경우
				set.remove(input[maxIdx]);
				set.add(input[i]);
				answer++;
			}
		}
		System.out.println(answer);
	}
}
```

# 풀이

- 사용 알고리즘 : 그리디
- 최적의 방법으로 전기용품을 사용하려면 앞으로 사용할 전기용품은 최대한 뽑지 않아야 한다.
    - 만약 현재 멀티탭에 꽂은 전기용품이 앞으로 전부 사용한다면 무엇을 뽑아야 할까?
- 전기용품의 경우의 수는 다음과 같다.
    1. 콘센트가 비어있는 경우 → Set에 추가
    2. 이미 사용중인 전기용품인 경우 → Continue
    3. 전기 콘센트를 뽑아야 하는 경우
        1. 앞으로 사용하지 않는 전기용품이 멀티탭에 꽂아있는 경우
        2.  현재 멀티탭에 꽂은 전기용품이 앞으로 전부 사용하는 경우
            - 현재 멀티탭에 꽂아있는 전기용품들 중 가장 늦게 사용하는 전기용품을 뽑는다.
- 각 전기용품 별로 앞으로 사용할 전기용품의 Index들을 저장한다.  사용할 때마다 저장한 Index를 삭제하게 되면 위의 3번에서 앞으로 전기용품이 언제 사용할지 파악할 수 있게 된다.
    - 3.a의 경우 저장되어 있는 Index가 없다면 앞으로 사용하지 않는 것이므로 전기용품을 뽑으면 된다.
    - 3.b의 경우 가장 먼저 저장한 전기용품의 Index들을 비교해 가장 늦게 사용할 전기용품을 선택해 뽑는다.
- 이 문제의 핵심은 3번을 그리디하게 처리하고, 3.b에서 가장 늦게 사용하는 전기용품을 뽑는 다는 것을 파악하는 것이다.