package com.sy.web.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

public class SessionTimeOutFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		HttpServletRequest requ = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		if (!SecurityUtils.getSubject().isAuthenticated()) {
			// 判断session里是否有用户信息,且是否为ajax请求，如果是ajax请求响应头会有，x-requested-with
			if (requ.getHeader("x-requested-with") != null
					&& requ.getHeader("x-requested-with").equalsIgnoreCase(
							"XMLHttpRequest")) {
				//resp.setHeader("session-status", "timeout");// 在响应头设置session状态
				String jsonStr = "{\"statusCode\":301,\"message\":\"登录已超时,请刷新页面,或者重新登录!\"}";
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
				JsonNode df = mapper.readValue(jsonStr, JsonNode.class);				
				PrintWriter out = response.getWriter();
				response.setContentType("text/plain");
				//System.out.println(df);					
				out.println(df);
				out.flush();
				out.close();
				return;
			}
		}
		chain.doFilter(requ, response);
	}

	@Override
	public void destroy() {

	}

}
