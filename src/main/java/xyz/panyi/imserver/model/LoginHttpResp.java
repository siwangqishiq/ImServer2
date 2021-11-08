package xyz.panyi.imserver.model;

/**
 * 登录 返回结果
 */
public class LoginHttpResp {
    private long uid;
    private String token;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
