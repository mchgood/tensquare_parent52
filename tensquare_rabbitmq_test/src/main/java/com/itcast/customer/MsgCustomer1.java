package com.itcast.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @auther: tangkc
 * @Date: 2019/8/31 16:58
 * @Description:
 */
@Component
@RabbitListener(queues = "itcast")
public class MsgCustomer1 {

	@RabbitHandler
	public void getMsg(String msg){
		System.out.println(String.format("itcast：%s",msg));
	}
}
