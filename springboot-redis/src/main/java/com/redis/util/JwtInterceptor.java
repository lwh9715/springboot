package org.relax.auth.util;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName : JWTInterceptor
 * @Description : JWT拦截器
 * @Author : 梁文辉
 * @Date: 2021-04-11 14:15
 */
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        Map<String, Object> map = new HashMap<>();
        //获取请求头的token令牌
        String token = request.getHeader("token");
        try {
            DecodedJWT verify = JwtUtil.verify(token);    //验证令牌
            map.put("state",true);
            map.put("msg","请求成功!");
            return true;    //验证通过,放行
        }catch (SignatureVerificationException e){
            e.getMessage();
            map.put("msg","无效签名!");
        }catch (TokenExpiredException e){
            e.getMessage();
            map.put("msg","token过期!");
        }catch (AlgorithmMismatchException e){
            e.getMessage();
            map.put("msg","token算法不一致!");
        }catch (Exception e){
            e.getMessage();
            map.put("msg","token无效!");
        }
        map.put("state",false);    //设置状态
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}
