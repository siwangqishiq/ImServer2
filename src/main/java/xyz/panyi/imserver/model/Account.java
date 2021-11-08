package xyz.panyi.imserver.model;

/**
 * 用户账户
 */
public class Account {
    private long uid;
    private String username;
    private String pwd;

    public Account() {
    }

    public Account(long uid, String username, String pwd) {
        this.uid = uid;
        this.username = username;
        this.pwd = pwd;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Account genInstance(){
        Account account = new Account();
        account.setUid(uid);
        return account;
    }
}
