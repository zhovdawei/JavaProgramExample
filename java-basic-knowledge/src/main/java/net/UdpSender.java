package net;

import java.io.IOException;
import java.net.*;

public class UdpSender {

    public static void sent() throws IOException {
        DatagramSocket ds = new DatagramSocket();
        byte[] bys = "Hello,world!".getBytes();
        DatagramPacket dp = new DatagramPacket(bys,0,bys.length,
                InetAddress.getByName("localhost"),12333);
        ds.send(dp);
        ds.close();
    }

    public static void main(String[] args) throws IOException {
        sent();
    }
}
