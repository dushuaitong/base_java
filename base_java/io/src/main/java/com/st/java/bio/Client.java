package com.st.java.bio;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class Client {

    public static void main(String[] args) throws Exception {
        Socket socket = null;
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        InetSocketAddress addr = new InetSocketAddress("127.0.0.1", 10034);
        try {
            socket = new Socket();
            socket.connect(addr);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
            while (true) {
                outputStream.writeUTF("make hello word");
                outputStream.flush();
                TimeUnit.SECONDS.sleep(2);

                String userName = inputStream.readUTF();
                System.out.println("接收服务端端message:" + userName);
                outputStream.writeUTF("hello," + userName);
                outputStream.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }
}