package com.wjw.graduationproject.util;

import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * @author XWS
 * @ClassName JwtHelper
 * @Description Jwt工具类
 * @create 2018-11-06 12:37
 * @Version 1.0
 */

public class JwtHelper {

  /**
   * 解析JWT
   * @param jonsWebToken
   * @param base64Security
   * @return
   */
  public static Claims parseJWT(String jonsWebToken, String base64Security){
    try {
      Claims claims = Jwts.parser()
              .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))//设置签名秘钥
              .parseClaimsJws(jonsWebToken).getBody();//将加密的jwt信息解密成claims对象
      return claims;
    }catch (ExpiredJwtException e){
      return null;
    }
  }

  /**
   * 创建JWT的token字符串
   * @param name
   * @param userId
   * @param audience
   * @param issuer
   * @param TTLMillis
   * @param base64Security
   * @return
   */
  public static String createJwt(String name, Integer userId, String audience,
                                 String issuer, long TTLMillis, String base64Security){
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;//设置采用的签名算法

    long nowMillis = System.currentTimeMillis();
    Date now = new Date(nowMillis);

    //生成签名密钥
    byte[] apiKeySecreBytes = DatatypeConverter.parseBase64Binary(base64Security);
    Key signingKey = new SecretKeySpec(apiKeySecreBytes,signatureAlgorithm.getJcaName());

    //添加构成JWT的参数
    JwtBuilder builder = Jwts.builder().setHeaderParam("type", "JWT")
            .claim("unique_name", name)
            .claim("userid", userId)
            .setIssuer(issuer)
            .setAudience(audience)
            .signWith(signatureAlgorithm, signingKey);

    //添加Token过期时间
    if (TTLMillis >= 0){
      long expMillis = nowMillis + TTLMillis;
      Date exp = new Date(expMillis);
      builder.setExpiration(exp).setNotBefore(now);

    }

    //生成JWT
    return builder.compact();
  }
}
