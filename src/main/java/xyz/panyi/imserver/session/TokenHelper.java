package xyz.panyi.imserver.session;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.netty.util.internal.StringUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.auth0.jwt.algorithms.Algorithm.HMAC256;
import static xyz.panyi.imserver.config.Config.ACCOUNT_SECRECT;

/**
 * token 签发 及解码
 */
public class TokenHelper {
    public static final long ACCOUNT_TOKEN_EXPIRE_TIME = 100 * 24 * 60 * 60 * 1000; // 签发的token超时时间

    private static JWTVerifier verifier;
    static {
        verifier = JWT.require(HMAC256(ACCOUNT_SECRECT)).build();
    }

    private static final String KEY_UID = "_uid";
    private static final String KEY_ACCOUNT = "_account";
    private static final String KEY_PWD = "_pwd";

    public static String createToken(long uid) {
        Map<String, Object> headMap = new HashMap<String, Object>();
        headMap.put(KEY_UID , String.valueOf(uid));

        String token = null;
        token = JWT.create().withHeader(headMap)
                .withExpiresAt(new Date(System.currentTimeMillis() + ACCOUNT_TOKEN_EXPIRE_TIME))
                .sign(HMAC256(ACCOUNT_SECRECT));
        return token;
    }


    public static long getUidFromToken(final String token) {
        if (StringUtil.isNullOrEmpty(token))
            return -1;

        try {
            DecodedJWT decodeJWT = verifier.verify(token);
            final long uid = Long.parseLong(decodeJWT.getHeaderClaim(KEY_UID).asString());
            return uid;
        } catch (Exception e) {
            return -1;
        }
    }
}
