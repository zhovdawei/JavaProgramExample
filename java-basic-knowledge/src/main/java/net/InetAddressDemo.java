package net;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressDemo {
    public static void test1() throws UnknownHostException {
        InetAddress netAdd = InetAddress.getByName("zdw.gitlab.com");
        System.out.println(netAdd);
        System.out.println(netAdd.getHostName());
        System.out.println(netAdd.getHostAddress());
    }

    public static void test2() throws UnknownHostException {
        InetAddress netAdd = InetAddress.getLocalHost();
        System.out.println(netAdd);
    }

    public static void main(String[] args) throws UnknownHostException {
        test1();
        test2();
    }
}
