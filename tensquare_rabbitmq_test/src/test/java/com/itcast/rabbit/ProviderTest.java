package com.itcast.rabbit;

import com.itcast.RabbitApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @auther: tangkc
 * @Date: 2019/8/31 16:55
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitApplication.class)
public class ProviderTest {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	/**
	 * 直接模式
	 */
	@Test
	public void sendMsg1(){
		/**
		 * routingKey	队列
		 */
		rabbitTemplate.convertAndSend("itcast","直接模式测试");
	}

	/**
	 * 分裂模式
	 */
	@Test
	public void sendMsg2(){
		/**
		 * exchange	交换器
		 */
		rabbitTemplate.convertAndSend("heima","","分裂模式测试");
	}

	/**
	 * 主题模式
	 */
	@Test
	public void sendMsg3(){
		/**
		 * exchange	交换器
		 * routingKey	匹配规则
		 */
		rabbitTemplate.convertAndSend("topic","good.log","good.asd分裂模式测试");
	}
}
