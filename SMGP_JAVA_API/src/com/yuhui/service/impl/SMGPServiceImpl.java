package com.yuhui.service.impl;

import java.util.ArrayList;

import com.shouyi.SMGP_API.Report;
import com.shouyi.SMGP_API.Sms;
import com.shouyi.SMGP_API.SmsClient;
import com.yuhui.service.SMGPService;

public class SMGPServiceImpl implements SMGPService {

	private SmsClient client;

	public SMGPServiceImpl() {

	}

	public SMGPServiceImpl(SmsClient client) {
		this.client = client;
	}

	@Override
	public int Login(String ipAddress, int port, String userName, String pwd,
			int timeout) {

		int result = client.Login(ipAddress, port, userName, pwd, timeout);
		return result;
	}

	@Override
	public int[] SendSms(String srcNo, String destNo, String content,
			boolean isNeedReport, StringBuilder msgID) {
		int[] result = client.SendSms(srcNo, destNo, content, isNeedReport,
				msgID);
		return result;
	}

	@Override
	public int[] SendLongSms(String srcNo, String destNo, String content,
			boolean isNeedReport, StringBuilder msgID) {
		int[] result = client.SendLongSms(srcNo, destNo, content, isNeedReport,
				msgID);
		return result;
	}

	@Override
	public int RecvSms(ArrayList<Sms> smsRecList) {
		int result = client.RecvSms(smsRecList);
		return result;
	}

	@Override
	public int RecvSmsReport(ArrayList<Report> smsReportList) {
		int result = client.RecvSmsReport(smsReportList);
		return result;
	}

	@Override
	public void Disconnect() {
		client.Disconnect();

	}

}
