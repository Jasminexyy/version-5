package cm.utils;

import cm.entity.LoginUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/31
 */
@Component
public class JwtTokenUtil implements Serializable {

    /**
     * 密钥
     */
    public static final String secret = "Divergence";
    public static final int TOKEN_TIME = 21600;

    /**
     * 生成令牌
     *
     * @param loginUser 数据声明
     * @return 令牌
     */
    public static String generateToken(LoginUser loginUser) {
        Date loginDate = new Date();
        Calendar expiresTime = Calendar.getInstance();
        expiresTime.add(Calendar.SECOND, TOKEN_TIME);
        Date expiresDate = expiresTime.getTime();
        Map<String, Object> map = new HashMap<>(2);
        map.put("alg", "HS512");
        map.put("typ", "JWT");
        String token = JWT.create().withHeader(map)
                .withClaim("userId", Long.toString(loginUser.getUserId()))
                .withClaim("userRole", loginUser.getUserRole())
                .withExpiresAt(expiresDate)
                .sign(Algorithm.HMAC512(secret));
        return token;
    }

    /**
     * 根据Token获取userId
     *
     * @param token
     * @return user_id
     */
    public static Long getUserId(String token) {
        Map<String, Claim> claims = JWT
                .require(Algorithm.HMAC256(secret))
                .build()
                .verify(token)
                .getClaims();
        Claim userIdClaim = claims.get("userId");
        if (null == userIdClaim || StringUtils.isEmpty(userIdClaim.asString())) {
            return null;
        }

        return Long.valueOf(userIdClaim.asString());
    }

    /**
     * 根据Token获取userRole
     *
     * @param token
     * @return java.lang.String
     */
    public static String getUserRole(String token) {
        Map<String, Claim> claims = JWT
                .require(Algorithm.HMAC256(secret))
                .build()
                .verify(token)
                .getClaims();
        Claim userRoleClaim = claims.get("userRole");
        if (null == userRoleClaim || StringUtils.isEmpty(userRoleClaim.asString())) {
            return null;
        }

        return userRoleClaim.asString();
    }
}