package com.zdw.leetcode;

/**
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 *
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 *
 * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 *
 * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
 *
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
 *
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
 *
 * 说明：
 *
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 *
 * 示例 1:
 *
 * 输入: "42"
 * 输出: 42
 * 示例 2:
 *
 * 输入: "   -42"
 * 输出: -42
 * 解释: 第一个非空白字符为 '-', 它是一个负号。
 *      我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 * 示例 3:
 *
 * 输入: "4193 with words"
 * 输出: 4193
 * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 * 示例 4:
 *
 * 输入: "words and 987"
 * 输出: 0
 * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
 *      因此无法执行有效的转换。
 * 示例 5:
 *
 * 输入: "-91283472332"
 * 输出: -2147483648
 * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
 *      因此返回 INT_MIN (−231) 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/string-to-integer-atoi
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * */

public class Exercise_8 {

    private final static String WORD ="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private final static String NEGATIVE = "-";

    private final static String POSITIVE = "+";

    private final static String NUMBER = "0123456789";

    private final static long MAX_INT = Long.parseLong(String.valueOf(Integer.MAX_VALUE));

    private final static long MIN_INT = Long.parseLong(String.valueOf(Integer.MIN_VALUE));

    public int myAtoi(String str) {
        str = str.trim();
        int strLength = str.length();
        if(strLength==0){
            return 0;
        }
        String head = str.substring(0,1);
        int index = 0;
        String value = "";
        String sign = "";
        if(WORD.indexOf(head)>-1){
            return 0;
        }
        if(NEGATIVE.equals(head) || NUMBER.indexOf(head)>-1 || POSITIVE.equals(head)){
            if(NEGATIVE.equals(head)){
                index = 1;
                sign = NEGATIVE;
            }
            if(POSITIVE.equals(head)){
                index = 1;
                sign = POSITIVE;
            }
            for (int i = index; i < strLength; i++) {
                String val = String.valueOf(str.charAt(i));
                if(NUMBER.indexOf(val)>-1){
                    value = value +val;
                }else {
                    break;
                }
                if(Long.parseLong(sign+value)==0){
                    value = "";
                }
                if(value.length()>(11+index)){
                    break;
                }
            }
            if(value.length()<=0){
                return 0;
            }else {
                long valTar = Long.parseLong(sign+value);
                if(valTar<MIN_INT){
                    return (int)MIN_INT;
                }else if(valTar>MAX_INT){
                    return (int)MAX_INT;
                }else {
                    return (int)valTar;
                }
            }
        }
        return 0;
    }
    public int myAtoi2(String str) {
        if (str == null) return 0;
        str = str.trim();
        if (str.length() == 0) return 0;
        int i = 0;
        //2.判断数字的符号
        int flag = 1;
        char ch = str.charAt(i);
        if (ch == '+') {
            i++;
        } else if (ch == '-') {
            flag = -1;
            i++;
        }
        //3.找出数字部分
        int res = 0;
        for (; i < str.length(); i++) {
            ch = str.charAt(i);
            if (ch < '0' || ch > '9')
                break;
            //溢出判断
            if (flag > 0 && res > Integer.MAX_VALUE / 10)
                return Integer.MAX_VALUE;
            if (flag > 0 && res == Integer.MAX_VALUE / 10 && ch - '0' > Integer.MAX_VALUE % 10)
                return Integer.MAX_VALUE;
            if (flag < 0 && -res < Integer.MIN_VALUE / 10)
                return Integer.MIN_VALUE;
            if (flag < 0 && -res == Integer.MIN_VALUE / 10 && -(ch - '0') < Integer.MIN_VALUE % 10)
                return Integer.MIN_VALUE;
            res = res * 10 + ch - '0';
        }
        return res * flag;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);
        String str1 = "  -2147483648";
        String str = "   +0 123";
        System.out.println(new Exercise_8().myAtoi(str));


    }

}
