package com.nova.sbnetty.utils;

import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/***
 * @ClassName: ClientChannelMap
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2019/2/24 下午7:32
 * @version : V1.0
 */

public class ClientChannelMap {

    private static final Map<Long, NioSocketChannel> clientChannelMap = new ConcurrentHashMap<>();

    public static void put(Long id, NioSocketChannel channel) {
        clientChannelMap.put(id, channel);
    }
    /**将SocketChannel改为NioSocketChannel*/
    public static NioSocketChannel get(Long id) {
        return clientChannelMap.get(id);
    }

    public static Map getMapInstance() {
        return clientChannelMap;
    }

    public static void remove(NioSocketChannel nioSocketChannel) {
        clientChannelMap.entrySet().stream()
                .filter(entry -> entry.getValue() == nioSocketChannel)
                .forEach(entry ->clientChannelMap.remove(entry.getKey()));
    }
}
