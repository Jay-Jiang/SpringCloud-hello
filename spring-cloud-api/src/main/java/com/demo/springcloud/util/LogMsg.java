package com.demo.springcloud.util;

/**
 * _@Author: jianghj
 * _@Date: 2019.10.30 030 11:20
 * _@Desc: 日志消息
 */
public class LogMsg {
	/**
	 * 消息主键
	 */
	String msgId;
	/**
	 * 发送号码
	 */
	String sendNum;
	/**
	 * 接受
	 */
	String receiveNum;
	/**
	 * 开始时间
	 */
	String startTime;
	/**
	 * 结束时间
	 */
	String endTime;
	/**
	 * 最终状态码
	 */
	String stateCode;
	/**
	 * 信息大小
	 */
	String msgSize;

	/**唯一的构造方法*/
	public LogMsg(String msgId) {
		this.msgId = msgId;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getSendNum() {
		return sendNum;
	}

	public void setSendNum(String sendNum) {
		this.sendNum = sendNum;
	}

	public String getReceiveNum() {
		return receiveNum;
	}

	public void setReceiveNum(String receiveNum) {
		this.receiveNum = receiveNum;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getMsgSize() {
		return msgSize;
	}

	public void setMsgSize(String msgSize) {
		this.msgSize = msgSize;
	}
}
