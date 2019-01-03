package com.jiuxian.web.action;

import com.jiuxian.base.user.entity.User;
import com.jiuxian.base.user.service.UserService;
import com.jiuxian.web.model.ActionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MainController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/main")
    public ActionResult main(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", request.getSession().getId());
        return ActionUtils.handleResult(map);
    }

    @RequestMapping(value = "/unAuth")
    public ActionResult unAuth() {
        return ActionUtils.unAuthorize();
    }

    @PostMapping(value = "/register")
    public ActionResult register(User user) {
        userService.doSave(user);
        return ActionUtils.handleResult();
    }
}
