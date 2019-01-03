package com.jiuxian.User;

import com.jiuxian.JiuxianBaseApplicationTests;
import com.jiuxian.base.user.dao.UserDao;
import com.jiuxian.base.user.entity.User;
import com.jiuxian.base.user.service.UserService;
import com.jiuxian.common.utils.JSONUtil;
import com.jiuxian.dao.Operator;
import com.jiuxian.dao.QueryCondition;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;

import java.util.List;

public class UserTest extends JiuxianBaseApplicationTests {

    @Autowired
    private UserService service;
    @Autowired
    private UserDao dao;

    @Test
    public void testQuery2() {
        User u = new User();
        u.setAccount("a");
        ExampleMatcher example = ExampleMatcher.matching().withMatcher("account", matcher -> matcher.contains());
        Example<User> ex = Example.of(u, example);
        User one = dao.findOne(ex);
        System.out.println(one);
    }

    @Test
    public void testQuery3() {
        QueryCondition query = new QueryCondition();
        List<QueryCondition> queryConditions = query.getQueryConditions();
        queryConditions.add(new QueryCondition("account", Operator.LIKE, "a"));
        queryConditions.add(new QueryCondition("id", Operator.LTE, 3));
        query.getGroupByFields().add("id");
        List<User> all = dao.findAll(query);
    }

    @Test
    public void testQuery4() {
        QueryCondition query = new QueryCondition();
        List<QueryCondition> queryConditions = query.getQueryConditions();
        //queryConditions.add(new QueryCondition("account", Operator.LIKE, "a"));
        //queryConditions.add(new QueryCondition("id", Operator.LTE, 3));
        //query.getGroupByFields().add("id");
        Pageable pageable = new PageRequest(1 - 1, 10);
        Page<User> all = dao.findAll(query, pageable);
        System.out.println(all.toString());
        System.out.println(JSONUtil.toJSON(all));
    }

    @Test
    public void testDelete() {
        User one = dao.findOne(18);
        one.setName("aaaaaaa");
        System.out.println(one);
        User one1 = dao.getOne(17L);
        one1.setName("bbbbbb");
        System.out.println(one1);

        User u = new User();
        u.setAccount("aa");
        User save = dao.save(u);
        System.out.println(save);
        save.setName("aaaaa");
        u = new User();
        u.setAccount("bb");
        User save1 = dao.saveAndFlush(u);
        System.out.println(save1);
        save1.setName("bbbbb");
        System.out.println(save1);
    }
}