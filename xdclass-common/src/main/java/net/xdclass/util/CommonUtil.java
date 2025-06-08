package net.xdclass.util;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.util.Random;
import java.util.UUID;

/**
 * @projectName: xdclass-1024-shop
 * @package: net.xdclass.util
 * @className: CommonUtil
 * @author: duruijuan
 * @description:
 * @since: 2025-06-05 19:30
 * @version: 1.0
 */

public class CommonUtil {
    /**
     * 获取ip
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null ||
                    ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress =
                        request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null ||
                    ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress =
                        request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null ||
                    ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress =
                        request.getRemoteAddr();
                if
                (ipAddress.equals("127.0.0.1")) {
                    // 根据⽹卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet =
                                InetAddress.getLocalHost();
                    } catch
                    (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress =
                            inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第⼀个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null &&
                    ipAddress.length() > 15) {
                // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress =
                            ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        return ipAddress;
    }

    /**
     * description:MD5加密
     *
     * @param data
     * @return {@link String}
     * @author: duruijuan
     * @since: 2025-06-05 19:30
     **/
    public static String MD5(String data) {
        try {
            java.security.MessageDigest md =
                    MessageDigest.getInstance("MD5");
            byte[] array =
                    md.digest(data.getBytes("UTF-8"));
            StringBuilder sb = new
                    StringBuilder();
            for (byte item : array) {

                sb.append(Integer.toHexString((item & 0xFF) |
                        0x100).substring(1, 3));
            }
            return sb.toString().toUpperCase();
        } catch (Exception exception) {
        }
        return null;
    }

    /**
     * description:获取验证码随机数
     *
     * @param length
     * @return String
     * @author: duruijuan
     * @since: 2025-06-06 15:40
     **/
    public static String getRandomCode(int length) {
        String sources = "0123456789";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(sources.charAt(random.nextInt(9)));
        }
        return stringBuilder.toString();
    }

    /**
     * description:获取当前时间戳
     *
     * @param
     * @return long
     * @author: duruijuan
     * @since: 2025-06-06 16:51
     **/
    public static long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * description:生成uuid
     *
     * @param
     * @return String
     * @author: duruijuan
     * @since: 2025-06-07 17:25
     **/
    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("_", "").substring(0, 32);

    }

    /**
     * description:获取随机长度的串
     *
     * @param length
     * @return String
     * @author: duruijuan
     * @since: 2025-06-08 14:25
     **/
    private static final String ALL_CHAR_NUM = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    //加盐，每个⼈的⼀般都不⼀样
    public static String getStringNumRandom(int length) {
        //⽣成随机数字和字⺟,
        Random random = new Random();
        StringBuilder saltString = new StringBuilder(length);
        for (int i = 1; i <= length; ++i) {
            saltString.append(ALL_CHAR_NUM.charAt(random.nextInt(ALL_CHAR_NUM.length())));
        }
        return saltString.toString();
    }
}
