package com.cheet.serializer;

import com.cheet.Entity.RpcResponse;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author yh
 * @date 2022/6/22 下午3:40
 */
public class CustomerMessageDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> list) throws Exception {
        int msgCount =0;
        if (byteBuf.readableBytes()>4){
            msgCount = byteBuf.readInt();

        }

        if (msgCount!=0 && byteBuf.readableBytes()>=msgCount){

            RpcResponse rpc=new RpcResponse();
            int ReqidLen=byteBuf.readInt();

            ByteBuf byteBuf1 = byteBuf.readBytes(ReqidLen);

            rpc.setRequestId(new String(byteBuf1.array()));

            rpc.setSuccess(byteBuf.readBoolean());

            int bodyLen=byteBuf.readInt();


            rpc.setData(SerialiZerTools.byteToObject(byteBuf.readBytes(bodyLen).array()));

            list.add(rpc);
        }
    }
}
