package com.company;

public class TertiarySearch {
    // perform binary search
    private int ternarySearch(int[] arr, int low, int high, int res) {
        // base case when input is out of bounds
        if (low > high) return -1;

        // split arr into three equal parts
        int firstMid = low + (high - low)/3;
        int secondMid = firstMid + (high - low)/2;

        // return when mid is found
        if (arr[firstMid] == res) return firstMid;
        if (arr[secondMid] == res) return secondMid;

        if (res < arr[firstMid])
            return ternarySearch(arr, low, firstMid - 1, res);
        if (res > arr[firstMid] && res < arr[secondMid])
            return ternarySearch(arr, firstMid + 1, secondMid - 1, res);
        if (res > arr[secondMid]) return ternarySearch(arr, secondMid + 1, high, res);

        return -1;
    }


    public static void main(String[] args) {
        // write your code here

        int[] res = new int[]{1, 3, 5, 7, 9, 11, 15};
        TertiarySearch tertiarySearchObj = new TertiarySearch();

        // test case 1: when item is in the array
        int itemInList = 11;
        int itemInListResult = tertiarySearchObj.ternarySearch(res, 0, res.length - 1, itemInList);
        System.out.println(itemInListResult);

        // test case 2: when item is not in the array
        int itemNotInList = 19;
        int itemNotInListResult = tertiarySearchObj.ternarySearch(res ,0, res.length - 1, itemNotInList);
        System.out.println(itemNotInListResult);
    }
}
