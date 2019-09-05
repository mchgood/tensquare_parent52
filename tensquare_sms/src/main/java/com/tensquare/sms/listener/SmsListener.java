package com.tensquare.sms.listener;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.tensquare.sms.common.Config;
import com.tensquare.sms.common.HttpUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @auther: tangkc
 * @Date: 2019/9/5 22:46
 * @Description:
 */
@Component
@RabbitListener(queues = "msg")
public class SmsListener {

	@RabbitHandler
	public void executeSms(Map<String,String> cond) throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		sb.append("accountSid").append("=").append(Config.ACCOUNT_SID);
		sb.append("&to").append("=").append(cond.get("mobile"));
		sb.append("&param").append("=").append(URLEncoder.encode("","UTF-8"));
		sb.append("&templateid").append("=").append(cond.get("checkcode"));
		String body = sb.toString() + HttpUtil.createCommonParam(Config.ACCOUNT_SID, Config.AUTH_TOKEN);
		String result = HttpUtil.post(Config.BASE_URL, body);
		System.out.println(result);
	}
}
