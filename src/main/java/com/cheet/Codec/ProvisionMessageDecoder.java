package com.cheet.Codec;

import com.cheet.Entity.RpcRequest;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author yh
 * @date 2022/6/21 上午9:19
 */
public class ProvisionMessageDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> list) throws Exception {
        int redLen=0;
        //

        if (in.readableBytes()>4) {
             redLen = in.readInt();

        }

        if (redLen>4 && in.readableBytes()>=redLen){
            byte reqLen=in.readByte();
            RpcRequest request=new RpcRequest();
            byte[] ByteArray=new byte[reqLen];

            for (int i=0;i<reqLen;i++){
                ByteArray[i]=in.readByte();
            }
             request.setRequestId(new String(ByteArray));

            int MethodLen=in.readByte();
            ByteArray=new byte[MethodLen];
            for (int i=0;i<MethodLen;i++){
                ByteArray[i]=in.readByte();
            }
            request.setMethod(new String(ByteArray));

            byte ArgsLens=in.readByte();

            Object[] cls=new Object[ArgsLens];
            for (int i=0;i<ArgsLens;i++){
                int ReadLen=in.readInt();
                ByteBuf byteBuf = in.readBytes(ReadLen);
                cls[i]=SerialiZerTools.byteToObject(byteBuf.array());
            }
            request.setArgsByteLen(ArgsLens);
            request.setArgs(cls);

            list.add(request);
        }


    }
}
