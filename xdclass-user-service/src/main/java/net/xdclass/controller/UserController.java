package net.xdclass.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.xdclass.enums.BizCodeEnum;
import net.xdclass.request.UserRegisterRequest;
import net.xdclass.service.FileService;
import net.xdclass.service.UserService;
import net.xdclass.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author duruijuan
 * @since 2025-06-03
 */
@Api(tags = "用户模块")
@RestController
@RequestMapping("/api/user/v1")
public class UserController {
    @Autowired
    private FileService fileService;
    @Autowired
    private UserService userService;
    /**
     * 上传⽤户头像
     * <p>
     * 默认⽂件⼤⼩ 1M,超过会报错 description:
     *
     * @param file
     * @return JsonData
     * @author: duruijuan
     * @since: 2025-06-07 18:24
     */

    @ApiOperation("⽤户头像上传")
    @PostMapping(value = "/upload")
    public JsonData
    uploadHeaderImg(@ApiParam(value = "⽂件上传", required = true) @RequestPart("file") MultipartFile file) {
        String result = fileService.uploadUserImg(file);
        return result != null ?
                JsonData.buildSuccess(result) : JsonData.buildResult(BizCodeEnum.FILE_UPLOAD_USER_IMG_FAIL);
    }

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public JsonData register(@ApiParam(value = "用户注册对象", required = true) @RequestBody UserRegisterRequest registerRequest) {
        JsonData jsonData=userService.register(registerRequest);
        return jsonData;
    }
}

