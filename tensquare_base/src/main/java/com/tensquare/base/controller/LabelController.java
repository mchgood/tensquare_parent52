package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tangkc
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

    /**
     * 带参数模糊查询
     * @param label
     * @return
     */
    @PostMapping("/label/search")
    public Result findSearch(@RequestBody Label label){
        List<Label> list = labelService.findSearch(label);
        return new Result(true, StatusCode.OK,"查询成功",list);
    }

    /**
     * 带参数模糊查询
     * @param label
     * @return
     */
    @PostMapping("/label/search/{page}/{size}")
    public Result findQuery(@RequestBody Label label,@PathVariable int page,@PathVariable int size){
        Page<Label> pageData = labelService.findQuery(label,page,size);
        //封装返回对象
        PageResult<Label> pageResult = new PageResult<>();
        pageResult.setRows(pageData.getContent())
                .setTotal(pageData.getTotalElements());
        return new Result(true, StatusCode.OK,"添加成功",pageResult);
    }
}
