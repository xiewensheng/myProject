package com.wjw.graduationproject.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author XWS
 * @ClassName Audience
 * @Description JWT实体类
 * @create 2018-11-06 11:19
 * @Version 1.0
 */
@Component("Audience")
//@ConfigurationProperties(prefix = "audience")
public class Audience {
  private static String clientId;
  private static String base64Secret;
  private static String name;
  private static int expiresSecond;

  public static String getClientId() {
    return clientId;
  }

  @Value("${audience.clientId}")
  public void setClientId(String clientId) {
    Audience.clientId = clientId;
  }

  public static String getBase64Secret() {
    return base64Secret;
  }

  @Value("${audience.base64Secret}")
  public  void setBase64Secret(String base64Secret) {
    Audience.base64Secret = base64Secret;
  }

  public static String getName() {
    return name;
  }

  @Value("${audience.name}")
  public void setName(String name) {
    Audience.name = name;
  }

  public static int getExpiresSecond() {
    return expiresSecond;
  }

  @Value("${audience.expiresSecond}")
  public void setExpiresSecond(int expiresSecond) {
    Audience.expiresSecond = expiresSecond;
  }
}
