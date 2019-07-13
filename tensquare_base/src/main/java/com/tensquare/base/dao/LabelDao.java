package com.tensquare.base.dao;

import com.tensquare.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Auther: lenovo
 * @Date: 2019-07-13 22:24
 * @Description:
 */
//第一个继承代表使用JPA
//第二个继承代表使用分页
public interface LabelDao extends JpaRepository<Label,String>, JpaSpecificationExecutor<Label> {

}
