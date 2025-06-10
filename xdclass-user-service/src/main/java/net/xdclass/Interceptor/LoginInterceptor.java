package net.xdclass.Interceptor;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.Model.LoginUser;
import net.xdclass.enums.BizCodeEnum;
import net.xdclass.util.CommonUtil;
import net.xdclass.util.JWTUtil;
import net.xdclass.util.JsonData;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @projectName: xdclass-1024-shop
 * @package: net.xdclass.Interceptor
 * @className: LoginInterceptor
 * @author: duruijuan
 * @description:
 * @since: 2025-06-10 15:45
 * @version: 1.0
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor{
    public static ThreadLocal<LoginUser> threadLocal=new ThreadLocal<>();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String accessToken=request.getHeader("token");
        if(accessToken==null){
            accessToken=request.getParameter("token");
        }
        if(StringUtils.isNotBlank(accessToken)) {
            //不为空
            Claims claims = JWTUtil.checkJWT(accessToken);
            if (claims == null) {
                //未登录
                CommonUtil.sendJsonMessage(response, JsonData.buildResult(BizCodeEnum.ACCOUNT_UNLOGIN));
                return false;

            }
            long userId=Long.valueOf(claims.get("id").toString());
            String headImg=(String) claims.get("head_img");
            String mail=(String) claims.get("mail");
            String name=(String) claims.get("name");
            LoginUser loginUser=new LoginUser();
            loginUser.setName(name);
            loginUser.setMail(mail);
            loginUser.setHeadImg(headImg);
            loginUser.setId(userId);
            //通过attribute传递用户登录信息
            //request.setAttribute("loginUser",loginUser);
            //通过threadLocal传递用户登录信息 TODO
            threadLocal.set(loginUser);


            return true;

        }
        CommonUtil.sendJsonMessage(response, JsonData.buildResult(BizCodeEnum.ACCOUNT_UNLOGIN));
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
