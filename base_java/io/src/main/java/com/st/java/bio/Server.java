package com.st.java.bio;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author dushuaitong
 */
public class Server {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(10034));
        try {
            while (true) {
                // 此处也可以更换为更好的线程池
                new Thread(new ServerTask(serverSocket.accept())).start();
            }
        } finally {
            serverSocket.close();
        }
    }

    private static class ServerTask implements Runnable {
        private Socket socket;

        public ServerTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream())) {
                int i = 0;
                while (true) {
                    String userName = inputStream.readUTF();
                    System.out.println("接收到客户端message:" + userName);
                    outputStream.writeUTF("hello," + userName);
                    outputStream.flush();
                    if (i++ > 30) {
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
