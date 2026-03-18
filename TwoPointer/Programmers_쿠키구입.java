class Solution {
    public int solution(int[] cookie) {
        int answer = 0;
        int l = 0, r = 0;
        
        for(int i = 0; i < cookie.length; i++) {
            r += cookie[i];
        }
        
        for(int i = 0; i < cookie.length; i++) {
            l += cookie[i];
            r -= cookie[i];
            if(l == r) {
                if(answer < l) {
                    answer = l;
                }
            } else {
                int il = 0, ir = cookie.length - 1;
                int tl = l, tr = r;
                while(il <= i && ir >= i) {
                    if(tl == tr) {
                        if (answer < tl) {
                            answer = tl;
                        }
                        break;
                    } else {
                        if(tl > tr) {
                            tl -= cookie[il++];
                        } else {
                            tr -= cookie[ir--];
                        }
                    }
                }
            }
        }
        
        return answer;
    }
}