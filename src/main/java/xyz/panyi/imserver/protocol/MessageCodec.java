package xyz.panyi.imserver.protocol;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import xyz.panyi.imserver.config.Config;
import xyz.panyi.imserver.log.LogUtil;
import xyz.panyi.imserver.model.LoginHttpResp;
import xyz.panyi.imserver.model.LoginReqMessage;
import xyz.panyi.imserver.model.Message;
import xyz.panyi.imserver.model.MessageTypes;

import java.nio.charset.Charset;
import java.util.List;

/**
 * 消息编解码器
 *
 */
public class MessageCodec extends ByteToMessageCodec<Message> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int magicNumber = in.readInt();
        LogUtil.log("magicNumber : {}" , magicNumber);
        if(magicNumber != Message.MAGIC_NUMBER){
            LogUtil.log("magic number error close channel");
            ctx.channel().closeFuture();
            return;
        }

        int version = in.readInt();
        if(version == Config.PROTOCOL_VERSION){
            long length = in.readLong();
            int messageType = in.readInt();
            int bodyEncode = in.readInt();
            long uniqueId = in.readLong();
            long bodyLength = length - 32;//体长度  等于总长度 -头(32)字节数

            final Message head = new Message();
            head.setMagicNumber(magicNumber);
            head.setVersion(version);
            head.setLength(length);
            head.setType(messageType);
            head.setBodyEncode(bodyEncode);
            head.setUniqueId(uniqueId);
            head.setBodyLength(bodyLength);
            final byte body[] = new byte[(int)bodyLength];
            in.readBytes(body);

            Message result = null;
            switch (result.getType()){
                case MessageTypes.LOGIN_REQ://登录请求
                    LoginHttpResp data = JSON.parseObject(new String(body , "UTF-8") , LoginHttpResp.class);
                    result = new LoginReqMessage(head , data);
                    break;
                default:
                    break;
            }//end switch

            if(result != null){
                out.add(result);
            }

            in.release();
        }else{
            LogUtil.log("version not support");
            ctx.channel().closeFuture();
        }
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        out.writeInt(Message.MAGIC_NUMBER);
        out.writeInt(Config.PROTOCOL_VERSION);
        if(msg.getBodyEncode() == Message.BODY_ENCODE_JSON){//json encode body
            String dataString = JSON.toJSON(msg.getData()).toString();
            byte[] body = dataString.getBytes(Charset.forName("UTF-8"));
            long length = body.length + 32;

            out.writeLong(length);
            out.writeInt(msg.getType());
            out.writeInt(msg.getBodyEncode());
            out.writeLong(msg.getUniqueId());
            out.writeBytes(body);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        LogUtil.log("Exception occur {}" , cause);
        ctx.channel().closeFuture();
    }


}
