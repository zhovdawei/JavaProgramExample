package net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpReceiver {

    public static void receive() throws IOException {
        DatagramSocket ds = new DatagramSocket(12333);
        byte[] bys = new byte[1024];
        DatagramPacket dp = new DatagramPacket(bys, bys.length);
        ds.receive(dp);
        String str = new String(dp.getData(), 0, dp.getLength());
        System.out.println(str + " => " + dp.getAddress());
        ds.close();
    }

    public static void main(String[] args) throws IOException {
        receive();
    }

}
