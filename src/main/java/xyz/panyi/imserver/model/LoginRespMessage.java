package xyz.panyi.imserver.model;

/**
 * 登录请求
 * 返回
 *
 */
public class LoginRespMessage extends Message<Result> {
    public LoginRespMessage(Result result){
        super();
        setData(result);
    }

    @Override
    public int getType() {
        return MessageTypes.LOGIN_RESP;
    }
}
