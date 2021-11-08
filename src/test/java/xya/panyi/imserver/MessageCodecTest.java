package xya.panyi.imserver;

import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.junit.Test;
import xyz.panyi.imserver.protocol.LengthFieldDecoder;
import xyz.panyi.imserver.protocol.MessageCodec;
public class MessageCodecTest {

    @Test
    public void testCodec(){
        EmbeddedChannel channel = new EmbeddedChannel(
                new LengthFieldDecoder(),
                new MessageCodec()
                );


    }
}
