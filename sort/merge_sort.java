package sort;

import java.util.Arrays;

public class merge_sort {

    int[] arr = {6, 12, 4, 9, 1};

    public static void main(String[] args) {
        merge_sort ms = new merge_sort();
        int[] result = ms.sort(ms.arr);
        System.out.println(Arrays.toString(result));
    }

    public int[] sort(int[] arr) {
        if(arr.length == 1) {
            return arr;
        }

        int mid = arr.length / 2;
        int[] left = sort(Arrays.copyOfRange(arr, 0, mid));
        int[] right = sort(Arrays.copyOfRange(arr, mid, arr.length));



        return merge(left, right);

    }

    public int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];

        int i = 0, j = 0;
        while(i < left.length && j < right.length) {
            if(left[i] < right[j]) {
                result[i + j] = left[i++];
            } else {
                result[i + j] = right[j++];
            }
        }

        while(i < left.length) {
            result[i + j] = left[i++];
        }

        while(j < right.length) {
            result[i + j] = right[j++];
        }

        System.out.println(Arrays.toString(result));

        return result;
    }
}
