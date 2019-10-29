package com.demo.springcloud.util;

import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * _@Author: jianghj
 * _@Date: 2019.10.29 029 9:36
 * _@Desc:
 */

public class ParseLogUtil {
	private static final String SESSION_FLAG = "SessID";

	public static void main(String[] args) {

		String filePath = "C:/Users/Jay/Desktop/log.txt";
		List<SessionLog> list = parseLogFile(filePath);
		JSONArray res = JSONArray.fromObject(list);
		System.out.println(res.toString());
	}

	/**
	 * _@功能描述: 从日志文件中获取指定的键值对信息，并关联到对应的SessionID 上
	 * _@author: jhjing
	 * _@date: 2019.10.29
	 * _@param:filePath 日志文件的绝对路径
	 * _@return:
	 */
	public static List<SessionLog> parseLogFile(String filePath) {

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
			String tempLine = "";
			//保存读取结果
			List<SessionLog> list = new ArrayList<SessionLog>();
			SessionLog sessionLog = null;

			while ((line = br.readLine()) != null) {
				//如果 SessionID 尚且为空
				if (sessionLog == null) {
					//在行中寻找 SessionID 的标记
					int flag = line.indexOf(SESSION_FLAG);

					//如果行中不包含 SessionID ，则直接读取下一行
					if (flag < 1) {
						continue;
					}
					//如果行中包含 SessionID ，则直接从 SessionID 的标记位置开始读取
					line = line.substring(flag - 1);
				}

				//以中括号分割目标信息，如果不包含，直接进入下一行
				if (!line.contains("[") || !line.contains("]")) {
					continue;
				}

				//开始读取目标信息
				tempLine = line;
				int start = tempLine.indexOf("[");
				int end = tempLine.indexOf("]");

				while (tempLine.length() > 0 && start > -1 && end > -1) {
					//获取被中括号包裹的信息体
					String targetInfo = tempLine.substring(start + 1, end);
					//获取信息体中的键值对
					if (targetInfo.contains(":")) {
						String[] params = targetInfo.split(":");
						if (params.length == 2) {
							//当前行中是否包含 sessionId
							if (SESSION_FLAG.equals(params[0])) {
								if (sessionLog != null) {
									//将有效的旧session ID 的信息保存下来
									list.add(sessionLog);
								}
								//创建新的 session ID
								sessionLog = new SessionLog(params[1]);
							} else if (sessionLog != null) {
								//将目标键值对，添加到当前非空的 SessionID 中
								sessionLog.getLogs().put(params[0], params[1]);
							}
						}
					}
					//如果读到了行尾，则结束读取
					if (end >= tempLine.length() - 1) {
						break;
					}
					//继续向后搜索目标信息体
					tempLine = tempLine.substring(end + 1);
					start = tempLine.indexOf("[");
					end = tempLine.indexOf("]");
				}
			}
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

}

