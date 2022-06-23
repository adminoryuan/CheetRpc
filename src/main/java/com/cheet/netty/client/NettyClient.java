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
    private ClientHandle handle=ClientHandle.getHandleInstance();

    private volatile Channel channel;

    public void Dial(String addr,int port) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap  = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientChannelHandle());
            ChannelFuture future = bootstrap.connect(addr, port);

            channel=future.sync().channel();


//            future.addListener(new ChannelFutureListener() {
//                @Override
//                public void operationComplete(ChannelFuture channelFuture) throws Exception {
//                    channel=channelFuture.channel();
//                    System.out.println("成功");
//                }
//            });
           // Object write = CallRemote("", 100 , 200);
           this.channel.closeFuture().sync();

        } catch (Exception e) {
            throw new Exception("链接错误，请检查ip地址是否正确");
          //  e.printStackTrace();
        } finally {
           group.shutdownGracefully();
        }



    }

    public Object CallRemote(String method,Object... args) throws InterruptedException {
        RpcRequest request=new RpcRequest();

        request.setMethod(method);

        request.setArgs(args);

        request.setMethodLen((byte)2);

        this.channel.writeAndFlush(request);

        return  handle.Call();

    }


}
