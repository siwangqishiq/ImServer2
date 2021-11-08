package xyz.panyi.imserver.service;

import org.springframework.stereotype.Component;
import xyz.panyi.imserver.model.Account;
import xyz.panyi.imserver.model.LoginHttpResp;
import xyz.panyi.imserver.session.TokenHelper;

import java.util.HashMap;
import java.util.Map;

@Component
public class AccountServiceImpl implements AccountService{
    private static final Map<String , Account> mData = new HashMap<String,Account>();

    static {
        for(int i = 1;i<10;i++){
            Account account = new Account(i , "siwangqishiq" + i , "111111");
            mData.put(account.getUsername() , account);
        }
    }

    @Override
    public Account checkAccount(String username, String password) {
        Account account = mData.get(username);
        if(account == null)
            return null;

        if(account.getPwd().equals(password)){
            return account.genInstance();
        }

        return null;
    }

    @Override
    public LoginHttpResp buildLoginResp(Account account) {
        final LoginHttpResp result = new LoginHttpResp();
        result.setUid(account.getUid());
        result.setToken(TokenHelper.createToken(account.getUid()));
        return result;
    }
}
