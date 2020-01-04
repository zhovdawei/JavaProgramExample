package com.zdw.leetcode;

import java.util.*;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * */
public class Exercise_3 {

    public static int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        String maxStr = "";
        int maxLength = 0;
        for (int i = 0; i < chars.length ; i++) {
            String charStr = String.valueOf(chars[i]);
            if(maxStr.indexOf(charStr)<0){
                maxStr = maxStr +charStr;
            }else {
                if(maxLength < maxStr.length()){
                    maxLength = maxStr.length();
                }
                maxStr = "" + charStr;
            }
        }
        if(maxLength < maxStr.length()){
            maxLength = maxStr.length();
        }
        return maxLength;
    }

    public static int lengthOfLongestSubstring1(String s) {
        char[] chars = s.toCharArray();
        String maxStr = "";
        int maxLength = 0;
        for (int i = 0; i < chars.length ; i++) {
            for (int j = i; j < chars.length; j++) {
                String charStr = String.valueOf(chars[j]);
                if(maxStr.indexOf(charStr)<0){
                    maxStr = maxStr +charStr;
                }else {
                    if(maxLength < maxStr.length()){
                        maxLength = maxStr.length();
                    }
                    maxStr = "" + charStr;
                }
            }
            if(maxLength < maxStr.length()){
                maxLength = maxStr.length();
            }
            maxStr = "";
        }
        if(maxLength < maxStr.length()){
            maxLength = maxStr.length();
        }
        return maxLength;
    }

    public static int lengthOfLongestSubstring2(String s) {
        char[] chars = s.toCharArray();
        Set<Integer> set = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1>o2){
                    return -1;
                }else if(o1.equals(o2)){
                    return 0;
                }else {
                    return 1;
                }
            }
        });
        int length = chars.length;
        String maxStr = "";
        for (int i = 0; i < length ; i++) {
            for (int j = i; j < length; j++) {
                String charStr = String.valueOf(chars[j]);
                if(maxStr.indexOf(charStr)<0){
                    maxStr = maxStr +charStr;
                }else {
                    maxStr = "" + charStr;
                }
                set.add(maxStr.length());
            }
            maxStr = "";
        }
        if(set.size()>0){
            return set.iterator().next();
        }
        return 0;
    }

    public static int lengthOfLongestSubstring3(String s){
        int length = s.length();
        char[] chars = s.toCharArray();
        int max = 0;
        String maxStr = "";
        for (int i = 0; i < length ; i++) {
            String str = String.valueOf(chars[i]);
            if(maxStr.indexOf(str)<0){
                maxStr = maxStr + str;
            }else {
                if(max<maxStr.length()){
                    max = maxStr.length();
                }
                while (maxStr.indexOf(str)>=0){
                    if(maxStr.length()>1){
                        maxStr = maxStr.substring(1);
                    }else {
                        maxStr = "";
                    }
                }
                maxStr = maxStr + str;
            }
        }
        if(max<maxStr.length()){
            max = maxStr.length();
        }
        return max;
    }

    public static void main(String[] args) {
        int length = Exercise_3.lengthOfLongestSubstring3("abcabcbb");
        System.out.println(length);
    }

}
