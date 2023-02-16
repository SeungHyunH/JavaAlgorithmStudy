import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(stk.nextToken());
		int k = Integer.parseInt(stk.nextToken());
		int answer = 0;
		stk = new StringTokenizer(br.readLine());
		HashSet<Integer> set = new HashSet<>();
		HashMap<Integer,ArrayDeque<Integer>> map = new HashMap<>();
		int[] input = new int[k];
		for(int i = 0; i < k; i++){
			input[i] = Integer.parseInt(stk.nextToken());
			if(map.containsKey(input[i])) {
				map.get(input[i]).offerLast(i);
			}else {
				ArrayDeque<Integer> temp = new ArrayDeque<>();
				temp.offerLast(i);
				map.put(input[i],temp);
			}
		}
		for(int i = 0; i < k; i++){
			map.get(input[i]).pollFirst();
			if(set.size() < n) set.add(input[i]); //콘센트가 비어있을 때
			else if(set.contains(input[i])) continue; //이미 사용하고 있을 때
			else {
				boolean find = false;
				int maxIdx = 0;
				for(int key : set) {
					if(map.get(key).size() == 0) {//현재 사용중인 전기용품이 앞으로 사용하지 않을 때
						find = true;
						answer++;
						set.remove(key);
						set.add(input[i]);
						break;
					}
					maxIdx = Math.max(maxIdx, map.get(key).peekFirst()); //현재 사용중인 전기용품 중 가장 늦게 사용하는 것을 선택
				}
				if(find) continue;//이미 콘센트를 뽑은 경우
				set.remove(input[maxIdx]);
				set.add(input[i]);
				answer++;
			}
		}
		System.out.println(answer);
	}
}