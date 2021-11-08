package xyz.panyi.imserver.model;

import xyz.panyi.imserver.config.Config;

import java.nio.ByteBuffer;
import java.util.UUID;

import static xyz.panyi.imserver.model.MessageTypes.UNDEF;

public class Message<T> {
    public static final int MAGIC_NUMBER;

    private static final String MAGIC_NUMBER_STR = "yoki";

    public static final int BODY_ENCODE_JSON = 1;//json
    public static final int BODY_ENCODE_BINARY = 2;//二进制方式

    static {
        ByteBuffer buf = ByteBuffer.allocate(4);
        buf.put(MAGIC_NUMBER_STR.getBytes());
        buf.flip();
        MAGIC_NUMBER = buf.asIntBuffer().get();
    }

    protected int magicNumber;
    protected int version;
    protected long length;
    protected int type;
    protected int bodyEncode;
    protected long uniqueId;
    protected long bodyLength;
    protected T data;

    public Message(){
        magicNumber = MAGIC_NUMBER;
        version = Config.PROTOCOL_VERSION;
        bodyEncode = BODY_ENCODE_JSON;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getBodyLength() {
        return bodyLength;
    }

    public void setBodyLength(long bodyLength) {
        this.bodyLength = bodyLength;
    }

    public int getType(){
        return UNDEF;
    }

    public int getMagicNumber() {
        return magicNumber;
    }

    public void setMagicNumber(int magicNumber) {
        this.magicNumber = magicNumber;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getBodyEncode() {
        return bodyEncode;
    }

    public void setBodyEncode(int bodyEncode) {
        this.bodyEncode = bodyEncode;
    }

    public long getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(long uniqueId) {
        this.uniqueId = uniqueId;
    }

    public void writeHead(final Message head){
        if(head == null)
            return;

        this.magicNumber = head.magicNumber;
        this.version = head.version;
        this.length = head.length;
        this.bodyEncode = head.bodyEncode;
        this.uniqueId = head.uniqueId;
    }

    public static long genUniqueId(){
        return UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }
}
