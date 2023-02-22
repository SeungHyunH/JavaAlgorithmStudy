# 문제

[프로그래머스 Level3 - 등산코스 정하기](https://school.programmers.co.kr/learn/courses/30/lessons/118669)

- XX산은 n개의 지점으로 이루어져 있다. 각 지점은 1~N까지의 번호가 붙어있으며, 출입구/쉼터/산봉우리 중 하나이다.
    - 각 지점은 양방향 통행이 가능한 등산로로 연결되어 있다. 등산로 별로 이동하는데 일정시간이 소요된다.
- 등산코스를 따라 이동하는 중 쉼터 혹은 산봉우리를 방문할 때마다 휴식을 취할 수 있으며, 휴식없이 이동해야 하는 시간 중 가장 긴 시간을 해당 등산코스의 intensity라고 부르기로 한다.
- 출입구중 한 곳에서 출발하여 산봉우리 중 한곳을 방문한 뒤 다시 원래의 출입구로 돌아오려고 한다.
    - 등산코스에서 출입고는 처음과 끝, 산봉우리는 한번만 포함되어야 한다.
- 모든 쉼터를 들릴 필요가 없다.
- 최소가 되는 intensity를 구해야 한다.
    - intensity가 최소가 되는 등산코스가 여러 개라면 그중 산봉우리의 번호가 가장 낮은 등산코스를 선택한다.

# 제한사항

### 입력

- 2 ≤ `n` ≤ 50,000
- `n` - 1 ≤ `paths`의 길이 ≤ 200,000
- `paths`의 원소는 `[i, j, w]` 형태입니다.
    - `i`번 지점과 `j`번 지점을 연결하는 등산로가 있다는 뜻입니다.
    - `w`는 두 지점 사이를 이동하는 데 걸리는 시간입니다.
    - 1 ≤ `i` < `j` ≤ `n`
    - 1 ≤ `w` ≤ 10,000,000
    - 서로 다른 두 지점을 직접 연결하는 등산로는 최대 1개입니다.
- 1 ≤ `gates`의 길이 ≤ `n`
    - 1 ≤ `gates`의 원소 ≤ `n`
    - `gates`의 원소는 해당 지점이 출입구임을 나타냅니다.
- 1 ≤ `summits`의 길이 ≤ `n`
    - 1 ≤ `summits`의 원소 ≤ `n`
    - `summits`의 원소는 해당 지점이 산봉우리임을 나타냅니다.
- 출입구이면서 동시에 산봉우리인 지점은 없습니다.
- `gates`와 `summits`에 등장하지 않은 지점은 모두 쉼터입니다.
- 임의의 두 지점 사이에 이동 가능한 경로가 항상 존재합니다.

### 출력

- return 하는 배열은 `[산봉우리의 번호, intensity의 최솟값]` 순서여야 합니다.

# 코드

```java
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        HashMap<Integer,ArrayList<int[]>> graph = new HashMap<>();
        ArrayDeque<int[]> que = new ArrayDeque<>();
        Set<Integer> isGate = new HashSet<>();
        int[] intensitys = new int[n+1];
        Arrays.fill(intensitys,Integer.MAX_VALUE);
        for(int gate : gates) {
        	isGate.add(gate);
        	que.offer(new int[] {gate,0});
        	intensitys[gate]=0;
        }
        Set<Integer> isSummits = new HashSet<>();
        for(int summit : summits) {
        	isSummits.add(summit);
        }
        for(int[] path : paths) {
        	if(!graph.containsKey(path[0]))
        		graph.put(path[0],new ArrayList<>());
        	if(!graph.containsKey(path[1]))
        		graph.put(path[1],new ArrayList<>());
        	
        	if(isGate.contains(path[0])||isSummits.contains(path[1])) {
        		graph.get(path[0]).add(new int[] {path[1],path[2]});
        	}else if(isGate.contains(path[1])||isSummits.contains(path[0])) {
        		graph.get(path[1]).add(new int[] {path[0],path[2]});
        	}else {
        		graph.get(path[0]).add(new int[] {path[1],path[2]});
        		graph.get(path[1]).add(new int[] {path[0],path[2]});
        	}
        }
        while(!que.isEmpty()) {
        	int[] cur = que.poll();
        	if(intensitys[cur[0]] < cur[1]) continue;
        	
        	for(int[] next : graph.get(cur[0])) {
        		int intensity = Math.max(intensitys[cur[0]],next[1]);
        		if(intensitys[next[0]] > intensity) {
        			intensitys[next[0]] = intensity;
        			que.offer(new int[] {next[0],intensity});
        		}
        	}
        }
        int[] answer = {-1,Integer.MAX_VALUE};
        Arrays.sort(summits);
        for(int summit : summits) {
        	if(intensitys[summit] < answer[1]) {
        		answer[0]=summit;
        		answer[1]=intensitys[summit];
        	}
        }
        return answer;
    }
}
```

# 풀이

- 사용 알고리즘 : 다익스트라
- 문제에서 왕복을 강조해서 놓치기 쉽지만 A → B까지 최적의 코스로 이동했다면 B→A로 가는 것도 최적의 코스로 똑같이 오면 되기 때문에 왕복이 아닌 편도의 경우만 생각하면 된다.
- 모든 코스를 다 들려야 하는 것도 아니고, 경로의 합을 구하는 것도 아니기 때문에 최소의 intensity가 되는 것만 생각해 갱신하면 된다.
- 이 문제에서의 핵심은 출입구에서 시작해서 산봉우리로 끝나는 것을 어떻게 구현할 것인가? 라는 의문을 해결해야 한다.
    - 등산코스에서 나오면 안되는 예를 살펴보자. 모두 간선이 양방향이기 때문에 생기는 문제다.
        - 출입구 → 산봉우리 → 출입구
        - 출입구 → 출입구
    - 간단하게 생각해서 모든 간선을 양방향으로 넣지 않고 출입구는 나가는 간선만 그래프에 넣고, 산봉우리는 들어오는 간선만 그래프에 넣어서 강제로 출입구→산봉우리로 끝나게 한다.
    - 모든 출입구를 넣어서 시작하면 어차피 모든 간선을 지나야 하는 것이 아닌 최소 가중치만 찾으면 되므로 다익스트라를 응용해 산봉우리에서의 최소intensity만 찾으면 된다.
- 주의히야 할 점은 최소intensity가 동일한 경우가 여러개라면 번호가 가장 낮은것을 선택해야 한다. 따라서 산봉우리를 정렬한 뒤 최소intensity와 산봉우리를 찾아야 한다.