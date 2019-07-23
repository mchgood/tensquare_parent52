package com.tensquare.spit.dao;

import com.tensquare.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @auther: tangkc
 * @Date: 2019/7/23 21:54
 * @Description:
 */
public interface SpitDao extends MongoRepository<Spit,String> {

	public Page<Spit> findByParentid(String parentid, Pageable pageable);
}
