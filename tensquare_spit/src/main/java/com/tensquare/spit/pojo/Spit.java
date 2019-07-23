package com.tensquare.spit.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * @auther: tangkc
 * @Date: 2019/7/23 21:52
 * @Description:
 */
@Setter
@Getter
@NoArgsConstructor
public class Spit {
	@Id
	private String _id;
	private String content;
	private Date publishtime;
	private String userid;
	private String nickname;
	private Integer visits;
	private Integer thumbup;
	private Integer share;
	private Integer comment;
	private String state;
	private String parentid;
}
