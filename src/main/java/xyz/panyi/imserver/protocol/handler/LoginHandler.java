package xyz.panyi.imserver.protocol.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import xyz.panyi.imserver.model.LoginHttpResp;
import xyz.panyi.imserver.model.LoginReqMessage;
import xyz.panyi.imserver.model.LoginRespMessage;
import xyz.panyi.imserver.model.Result;

@Scope("prototype")
@ChannelHandler.Sharable
public class LoginHandler extends SimpleChannelInboundHandler<LoginReqMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginReqMessage msg) throws Exception {
        final LoginHttpResp req = msg.getData();

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);

        final Result result = new Result();
        result.setResult(false);
        result.setReason(cause.getMessage());
        ctx.writeAndFlush(new LoginRespMessage(result));
    }
}
