package xya.panyi.imserver;

import org.junit.Assert;
import org.junit.Test;
import xyz.panyi.imserver.log.LogUtil;
import xyz.panyi.imserver.session.TokenHelper;

public class TokenHelperTest {

    @Test
    public void genToken(){
        LogUtil.log("test token");
        String token = TokenHelper.createToken(123456);
        LogUtil.log("token = " + token);
        LogUtil.log("uid = " + TokenHelper.getUidFromToken(token));
        Assert.assertEquals(123456 , TokenHelper.getUidFromToken(token));
    }
}
