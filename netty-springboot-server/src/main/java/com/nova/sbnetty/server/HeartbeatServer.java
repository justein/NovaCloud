package com.nova.sbnetty.server;

import com.nova.sbnetty.handler.HeartbeatDecoder;
import com.nova.sbnetty.handler.HeartbeatServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

/***
 * @ClassName: HeartbeatServer
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2019/2/24 下午8:01
 * @version : V1.0
 */
@Component
public class HeartbeatServer {

    private static final Logger logger = LoggerFactory.getLogger(HeartbeatServer.class);

    /**创建IO处理线程和工作线程*/
    private EventLoopGroup boss = new NioEventLoopGroup();
    private EventLoopGroup worker = new NioEventLoopGroup();

    private int serverPort = 3000;

    @PostConstruct
    public void startServer() throws InterruptedException {
        System.out.println("正在初始化Netty服务端。。。。。。。。。。。");
        ServerBootstrap bootstrap = new ServerBootstrap()
                .group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(serverPort))
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new HeartbeatInitializer());

        ChannelFuture channelFuture = bootstrap.bind(serverPort).sync();

        if (channelFuture.isSuccess()) {
            logger.info("Netty服务端启动成功");
        }
    }

    @PreDestroy
    public void destroy() {
        /**工作线程优雅退出*/
        boss.shutdownGracefully();
        worker.shutdownGracefully();
        logger.info("Netty服务端正常关闭");
    }

    private class HeartbeatInitializer extends ChannelInitializer<Channel> {

        @Override
        protected void initChannel(Channel channel) throws Exception {
            channel.pipeline()
                    .addLast(new IdleStateHandler(5, 0, 0))
                    .addLast(new HeartbeatDecoder())
                    .addLast(new HeartbeatServerHandler());

        }
    }

}
