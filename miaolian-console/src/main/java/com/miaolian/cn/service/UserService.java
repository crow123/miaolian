package com.miaolian.cn.service;

import com.miaolian.cn.domain.User;

import java.util.List;

/**
 * Created by jiangyj on 2017/8/10.
 */
public interface UserService {

    List<User> getByPage(Integer age);
}
