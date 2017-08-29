package com.miaolian.cn.service.impl;

import com.miaolian.cn.dao.UserDao;
import com.miaolian.cn.domain.User;
import com.miaolian.cn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiangyj on 2017/8/11.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getByPage(Integer  age) {
        return userDao.select(new User(age));
    }
}
