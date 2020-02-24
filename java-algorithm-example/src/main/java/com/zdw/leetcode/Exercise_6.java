package com.zdw.leetcode;

/**
 *将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 *
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 *
 * 请你实现这个将字符串进行指定行数变换的函数：
 *
 * string convert(string s, int numRows);
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zigzag-conversion
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * */
public class Exercise_6 {

    public String convert(String s, int numRows) {
        if(numRows==1){
            return s;
        }
        String[] strs = new String[numRows];
        char[] chars = s.toCharArray();
        int flag = 1;
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            String cx = String.valueOf(chars[i]);
            strs[index] = strs[index]==null?cx:strs[index]+cx;
            if(flag>0){
                index ++;
                if(index==numRows-1){
                    flag = -1;
                }
            }else {
                index --;
                if(index == 0){
                    flag = 1;
                }
            }
        }
        String maxStr = "";
        for (int i = 0; i < strs.length; i++) {
            maxStr = maxStr + (strs[i]==null?"":strs[i]);
        }
        return maxStr;
    }

    public static void main(String[] args) {
        String str = "PAYPALISHIRING";
        System.out.println(new Exercise_6().convert(str,3));
    }

}
