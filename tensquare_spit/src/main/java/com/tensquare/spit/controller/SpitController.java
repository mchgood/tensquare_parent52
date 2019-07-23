package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import lombok.experimental.Delegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther: tangkc
 * @Date: 2019/7/23 22:01
 * @Description:
 */
@RestController//标记为返回jso
@CrossOrigin//跨域
public class SpitController {

	@Autowired
	private SpitService spitService;

	@Autowired
	private RedisTemplate redisTemplate;

	/**
	 * 查询所有
	 * @return
	 */
	@GetMapping("/spit")
	public Result findAll(){
		List<Spit> list = spitService.findAll();
		return new Result(true, StatusCode.OK,"查询成功",list);
	}

	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	@GetMapping("/spit/{spitId}")
	public Result findById(@PathVariable("spitId") String id){
		Spit info = spitService.findById(id);
		return new Result(true, StatusCode.OK,"查询成功",info);
	}

	/**
	 * 保存
	 * @param spit
	 * @return
	 */
	@PutMapping("/spit/{spitId}")
	public Result save(@PathVariable String spitId,@RequestBody Spit spit){
		spit.set_id(spitId);
		spitService.save(spit);
		return new Result(true, StatusCode.OK,"保存成功");
	}

	@DeleteMapping("/spit/{spitId}")
	public Result deleteById(@PathVariable("spitId") String id){
		spitService.deleteById(id);
		return new Result(true, StatusCode.OK,"删除成功");
	}

	/**
	 * 根据根节点查询
	 * @param parentid
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping("/spit/comment/{parentid}/{page}/{size}")
	public Result findByParentid(@PathVariable("parentid") String parentid,@PathVariable("page") int  page,@PathVariable("size") int  size){
		Page<Spit> pageData = spitService.findByParentid(parentid, page, size);
		return new Result(true, StatusCode.OK, "查询成功",
				new PageResult<Spit>(pageData.getTotalElements(), pageData.getContent()));
	}

	@PutMapping("/thumbup/{spitId}")
	public Result thumbup(@PathVariable String spitId) {
		// 解决重复点赞问题
		String userid = "111";  // 从登陆信息中获取
		if (redisTemplate.opsForValue().get("thumbup_" + userid) != null) {
			return new Result(false, StatusCode.ERROR, "不能重复点赞");
		}

		spitService.thumbup(spitId);
		redisTemplate.opsForValue().set("thumbup_" + userid, 1);
		return new Result(true, StatusCode.OK, "点赞成功");
	}


}
