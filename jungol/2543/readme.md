# 문제

[타일 채우기 > 문제은행 | JUNGOL](http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1804&sca=99&sfl=wr_hit&stx=2543)

- 정사각형 모양의 화장실 바닥을 타일로 채우려고 한다.
- 화장실의 바닥에는 배수구가 있는데 이곳에는 타일을 놓을 수 없다.
- 타일의 모양은 다음과 같은 4가지 종류가 있다.
    
    ![Untitled](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/52a46c08-8df8-46e3-b864-c926a2dac663/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230220%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230220T121245Z&X-Amz-Expires=86400&X-Amz-Signature=fee1cf020a6e57c73b874eec55873bd9a627cc07b6f4b7e8b241843d40ad2813&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Untitled.png%22&x-id=GetObject)
    
- 화장실 바닥의 한 변의 길이와 배수구의 위치를 입력으로 받아서 배수구를 제외한 나머지 부분에 빈 공간 없이 타일을 채우는 프로그램을 작성하시오.

# 제한사항

### 입력

- 첫 번째 줄에는 화장실 바닥의 한 변의 길이 N을 입력받는다.
    - ( 2≤N≤512, N은 2의 거듭제곱 2, 4, 8, 16...)
- 다음 줄에는 구멍의 위치 X , Y를 입력받는다.
    - X는 위쪽으로부터의 거리, Y는 왼쪽으로부터의 거리를 의미한다. (0≤X,Y<N)

### 출력

- 화장실 바닥의 각 위치에 배치한 타일의 번호를 모두 출력한다.
- 구멍의 위치에는 0을 출력한다.

# 코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static void dfs(int sx,int sy,int ex,int ey,int hx,int hy) {//시작좌표, 끝좌표, 구멍좌표
		if(sx == ex)
			return;
		int mx = (ex+sx)/2;//중간좌표
		int my = (ey+sy)/2;

		if(sx <= hx && hx <= mx &&sy <= hy && hy <= my) {//1사분면에 구멍이 있을 때 
			map[my][mx+1] = 1;
			map[my+1][mx] = 1;
			map[my+1][mx+1] = 1;
			
			dfs(sx,sy,mx,my,hx,hy);//1사분면 탐색 -> 기존 구멍 = 다음 구멍
			dfs(mx+1,sy,ex,my,mx+1,my);//2사분면 탐색 -> 2사분면에 채워진 타일 = 다음 구멍
			dfs(sx,my+1,mx,ey,mx,my+1);//3사분면 탐색 -> 3사분면에 채워진 타일 = 다음 구멍
			dfs(mx+1,my+1,ex,ey,mx+1,my+1);//4사분면 탐색 -> 4사분면에 채워진 타일 = 다음구멍
		}else if(mx+1 <= hx && hx <= ex  && sy <= hy && hy <= my) {//2사분면에 구멍이 있을 때 
			map[my][mx]=2;
			map[my+1][mx]=2;
			map[my+1][mx+1]=2;
			
			dfs(sx,sy,mx,my,mx,my);//1사분면 탐색 -> 1사분면에 채워진 타일 = 다음 구멍
			dfs(mx+1,sy,ex,my,hx,hy);//2사분면 탐색 -> 기존 구멍 = 다음 구멍
			dfs(sx,my+1,mx,ey,mx,my+1);//3사분면 탐색 -> 3사분면에 채워진 타일 = 다음 구멍
			dfs(mx+1,my+1,ex,ey,mx+1,my+1);//4사분면 탐색 -> 4사분면에 채워진 타일 = 다음구멍
		}else if(sx<= hx && hx <= mx && my+1 <= hy && hy <= ey) {//3사분면에 구멍이 있을 때
			map[my][mx]=3;
			map[my][mx+1]=3;
			map[my+1][mx+1]=3;
			
			dfs(sx,sy,mx,my,mx,my);//1사분면 탐색 -> 1사분면에 채워진 타일 = 다음 구멍
			dfs(mx+1,sy,ex,my,mx+1,my);//2사분면 탐색 -> 2사분면에 채워진 타일 = 다음 구멍
			dfs(sx,my+1,mx,ey,hx,hy);//3사분면 탐색 -> 기존 구멍 = 다음 구멍
			dfs(mx+1,my+1,ex,ey,mx+1,my+1);//4사분면 탐색 -> 4사분면에 채워진 타일 = 다음구멍
		}else if(mx+1 <= hx && hx <= ex && my+1 <= hy && hy <= ey ) {//4사분면에 구멍이 있을 때
			map[my][mx]=4;
			map[my][mx+1]=4;
			map[my+1][mx]=4;
			
			dfs(sx,sy,mx,my,mx,my);//1사분면 탐색 -> 1사분면에 채워진 타일 = 다음 구멍
			dfs(mx+1,sy,ex,my,mx+1,my);//2사분면 탐색 -> 2사분면에 채워진 타일 = 다음 구멍
			dfs(sx,my+1,mx,ey,mx,my+1);//3사분면 탐색 -> 3사분면에 채워진 타일 = 다음구멍
			dfs(mx+1,my+1,ex,ey,hx,hy);//4사분면 탐색 -> 기존 구멍 = 다음 구멍
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int hy = Integer.parseInt(stk.nextToken());
		int hx = Integer.parseInt(stk.nextToken());
		map = new int[n][n];
		dfs(0,0,n-1,n-1,hx,hy);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				sb.append(map[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb.toString());
	}
}
```

# 풀이

- 사용 알고리즘 : 분할 정복
- 배수구 구멍을 제외한 나머지 타일에 어떤 타일을 놓아야 되는지 판단하는 문제다.
- 배수구 구멍이 1,2,3,4분면 중 어디에 있는지 판단하고, 타일을 채워야 한다.
    - 1사분면에 구멍이 있다 → 나머지 타일을 1로 채움
    - 채우는 타일은 가운데를 기준으로 채워야 한다. (이건 TestCase를 보고 판단할 수 밖에 없다.)
- 배수구 구멍이 있는 부분을 제외한 부분은 어떻게 채워야 할까?
    - 나머지 타일을 구멍이라고 가정하고 분할정복 실시
    - 1사분면에 구멍이 있을 때 → 나머지 타일을 1로 채움 → 2,3,4사분면에 타일이 생김
        - 2사분면에 있는 타일 = 구멍 → 3사분면에 구멍이 있는 타일채우기
        - 3사분면에 있는 타일 = 구멍 → 2사분면에 구멍이 있는 타일채우기
        - 4사분면에 있는 타일 = 구멍 → 1사분면에 구멍이 있는 타일 채우기
- 특이하게도 이 문제는 테스트케이스를 보고 조건을 찾는 문제였다. 핵심은 구멍을 제외한 나머지 부분에 타일을 채운 뒤 그것을 다시 구멍으로 판단해 분할 정복을 하는 것이다.