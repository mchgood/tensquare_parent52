package com.tensquare.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{

    /**
     * 最新回答
     * @return
     */
    @Query(value = "SELECT * FROM tb_problem LEFT JOIN tb_pl on problemid=id WHERE labelid=? ORDER BY replytime",nativeQuery = true)
    public Page<Problem> newList(String labelid, Pageable page);

    /**
     * 热门回答
     * @return
     */
    @Query(value = "SELECT * FROM tb_problem LEFT JOIN tb_pl on problemid=id WHERE labelid=? ORDER BY reply",nativeQuery = true)
    public Page<Problem> hotList(String labelid,Pageable page);

    /**
     * 等待回答
     * @return
     */
    @Query(value = "SELECT * FROM tb_problem LEFT JOIN tb_pl on problemid=id WHERE labelid=? and reply=0 ORDER BY createtime",nativeQuery = true)
    public Page<Problem> waitList(String labelid,Pageable page);
}
