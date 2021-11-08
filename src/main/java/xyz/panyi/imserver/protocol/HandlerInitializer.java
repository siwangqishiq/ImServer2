package xyz.panyi.imserver.protocol;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Netty handler初始化方法
 */
public class HandlerInitializer extends ChannelInitializer<NioSocketChannel> {
    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        ch.pipeline().addLast("logger" , new LoggingHandler());
        ch.pipeline().addLast("length_decoder" ,
                new LengthFieldDecoder());
        ch.pipeline().addLast("message" , new MessageCodec());
    }
}
