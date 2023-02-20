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