package com.zdw.leetcode;

public class Exercise_9 {

    public boolean isPalindrome(int x) {
        if(x<0){
            return false;
        }else if(x<10){
            return true;
        }else {
            int m = x;
            int n = m%10;
            m = m/10;
            while (m>0){
                n = n *10 + m%10;
                m = m/10;
            }

            if(n == x){
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(new Exercise_9().isPalindrome(11));
    }

}
