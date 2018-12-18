package com.jiuxian.web.controller;

import com.jiuxian.common.utils.ActionUtils;
import com.jiuxian.web.model.ActionResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @PostMapping(value = "/login")
    public ActionResult ajaxLogin(String account, String password, boolean isRememberMe) {
        UsernamePasswordToken token = new UsernamePasswordToken(account, password);
        token.setRememberMe(isRememberMe);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        return ActionUtils.handleResult();
    }


    @PostMapping(value = "/logout")
    public ActionResult ajaxLogout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ActionUtils.logOut();
    }
}
