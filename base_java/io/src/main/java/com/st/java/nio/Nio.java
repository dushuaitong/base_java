package com.st.java.nio;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class Nio {
    public static void main(String[] args) throws Exception {
        Selector selector = Selector.open();
        ServerSocketChannel chanel = ServerSocketChannel.open();
        chanel.configureBlocking(false);
        chanel.socket().bind(new InetSocketAddress(100025), 1024);
        chanel.register(selector, SelectionKey.OP_ACCEPT);
    }
}
