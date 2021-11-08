package xyz.panyi.imserver.model;

/**
 * 登录请求
 *
 */
public class LoginReqMessage extends Message<LoginHttpResp> {

    public LoginReqMessage(Message head , LoginHttpResp data){
        writeHead(head);
        this.data = data;
    }

    public LoginReqMessage(){
    }

    @Override
    public int getType() {
        return MessageTypes.LOGIN_REQ;
    }
}
