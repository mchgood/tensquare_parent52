package com.tensquare.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.article.pojo.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{

    @Modifying//用于确定改操作是会影响数据库
    @Query(value = "UPDATE tb_article SET state=1 WHERE id=?",nativeQuery = true)//？后面的数字，代表是第几个参数
    public void updateState(String id);

    @Modifying//用于确定改操作是会影响数据库
    @Query(value = "UPDATE tb_article SET thumbup=IFNULL(thumbup,0)+1 WHERE id=?",nativeQuery = true)
    public void addThumbup(String id);
}
