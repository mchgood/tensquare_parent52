package entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Auther: lenovo
 * @Date: 2019-07-13 18:50
 * @Description:单对象通用返回实体类
 */
@Data
@Accessors(chain = true)//增加此注解，set返回对象
public class Result {
    private boolean flag;   //是否成功
    private Integer code;    //返回码
    private String message;  //返回信息
    private Object data;   //返回数据

    public Result(boolean flag, Integer code, String message, Object data) {
        super();
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result() {
    }

    public Result(boolean flag, Integer code, String message) {
        super();
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

}
