package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.Result;
import entity.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: lenovo
 * @Date: 2019-07-13 22:09
 * @Description:
 */
@Slf4j
@RestController  //不用再加responseBody
@CrossOrigin    //跨域处理
public class LabelController {

    @Autowired
    private LabelService labelService;

    @GetMapping("/label")
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",labelService.findAll());
    }

    @GetMapping("/label/{labelId}")
    public Result findById(@PathVariable("labelId") String labelId){
        return new Result(true, StatusCode.OK,"查询成功",labelService.findById(labelId));
    }

    @PostMapping("/label")
    public Result save(@RequestBody Label label){
        labelService.save(label);
        return new Result(true, StatusCode.OK,"添加成功");
    }

    @PutMapping("/label/{labelId}")
    public Result update(@RequestBody Label label,@PathVariable("labelId") String labelId){
        label.setId(labelId);
        labelService.update(label);
        return new Result(true, StatusCode.OK,"更新成功");
    }

    @DeleteMapping("/label/{labelId}")
    public Result deleteById(@PathVariable("labelId") String labelId){
        labelService.deleteById(labelId);
        return new Result(true, StatusCode.OK,"删除成功");
    }
}
