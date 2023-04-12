class Solution {
    public int[] solution1(int[] sequence, int k) {
        int[] answer = new int[2];
        int head = 0, tail = 1, length = 1000000001;
        int sum = sequence[0];
        
        if(sum == k) return new int[] {0,0};
        
        while(true){
            if(tail >= sequence.length) {
                while(true) {
                    if(sum < k || head >= sequence.length) break;
                    sum -= sequence[head++];
                    if(sum == k) {
                        if(tail - head < length) {
                            answer[0] = head;
                            answer[1] = tail - 1;
                        }
                        
                        break;
                    }
                }
                break;
            }
            
            if(sum <= k || head > tail) sum += sequence[tail++];
            else if(sum >= k) sum -= sequence[head++];
            
            if(sum == k) {
                if(tail - head < length){
                    answer[0] = head;
                    answer[1] = tail - 1;
                    length = tail - head;
                }
                
            }
        }

        return answer;
    }

    public int[] solution2(int[] sequence, int k) {
        int[] answer = new int[2];
        int head = 0, tail = 1, length = 1000000001;
        int sum = sequence[0];
        
        if(sum == k) return new int[] {0,0};
        
        while(head < sequence.length){
            if(tail >= sequence.length) {
                if(sum < k) break;
            }

            if(sum < k) sum += sequence[tail++];
            else sum -= sequence[head++];

            if(sum == k) {
                if(tail - head < length){
                    answer[0] = head;
                    answer[1] = tail - 1;
                    length = tail - head;
                }
                
            }
        }

        return answer;
    }
}