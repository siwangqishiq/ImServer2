package xyz.panyi.imserver.service;

import org.springframework.stereotype.Component;

import java.nio.channels.Channel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionServiceImpl implements SessionService{

    private ConcurrentHashMap<Long, Channel> mSessionMap = new ConcurrentHashMap<Long, Channel>();

    @Override
    public Channel bind(long uid, Channel channel) {
        return mSessionMap.put(uid , channel);
    }

    @Override
    public void unBind(long uid) {

    }

    @Override
    public Channel findChannelByUid(long uid) {
        return null;
    }
}
