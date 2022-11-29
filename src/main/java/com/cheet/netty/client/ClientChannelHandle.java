package com.cheet.netty.client;

import com.cheet.Codec.CustomerMessageDecoder;
import com.cheet.Codec.CustomerMessageEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author yh
 * @date 2022/6/21 下午7:38
 */
public class ClientChannelHandle extends ChannelInitializer<SocketChannel> {

    private static final int READ_TIMEOUT=4;
    private static final int Write_Timeout=6;
    private static final int ALL_IDEL_TIME_OUT=7;

    private ClientHandle handle;

    public ClientChannelHandle(ClientHandle handle) {
        this.handle = handle;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        //心跳检测
        socketChannel.pipeline().addLast(new CustomerMessageDecoder());
        socketChannel.pipeline().addLast(new CustomerMessageEncoder());
        socketChannel.pipeline().addLast(handle);
    }
}
