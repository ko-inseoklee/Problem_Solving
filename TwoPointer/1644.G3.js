const localFile = "1644.txt"
const fs = require('fs');
const filePath = process.platform === 'linux' ? '/dev/stdin' : localFile;
let input = fs.readFileSync(filePath).toString().trim().split('\n');

const nums = input[0].split(" ");

console.log(nums);