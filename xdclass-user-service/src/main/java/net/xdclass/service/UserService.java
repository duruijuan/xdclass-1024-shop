package net.xdclass.service;

import net.xdclass.model.UserVO;
import net.xdclass.request.UserLoginRequest;
import net.xdclass.request.UserRegisterRequest;
import net.xdclass.util.JsonData;

/**
 * @projectName: xdclass-1024-shop
 * @package: net.xdclass.service
 * @className: UserService
 * @author: duruijuan
 * @description:
 * @since: 2025-06-08 13:12
 * @version: 1.0
 */
public interface UserService {
    /**
     * description:用户注册
     * @param registerRequest
     * @return JsonData
     * @author: duruijuan
     * @since: 2025-06-08 15:35
     **/
    JsonData register(UserRegisterRequest registerRequest);
    /**
     * description:用户登录
     * @param userLoginRequest
     * @return JsonData
     * @author: duruijuan
     * @since: 2025-06-08 15:36
     **/

    JsonData login(UserLoginRequest userLoginRequest);
    /**
     * description:查询用户详情
     *
     * @param
     * @return void
     * @author: duruijuan
     * @since: 2025-06-11 11:27
     **/

    UserVO findUserDetail();
}
