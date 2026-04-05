package Hash;

import java.io.*;
import java.util.*;

public class bj_17219 { // 백준 제출 시 클래스명은 Main이어야 함
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 2. 초기 용량(Initial Capacity) 설정으로 HashMap 리사이징 최소화
        // N이 100,000일 때 load factor(0.75) 고려하여 설정하면 성능 향상
        Map<String, String> map = new HashMap<>(N * 4 / 3 + 1);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String site = st.nextToken();
            String password = st.nextToken();
            map.put(site, password);
        }

        // 3. 출력 최적화: StringBuilder를 사용하여 한 번에 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            String query = br.readLine();
            sb.append(map.get(query)).append("\n");
        }
        
        System.out.print(sb.toString());
        br.close();
    }
}