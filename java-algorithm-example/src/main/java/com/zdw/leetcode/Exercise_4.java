package com.zdw.leetcode;

/**
 *给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 *
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * */
public class Exercise_4 {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int i=0,j=0;
        int size = nums1.length+nums2.length;
        int[] nums = new int[size];
        for (int k = 0; k < size; k++) {
            if(i<nums1.length && j<nums2.length){
                if(nums1[i]<nums2[j]){
                    nums[k] = nums1[i];
                    i++;
                }else {
                    nums[k] = nums2[j];
                    j++;
                }
            }else if(i>=nums1.length){
                nums[k] = nums2[j];
                j++;
            }else if(j>=nums2.length){
                nums[k] = nums1[i];
                i++;
            }
        }

        if(size%2==0){
            return Double.parseDouble(nums[size/2]+nums[size/2 - 1]+"")/2;
        }else {
            return nums[size/2];
        }
    }

    public static double method1(int[] nums1, int[] nums2){




        return 0;
    }


    public static void main(String[] args) {
        int[] int1 = new int[]{1,2};
        int[] int2 = new int[]{3,4};

        System.out.println(findMedianSortedArrays(int1,int2));
    }

}
