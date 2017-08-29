package com.miaolian.cn.controller;

import com.miaolian.cn.core.Result;
import com.miaolian.cn.core.ResultGenerator;
import com.miaolian.cn.domain.TestTable;
import com.miaolian.cn.service.TestTableService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2017/08/14.
*/
@RestController
@RequestMapping("/test/table")
public class TestTableController {
    @Resource
    private TestTableService testTableService;

    @PostMapping
    public Result add(@RequestBody TestTable testTable) {
        testTableService.save(testTable);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        testTableService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody TestTable testTable) {
        testTableService.update(testTable);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        TestTable testTable = testTableService.findById(id);
        return ResultGenerator.genSuccessResult(testTable);
    }

    @GetMapping("/getAll")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "5") Integer size) {
        PageHelper.startPage(page, size);
        List<TestTable> list = testTableService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
