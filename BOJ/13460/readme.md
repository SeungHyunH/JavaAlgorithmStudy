# BOJ 13460번: 구슬 탈출  2

# 문제

[13460번: 구슬 탈출 2](https://www.acmicpc.net/problem/13460)

- 빨간색 구슬과 파란색 구슬이 직사각형 보드에 있고, 빨간색 구슬을 구멍을 통해 빼내야 한다.
    - 직사각형 보드의 크기는 N x M 크기이다.
- 구슬은 기울여서 움직인다. (상,하,좌,우로 기울이기)
    - 움직일 때 두 구슬은 동시에 움직인다.
    - 각각의 구슬은 한 칸의 크기를 차지한다.
    - 두 구슬은 같은 칸에 위치할 수 없다.
    - 벽을 통과할 수 없다.
- 파란색 공이 구멍에 빠져서는 안된다.

# 제한사항

### 입력

- 첫 번째 줄에는 보드의 세로, 가로 크기를 의미하는 두 정수 N, M (3 ≤ N, M ≤ 10)이 주어진다.
- 다음 N개의 줄에 보드의 모양을 나타내는 길이 M의 문자열이 주어진다.
    - 이 문자열은 '`.`', '`#`', '`O`', '`R`', '`B`' 로 이루어져 있다.
        - '`.`'은 빈 칸을 의미한다.
        - '`#`'은 공이 이동할 수 없는 장애물 또는 벽을 의미한다
        - '`O`'는 구멍의 위치를 의미한다.
        - '`R`'은 빨간 구슬의 위치를 의미한다.
        - '`B`'는 파란 구슬의 위치이다.
- 입력되는 모든 보드의 가장자리에는 모두 '`#`'이 있다.
- 구멍의 개수는 한 개 이며, 빨간 구슬과 파란 구슬은 항상 1개가 주어진다.

### 출력

- 최소 몇 번 만에 빨간 구슬을 구멍을 통해 빼낼 수 있는지 출력한다.
- 만약, 10번 이하로 움직여서 빨간 구슬을 구멍을 통해 빼낼 수 없으면 -1을 출력한다.

# 코드

```java
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
				if(!visited[nry][nrx][nby][nbx]) {//방문하지 않았을 경우에만 탐색진행
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
```

# 풀이

- 사용 알고리즘 : BFS
- 일반적인 좌표탐색문제와 다르게 두 공의 좌표를 움직이는 문제다.  문제를 잘 읽어보면 다음과 같은 순서대로 처리를 해주어야 한다.
    1. 두 공을 벽을 만날때까지 움직인다.
    2. 움직이면서 구멍을 만났는지 체크한다. 만약 만났다면 이동을 중지한다.
    3. 파란공이 빠졌다면 해당 방향은 탐색하지 않는다.
    4. 빨간공이 빠졌다면 BFS를 종료하여 정답을 반환한다.
    5. 두 공의 좌표가 동일하다면 뒤에 있었던 공을 한 칸 뒤로 다시 움직여준다.
    6. 두 공의 좌표가 동일한 곳을 방문한 것이 아니라면 탐색을 진행한다.
- 위에서 처리를 해야할 때 주의해야 할 점은 5번과 6번이다.
    - 5번의 경우 더 뒤에 있었던 공이 파란공인지, 빨간 공인지 판단해야 한다. 구현하는 방법은 두 가지정도 생각났는데 이 문제는 두 번째 방법을 선택했다.(구현이 쉬워서)
        - 움직이기 전 각 공의 좌표(cur.rx,cur.ry),(cur.bx,cur.by)를 비교하여 더 뒤에 있는 공을 판단하여 dx,dy만큼 뺀다.
        - 이동할 때 움직인 횟수를 카운팅하여 더 많이 움직인 쪽이 뒤에 있는 것이므로 dx,dy만큼 뺀다.
    - 6번에서 주의해야 할 점은 N x M X N X M의 배열로 선언해야 한다는 것이다. 처음에 구현할 때는 단순히 빨간공과 파란공 두개의 visited를 체크하면 된다고 생각해서 N x M x 2로 구현하려고 했으나, 이 경우 단순하게 빨간공이나 파란공이 해당 좌표에 위치했기만 하면 탐색을 진행할 수가 없다.
        - 예를들어 파란공이 전부 벽으로 막혀있다면 무조건 탐색을 진행할 수가 없다. 왜냐하면 파란색 공이 있는 좌표는 이전에 방문했었다고 체크하기 때문이다. 따라서 빨간공의 위치와 파란공이 있는 위치가 각각 똑같이 방문했던 적이 있는지 체크해야 한다.