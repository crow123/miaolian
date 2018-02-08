package com.miaolian.cn.core;

import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by jiangyj o
 * 通用Mapper
 */
public interface BaseTkMapper<T> extends Mapper<T>,MySqlMapper<T>,IdsMapper<T>,ConditionMapper<T> {
}
