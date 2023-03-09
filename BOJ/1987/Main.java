import java.io.*;
import java.util.*;

public class Main {
	static int r,c,answer;
	static char[][] map;
	static boolean[] alpha = new boolean[26];
	static int[] dy = {1,-1,0,0};
	static int[] dx = {0,0,1,-1};
	static boolean dfs(int y,int x,int depth) {
		answer = Math.max(answer, depth);
		if(depth == r*c)return true;
		for(int i = 0; i < 4; i++){
			int ny = y+dy[i];
			int nx = x+dx[i];
			if(0 <= nx && nx < c && 0 <= ny && ny < r) {
				if(alpha[map[ny][nx]-'A']) continue;
				alpha[map[ny][nx]-'A']=true;
				if(dfs(ny,nx,depth+1))return true;
				alpha[map[ny][nx]-'A']=false;
			}
		}
		return false;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		r = Integer.parseInt(stk.nextToken());
		c = Integer.parseInt(stk.nextToken());
		map = new char[r][];
		for(int i = 0; i < r; i++){
			map[i] = br.readLine().toCharArray();
		}
		alpha[map[0][0]-'A']=true;
		dfs(0,0,1);
		System.out.println(answer);
	}
}
