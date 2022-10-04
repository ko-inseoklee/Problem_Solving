const localFile = "1644.txt"
const fs = require('fs');
const filePath = process.platform === 'linux' ? '/dev/stdin' : localFile;
let input = fs.readFileSync(filePath).toString().trim().split('\n');

const nums = input[0].split(" ");

const num = parseInt(nums[0]);

let primes = [];

let check = new Array(num + 1).fill(true);
for (let i = 2; i * i <= num; i++) {
  if (!check[i]) continue;
  for (let j = i * i; j <= num; j += i) {
    check[j] = false;
  }
}
for (let i = 2; i <= num; i++) {
  if (check[i]) primes.push(i);
}

let tmp = 0;
let start = 0, end = 0;
let result = 0;

while(end < primes.length) {
    tmp += primes[end++];
    while(tmp > num) tmp -= primes[start++];

    if (tmp === num) result ++;
}

console.log(result);