package com.yuhui.service;

import java.util.ArrayList;

import com.shouyi.SMGP_API.Report;
import com.shouyi.SMGP_API.Sms;

public interface SMGPService {
	/**
	 * 功能 ：连接 SMGP 服务器并登录
	 * 
	 * @param ipAddress
	 *            服务器IP地址
	 * @param port
	 *            端口号
	 * @param userName
	 *            登录账号
	 * @param pwd
	 *            密码
	 * @param timeout
	 *            连接或发送短消息时的等待超时时间(秒)，建议值 30
	 * @return 0 初始化成功 -1 连接服务器失败
	 * 
	 */
	int Login(String ipAddress, int port, String userName, String pwd,
			int timeout);

	/**
	 * 功能：发送指定的短信，它能自动分段发送,每段信息最大长度为70 个字
	 * 
	 * @param srcNo
	 *            原号码
	 * @param destNo
	 *            目标号码
	 * @param content
	 *            发送内容
	 * @param isNeedReport
	 *            是否需要回执报告，false 为不需要，true 为需要
	 * @param msgID
	 *            服务器返回的 msgID
	 * @return 0 发送成功 -1 发送失败 -2 未初始化 返回其他 请查看错误代码含义
	 */
	int[] SendSms(String srcNo, String destNo, String content,
			boolean isNeedReport, StringBuilder msgID);

	/**
	 * 功能：发送长短信，它能自动分段发送,每段信息最大长度为 67 个字，手机收到一条完整短信。
	 * 
	 * @param srcNo
	 *            原号码
	 * @param destNo
	 *            目标号码
	 * @param content发送的内容
	 * @param isNeedReport是否需要回执报告
	 *            ，false 为不需要，true 为需要
	 * @param msgID服务器返回全部分段短信的
	 *            msgID 集合
	 * @returnint 数组，表示各分段短信返回的结果集合 0 发送成功 -1 发送失败 -2 未初始化 返回其他 请查看错误代码含义
	 */
	int[] SendLongSms(String srcNo, String destNo, String content,
			boolean isNeedReport, StringBuilder msgID);

	/**
	 * 功能：返回接收队列中短信
	 * 
	 * @param smsRecList返回短信息列表
	 * @return 0 取回复短信成功，短信内容在 smsRecList中 -1 短信队列为空，取短信失败
	 */
	int RecvSms(ArrayList<Sms> smsRecList);

	/**
	 * 功能：返回接收队列中状态报告
	 * 
	 * @param smsReportList返回状态报告列表
	 * @return 0 取状态报告成功，状态报告内容在smsReportList中 -1 状态报告队列为空，取状态报告失败
	 */
	int RecvSmsReport(ArrayList<Report> smsReportList);

	/**
	 * 功能：断开与服务器之间的连接
	 */
	void Disconnect();
}
