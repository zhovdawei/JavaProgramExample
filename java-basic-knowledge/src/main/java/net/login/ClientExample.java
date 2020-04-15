package net.login;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientExample {

    public static void client1() throws IOException {
        Socket client = new Socket("localhost", 12345);
        OutputStream output = client.getOutputStream();
        InputStream input = client.getInputStream();

        output.write("admin".getBytes());
        output.flush();
        client.shutdownOutput();

        byte[] data = new byte[1024];
        int len;
        while ((len = input.read(data)) != -1) {
            System.out.println(new String(data,0,len));
        }
        client.close();
    }

    public static void main(String[] args) throws IOException {
        client1();
    }

}
