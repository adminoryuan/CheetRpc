package com.cheet.netty.client;

import com.cheet.Entity.RpcResponse;
import com.cheet.call.OnRpcRevice;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author yh
 * @date 2022/6/20 下午8:32
 */
public class ClientHandle extends ChannelInboundHandlerAdapter {
    OnRpcRevice onRpcRevice=OnRpcRevice.getIntstance();
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {


        RpcResponse msg1 =(RpcResponse) msg;

        onRpcRevice.Put(msg1.getRequestId(),msg1.getData());

        super.channelRead(ctx, msg);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }
}
