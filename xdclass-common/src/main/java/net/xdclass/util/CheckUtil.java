package net.xdclass.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @projectName: xdclass-1024-shop
 * @package: net.xdclass.util
 * @className: CheckUtil
 * @author: duruijuan
 * @description:
 * @since: 2025-06-06 15:28
 * @version: 1.0
 */
public class CheckUtil {
    /**
     * 邮箱正则
     */
    private static final Pattern MAIL_PATTERN = Pattern.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
    /**
     * ⼿机号正则，暂时未⽤
     */
    private static final Pattern PHONE_PATTERN= Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
    /**
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (null == email || "".equals(email))
        {
            return false;
        }
        Matcher m = MAIL_PATTERN.matcher(email);
        return m.matches();
    }
    /**
     * 暂时未⽤
     * @param phone
     * @return
     */
    public static boolean isPhone(String phone)
    {
        if (null == phone || "".equals(phone))
        {
            return false;
        }
        Matcher m = PHONE_PATTERN.matcher(phone);
        return m.matches();
    }
}
