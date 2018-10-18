package xyz.huanxicloud.findjob.common.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import xyz.huanxicloud.findjob.common.Constant;
import xyz.huanxicloud.findjob.pojo.Vender;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT工具类
 *
 * @author hackyo
 * Created on 2017/12/8 9:20.
 */
@Component
public class JwtVenderUtil implements Serializable {


    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private static String generateToken(Map<String, Object> claims) {
        //失效时间
        Date expirationDate = new Date(System.currentTimeMillis() + Constant.getTokenTime());
        return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, Constant.getVenderJwtSercret()).compact();
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    public static Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(Constant.getVenderJwtSercret()).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 生成令牌
     *
     * @return 令牌
     */
    public static String generateToken(Vender vender) {
        Map<String, Object> claims = new HashMap<>(2);
        claims.put("sub", vender.getVenderId());
        claims.put("created", new Date());
        claims.put("status", vender.getStatus());
        claims.put("name", vender.getName());
        return generateToken(claims);
    }

    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public static String getVenderIdFromToken(String token) {
        String Vendername;
        try {
            Claims claims = getClaimsFromToken(token);
            Vendername = claims.getSubject();
        } catch (Exception e) {
            Vendername = null;
        }
        return Vendername;
    }

    public static Vender getVenderFormToken(String token) {
        Claims claims = getClaimsFromToken(token);
        Vender vender = new Vender();
        try {
            if (claims.get("name") != null)
                vender.setName(claims.get("name").toString());
            vender.setStatus(claims.get("status").toString());
            vender.setVenderId(claims.getSubject());
        } catch (NullPointerException e) {
            //抓到空指针异常,解析失败(Token不合法)
            return null;
        }
        return vender;
    }

    /**
     * 判断令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    public static Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 刷新令牌
     *
     * @param token 原令牌
     * @return 新令牌
     */
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            Claims claims = getClaimsFromToken(token);
            claims.put("created", new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    /**
     * 验证令牌
     *
     * @param token 令牌
     * @return 是否有效
     */
    public static Boolean validateToken(String token, Vender Vender) {
        String Vendername = getVenderIdFromToken(token);
        return (Vendername.equals(Vender.getVenderId()) && !isTokenExpired(token));
    }

}
