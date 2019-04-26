package com.jiuxian.controller;

import com.jiuxian.entity.User;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-26 15:11:00
 * Comment:
 */
@Api(tags = "用户相关API", value = "用户相关API")
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private static Map<Integer, User> users = Collections.synchronizedMap(new HashMap<>());

    @PostConstruct
    public void init() {
        User user = new User();
        user.setId(1);
        user.setName("ZhangSan");

        users.put(1, user);
        user = new User();
        user.setId(2);
        user.setName("LiSi");
        users.put(2, user);
    }

    @ApiOperation(value = "查询所有用户信息")
    @GetMapping
    public Collection<User> list() {
        return users.values();
    }

    @ApiOperation(value = "根据用户id查询用户信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path")
    @GetMapping(value = "/{id}")
    public User get(@PathVariable Integer id) {
        return users.get(id);
    }

    @ApiOperation(value = "根据用户id修改用户信息", notes = "id 放到url 后面")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
    @PutMapping(value = "/{id}")
    public User update(@PathVariable Integer id, @RequestBody User user) {
        users.put(id, user);
        return user;
    }

    @ApiOperation(value = "保存用户信息")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @PostMapping
    public User save(@RequestBody User user) {
        users.put(user.getId(), user);
        return user;
    }

    @ApiOperation(value = "删除用户信息")
    @ApiImplicitParam(name = "id", value = "用户详细实体user", required = true, dataType = "Integer", paramType = "path")
    @DeleteMapping(value = "/{id}")
    public User delete(@PathVariable Integer id) {
        return users.remove(id);
    }
}
