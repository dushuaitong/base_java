package com.st.java.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author dushuaitong
 */
public class NioClient implements Runnable {

    private SocketChannel socketChannel;
    private Selector selector;
    private volatile boolean started;
    private String ip;
    private int port;

    public NioClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            started = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        SelectionKey key = null;
        try {
            connect();
            while (started) {
                selector.select(1000);
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();
                while (it.hasNext()) {
                    key = it.next();
                    it.remove();
                    write(key);
                }
            }
        } catch (Exception e) {
            if (key != null){
                key.cancel();
                if (key.channel() != null) {
                    try {
                        key.channel().close();
                    } catch (Exception ex) { }
                }
            }
        }
    }

    private void write(SelectionKey key) throws Exception {
        if (key.isValid()) {
            SocketChannel sc = (SocketChannel) key.channel();
            if (key.isConnectable()) {
                if (sc.finishConnect()) {
                    socketChannel.register(selector,
                            SelectionKey.OP_READ);
                } else {
                    System.exit(1);
                }
            }
            if (key.isReadable()) {
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(buffer);
                if (readBytes > 0 ) {
                    buffer.flip();
                    byte[] bytes = new byte[buffer.remaining()];
                    buffer.get(bytes);
                    String result = new String(bytes,"UTF-8");
                    System.out.println("客户端收到消息：" + result);
                } else if( readBytes < 0 ){
                    key.cancel();
                    sc.close();
                }
            }
        }
    }

    private void connect() throws Exception {
        if (socketChannel.connect(new InetSocketAddress(ip, port))){
            socketChannel.register(selector, SelectionKey.OP_READ);
        } else {
            socketChannel.register(selector,SelectionKey.OP_CONNECT);
        }
    }

    public void sendMsg(String msg) throws Exception{
        doWrite(socketChannel, msg);
    }

    private void doWrite(SocketChannel channel,String request)
            throws IOException {
        byte[] bytes = request.getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        writeBuffer.put(bytes);
        writeBuffer.flip();
        channel.write(writeBuffer);
    }
}
