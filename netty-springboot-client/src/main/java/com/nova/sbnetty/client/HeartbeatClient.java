package com.nova.sbnetty.client;

import com.nova.sbnetty.handler.HeartEncoder;
import com.nova.sbnetty.handler.HeartbeatClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/***
 * @ClassName: HeartbeatClient
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2019/2/24 下午8:14
 * @version : V1.0
 */
@Component
public class HeartbeatClient {

    private static final Logger logger = LoggerFactory.getLogger(HeartbeatClient.class);

    private static final int SERVER_PORT = 3000;

    private String serverHost = "127.0.0.1";

    private EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

    private SocketChannel channel;

    @PostConstruct
    public void buildClient() throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                 .channel(NioSocketChannel.class)
                 .handler(new ClientHandlerInitializer());

        ChannelFuture channelFuture = bootstrap.connect(serverHost, SERVER_PORT).sync();
        if (channelFuture.isSuccess()) {
            logger.info("Netty 客户端启动成功");
        }
        channel = (SocketChannel) channelFuture.channel();
    }

    private class ClientHandlerInitializer extends ChannelInitializer<Channel> {

        @Override
        protected void initChannel(Channel channel) throws Exception {
            channel.pipeline()
                    .addLast(new IdleStateHandler(0, 10, 0))
                    .addLast(new HeartEncoder())
                    .addLast(new HeartbeatClientHandler());
        }
    }
}
