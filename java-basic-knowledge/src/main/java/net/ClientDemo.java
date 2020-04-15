package net;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class ClientDemo {

    public static void createClient() throws IOException {
        Socket client = new Socket("192.168.163.1",12345);
        OutputStream out = client.getOutputStream();
        out.write("hello".getBytes());
        System.out.println("客户端已经发送");
        client.close();
    }

    public static void main(String[] args) throws IOException {
        createClient();
    }

}
