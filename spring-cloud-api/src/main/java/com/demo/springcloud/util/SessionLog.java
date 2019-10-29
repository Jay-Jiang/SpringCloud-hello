package com.demo.springcloud.util;

import java.util.HashMap;
import java.util.Map;

/**
 * _@Author: jianghj
 * _@Date: 2019.10.29 029 11:28
 * _@Desc: 用于保存日志解析的结果
 */
public class SessionLog {
	private String sessionId;
	private Map<String, String> logs;

	public SessionLog() {
		logs = new HashMap<>();
	}

	public SessionLog(String sessionId) {
		this.sessionId = sessionId;
		logs = new HashMap<>();
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Map<String, String> getLogs() {
		return logs;
	}

	public void setLogs(Map<String, String> logs) {
		this.logs = logs;
	}
}
