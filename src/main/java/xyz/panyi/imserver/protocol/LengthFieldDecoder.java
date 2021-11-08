package xyz.panyi.imserver.protocol;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class LengthFieldDecoder extends LengthFieldBasedFrameDecoder {
    public LengthFieldDecoder() {
        super(10 * 1024 * 1024 , 8 , 8);
    }
}
