package Hash;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class bj_14503 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;


    static class Robot {
        int direction;
        int pos_x;
        int pos_y;
        int cnt;
        int[] dx = {-1, 0, 1, 0}; // 북, 동, 남, 서
        int[] dy = {0, 1, 0, -1}; // 북, 동, 남, 서

        public Robot(int direction, int pos_x, int pos_y) {
            this.direction = direction;
            this.pos_x = pos_x;
            this.pos_y = pos_y;
            this.cnt = 0;
        }

        public void turn_left() {
            this.direction = (this.direction + 3) % 4;
        }

        public void forward() {
            this.pos_x = this.pos_x + dx[direction];
            this.pos_y = this.pos_y + dy[direction];
        }

        public void backword() {
            this.pos_x = this.pos_x + dx[(this.direction + 2) % 4];
            this.pos_y = this.pos_y + dy[(this.direction + 2) % 4];
        }

        public int get_forward(int[][] map) {
            int px = this.pos_x + dx[direction];
            int py = this.pos_y + dy[direction];

            if (px < 0 || px >= map.length || py < 0 || py >= map[0].length) {
                return 1; // 맵 밖은 벽으로 간주
            }

            return map[px][py];
        }

        public int get_backward(int[][] map) {
            int px = this.pos_x + dx[(this.direction + 2) % 4];
            int py = this.pos_y + dy[(this.direction + 2) % 4];
            if (px < 0 || px >= map.length || py < 0 || py >= map[0].length) {
                    return 1; // 맵 밖은 벽으로 간주
                }
            return map[px][py];
        }

        public boolean find_empty(int[][] map) {

            for(int i = 0; i < 4; i++) {
                int px = this.pos_x + dx[i], py = this.pos_y + dy[i];
                // 맵 경계를 벗어나지 않는지 확인
                if (px >= 0 && px < map.length && py >= 0 && py < map[0].length) {
                    if(map[px][py] == 0) return true;
                }

            }

            return false;
        }

        public boolean cal(int[][] map) {
            // 1. 현재 위치 청소
            if (map[pos_x][pos_y] == 0) {
                map[pos_x][pos_y] = 2;
                cnt++;
            }

            // 2. 주변 4칸 중 청소되지 않은 빈 칸이 있는지 확인
            if (find_empty(map)) {
                // 빈 칸이 있는 경우
                for (int i = 0; i < 4; i++) {
                    turn_left(); // 반시계 90도 회전
                    if (get_forward(map) == 0) { // 앞쪽 칸이 청소되지 않은 빈 칸인 경우
                        forward();
                        return true; // 한 칸 전진 후 다시 1번으로
                    }
                }
            } else {
                // 3. 빈 칸이 없는 경우
                if (get_backward(map) == 1) { 
                    return false; // 후진 불가능(벽)하면 작동 중지
                } else {
                    backword(); // 바라보는 방향 유지하며 후진
                    return true;
                }
            }
            return true;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 1. 첫 줄: N(행), M(열) 파싱
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 11
        int M = Integer.parseInt(st.nextToken()); // 10

        // 2. 두 번째 줄: 기타 설정값 (예: 시작 x, 시작 y, 방향 등)
        st = new StringTokenizer(br.readLine());
        int startX = Integer.parseInt(st.nextToken()); // 7
        int startY = Integer.parseInt(st.nextToken()); // 4
        int dir = Integer.parseInt(st.nextToken());    // 0

        // 3. 2차원 배열 선언 및 데이터 채우기
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }



        Robot r = new Robot(dir, startX, startY);

        while (r.cal(map)); 
            System.out.println(r.cnt);

        // 입력 확인용 (제출 시 삭제)
        // System.out.println("배열 로드 완료: " + N + "x" + M);
    }

}