# 문제

[2661번: 좋은수열](https://www.acmicpc.net/problem/2661)

- 숫자 1, 2, 3으로만 이루어지는 수열이 있다.
    - 임의의 길이의 인접한 두 개의 부분 수열이 동일한 것이 있으면, 그 수열을 나쁜 수열이라고 부른다.
    - 그렇지 않은 수열은 좋은 수열이다.
- 길이가 N인 좋은 수열들을 N자리의 정수로 보아 그중 가장 작은 수를 나타내는 수열을 구하는 프로그램을 작성하라.
    - 예를 들면, 1213121과 2123212는 모두 좋은 수열이지만 그 중에서 작은 수를 나타내는 수열은 1213121이다.

# 제한사항

### 입력

- 입력은 숫자 N하나로 이루어진다.
    - N은 1 이상 80 이하이다.

### 출력

- 첫 번째 줄에 1, 2, 3으로만 이루어져 있는 길이가 N인 좋은 수열들 중에서 가장 작은 수를 나타내는 수열만 출력한다.
    - 수열을 이루는 1, 2, 3들 사이에는 빈칸을 두지 않는다.

# 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[] arr;
	static boolean dfs(int depth) {
		if(depth == n) {
			for(int i = 1; i <= depth/2 ; i++){
				int a = arr[depth];
				int b = arr[depth-i];
				for(int j = 1; j < i ; j++){
					a = a*10+arr[depth-j];
					b = b*10+arr[depth-i-j];
				}
				if(a==b) {return false;}
			}
			
			StringBuilder sb = new StringBuilder();
			for(int i = 1; i <= n; i++){
				sb.append(arr[i]);
			}
			System.out.println(sb.toString());
			return true;
		}
		boolean isValid=true;
		for(int i = 1; i <= depth/2 ; i++){
			int a = arr[depth];
			int b = arr[depth-i];
			for(int j = 1; j < i ; j++){
				a = a*10+arr[depth-j];
				b = b*10+arr[depth-i-j];
			}
			if(a==b) {isValid=false;}
		}
		
		if(isValid) {
			arr[depth+1] = 1;
			if(dfs(depth+1))return true;
			arr[depth+1] = 2;
			if(dfs(depth+1))return true;
			arr[depth+1] = 3;
			if(dfs(depth+1))return true;
		}
		return false;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n+1];
		dfs(0);
	}
}
```

# 풀이

- 사용 알고리즘 : DFS
- 처음 읽어보면 좋은 수열을 체크하기 위해서는 모든 부분집합을 가지고 비교할 필요성이 있어보인다. 어드 구간이든 중복되는 것이 없어야 하기 때문이다. 하지만 잘 생각해보면 체크해야할 부분을 줄일 수 있다는 것을 알 수 있다.
    - 새롭게 수를 하나 붙일때마다 검사를 한다고 생각해보자. 그렇다면 개수가 1개일때~현재길이의 절반까지 체크한다면 현재 저장된 수열이 좋은수열인지 체크할 수 있다.
- 현재 풀이는 그냥 int를 활용해 두 수가 같은지 비교했지만, 수가 커진다면 int의 범위를 넘어서기 때문에 사실 String형으로 문자를 늘리는 식으로 구현해 비교하는 것이 더 맞는 방법이라고 생각한다.
