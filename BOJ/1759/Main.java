import java.io.*;
import java.util.*;

public class Main {
	static int l,c;
	static Set<String> vowels;
	static String[] alpha;
	static StringBuilder sb;
	static String[] password;
	static void dfs(int depth,int start) {//조합
		if(depth == l) {//basis part
			int vowelCnt = 0;
			for(int i = 0; i < l; i++){//모음의 개수 카운팅
				if(vowels.contains(password[i])) vowelCnt++;
			}
			if(vowelCnt < 1 || l-vowelCnt < 2) return; //모음의 개수, 자음의 개수 체크 -> 자음의 개수 = 전체길이 - 모음의 개수
			for(String s : password) {
				sb.append(s);
			}
			sb.append('\n');
			return;
		}
		
		for(int i = start; i < c; i++){
			password[depth]=alpha[i];
			dfs(depth+1,i+1);
		}
	}
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer stk = new StringTokenizer(br.readLine());
    	l = Integer.parseInt(stk.nextToken());
    	c = Integer.parseInt(stk.nextToken());
    	vowels = new HashSet<>();//모음 체크용 집합 선언
    	vowels.add("a");
    	vowels.add("e");
    	vowels.add("i");
    	vowels.add("o");
    	vowels.add("u");
    	stk = new StringTokenizer(br.readLine());
    	alpha = new String[c];
    	for(int i = 0; i < c; i++){
			alpha[i]=stk.nextToken();
		}
    	Arrays.sort(alpha);//사전순 출력을 위해 정렬
    	sb = new StringBuilder();
    	password = new String[l];
    	dfs(0,0);//조합
    	System.out.println(sb.toString());
    }
}