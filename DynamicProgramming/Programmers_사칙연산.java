/*
    프로그래머스 문제 - 사칙연산

    알고리즘
    1. 가능한 부호 경우의 수를 모두 찾음.
        - 뒷자리부터 변경될지, 아닐지 파악해서 부분 부호 저장.
        - Set으로 중복되는 부분은 걸러서 다음 연산에 적게 반영
    2. 그 중에 가장 큰 값을 리턴


    문제점 - 정확도는 통과했으나, 효율성은 통과하지 못함.
*/

import java.util.*;

class Solution {
    public int solution(String arr[]) {
        int answer = -1;
        
        int[] nums = new int[arr.length / 2 + 1];
        String[] signs = new String[arr.length / 2];
        
        for(int i = 0; i < arr.length - 1; i += 2) {
            nums[i / 2] = Integer.parseInt(arr[i]);
            signs[i / 2] = arr[i + 1].equals("-") ? "0" : "1";
        }
        
        nums[nums.length - 1] = Integer.parseInt(arr[arr.length - 1]);
        
        List<Set<String>> ps = new ArrayList<>(signs.length);
        
        Set<String> init = new HashSet<String>();
        init.add(signs[signs.length - 1]);
        ps.add(init);
        
        
        for(int i = 1; i < signs.length; i++) {
            Set<String> in_process = new HashSet<String>();
            
            String curr = signs[signs.length - i - 1];
            
            List<String> psl = new ArrayList<>(ps.get(i - 1));
            
            for(int j = 0; j < psl.size(); j++) {
                if(curr.equals("1")) {
                    in_process.add(curr + psl.get(j));
                } else {
                    List<String> possibles = get_possibles(psl.get(j));
                    in_process.addAll(possibles);
                }
            }
            
            ps.add(in_process);
            
        }
        
        List<String> p_signs = new ArrayList<>(ps.get(ps.size() - 1));
        int max = Integer.MIN_VALUE;
        for(String p_sign: p_signs) {
            int t = nums[0];
            for(int i = 0; i < p_sign.length(); i++) {
                char c = p_sign.charAt(i);
                if(c == '0') {
                    t -= nums[i + 1];
                } else {
                    t += nums[i + 1];
                }
            }
            
            if(t > max) max = t;
        }
        
        
        return max;
    }
    
    List<String> get_possibles(String v) {
        String full_v = "0" + v;
        List<String> results = new ArrayList<>();
        results.add(full_v);
        
        for(int i = 1; i < full_v.length(); i++) {
            StringBuilder sb = new StringBuilder(full_v);
            sb.setCharAt(i, full_v.charAt(i) == '0' ? '1' : '0'); // 1번 인덱스 'e'를 'a'로 변경
            full_v = sb.toString();
            results.add(full_v);
        }
        
        return results;
    }
}