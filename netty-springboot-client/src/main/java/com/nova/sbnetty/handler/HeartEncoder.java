package com.nova.sbnetty.handler;

import com.nova.sbnetty.protocol.CustomProtocol;
import com.nova.sbnetty.protocol.PingPongProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 客户端心跳消息编码器
 * @ Author Lyn  2019-02-22 22:35:08
 *
 * */
public class HeartEncoder extends MessageToByteEncoder<PingPongProtocol> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext,
                          PingPongProtocol customProtocol, ByteBuf byteBuf) throws Exception {

        byteBuf.writeLong(customProtocol.getId());
        byteBuf.writeBytes(customProtocol.getContent().getBytes());
    }
}
