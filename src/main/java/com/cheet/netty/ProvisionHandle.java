package com.cheet.netty;

import com.cheet.Entity.RpcRequest;
import com.cheet.Entity.RpcResponse;
import com.cheet.call.ExecFunc;
import com.cheet.call.ExecFuncImpl;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author yh
 * @date 2022/6/20 下午7:50
 */
public class ProvisionHandle extends ChannelInboundHandlerAdapter {
    ExecFunc exec=new ExecFuncImpl();
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RpcRequest msg1 = (RpcRequest) msg;


        Object res = exec.Exec(msg1.getMethod(),msg1.getArgs());

        System.out.println(res);
        RpcResponse response=new RpcResponse();

        response.setRequestId(msg1.getRequestId());

        response.setData(res);

        response.setSuccess(true);

        System.out.println("res");

        ctx.writeAndFlush(response);
        //super.channelRead(ctx, msg);
    }
}
