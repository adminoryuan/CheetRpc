package com.cheet.serializer;

import com.cheet.Entity.RpcResponse;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * @author yh
 * @date 2022/6/22 下午3:32
 */
public class ProvisionMessageEncoder extends MessageToByteEncoder<RpcResponse> {


    @Override
    protected void encode(ChannelHandlerContext ctx, RpcResponse rps, ByteBuf byteBuf) throws Exception {
        int len=0;

        byte[] bytes = rps.getRequestId().getBytes();

        len+=bytes.length;

        len+=2;

        byte[] bytes1 = SerialiZerTools.objectToByte(rps.getData());

        len+=bytes1.length;

        byteBuf.writeInt(len);

        //添加qid的长度
        byteBuf.writeInt(bytes.length);

        byteBuf.writeBytes(bytes);

        byteBuf.writeBoolean(rps.isSuccess());

        //消息长度
        byteBuf.writeInt(bytes1.length);

        byteBuf.writeBytes(bytes1);

        System.out.println("发送成功");
    }
}
