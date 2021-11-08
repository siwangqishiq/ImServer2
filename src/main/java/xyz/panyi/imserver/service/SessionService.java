package xyz.panyi.imserver.service;

import org.springframework.stereotype.Service;

import java.nio.channels.Channel;

/**
 * 管理socketChannel
 */
@Service
public interface SessionService {
    /**
     *
     * @param uid
     * @param channel
     */
    Channel bind(long uid , Channel channel);

    /**
     *
     * @param uid
     */
    void unBind(long uid);

    /**
     *
     * @param uid
     * @return
     */
    Channel findChannelByUid(long uid);
}
