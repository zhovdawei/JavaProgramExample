package net.login;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerExample {

    public static void server1() throws IOException {

        ServerSocket server = new ServerSocket(12345);
        Socket client = server.accept();
        System.out.println("一个客户端连接成功");
        OutputStream out = client.getOutputStream();
        InputStream input = client.getInputStream();

        byte[] data = new byte[1024];
        int len;
        while ((len = input.read(data)) != -1) {
            System.out.println(new String(data,0,len));
        }

        out.write("欢迎登陆".getBytes());
        out.flush();
        client.close();
        client.close();
    }

    public static void main(String[] args) throws IOException {
        server1();
    }

}
