package xyz.panyi.imserver.service;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.stereotype.Service;
import xyz.panyi.imserver.config.Config;
import xyz.panyi.imserver.log.LogUtil;

import javax.annotation.PostConstruct;

@Service
public class IMNettyService {

    private NioEventLoopGroup boss;
    private NioEventLoopGroup worker;

    @PostConstruct
    public void start(){
        new Thread(()->{
            startIMService();
        } , "ImThread").start();
    }

    public void startIMService(){
        LogUtil.log("start im service...");

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        boss = new NioEventLoopGroup(1);
        worker = new NioEventLoopGroup(4);
        serverBootstrap.group(boss , worker);
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel ch) throws Exception {
                ch.pipeline().addLast("logger" , new LoggingHandler());
            }
        });

        ChannelFuture channelFuture = serverBootstrap.bind(Config.IM_SERVICE_PORT);
        try {
            channelFuture.sync();
            LogUtil.log("chanel start bind port : " + Config.IM_SERVICE_PORT);

            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if(boss != null){
                boss.shutdownGracefully();
            }
            if(worker != null){
                worker.shutdownGracefully();
            }
            LogUtil.log("IMService has been closed!");
        }
    }
}
