BOJ 16929번: Two Dots
문제
점 k개로 이루어진 사이클이 존재하는지 구하시오
k ≥ 4
모든 k개의 점은 서로 다르다
모든 점의 색은 같다.
모든 1 ≤ i ≤ k-1에 대해서, di와 di+1은 인접한다.또, dk와 d1도 인접해야 한다.
두 점이 인접하다는 것은 각각의 점이 들어있는 칸이 변을 공유한다는 의미이다.
제한사항
입력
첫째 줄에 게임판의 크기 N, M이 주어진다.
둘째 줄부터 N개의 줄에 게임판의 상태가 주어진다.
게임판은 모두 점으로 가득차 있고, 게임판의 상태는 점의 색을 의미한다.
점의 색은 알파벳 대문자 한 글자이다.
출력
사이클이 존재하는 경우에는 "Yes", 없는 경우에는 "No"를 출력한다.
코드
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n,m;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};	
	static char[][] map;
	static boolean[][] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		map = new char[n][];
		visited = new boolean[n][m];
		for(int i = 0; i < n; i++){
			map[i] = br.readLine().toCharArray();
		}
		
		for(int i = 0; i < n; i++){
			for(int j = 0; j < m; j++){
				if(visited[i][j])//사이클을 체크한 점은 다시 검사할 필요가 없음
					continue;
				if(dfs(-1,-1,i,j)) {//사이클인 경우
					System.out.println("Yes");
					return;
				}
			}
		}
		System.out.println("No");
		br.close();
	}
	static boolean dfs(int beforeY,int beforeX,int curY,int curX) {
		if(visited[curY][curX]) return true; //같은 점을 탐색하는 경우 = 사이클
		visited[curY][curX] = true;
		
		for(int i = 0; i < 4; i++){
			int ny = curY+dy[i];
			int nx = curX+dx[i];
			
			if(ny < 0 || ny >= n || nx < 0 || nx >= m) continue;//범위 밖인 경우
			if(ny == beforeY && nx == beforeX) continue; //이전과 같은 점을 탐색하는 경우
			if(map[curY][curX] == map[ny][nx] && dfs(curY,curX,ny,nx)) //같은 색이면서 사이클이면 true
				return true;
		}
		return false; //사이클을 못 찾은 경우
	}
}
풀이
사용 알고리즘 : DFS
전형적인 DFS문제다. 다른 문제와 달리 조심해야 하는 점은 visited로 사이클을 체크해야 한다는 점이다.
이전에 방문했던 점을 다시 방문하는 경우는 사이클인 경우다.
바로 직전에 움직였던 점을 다시 방문하는 경우를 피해야 하는데, 이를 visited로 체크해서는 안된다. (visited가 true인 점도 방문해야 하므로 이 경우 사이클 체크를 못함)
문제 조건의 최소 4개의 점은 사이클을 이루는 경우 항상 4개 이상일 수밖에 없다.
한 점에서 출발한다고 가정하면, 다시 그 점으로 돌아오는 최소의 방법은 한 바퀴 돌아서 4개의 점으로 사이클을 이루는 경우다. (단 직전에 움직였던 점으로 다시 안간다는 조건 하에)
사이클을 체크한 점이라면 dfs로 탐색할 필요가 없다. 인접한 모든 점을 탐색했는데 사이클을 찾지 못한 점을 또 체크할 필요는 없다.