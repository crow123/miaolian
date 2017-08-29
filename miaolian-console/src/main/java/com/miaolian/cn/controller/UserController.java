package com.miaolian.cn.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.miaolian.cn.core.RedisUtil;
import com.miaolian.cn.domain.User;
import com.miaolian.cn.dao.UserDao;
import com.miaolian.cn.service.UserService;
import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangyj on 2017/8/10.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = Logger.getLogger(UserController.class);


    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public List<User> getAll(){
            List<Integer> list = new ArrayList<>();
            list.add(1);
            list.add(2);
            RedisUtil.set("list",list,0);
            System.out.println(RedisUtil.getObj("list"));


        return userDao.getAll();
    }

    @RequestMapping(value = "/getList",method = RequestMethod.GET)
    public PageInfo<User> getList(){

        PageHelper.startPage(1,10);
        List<User> list = userService.getByPage(23);
        PageInfo<User> page = new PageInfo<>(list);

        List<User> list1 = userDao.selectByRowBounds(new User(23),new RowBounds(1,2));
        PageInfo<User> page1 = new PageInfo<>(list1);
        return page;
    }
}
