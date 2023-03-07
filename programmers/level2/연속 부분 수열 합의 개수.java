import java.io.*;
import java.util.*;
class Solution {
    public int solution(int[] elements) {
        int n = elements.length;
        int[] rotateElements = new int[n*2];
        for(int i = 0; i < elements.length; i++){
			rotateElements[i] = elements[i];
			rotateElements[i+n] = elements[i];
		}
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < n; i++){
			int cnt = 0;
			for(int j = i,jEnd = i+n; j < jEnd; j++){
				cnt += rotateElements[j];
				set.add(cnt);
			}
		}
        return set.size();
    }
}
