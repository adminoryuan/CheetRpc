package com.cheet.netty.client;

import com.cheet.Entity.RpcRequest;
import com.cheet.Entity.RpcResponse;
import com.cheet.call.OnRpcRevice;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author yh
 * @date 2022/6/20 下午8:33
 */
public class NettyClient {

    public NettyClient() {

    }
    private ClientHandle handle=new ClientHandle();

    private volatile Channel channel;

    public void Dial(String addr,int port) throws Exception {

             EventLoopGroup group = new NioEventLoopGroup();

            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientChannelHandle(handle));
            ChannelFuture future = bootstrap.connect(addr, port);

            channel = future.sync().channel();


            channel.closeFuture().addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    System.out.println("关闭了");
                }
            });
    }

    public Object SyncCall(String method,Object... args) throws InterruptedException {
        RpcRequest request=new RpcRequest();

        request.setMethod(method);

        request.setArgs(args);

        request.setMethodLen((byte)2);

        this.channel.writeAndFlush(request);

        return  handle.Call();

    }


}
