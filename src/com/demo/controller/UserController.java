package com.demo.controller;

import com.demo.common.model.Token;
import com.demo.common.model.User;
import com.demo.common.model.VerificationCode;
import com.demo.service.UserService;
import com.demo.utils.DateUtil;
import com.demo.utils.StringUtils;
import com.demo.utils.UUIDGenerator;
import com.demo.utils.VerifyCodeGenerator;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UserController extends Controller {

    @ActionKey("/getVerifyCode")
    public void getVerifyCode() {
        String mobile = getPara("mobile");
        Map<String,Object> map = UserService.getVerifyCode(mobile);
        setAttrs(map);
        renderJson();
    }

    @ActionKey("/register")
    public void register() {
        String mobile = getPara("mobile");
        String password = getPara("password");
        String code = getPara("code");
        Map<String,Object> map = UserService.register(mobile,password,code);
        setAttrs(map);
        renderJson();
    }

    @ActionKey("/login")
    public void login(){
        String mobile = getPara("mobile");
        String password = getPara("password");
        String token = getPara("token");
        Map<String,Object> map = UserService.login(mobile,password,token);
        setAttrs(map);
        renderJson();
    }

}
