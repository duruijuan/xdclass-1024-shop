package net.xdclass.controller;


import com.aliyun.oss.model.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.xdclass.enums.BizCodeEnum;
import net.xdclass.model.UserVO;
import net.xdclass.request.UserLoginRequest;
import net.xdclass.request.UserRegisterRequest;
import net.xdclass.service.FileService;
import net.xdclass.service.UserService;
import net.xdclass.util.CommonUtil;
import net.xdclass.util.JWTUtil;
import net.xdclass.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


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
        JsonData jsonData = userService.register(registerRequest);
        return jsonData;
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public JsonData login(@ApiParam(value = "用户登录对象", required = true) @RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        String ip=CommonUtil.getIpAddr(request);
        JsonData jsonData = userService.login(userLoginRequest);
        return jsonData;
    }
    /**
     * description:用户个人信息查询
     * @param
     * @return JsonData
     * @author: duruijuan
     * @since: 2025-06-11 11:41
     **/
    @GetMapping("/detail")
    @ApiOperation("个人信息查询")
    public JsonData detail(){
        UserVO userVO=userService.findUserDetail();
        return JsonData.buildSuccess(userVO);

    }
//     刷新token方案
//    @PostMapping("/refresh_token")
//    public JsonData getRefreshToken(Map<String, Object> param) {
        //先去redis找refresh_token是否存在
        //refresh_token存在，解密accessToken
        //重新调用 JWTUtil.geneJsonWebToken()，生成accessToken
        //重新生成refresh_token，并存储redis,设置30天过期时间
        //返回给前端
//        return null;
    //}
}

