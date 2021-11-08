package xyz.panyi.imserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.panyi.imserver.log.LogUtil;
import xyz.panyi.imserver.model.Account;
import xyz.panyi.imserver.model.HttpResp;
import xyz.panyi.imserver.service.AccountService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {
    @Autowired
    private HttpServletRequest req;

    @Autowired
    private AccountService mAccountService;

    @GetMapping(value = "login")
    HttpResp login(@RequestParam(value = "username", required = true) String username,
                   @RequestParam(value = "pwd", required = true) String pwd){
        LogUtil.log( req.getRemoteAddr() + " http login username|pwd : {} | {} " , username , pwd);

        final Account account = mAccountService.checkAccount(username , pwd);
        if(account == null){
            return HttpResp.genError("username or password error");
        }
        return HttpResp.genResp(mAccountService.buildLoginResp(account));
    }
}
