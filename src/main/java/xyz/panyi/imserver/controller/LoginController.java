package xyz.panyi.imserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.panyi.imserver.log.LogUtil;
import xyz.panyi.imserver.model.Account;
import xyz.panyi.imserver.model.LoginResp;
import xyz.panyi.imserver.model.Resp;
import xyz.panyi.imserver.service.AccountService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {
    @Autowired
    private HttpServletRequest req;

    @Autowired
    private AccountService mAccountService;

    @GetMapping(value = "login")
    Resp login(@RequestParam(value = "username", required = true) String username,
                                @RequestParam(value = "pwd", required = true) String pwd){
        LogUtil.log( req.getRemoteAddr() + " http login username|pwd : %s | %s" , username , pwd);

        final Account account = mAccountService.checkAccount(username , pwd);
        if(account == null){
            return Resp.genError("username or password error");
        }
        return Resp.genResp(mAccountService.buildLoginResp(account));
    }
}
