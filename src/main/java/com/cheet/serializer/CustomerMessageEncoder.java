package com.cheet.serializer;

import com.cheet.Entity.RpcRequest;
import com.sun.xml.internal.ws.wsdl.writer.document.soap.Body;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author yh
 * @date 2022/6/21 上午9:19
 */
public class CustomerMessageEncoder extends MessageToByteEncoder<RpcRequest> {


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, RpcRequest rpcRequest, ByteBuf byteBuf) throws Exception {

        String requestId = rpcRequest.getRequestId();
        int SumLens=0;

        List<Byte> reqByte=new ArrayList<>();

        reqByte.add((byte)requestId.getBytes().length);

        for (byte aByte : requestId.getBytes()) {
            reqByte.add(aByte);
        }




        byte lens=(byte)rpcRequest.getMethod().getBytes().length;
        reqByte.add(lens);


        for (int i = 0; i < rpcRequest.getMethod().length(); i++) {
            reqByte.add((byte)rpcRequest.getMethod().charAt(i));
        }




        byte ArgLens =(byte) rpcRequest.getArgs().length;


        reqByte.add(ArgLens);

        List<byte[]> ArgsList=new ArrayList<>();
        for (int i=0;i<rpcRequest.getArgs().length;i++){
            Object arg = rpcRequest.getArgs()[i];
            byte[] bytes = SerialiZerTools.objectToByte(arg);
            SumLens+=bytes.length;

            ArgsList.add(bytes);

        }

        SumLens+=reqByte.size();
        //第一个为长度
        byteBuf.writeInt(SumLens);

        for (Byte aByte : reqByte) {
            byteBuf.writeByte(aByte);
        }


        for (byte[] bytes : ArgsList) {
            byteBuf.writeInt(bytes.length);
            byteBuf.writeBytes(bytes);
        }




    }
}
