# 제목

[1759번: 암호 만들기](https://www.acmicpc.net/problem/1759)

- 암호를 만들려고 한다. 암호는 다음 규칙에 따라 만들어 진다.
    - 암호는 l의 길이로 이루어져 있다.
    - 모음(a, e, i, o, u)은 1개, 자음은 2개 이상으로 이루어져 있다.
    - 사전순으로 정렬되어 있다.
- C개의 문자를 주어졌을 때 가능한 모든 암호를 출력하시오.
    - 오름차순으로 출력해야 한다.

# 제한사항

### 입력

- 첫째 줄에 두 정수 L, C가 주어진다. (3 ≤ L ≤ C ≤ 15)
- 다음 줄에는 C개의 문자들이 공백으로 구분되어 주어진다.
    - 주어지는 문자들은 알파벳 소문자이며, 중복되는 것은 없다.

# 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	static int l,c;
	static Set<String> vowels;
	static String[] alpha;
	static StringBuilder sb;
	static String[] password;
	static void dfs(int depth,int start) {
		if(depth == l) {
			int vowelCnt = 0;
			for(int i = 0; i < l; i++){
				if(vowels.contains(password[i])) vowelCnt++;
			}
			if(vowelCnt < 1 || l-vowelCnt < 2) return;
			for(String s : password) {
				sb.append(s);
			}
			sb.append('\n');
			return;
		}
		
		for(int i = start; i < c; i++){
			password[depth]=alpha[i];
			dfs(depth+1,i+1);
		}
	}
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer stk = new StringTokenizer(br.readLine());
    	l = Integer.parseInt(stk.nextToken());
    	c = Integer.parseInt(stk.nextToken());
    	vowels = new HashSet<>();
    	vowels.add("a");
    	vowels.add("e");
    	vowels.add("i");
    	vowels.add("o");
    	vowels.add("u");
    	stk = new StringTokenizer(br.readLine());
    	alpha = new String[c];
    	for(int i = 0; i < c; i++){
			alpha[i]=stk.nextToken();
		}
    	Arrays.sort(alpha);
    	sb = new StringBuilder();
    	password = new String[l];
    	dfs(0,0);
    	System.out.println(sb.toString());
    }
}
```

# 풀이

- 사용 알고리즘 : 조합
- 전형적인 조합문제다. 사전순으로 출력하기 위해 미리 정렬한 뒤 조합을 돌리면 된다.
- set을 이용해 모음의 개수를 센 뒤 암호의 조건을 체크하면 된다.