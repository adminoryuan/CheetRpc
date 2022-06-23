package com.cheet.netty.client;

import com.cheet.serializer.CustomerMessageDecoder;
import com.cheet.serializer.CustomerMessageEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author yh
 * @date 2022/6/21 下午7:38
 */
public class ClientChannelHandle extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        socketChannel.pipeline().addLast(new CustomerMessageDecoder());
        socketChannel.pipeline().addLast(new CustomerMessageEncoder());
        socketChannel.pipeline().addLast(ClientHandle.getHandleInstance());
    }
}
