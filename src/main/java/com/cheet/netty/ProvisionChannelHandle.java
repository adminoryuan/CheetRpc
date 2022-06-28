package com.cheet.netty;

import com.cheet.serializer.ProvisionMessageDecoder;
import com.cheet.serializer.ProvisionMessageEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @author yh
 * @date 2022/6/21 下午7:38
 */
public class ProvisionChannelHandle extends ChannelInitializer<SocketChannel> {

    private static  int READ_TIMEOUT=4;
    private static  int Write_Timeout=6;
    private static  int ALL_IDEL_TIME_OUT=7;

    public void Heartbeat(int WriteTime, int ReadOutTime, int ALL_READTIME){
        READ_TIMEOUT=READ_TIMEOUT;
        Write_Timeout=WriteTime;
        ALL_IDEL_TIME_OUT=ALL_READTIME;
    }
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        if (READ_TIMEOUT!=-1)
            socketChannel.pipeline().addLast(new IdleStateHandler(READ_TIMEOUT,Write_Timeout,ALL_IDEL_TIME_OUT));
        socketChannel.pipeline().addLast(new ProvisionMessageEncoder());
        socketChannel.pipeline().addLast(new ProvisionMessageDecoder());
        socketChannel.pipeline().addLast(new ProvisionHandle());
    }
}
