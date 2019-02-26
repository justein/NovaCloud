package com.nova.sbnetty.handler;

import com.nova.sbnetty.protocol.PingPongProtocol;
import com.nova.sbnetty.utils.ClientChannelMap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * @ClassName: HeartbeatServerHandler
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2019/2/24 下午7:29
 * @version : V1.0
 */
public class HeartbeatServerHandler extends SimpleChannelInboundHandler<PingPongProtocol> {

    private final static Logger LOGGER = LoggerFactory.getLogger(HeartbeatServerHandler.class);

    private static final ByteBuf SERVER_RESPONSE = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer(new PingPongProtocol(155895432L, "pong" ).toString(), CharsetUtil.UTF_8));

    /**通道不再活跃时，将channel从缓存中移除*/
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ClientChannelMap.remove((NioSocketChannel) ctx.channel());
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        if (evt instanceof IdleStateEvent) {

            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            if (idleStateEvent.state() == IdleState.READER_IDLE) {
               LOGGER.info("5秒内没有收到客户端的任何消息");
               ctx.writeAndFlush(SERVER_RESPONSE).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
            }
        }
        /**将事件广播出去*/
        super.userEventTriggered(ctx, evt);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, PingPongProtocol pingPongProtocol) throws Exception {

        LOGGER.info("收到来自客户端{}的消息", pingPongProtocol);

        /**存储客户端相关信息*/
        ClientChannelMap.put(pingPongProtocol.getId(), (NioSocketChannel) channelHandlerContext.channel());

    }

}
