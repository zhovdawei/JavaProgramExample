package com.zdw.leetcode;

/**
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/regular-expression-matching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * */

public class Exercise_10 {

    public boolean isMatch(String s, String p) {
        String type_1 = ".*";
        String type_2 = ".";
        String tyoe_3 = "*";
        if(s.length()<0){
            if(p.indexOf(type_2)>-1){
                return true;
            }else {
                if(p.length()>0){
                    return false;
                }else {
                    return true;
                }
            }
        }
        if(p.indexOf(type_1)>-1){
            return true;
        }
        if(s.equals(p)){
            return true;
        }
        //抽象化s
        s=method(s);
        if(p.indexOf(s)>-1){
            return true;
        }else {
            if(s.substring(s.length()-1).equals(tyoe_3)){
                if(p.indexOf(s)>-1){
                    return true;
                }else {
                    return false;
                }
            }else {
                int index_s = s.lastIndexOf("*");
                String left_s = s.substring(0,index_s+1);
                String right_s = s.substring(index_s+1);
                int index_p = p.lastIndexOf("*");
                String left_p,right_p;
                if(index_p<p.length()){
                    left_p = p.substring(0,index_p+1);
                    right_p = p.substring(index_p+1);
                }else {
                    left_p = p;
                    right_p = "";
                }
                if(left_p.indexOf(left_s)>-1){
                    if(right_s.length() == right_p.length()){
                        for (int i = 0; i < right_p.length() ; i++) {
                            if(right_p.charAt(i)!='.' && right_p.charAt(i) == right_s.charAt(i)){
                                return false;
                            }
                        }
                        return true;
                    }else {
                        return false;
                    }
                }else {
                    return false;
                }
            }
        }

    }

    public static String method(String s){
        String copy = s;
        int start,end;
        //抽象化s
        for (int i = 0; i < s.length() ; i++) {
            char tar = s.charAt(i);
            start = i;
            end = i;
            while (i+1<s.length() && tar == s.charAt(i+1)){
                end = i+1;
                i++;
            }
            if(start<end){
                String part_s = s.substring(start,end+1);
                copy = copy.replaceAll(part_s,tar+"*");
            }
        }
        return copy;
    }


    public static void main(String[] args) {


        System.out.println(new Exercise_10().isMatch("ab",".*c"));



    }

}
