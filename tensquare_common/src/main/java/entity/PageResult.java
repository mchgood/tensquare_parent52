package entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Auther: lenovo
 * @Date: 2019-07-13 19:02
 * @Description:分页通用实体类
 */
@Data
@Accessors(chain = true)//增加此注解，set返回对象
public class PageResult <T>{
    private long total;
    private List<T> rows;

    public PageResult() {
    }

    public PageResult(Long total, List<T> rows) {
        super();
        this.total = total;
        this.rows = rows;
    }
}
