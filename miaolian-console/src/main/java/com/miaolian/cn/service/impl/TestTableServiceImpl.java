package com.miaolian.cn.service.impl;

import com.miaolian.cn.dao.TestTableMapper;
import com.miaolian.cn.domain.TestTable;
import com.miaolian.cn.service.TestTableService;
import com.miaolian.cn.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/08/14.
 */
@Service
@Transactional
public class TestTableServiceImpl extends AbstractService<TestTable> implements TestTableService {
    @Resource
    private TestTableMapper testTableMapper;

}
