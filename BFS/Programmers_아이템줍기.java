import java.util.*;

class Solution {
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;
        
        int[][] mat = new int[102][102];
        boolean[][] visited = new boolean[102][102];
        
        for(int i = 0; i < rectangle.length; i++) {
            int x1 = rectangle[i][0] * 2, y1 = rectangle[i][1] * 2, x2 = rectangle[i][2] * 2, y2 = rectangle[i][3] * 2;
            
            for(int j = x1; j <= x2; j++) {
                for(int k = y1; k <= y2; k++){
                    mat[j][k] = 1;
                }
            }
        }
        
        for(int i = 0; i < rectangle.length; i++) {
            int x1 = rectangle[i][0] * 2, y1 = rectangle[i][1] * 2, x2 = rectangle[i][2] * 2, y2 = rectangle[i][3] * 2;
            
            for(int j = x1 + 1; j < x2; j++) {
                for(int k = y1 + 1; k < y2; k++){
                    mat[j][k] = 0;
                }
            }
        }
        
        int cx = characterX * 2, cy = characterY * 2, ix = itemX * 2, iy = itemY * 2;
        
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{cx, cy, 0}); // {x, y, distance}

        int min = Integer.MAX_VALUE;
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            
            if(current[0] == ix && current[1] == iy) {
                if(min > current[2]) {
                    min = current[2];
                }
            } 
            int x1 = current[0] + 1, x2 = current[0] - 1, y1 = current[1] + 1, y2 = current[1] - 1;
                if(mat[x1][current[1]] == 1 && !visited[x1][current[1]]){
                    queue.offer(new int[]{x1, current[1], current[2] + 1});
                    visited[x1][current[1]] = true;
                }
                
                if(mat[x2][current[1]] == 1 && !visited[x2][current[1]]) {
                    queue.offer(new int[]{x2, current[1], current[2] + 1});
                    visited[x2][current[1]] = true;
                }
                
                if(mat[current[0]][y1] == 1 && !visited[current[0]][y1]) {
                    queue.offer(new int[]{current[0], y1, current[2] + 1});
                    visited[current[0]][y1] = true;
                    
                }
                
                if(mat[current[0]][y2] == 1 && !visited[current[0]][y2]) {
                    queue.offer(new int[]{current[0], y2, current[2] + 1});
                    visited[current[0]][y2] = true;
                    
                }
        }
        return min / 2;
    }
}