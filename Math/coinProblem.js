function solution(beginning, target) {
    var answer = 0;
    var arr = new Array(beginning.length);
    for(var i = 0; i < arr.length; i++){
        arr[i] = '';
    }
    for(var i = 0; i < arr.length; i++){
        for(var j = 0; j < arr.length; j++){
            if (beginning[i][j] === target[i][j]) arr[i] += (0).toString();
            else arr[i] += (1).toString();
        }
    }
    
    for(var i = 1; i < arr.length; i++){
        if (arr[i] === arr[0]) continue;
        else {
            var tmp = (parseInt(arr[i]) + parseInt(arr[0])).toString();
            if(tmp.indexOf(0) !== -1) {
                answer = -1;
                break;
            }
        }
    }

    if (answer !== -1){
        // 1이 많은 쪽부터 뒤집기(row,col)
        // 반복하면서 카운팅 (그래도 시간 안넘김.)
    }
    
    
    return answer;
}