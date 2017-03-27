package com.demo.service;

import com.demo.common.model.Token;
import com.demo.common.model.User;
import com.demo.common.model.VerificationCode;
import com.demo.utils.DateUtil;
import com.demo.utils.StringUtils;
import com.demo.utils.UUIDGenerator;
import com.demo.utils.VerifyCodeGenerator;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class UserService {

    /**
     * 获取短信验证码
     * @param mobile
     * @return
     */
    public static Map<String, Object> getVerifyCode(String mobile) {
        Map<String,Object> map = new HashMap<>();

        if (StringUtils.isEmpty(mobile)) {
            map.put("code", 1);
            map.put("msg", "手机号不能为空");
            map.put("data", "");
        } else {
            User user = User.dao.findUser(mobile);
            if (user != null) {
                map.put("code", 1);
                map.put("msg", "手机号已经存在");
                map.put("data", "");
            } else {
                String code = VerifyCodeGenerator.getVerifyCode();
                //发送验证码
                //发送成功后
                //插入数据库
                boolean send = VerificationCode.dao.saveVerifyCode(mobile,code);
                if (send) {
                    map.put("code", 0);
                    map.put("msg", "OK");
                    map.put("data", code);
                } else {
                    map.put("code", 1);
                    map.put("msg", "验证码获取失败");
                    map.put("data", "");
                }
            }
        }

        return map;
    }

    /**
     * 用户注册
     * @param mobile
     * @param password
     * @param code
     * @return
     */
    public static Map<String,Object> register(String mobile,String password,String code) {
        Map<String,Object> map = new HashMap<>();
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password) || StringUtils.isEmpty(code)) {
            map.put("code", 1);
            map.put("msg", "手机号密码或验证码不能为空");
            map.put("data", "");
        } else {
            VerificationCode verificationCode = VerificationCode.dao.verifyCode(mobile,code);
            if (verificationCode == null) {
                map.put("code", 1);
                map.put("msg", "验证码错误,注册失败");
                map.put("data", "");
            } else {
                long time = new Date().getTime() - verificationCode.getCreateTime().getTime();
                if (time < DateUtil.MINUTE_TIME*5) {
                    User user = User.dao.saveUser(mobile, password);
                    if (user == null) {
                        map.put("code", 1);
                        map.put("msg", "服务器异常,注册失败");
                        map.put("data", "");
                    } else {
                        map.put("code", 0);
                        map.put("msg", "注册成功");
                        map.put("data", user);
                    }
                }
            }
        }
        return map;
    }

    /**
     * 用户登录
     * @param mobile
     * @param password
     * @param token
     * @return
     */

    public static Map<String,Object> login(String mobile,String password,String token){
        Map<String,Object> map = new HashMap<>();
        if (StringUtils.isEmpty(token)) {
            if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
                map.put("code", 1);
                map.put("msg", "用户名或密码不能为空");
            } else {
                User user = User.dao.findUserByMobile(mobile, password);
                String tokenStr = UUIDGenerator.getUUID16();
                Token t = Token.dao.saveToken(user.getId(), tokenStr);
                Map<String, Object> data = new HashMap<>();
                data.put("token", t.getToken());
                data.put("obj", user);
                map.put("code", 0);
                map.put("msg", "OK");
                map.put("data", data);
            }
        } else {
            Token token1 = Token.dao.findToken(token);
            if (token1 == null) {
                map.put("code",1);
                map.put("msg","Token不存在，请重新登录");
            } else {
                long time = DateUtil.getNow().getTime() - token1.getUpdateTime().getTime();
                if (time < DateUtil.MONTH_TIME) {
                    User user = User.dao.findUserById(token1.getUid());
                    Token.dao.updateToken(token1.getToken());
                    Map<String, Object> data = new HashMap<>();
                    data.put("token", token1.getToken());
                    data.put("obj", user);
                    map.put("code", 0);
                    map.put("msg", "OK");
                    map.put("data", data);
                } else {
                    map.put("code", 1);
                    map.put("msg", "Token已过期，请重新登录");
                }

            }

        }
        return map;
    }


}
