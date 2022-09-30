const fs = require('fs');
const filePath = process.platform === 'linux' ? '/dev/stdin' : '1013.txt';
let input = fs.readFileSync(filePath).toString().trim().split('\n');

const re = new RegExp('^(100+1+|01)+$');

const y = "YES";
const n = "NO";

for(let i = 1; i < input.length; i++){
    if(re.test(input[i])) console.log(y);
    else console.log(n);
}

