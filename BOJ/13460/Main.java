import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	static int n,m;
	static int[] red,blue;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static char[][] map;
	static boolean[][][][]visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		
		map = new char[n][];
		visited = new boolean[n][m][n][m];
		
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				if(map[i][j]=='B')
					blue = new int[]{i,j};
				else if(map[i][j]=='R')
					red = new int[] {i,j};
			}
		}
		
		System.out.println(bfs());
		br.close();
	}
	
	static int bfs() {
		ArrayDeque<Node> queue = new ArrayDeque<Node>();//큐 선언
		queue.add(new Node(red[1],red[0],blue[1],blue[0],1));//초기 노드 삽입
		visited[red[0]][red[1]][blue[0]][blue[1]]=true;//초기노드 방문처리
		while(!queue.isEmpty()) {//탐색할 노드가 없을때까지 탐색
			Node cur = queue.poll();
			
			if(cur.depth > 10)//출력조건 : 이동횟수가 10보다 넘으면 -1을 출력
				return -1;
			
			for (int i = 0; i < 4; i++) {
				int nrx = cur.rx;
				int nry = cur.ry;
				int nbx = cur.bx;
				int nby = cur.by;
				int cntR = 0;
				int cntB = 0;
				
				boolean holeR = false;
				boolean holeB = false;
				
				while(map[nry+dy[i]][nrx+dx[i]]!='#') {//벽을 만날때까지 이동 (기울여서 움직이는 것이기 때문에)
					nrx += dx[i];
					nry += dy[i];
					cntR++;
					if(map[nry][nrx]=='O') {//움직이다가 구멍을 만났을 경우 이동을 중단
						holeR = true;
						break;
					}
				}
				
				while(map[nby+dy[i]][nbx+dx[i]]!='#') {//벽을 만날때까지 이동 (기울여서 움직이는 것이기 때문에)
					nbx += dx[i];
					nby += dy[i];
					cntB++;
					if(map[nby][nbx]=='O') {//움직이다가 구멍을 만났을 경우 이동을 중단
						holeB = true;
						break;
					}
				}
				
				if(holeB)//파란색공이 구멍에 빠졌을 때
					continue;
				if(holeR)//빨간색 공만 구멍에 빠졌을 때
					return cur.depth;
				if(nrx == nbx && nry == nby) {//두 공이 겹쳐진 경우 더 많이 이동한 쪽을 뒤로이동
					if(cntB > cntR) {
						nby-=dy[i];
						nbx-=dx[i];
					}else {
						nry-=dy[i];
						nrx-=dx[i];
					}
				}
				if(!visited[nry][nrx][nby][nbx]) {
					visited[nry][nrx][nby][nbx] = true;
					queue.add(new Node(nrx,nry,nbx,nby,cur.depth+1));
				}
			}
		}
		return -1;//만약 모두 탐색했는데 구멍을 찾지못했다면 -1을 반환
	}
	
	static class Node{
		int rx,ry,bx,by,depth;

		public Node(int rx, int ry, int bx, int by, int depth) {
			super();
			this.rx = rx;
			this.ry = ry;
			this.bx = bx;
			this.by = by;
			this.depth = depth;
		}
		
	}
}
