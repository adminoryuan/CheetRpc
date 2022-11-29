package com.cheet.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author yh
 * @date 2022/6/20 下午8:00
 */
public class NettyServer {
    public NettyServer() {

    }
    ProvisionChannelHandle provisionChannelHandle = new ProvisionChannelHandle();
    public void Heartbeat(int WriteTime, int ReadOutTime, int ALL_READTIME) {
        provisionChannelHandle.Heartbeat(WriteTime,ReadOutTime,ALL_READTIME);
    }

    public void Run(int Port){
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
        EventLoopGroup workerGroup = new NioEventLoopGroup();


        try {
            ServerBootstrap b = new ServerBootstrap(); // (2)
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class) // (3)
                    .childHandler(provisionChannelHandle)  //(4)
                    .option(ChannelOption.SO_BACKLOG, 128)          // (5)
                    .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)


            // 绑定端口，开始接收进来的连接
            ChannelFuture f = b.bind(Port).sync(); // (7)

            // 等待服务器  socket 关闭 。
            f.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
