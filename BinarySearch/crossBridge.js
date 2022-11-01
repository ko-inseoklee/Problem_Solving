function solution(stones, k) {
    var answer = 0;
    var start = 0, end = 2000000001;
    var mid = 0;
    while(start <= end) {
        mid = parseInt((start + end) / 2);
        var tmp = 0, tmax = -1;
        for(var i = 0; i < stones.length; i++){
            if(stones[i] - mid <= 0) {
                tmp++;
            } else {
                if (tmax < tmp) tmax = tmp;
                tmp = 0;
                if (tmax >= k) break;
            }
        }
        if(tmax === -1) tmax = tmp;
        
        if (tmax >= k) {
            end = mid - 1;
            answer = end;
        }
        else {
            start = mid + 1;
        }
        
    }
    return answer + 1;
}