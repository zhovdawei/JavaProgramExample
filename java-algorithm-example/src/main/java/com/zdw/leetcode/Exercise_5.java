package com.zdw.leetcode;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Exercise_5 {

    private Set<String> set = new TreeSet<>(new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            int length1 = o1.length();
            int length2 = o2.length();
            if(length1>length2){
                return -1;
            }else if(length1==length2){
                return 0;
            }else {
                return 1;
            }
        }
    });

    public String longestPalindrome(String s) {
        if(s.length()==0 || s.length()==1){
            return s;
        }
        int mid = s.length()/2;
        validateStr(s,0,mid);
        validateStr(s,mid+1,s.length()-1);
        return set.iterator().next();
    }

    public void validateStr(String s,int start,int end){
        if(start<=end){
            int mid = (start+end)/2;
            //先判断中心对称
            pointSame(s,mid);
            //再判断左对称
            leftSame(s,mid);
            //再判断右对称
            rightSame(s,mid);
            if(start != end){
                validateStr(s,start,mid);
                validateStr(s,mid+1,end);
            }
        }
    }

    public void pointSame(String s,int mid){
        int left = mid-1;
        int right = mid+1;
        String maxStr = String.valueOf(s.charAt(mid));
        while (left>=0 && right<s.length()){
            char a = s.charAt(left);
            char b = s.charAt(right);
            if(a==b){
                maxStr = a+maxStr+b;
            }else {
                break;
            }
            left = left-1;
            right = right+1;
        }
        set.add(maxStr);
    }

    public void leftSame(String s,int mid){
        int left = mid -1;
        int right = mid;
        String maxStr = "";
        while (left>=0 && right<s.length()){
            char a = s.charAt(left);
            char b = s.charAt(right);
            if(a==b){
                maxStr = a+maxStr+b;
            }else {
                break;
            }
            left = left-1;
            right = right+1;
        }
        set.add(maxStr);
    }

    public void rightSame(String s,int mid){
        int left = mid;
        int right = mid+1;
        String maxStr = "";
        while (left>=0 && right<s.length()){
            char a = s.charAt(left);
            char b = s.charAt(right);
            if(a==b){
                maxStr = a+maxStr+b;
            }else {
                break;
            }
            left = left-1;
            right = right+1;
        }
        set.add(maxStr);
    }


    public static void main(String[] args) {
        String s = "babad";
        Exercise_5 exercise_5 = new Exercise_5();
        System.out.println(exercise_5.longestPalindrome(s));

    }

}
