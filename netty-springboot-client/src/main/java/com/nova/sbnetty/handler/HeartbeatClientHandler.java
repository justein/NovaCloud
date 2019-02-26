package com.nova.sbnetty.handler;

import com.nova.sbnetty.protocol.CustomProtocol;
import com.nova.sbnetty.protocol.PingPongProtocol;
import com.nova.sbnetty.utils.SpringFactoryUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;


/**
 *
 * */
public class HeartbeatClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private final static Logger LOGGER = LoggerFactory.getLogger(HeartbeatClientHandler.class);

    /**
     * 事件处理
     * */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        if (evt instanceof IdleStateEvent) {

            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;

            if (idleStateEvent.state() == IdleState.WRITER_IDLE) {
                LOGGER.info("已经有10秒钟没有发送消息");
                CustomProtocol customProtocol = SpringFactoryUtil.getBean("clientHeartbeat", PingPongProtocol.class);
                ctx.writeAndFlush(customProtocol).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
            }
        }
        /**同时将事件抛出去*/
        super.userEventTriggered(ctx, evt);
    }

    /**
     * 解析管道报文
     * */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        LOGGER.debug("接收到来自服务端的回送消息", byteBuf.toString(CharsetUtil.UTF_8));
    }
}
