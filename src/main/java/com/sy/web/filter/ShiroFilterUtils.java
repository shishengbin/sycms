package com.sy.web.filter;

import java.io.PrintWriter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

public class ShiroFilterUtils {
	
	
	public static boolean isAjax(ServletRequest request){
		String header = ((HttpServletRequest) request).getHeader("X-Requested-With");
		if("XMLHttpRequest".equalsIgnoreCase(header)){
			//LoggerUtils.debug(CLAZZ, "当前请求为Ajax请求");
			return Boolean.TRUE;
		}
		//LoggerUtils.debug(CLAZZ, "当前请求非Ajax请求");
		return Boolean.FALSE;
	}
	
	
	public static void out(ServletResponse response){
		PrintWriter out = null;
		try {
			response.setCharacterEncoding("UTF-8");//设置编码
			response.setContentType("application/json");//设置返回类型
			
			String jsonStr = "{\"statusCode\":301,\"message\":\"登录已超时，请重新登录！\"}";
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
			JsonNode df = mapper.readValue(jsonStr, JsonNode.class);	
			
			out = response.getWriter();
			out.println(df);//输出
		} catch (Exception e) {
		}finally{
			if(null != out){
				out.flush();
				out.close();
			}
		}
	}
}
