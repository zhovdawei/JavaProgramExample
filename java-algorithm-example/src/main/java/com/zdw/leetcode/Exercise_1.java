package com.zdw.leetcode;

import java.util.HashMap;

/**
 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/two-sum
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * */
public class Exercise_1 {
    public int[] twoSum_1(int[] nums, int target) {
        int[] ints = new int[2];
        for(int i = 0;i<nums.length;i++){
            int a = target - nums[i];
            for (int j = 0; j < nums.length; j++) {
                if(i!=j && nums[j] == a){
                    return new int[]{j,i};
                }
            }
        }
        throw new NullPointerException();
    }

}

