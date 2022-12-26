package com.st.java.nio.server;

/**
 * @author dushuaitong
 */
public class Server {
    private static NioServer nioServer;

    public static void main(String[] args){
        nioServer = new NioServer(8896);
        new Thread(nioServer,"Server").start();
    }
}
