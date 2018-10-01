package xyz.huanxicloud.findjob.common.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import xyz.huanxicloud.findjob.pojo.User;

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
public class JwtTokenUtil implements Serializable {

    /**
     * 密钥
     */
    private static final String secret = "micherhimmeCteqdi0";

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private static String generateToken(Map<String, Object> claims) {
        Date expirationDate = new Date(System.currentTimeMillis() + 2592000L * 1000);
        return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, secret).compact();
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
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
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
    public static String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>(2);
        claims.put("sub", user.getUserId());
        claims.put("created", new Date());
        claims.put("type",user.getType());
        claims.put("status",user.getStatus());
        return generateToken(claims);
    }

    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public static String getUserIdFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public static User getUserFormToken(String token) {
        Claims claims = getClaimsFromToken(token);
        User user = new User();
        try {
            user.setType(claims.get("type").toString());
            user.setStatus(claims.get("status").toString());
        }catch (NullPointerException e){
            //抓到空指针异常,解析失败
            return null;
        }

        return user;
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
    public static Boolean validateToken(String token, User user) {
        String username = getUserIdFromToken(token);
        return (username.equals(user.getUserId()) && !isTokenExpired(token));
    }

}
