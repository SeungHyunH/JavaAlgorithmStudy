# 문제

[2174번: 로봇 시뮬레이션](https://www.acmicpc.net/problem/2174)

- 가로 A(1≤A≤100), 세로 B(1≤B≤100) 크기의 땅이 있다. 이 땅 위에 로봇들이 N(1≤N≤100)개 있다.

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b5da7792-160e-4717-9c2c-e1c034e68373/Untitled.png)

- 로봇들의 초기 위치는 x좌표와 y좌표로 나타난다. 위의 그림에서 보듯 x좌표는 왼쪽부터, y좌표는 아래쪽부터 순서가 매겨진다.
- 각 로봇은 맨 처음에 NWES 중 하나의 방향을 향해 서 있다. 초기에 서 있는 로봇들의 위치는 서로 다르다.
- 이러한 로봇들에 M(1≤M≤100)개의 명령을 내리려고 한다. 각각의 명령은 순차적으로 실행된다.
    - 즉, 하나의 명령을 한 로봇에서 내렸으면, 그 명령이 완수될 때까지 그 로봇과 다른 모든 로봇에게 다른 명령을 내릴 수 없다.
- 각각의 로봇에 대해 수행하는 명령은 다음의 세 가지가 있다.
    1. L: 로봇이 향하고 있는 방향을 기준으로 왼쪽으로 90도 회전한다.
    2. R: 로봇이 향하고 있는 방향을 기준으로 오른쪽으로 90도 회전한다.
    3. F: 로봇이 향하고 있는 방향을 기준으로 앞으로 한 칸 움직인다.
- 잘못된 명령에는 다음의 두 가지가 있을 수 있다.
    1. Robot X crashes into the wall: X번 로봇이 벽에 충돌하는 경우이다. 즉, 주어진 땅의 밖으로 벗어나는 경우가 된다.
    2. Robot X crashes into robot Y: X번 로봇이 움직이다가 Y번 로봇에 충돌하는 경우이다.

# 제한 사항

### 입력

- 첫째 줄에 두 정수 A, B가 주어진다.
- 다음 줄에는 두 정수 N, M이 주어진다.
- 다음 N개의 줄에는 각 로봇의 초기 위치(x, y좌표 순) 및 방향이 주어진다.
- 다음 M개의 줄에는 각 명령이 명령을 내리는 순서대로 주어진다.
    - 각각의 명령은 명령을 내리는 로봇, 명령의 종류(위에 나와 있는), 명령의 반복 회수로 나타낸다.
    - 각 명령의 반복 회수는 1이상 100이하이다.

### 출력

- 첫째 줄에 시뮬레이션 결과를 출력한다.
    - 문제가 없는 경우에는 OK를
    - 그 외의 경우에는 위의 형식대로 출력을 한다.
        - 만약 충돌이 여러 번 발생하는 경우에는 가장 먼저 발생하는 충돌을 출력하면 된다.

# 코드

```jsx
import java.io.*;
import java.util.*;

public class Main {
	static class Robot{
		int x;
		int y;
		int d;
		public Robot(int x, int y, int d) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
		}		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(stk.nextToken());
		int b = Integer.parseInt(stk.nextToken());
		stk = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(stk.nextToken());
		int m = Integer.parseInt(stk.nextToken());
		Robot[] robots = new Robot[n+1];
		int[][] map = new int[b][a];
		int[] dy = {-1,0,1,0};
		int[] dx = {0,1,0,-1};
		for(int i = 1; i <= n; i++){
			stk = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(stk.nextToken())-1;
			int y = b-Integer.parseInt(stk.nextToken());
			int d = 0;
			String dir = stk.nextToken();
			if(dir.equals("N")) {
				d = 0;
			}else if(dir.equals("E")) {
				d = 1;
			}else if(dir.equals("S")) {
				d = 2;
			}else if(dir.equals("W")) {
				d = 3;
			}
			robots[i] = new Robot(x, y, d);
			map[y][x]=i;
		}
		for(int i = 0; i < m; i++){
			stk = new StringTokenizer(br.readLine());
			int robotIdx = Integer.parseInt(stk.nextToken());
			String command = stk.nextToken();
			int commandCount = Integer.parseInt(stk.nextToken());
			if(command.equals("F")) {
				map[robots[robotIdx].y][robots[robotIdx].x]=0;
				for(int j = 0; j < commandCount; j++){
					robots[robotIdx].y += dy[robots[robotIdx].d];
					robots[robotIdx].x += dx[robots[robotIdx].d];
					if(robots[robotIdx].x < 0 || robots[robotIdx].x >= a || robots[robotIdx].y < 0 || robots[robotIdx].y >= b) {
						StringBuilder sb = new StringBuilder();
						sb.append("Robot ").append(robotIdx).append(" crashes into the wall\n");
						System.out.println(sb.toString());
						return;
					}
					
					if(map[robots[robotIdx].y][robots[robotIdx].x] != 0) {
						StringBuilder sb = new StringBuilder();
						sb.append("Robot ").append(robotIdx).append(" crashes into robot ").append(map[robots[robotIdx].y][robots[robotIdx].x]).append("\n");
						System.out.println(sb.toString());
						return;
					}
				}
				map[robots[robotIdx].y][robots[robotIdx].x]=robotIdx;
			}else {
				commandCount %=4;
				if(command.equals("L")) {
					robots[robotIdx].d -= commandCount;
					if(robots[robotIdx].d < 0) robots[robotIdx].d += 4;
				}else {
					robots[robotIdx].d += commandCount;
					if(robots[robotIdx].d >= 4) robots[robotIdx].d -= 4;
				}
			}
		}
		System.out.println("OK\n");
	}
}
```

# 풀이

- 사용 알고리즘 : 시뮬레이션
- 제목에서도 나와있듯이 시뮬레이션구현 문제다. Y좌표계가 뒤집혀 있기 때문에 인덱스를 반전시킬 필요성이 있다.
    - 최대 Y값 : 5라면 5→1, 4→2, 3→3, 2→4, 1→5
    - 사용할 y값 = y최대값 - 입력시 주어지는 y값
- 방향을 회전시키는 것은 한 번의 명령당 최대 100번을 회전하게 된다. 이는 3번 이하로  줄일 수 있다.
    - 왼쪽 회전이든 오른쪽 회전이든 4번회전하게 된다면 360도를 돌아 제자리로 돌아오게 되므로, 회전 수를 4로 나머지연산을 하면 된다.
