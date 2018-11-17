package com.jiuxian.base.user.controller;

import com.jiuxian.base.user.entity.User;
import com.jiuxian.base.user.service.UserService;
import com.jiuxian.core.web.action.ActionUtils;
import com.jiuxian.core.web.model.ActionResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping(value = "/add")
    public ActionResult register(User bean) {
        Subject subject = SecurityUtils.getSubject();
        subject.isRemembered();
        subject.isAuthenticated();
        service.doSave(bean);
        return ActionUtils.handleResult();
    }

    @GetMapping(value = "/list")
    public ActionResult list() {
        List<User> all = service.findAll();
        return ActionUtils.handleResult(all);
    }
}
