package com.nova.sbnetty.handler;

import com.nova.sbnetty.protocol.PingPongProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 服务端编码器
 * @Author Lyn  2019-02-22 22:36:31
 *
 * */

public class HeartbeatDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

        long id = byteBuf.readLong();
        /**特别注意在定义接收数组的时候，尽量使用实际报文长度，不要写死，并发场景下容易积压消息*/
        byte[] bytes = new byte[byteBuf.readableBytes()];
        /**从缓冲区读入本地*/
        byteBuf.readBytes(bytes);
        /**还原报文内容*/
        String msgContent = new String(bytes);

        /**构造出心跳消息*/
        PingPongProtocol pingPongProtocol = new PingPongProtocol(id, msgContent);
//        pingPongProtocol.setId(id);
//        pingPongProtocol.setContent(msgContent);

        list.add(pingPongProtocol);
    }
}
