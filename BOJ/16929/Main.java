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
