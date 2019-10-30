package com.demo.springcloud.util;

import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * _@Author: jianghj
 * _@Date: 2019.10.29 029 9:36
 * _@Desc:
 */

public class ParseLogUtil {

	//关键字段标记声明
	private static final String MSG_ID_FLAG = "MsgID:";
	private static final String SEND_NUM_FLAG = "Sndr:";
	private static final String RECEIVE_NUM_FLAG = "Rcvr:";
	private static final String STATE_CODE_FLAG = "FlwOvrStatCode:";
	private static final String MSG_SIZE_FLAG = "MsgSz:";

	public static void main(String[] args) {

		String filePath = "C:/Users/Jay/Desktop/target.log";
		List<LogMsg> list = parseLogGroupByMsgId(filePath);
		JSONArray res = JSONArray.fromObject(list);
		System.out.println(res.toString());
	}

	/**
	 * _@功能描述: 从日志文件中按 MsgId 获取相关信息
	 * _@author: jhjing
	 * _@date: 2019.10.29
	 * _@param:filePath 日志文件的绝对路径
	 * _@return:
	 */
	public static List<LogMsg> parseLogGroupByMsgId(String filePath) {

		//参数非空校验
		if (StringUtils.isEmpty(filePath)) {
			return null;
		}

		BufferedReader br = null;

		try {
			//创建读取流，并指定目标文件读取的字符集，防止中文乱码
			br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8));
			//按行读取
			String line = "";
			//保存读取结果
			List<LogMsg> list = new ArrayList<LogMsg>();
			LogMsg logMsg = null;

			while ((line = br.readLine()) != null) {
				String msgId = null;
				//获取行中的 MsgID
				msgId = getValueEndByBracket(line, MSG_ID_FLAG);

				if (logMsg == null && msgId == null) {
					//如果行中不包含 MsgId ，则直接读取下一行
					continue;
				} else if (logMsg != null) {
					//如果当前行中的MsgID，和当前的消息实例ID不同
					if (msgId != null && !msgId.equals(logMsg.getMsgId())) {
						//保存原先的消息实例
						list.add(logMsg);
						//创建新的消息实例
						logMsg = new LogMsg(msgId);
					}
				} else {
					//创建信息实例
					logMsg = new LogMsg(msgId);
				}

				//解析发送号码
				if (logMsg.getSendNum() == null) {
					logMsg.setSendNum(getValueEndByBracket(line, SEND_NUM_FLAG));

				}
				//解析接受号码
				if (logMsg.getReceiveNum() == null) {
					logMsg.setReceiveNum(getValueEndByBracket(line, RECEIVE_NUM_FLAG));

				}
				//解析状态码
				String stateCodeStr = getValueEndBySemicolon(line, STATE_CODE_FLAG);
				setMaxStateCode(logMsg, stateCodeStr);
				//解析短信大小
				String msgSizeStr = getValueEndBySemicolon(line, MSG_SIZE_FLAG);
				setMaxMsgSize(logMsg, msgSizeStr);
				//解析开始和结束时间
				String timeStr = getValueEndByBracket(line, "[");
				setStartOrEndTime(logMsg, timeStr);
			}

			//将最后一个消息实例保存下来
			list.add(logMsg);
			//返回解析结果
			return list;

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			//释放资源
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * _@功能描述: 获取指定字段开头，以中括号结尾的值
	 * _@author: jhjing
	 * _@date: 2019.10.30 030
	 * _@param:source
	 * _@param:start
	 * _@return:
	 */
	private static String getValueEndByBracket(String source, String start) {
		//参数校验
		if (StringUtils.isEmpty(source) || StringUtils.isEmpty(start)) {
			return "";
		}

		return getValue(source, start, "]");
	}

	/**
	 * _@功能描述: 获取指定字段开头，以分号结尾的值
	 * _@author: jhjing
	 * _@date: 2019.10.30 030
	 * _@param:source
	 * _@param:start
	 * _@return:
	 */
	private static String getValueEndBySemicolon(String source, String start) {
		//参数校验
		if (StringUtils.isEmpty(source) || StringUtils.isEmpty(start)) {
			return "";
		}

		return getValue(source, start, ";");
	}

	/**
	 * _@功能描述: 截取指定字符串
	 * _@author: jhjing
	 * _@date: 2019.10.30 030
	 * _@param:source
	 * _@param:start
	 * _@return:
	 */
	private static String getValue(String source, String start, String end) {
		//source 中是否含有 start
		int index = source.indexOf(start);
		if (index < 0) {
			return null;
		}
		//从 start 位置截取
		String subSource = source.substring(index);

		int startIndex = start.length();
		int endIndex = subSource.indexOf(end);
		return subSource.substring(startIndex, endIndex);
	}

	/**
	 * _@功能描述: 设置最终状态为最大值
	 * _@author: jhjing
	 * _@date: 2019.10.30 030
	 * _@param:logMsg
	 * _@param:newCode
	 * _@return:
	 */
	private static void setMaxStateCode(LogMsg logMsg, String newCode) {
		if (StringUtils.isEmpty(newCode)) {
			return;
		}

		String oldCode = logMsg.getStateCode();

		if (oldCode == null) {
			logMsg.setStateCode(newCode);
			return;
		}

		if (Long.parseLong(newCode) > Long.parseLong(oldCode)) {
			logMsg.setStateCode(newCode);
		}
	}

	/**
	 * _@功能描述: 设置短信大小为最大值
	 * _@author: jhjing
	 * _@date: 2019.10.30 030
	 * _@param:logMsg
	 * _@param:newSize
	 * _@return:
	 */
	private static void setMaxMsgSize(LogMsg logMsg, String newSize) {
		if (StringUtils.isEmpty(newSize)) {
			return;
		}

		String oldSize = logMsg.getMsgSize();

		if (oldSize == null) {
			logMsg.setMsgSize(newSize);
			return;
		}

		//获取数字
		String newSizeNum = newSize.substring(0, newSize.indexOf(" "));
		String oldSizeNum = oldSize.substring(0, oldSize.indexOf(" "));
		//比较大小
		if (Long.parseLong(newSizeNum) > Long.parseLong(oldSizeNum)) {
			logMsg.setMsgSize(newSize);
		}
	}

	/**
	 * _@功能描述: 设置开始和结束时间
	 * _@author: jhjing
	 * _@date: 2019.10.30 030
	 * _@param:logMsg
	 * _@param:time
	 * _@return:
	 */
	private static void setStartOrEndTime(LogMsg logMsg, String time) {
		if (StringUtils.isEmpty(time)) {
			return;
		}

		String startTime = logMsg.getStartTime();
		String endTime = logMsg.getEndTime();
		if (startTime == null) {
			logMsg.setStartTime(time);
			logMsg.setEndTime(time);
			return;
		}

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS", Locale.ENGLISH);
		try {
			Long startMills = format.parse(startTime).getTime();
			Long endMills = format.parse(endTime).getTime();
			Long timeMills = format.parse(time).getTime();

			if (timeMills > endMills) {
				logMsg.setEndTime(time);
				return;
			}

			if (timeMills < startMills) {
				logMsg.setStartTime(time);
			}

		} catch (Exception e) {
		}
	}


}

