package net.xdclass.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.Model.LoginUser;
import net.xdclass.enums.BizCodeEnum;
import net.xdclass.enums.SendCodeEnum;
import net.xdclass.interceptor.LoginInterceptor;
import net.xdclass.mapper.UserMapper;
import net.xdclass.model.UserDO;
import net.xdclass.model.UserVO;
import net.xdclass.request.UserLoginRequest;
import net.xdclass.request.UserRegisterRequest;
import net.xdclass.service.NotifyService;
import net.xdclass.service.UserService;
import net.xdclass.util.CommonUtil;
import net.xdclass.util.JWTUtil;
import net.xdclass.util.JsonData;

import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @projectName: xdclass-1024-shop
 * @package: net.xdclass.service.impl
 * @className: UserServiceImpl
 * @author: duruijuan
 * @description:
 * @since: 2025-06-08 13:13
 * @version: 1.0
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private NotifyService notifyService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * description:用户注册 邮箱验证码验证 密码加密（TODO） 账号唯⼀性检查(TODO) 插⼊数据库 新注册⽤户福利发放(TODO)
     *
     * @param registerRequest
     * @return JsonData
     * @author: duruijuan
     * @since: 2025-06-08 13:16
     **/
    @Override
    public JsonData register(UserRegisterRequest registerRequest) {
        boolean checkCode = false;
        //校验验证码
        if (StringUtils.isNotBlank(registerRequest.getMail())) {
            checkCode = notifyService.checkCode(SendCodeEnum.USER_REGISTER, registerRequest.getMail(), registerRequest.getCode());

        }
        if (!checkCode) {
            return JsonData.buildResult(BizCodeEnum.CODE_ERROR);

        }
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(registerRequest, userDO);
        userDO.setCreateTime(new Date());
        userDO.setSlogan("人生需要动态规划，学习需要贪心算法");
        //设置密码 TODO
        //userDO.setPwd(registerRequest.getPwd());
        //生成秘钥 盐
        userDO.setSecret("$1$" + CommonUtil.getStringNumRandom(8));
        //密码+盐处理
        String crpyPwd = Md5Crypt.md5Crypt(registerRequest.getPwd().getBytes(), userDO.getSecret());
        userDO.setPwd(crpyPwd);


        //账号唯一性检查 TODO
        if (checkUnique(userDO.getMail())) {
            int rows = userMapper.insert(userDO);
            log.info("rows:{},注册成功:{}", rows, userDO.toString());
            //新用户注册成功，初始化信息，发放福利等 TODO
            userRegisterInitTask(userDO);
            return JsonData.buildSuccess();
        } else {
            return JsonData.buildResult(BizCodeEnum.ACCOUNT_REPEAT);
        }


    }

    /**
     * description: 1.根据Mail去数据库找有没有这条记录 2.有的话就用秘钥+用户传递的明文密码加密，再和数据库的密文进行匹配
     *
     * @param userLoginRequest
     * @return JsonData
     * @author: duruijuan
     * @since: 2025-06-08 15:36
     **/
    @Override
    public JsonData login(UserLoginRequest userLoginRequest) {
        List<UserDO> userDOList = userMapper.selectList(new QueryWrapper<UserDO>().eq("mail", userLoginRequest.getMail()));
        if (userDOList != null && userDOList.size() == 1) {
            //已经注册
            UserDO userDO = userDOList.get(0);
            String cryptPwd = Md5Crypt.md5Crypt(userLoginRequest.getPwd().getBytes(), userDO.getSecret());
            if (cryptPwd.equals(userDO.getPwd())) {
                //登录成功，生成token TODO
                LoginUser loginUser =LoginUser.builder().build();
                BeanUtils.copyProperties(userDO, loginUser);
                String accessToken = JWTUtil.geneJsonWebToken(loginUser);
                //accessToken过期时间
                //UUID生成一个token
                //String  refreshToken=CommonUtil.generateUUID();
                //redisTemplate.opsForValue().set(refreshToken,"1",1000*60*60*24*30);
                return JsonData.buildSuccess(accessToken);


            } else {
                //登陆失败
                return JsonData.buildResult(BizCodeEnum.ACCOUNT_PWD_ERROR);
            }

        } else {
            //未注册
            return JsonData.buildResult(BizCodeEnum.ACCOUNT_UNREGISTER);
        }

    }
    /**
     * description:查询用户详情
     *
     * @param
     * @return void
     * @author: duruijuan
     * @since: 2025-06-11 11:27
     **/
    @Override
    public UserVO findUserDetail() {
        LoginUser loginUser=LoginInterceptor.threadLocal.get();
        UserDO userDO=userMapper.selectOne(new QueryWrapper<UserDO>().eq("id",loginUser.getId()));
        UserVO userVO=new UserVO();
        BeanUtils.copyProperties(userDO,userVO);
        return userVO;



    }

    /**
     * description:校验用户账号唯一
     *
     * @param mail
     * @return boolean
     * @author: duruijuan
     * @since: 2025-06-08 13:36
     **/
    private boolean checkUnique(String mail) {
        QueryWrapper queryWrapper = new QueryWrapper<UserDO>().eq("mail", mail);
        List<UserDO> list = userMapper.selectList(queryWrapper);
        return list.size() > 0 ? false : true;


    }

    /**
     * description:用户注册，初始化福利信息  TODO
     *
     * @param userDO
     * @return void
     * @author: duruijuan
     * @since: 2025-06-08 13:33
     **/
    private void userRegisterInitTask(UserDO userDO) {

    }
}
