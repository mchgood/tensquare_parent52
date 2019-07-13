package com.tensquare.base.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Auther: lenovo
 * @Date: 2019-07-13 22:16
 * @Description:
 */
@Data
@Entity
@Table(name = "tb_label")
public class Label implements Serializable {
    @Id
    private String id;
    private String labelname;   //标签名称
    private String state;       //状态
    private Long count;         //使用数量 0：无效 1：有效
    private Long fans;          //关注数
    private String recommend;   //是否推荐 0：不推荐 1:推荐
}
