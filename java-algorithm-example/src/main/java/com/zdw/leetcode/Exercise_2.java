package com.zdw.leetcode;

public class Exercise_2 {

     class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
         ListNode nodeHead = new ListNode(0);
         ListNode a = l1,b = l2,node = nodeHead;
         int carry = 0;
         while (a != null || b != null){
             int x = (a!=null)?a.val:0;
             int y = (b!=null)?b.val:0;
             int sum = carry+x+y;
             carry = sum / 10;
             node.next = new ListNode(sum % 10);
             node = node.next;
             if(a!=null){
                 a = a.next;
             }
             if(b!=null){
                 b = b.next;
             }
         }
         if(carry>0){
             node.next = new ListNode(carry);
         }
         return nodeHead.next;
    }


}
