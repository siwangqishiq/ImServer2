package xyz.panyi.imserver.protocol.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.stereotype.Component;
import xyz.panyi.imserver.model.LoginHttpResp;
import xyz.panyi.imserver.model.LoginReqMessage;

@Component
@ChannelHandler.Sharable
public class LoginHandler extends SimpleChannelInboundHandler<LoginReqMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginReqMessage msg) throws Exception {
        if(msg.getData() == null){
            return;
        }

        LoginHttpResp data = msg.getData();
    }
}
