package net.xdclass.service;

import net.xdclass.request.UserRegisterRequest;
import net.xdclass.util.JsonData;
import springfox.documentation.spring.web.json.Json;

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
    JsonData register(UserRegisterRequest registerRequest);
}
