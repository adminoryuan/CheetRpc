package com.cheet.netty.client;

import com.cheet.Entity.RpcResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author yh
 * @date 2022/6/20 下午8:32
 */
public class ClientHandle extends ChannelInboundHandlerAdapter {
    SyncFuture<RpcResponse> syncFuture=new SyncFuture<>(); //初始化同步器


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent evt1 = (IdleStateEvent) evt;
            if (evt1.state()== IdleState.WRITER_IDLE){
                System.out.println("读超时");
            }

        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {


        RpcResponse msg1 =(RpcResponse) msg;

        syncFuture.setResponse(msg1); //将消息放入同步器

        super.channelRead(ctx, msg);
    }

    /**
     *
     * @return
     */
    public Object Call() {
        //阻塞等待消息响应
        return syncFuture.get().getData();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        super.channelInactive(ctx);
    }
}
