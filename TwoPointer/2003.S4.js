const fs = require('fs');
const filePath = process.platform === 'linux' ? '/dev/stdin' : '2003.txt';
let input = fs.readFileSync(filePath).toString().trim().split('\n');

const [l, tn] = input[0].split(" ");
const nums = input[1].split(" ");

n = parseInt(tn);

let result = 0;

let head = 0, tail = 0, sub = 0;

sub = parseInt(nums[head]);
if(sub === n) result++;
// console.log(nums.length);

while (true) {
    // console.log(`[Before] sub = ${sub}, head = ${head}, tail = ${tail}`);
    if(tail === nums.length - 1){
        // console.log(`   almost end`);
        while(head < tail){
            // console.log(`    [Inner While] sub = ${sub}, head = ${head}, tail = ${tail}`);
            sub -= parseInt(nums[head++]);

            if(sub === n) result++;
        }

        break;
    }
    else{
        if(sub <= n) sub += parseInt(nums[++tail]);
        else sub -= parseInt(nums[head++]);

        if(sub === n) result++;
    }

    // console.log(`  [After] sub = ${sub}, head = ${head}, tail = ${tail}`);
}

console.log(result);