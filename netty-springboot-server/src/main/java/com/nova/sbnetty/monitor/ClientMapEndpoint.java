package com.nova.sbnetty.monitor;

import com.nova.sbnetty.utils.ClientChannelMap;
import org.springframework.boot.actuate.endpoint.AbstractEndpoint;

import java.util.Map;

/***
 * @ClassName: ClientMapEndpoint
 * @Description: TODO 使用endpoint完成map的实时获取
 * @Author: Lyn
 * @Date: 2019/2/24 下午8:28
 * @version : V1.0
 */

public class ClientMapEndpoint extends AbstractEndpoint<Map<Long, io.netty.channel.socket.SocketChannel>> {

    public ClientMapEndpoint(String id ) {
        super(id, false);
    }

    @Override
    public Map<Long, io.netty.channel.socket.SocketChannel> invoke() {
        return ClientChannelMap.getMapInstance();
    }
}
