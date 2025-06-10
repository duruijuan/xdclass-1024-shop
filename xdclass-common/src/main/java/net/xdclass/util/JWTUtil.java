package net.xdclass.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.Model.LoginUser;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Objects;

/**
 * @projectName: xdclass-1024-shop
 * @package: net.xdclass.util
 * @className: JWTUtil
 * @author: duruijuan
 * @description:
 * @since: 2025-06-08 16:43
 * @version: 1.0
 */
@Slf4j
public class JWTUtil {
    /**
     * description:过期时间，正常7天，方便测试改为70天
     *
     * @author: duruijuan
     * @since: 2025-06-08 16:44
     **/
    private static final long EXPIRE = 60 * 1000 * 60 * 24 * 7 * 10;
    /**
     * description:加密的秘钥
     *
     * @author: duruijuan
     * @since: 2025-06-08 16:46
     **/
    private static final String SECRET = "xdclass.net666";
    /**
     * description:令牌前缀
     *
     * @author: duruijuan
     * @since: 2025-06-08 16:46
     **/
    private static final String TOKEN_PREFIX = "xdclass1024shop";
    /**
     * description:subject
     *
     * @author: duruijuan
     * @since: 2025-06-08 16:48
     **/
    private static final String SUBJECT = "xdclass";

    public static String geneJsonWebToken(LoginUser loginUser) {
        if (loginUser == null) {
            throw new NullPointerException("loginUser对象为空");
        }
        //long userId=loginUser.getId();
        String token = Jwts.builder().setSubject(SUBJECT)
                //payload
                .claim("head_img", loginUser.getHeadImg())

                .claim("id", loginUser.getId())
                .claim("name", loginUser.getName())
                .claim("mail", loginUser.getMail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256, SECRET).compact();
        token = TOKEN_PREFIX + token;


        return token;
    }

    /**
     * description:校验token的方法
     *
     * @param token
     * @return Claims
     * @author: duruijuan
     * @since: 2025-06-08 17:03
     **/
    public static Claims checkJWT(String token) {
        try {
            final Claims claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();
            return claims;
        } catch (Exception e) {
            log.info("jwt token解密失败");
            return null;
        }
    }

}
