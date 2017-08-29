package com.miaolian.cn.dao;

import com.miaolian.cn.core.BaseTkMapper;
import com.miaolian.cn.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by jiangyj on 2017/8/10.
 */
public interface UserDao extends BaseTkMapper<User> {
    @Select("select * from user")
    List<User> getAll();

}
