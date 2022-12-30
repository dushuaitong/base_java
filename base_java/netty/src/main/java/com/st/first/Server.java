package com.st.first;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author dushuaitong
 * @description: netty server
 * @date 2022/12/29
 */
public class Server {
    public static void main(String[] args) throws Exception {
        start();
    }

    public static void start() throws Exception {
        NioEventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap boot = new ServerBootstrap();
        try {
            boot.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(34566))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline().addLast(new ServerChannelHandel());
                        }
                    });
            ChannelFuture f = boot.bind().sync();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }
}
