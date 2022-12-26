package com.st.java.nio.server;

import java.util.Scanner;

/**
 * @author dushuaitong
 */
public class Client {
    private static NioClient nioClient;

    public static void start(){
        nioClient = new NioClient("127.0.0.1", 8896);
        new Thread(nioClient,"client").start();
    }

    public static boolean sendMsg(String msg) throws Exception {
        nioClient.sendMsg(msg);
        return true;
    }

    public static void main(String[] args) throws Exception {
        start();
        Scanner scanner = new Scanner(System.in);
        nioClient.sendMsg(scanner.next());
    }
}
