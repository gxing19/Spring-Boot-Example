package Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * 自动注册属性类
 * 用于读取application.properties中的参数
 * @author gxing
 *
 */

@ConfigurationProperties(prefix="hello") //1. 参数绑定，指定前辍
public class HelloServiceProperties {
	
	private static final String MSG = "world";
	private String msg = MSG;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
