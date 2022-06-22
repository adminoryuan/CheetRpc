package com.cheet.netty;

import com.cheet.serializer.ProvisionMessageDecoder;
import com.cheet.serializer.ProvisionMessageEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author yh
 * @date 2022/6/21 下午7:38
 */
public class ProvisionChannelHandle extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(new ProvisionMessageEncoder());
        socketChannel.pipeline().addLast(new ProvisionMessageDecoder());
        socketChannel.pipeline().addLast(new ProvisionHandle());
    }
}
