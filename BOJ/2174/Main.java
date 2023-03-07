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
