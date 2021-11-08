package xyz.panyi.imserver.service;

import org.springframework.stereotype.Service;
import xyz.panyi.imserver.model.Account;
import xyz.panyi.imserver.model.LoginResp;
import xyz.panyi.imserver.model.Resp;

@Service
public interface AccountService {

    /**
     * 账户校验  若密码用户名正确 返回Account 失败返回 null
     * @param username
     * @param password
     * @return
     */
    Account checkAccount(String username , String password);

    /**
     * 从accout 签发token
     *
     * @param account
     * @return
     */
    LoginResp buildLoginResp(Account account);
}
