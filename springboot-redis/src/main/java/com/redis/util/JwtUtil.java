package org.relax.auth.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

/**
 * @ClassName : JWTUtils
 * @Description : jwt验证
 * @Author : 梁文辉
 * @Date: 2021-04-11 13:53
 */
public class JwtUtil {

    //令牌签名
    private static String SIGN = "!QAZ2WSX3EDC";

    /**
     * 生成token <header.payload.sign>三部分组成
     *
     * @param map
     * @return
     */
    public static String getToken(Map<String, String> map) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DAY_OF_MONTH, 180);    //默认过期时间为：180分钟
        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();
        //payload
        map.forEach((k,v) -> {
            builder.withClaim(k,v);
        });
        String token = builder.withExpiresAt(instance.getTime())   //指定令牌过期时间
                .sign(Algorithm.HMAC256(SIGN));    //sign
        return token;
    }

    /**
     * 验证令牌token 合法性
     */
    public static DecodedJWT verify(String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        return verify;
    }

    /**
     * 获得Token中的信息无需secret解密也能获得
     * @param token
     * @param claim
     * @return
     */
    public static String getClaim(String token, String claim) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(claim).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}
