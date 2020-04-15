package net;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemo {

    public static void createServer() throws IOException {
        ServerSocket server = new ServerSocket(12345);
        Socket client = server.accept();
        InputStream is = client.getInputStream();
        byte[] bytes = new byte[1024];
        int num = is.read(bytes);
        String msg = new String(bytes, 0, num);
        System.out.println(client.getInetAddress().toString() + " => " + msg);
        client.close();
        server.close();
    }

    public static void main(String[] args) throws IOException {
        createServer();
    }

}
