/*
    V2. DP풀이.

    3-loop로 풀이.
    for 1: 계산하려고 하는 개수 (2개 ~ n개)
    for 2: 시작점 0, 1, 2 의 경우 => 0, 1 ,2 이런식
    for 3: 중간 포인트. 나눠서 최적해를 구할 위치. 0, 1, 2 의 경우 => (0), (1, 2), (0, 1), (2) 이런식으로 나눠서 최적해를 구할 위치.
*/

package DynamicProgramming;
import java.util.*;

class Solution {
    public int solution(String arr[]) {
        int answer = Integer.MIN_VALUE;
        
        int[][] maxDP = new int[101][101];
        int[][] minDP = new int[101][101];
        
        for(int i = 0; i < 101; i++) {
            for(int j = 0; j < 101; j++) {
                maxDP[i][j] = Integer.MIN_VALUE;
                minDP[i][j] = Integer.MAX_VALUE;
            }
        }
        
        String[] signs = new String[100];
        
        for(int i = 0; i < arr.length - 1; i += 2) {
            maxDP[i / 2][i / 2] = Integer.parseInt(arr[i]);
            minDP[i / 2][i / 2] = Integer.parseInt(arr[i]);
            signs[i / 2] = arr[i + 1];
        }
        
        maxDP[arr.length / 2][arr.length / 2] = Integer.parseInt(arr[arr.length - 1]);
        minDP[arr.length / 2][arr.length / 2] = Integer.parseInt(arr[arr.length - 1]);
        
        // 5 => 1~4
        int n = arr.length / 2 + 1;
        
        for (int len = 1; len < n; len++) {
            for (int i = 0; i < n - len; i++) {
                int j = i + len;
                
                for (int k = i; k < j; k++) {
                    String sign = signs[k];
                    if (sign.equals("+")) {
                        maxDP[i][j] = Math.max(maxDP[i][j], maxDP[i][k] + maxDP[k+1][j]);
                        minDP[i][j] = Math.min(minDP[i][j], minDP[i][k] + minDP[k+1][j]);
                    } else {
                        maxDP[i][j] = Math.max(maxDP[i][j], maxDP[i][k] - minDP[k+1][j]);
                        minDP[i][j] = Math.min(minDP[i][j], minDP[i][k] - maxDP[k+1][j]);
                    }
                }
            }
        }
        
//         for(int i = 0; i < n; i++) {
//             for(int j = 0; j < n; j++) {
//                 if(j < i) {
//                     System.out.print("  ");
//                 } else {
//                     System.out.print(maxDP[i][j] + " ");
//                 }
//             }
            
//             System.out.println("");
            
//         }
        
        
        
        return maxDP[0][n - 1];
    }
}
