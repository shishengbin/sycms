package com.sy.common;

/**
 *操作json
 *@author sss 2013-8-10 
 */
public class JsonUtil {
	
	//to json
	public static String transferJsonResponse(String statusCode, String message, String navTabId,String rel,String callbackType,String forwardUrl) {
		Data d = new Data(statusCode, message, navTabId,rel,callbackType,forwardUrl);
		return d.toJsonString();
	}
	public static String transferJsonResponse(Integer statusCode,String message,String forwardUrl){
		Data d = new Data(statusCode.toString(), message,forwardUrl);
		return d.toJson();
	}
	
	//------------------------------------------------------------------------------------------------------

}
