import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[] arr;
	static boolean dfs(int depth) {
		if(depth == n) {
			for(int i = 1; i <= depth/2 ; i++){
				int a = arr[depth];
				int b = arr[depth-i];
				for(int j = 1; j < i ; j++){
					a = a*10+arr[depth-j];
					b = b*10+arr[depth-i-j];
				}
				if(a==b) {return false;}
			}
			
			StringBuilder sb = new StringBuilder();
			for(int i = 1; i <= n; i++){
				sb.append(arr[i]);
			}
			System.out.println(sb.toString());
			return true;
		}
		boolean isValid=true;
		for(int i = 1; i <= depth/2 ; i++){
			int a = arr[depth];
			int b = arr[depth-i];
			for(int j = 1; j < i ; j++){
				a = a*10+arr[depth-j];
				b = b*10+arr[depth-i-j];
			}
			if(a==b) {isValid=false;}
		}
		
		if(isValid) {
			arr[depth+1] = 1;
			if(dfs(depth+1))return true;
			arr[depth+1] = 2;
			if(dfs(depth+1))return true;
			arr[depth+1] = 3;
			if(dfs(depth+1))return true;
		}
		return false;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n+1];
		dfs(0);
	}
}
