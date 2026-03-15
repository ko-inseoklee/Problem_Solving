/*
    제미나이 제언
    1. 2배로 늘렸기 때문에, 1로 모두 채우고 2로 만들어주는 것이 아닌 2로 처리하기.
    2. visited를 mat안에 둬서, 메모리 효율성을 증가시키기
    3. 상하좌우 로직을 dx, dy로 써서 가독성 높이기.
*/
import java.util.*;

class Solution {
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {    
        
        int[][] mat = new int[102][102];
       // 1. 맵 그리기: 2단계 전략 (가장 확실함)
        for (int[] r : rectangle) {
            for (int i = r[0] * 2; i <= r[2] * 2; i++) {
                for (int j = r[1] * 2; j <= r[3] * 2; j++) {
                    mat[i][j] = 1;
                }
            }
        }
        for (int[] r : rectangle) {
            for (int i = r[0] * 2 + 1; i < r[2] * 2; i++) {
                for (int j = r[1] * 2 + 1; j < r[3] * 2; j++) {
                    mat[i][j] = 0; // 내부는 확실히 0으로 파냄
                }
            }
        }

        // 2. BFS 탐색
        int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

        Queue<int[]> queue = new ArrayDeque<>();

        int cx = characterX * 2, cy = characterY * 2, ix = itemX * 2, iy = itemY * 2;
        mat[cx][cy] = 0; // 시작점 방문 처리
        queue.offer(new int[]{cx, cy, 0}); // {x, y, distance}
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            if (curr[0] == ix && curr[1] == iy) return curr[2] / 2;

            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];

                if (nx >= 0 && nx < 102 && ny >= 0 && ny < 102 && mat[nx][ny] == 1) {
                    mat[nx][ny] = 0; // 큐에 넣을 때 즉시 방문 처리
                    queue.offer(new int[]{nx, ny, curr[2] + 1});
                }
            }
        }

        return 0;
    }
}