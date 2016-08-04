package com.yuhui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.shouyi.SMGP_API.Report;
import com.shouyi.SMGP_API.Sms;
import com.shouyi.SMGP_API.SmsClient;
import com.yuhui.service.SMGPService;
import com.yuhui.service.impl.SMGPServiceImpl;

public class Handler implements ActionListener {

	private JButton button;
	private JTextField txtSrcNo;
	private JTextField txtDestNo;
	private JTextArea txtContent;
	private JTextField txtUsername;
	private JTextField txtPort;
	private JTextField txtIP;
	private JPasswordField jPasswordField1;
	private JTextArea txtMsg;

	private SMGPService smgpService;
	private static SmsClient client;

	public Handler(SmsClient client, JButton button, JTextField txtSrcNo,
			JTextField txtDestNo, JTextArea txtContent, JTextField txtUsername,
			JTextField txtPort, JTextField txtIP,
			JPasswordField jPasswordField1, JTextArea txtMsg) {
		super();
		Handler.client=client;
		this.button = button;
		this.txtSrcNo = txtSrcNo;
		this.txtDestNo = txtDestNo;
		this.txtContent = txtContent;
		this.txtUsername = txtUsername;
		this.txtPort = txtPort;
		this.txtIP = txtIP;
		this.jPasswordField1 = jPasswordField1;
		this.txtMsg = txtMsg;
		smgpService = new SMGPServiceImpl(client);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "登录":
			btnLoginHandler();
			break;
		case "发送":
			btnSendHandler();
			break;
		case "发送长短信":
			btnSendLongHandler();
			break;
		case "返回短信":
			btnGetSmsHandler();
			break;
		case "返回状态报告":
			btnGetReportHandler();
			break;
		case "退出":
			btnExitHandler();
			break;

		default:
			break;
		}

	}

	private void btnLoginHandler() {
		if (button.getText() == "登录") {
			String msg = "";
			if (txtIP.getText() == null || txtIP.getText().equals("")) {
				msg = "IP不能为空!";
			}
			if (txtPort.getText() == null || txtPort.getText().equals("")) {
				msg = "Port不能为空!";
			}
			if (txtUsername.getText() == null
					|| txtUsername.getText().equals("")) {
				msg = "登录账号不能为空!";
			}
			if (!msg.equals("")) {
				JOptionPane.showMessageDialog(null, msg);
				return;
			}
			int loginResult = smgpService.Login(txtIP.getText(),
					Integer.parseInt(txtPort.getText()), txtUsername.getText(),
					String.valueOf(jPasswordField1.getPassword()), 30);

			if (loginResult == 0) {
				msg = "登录成功!";
				button.setText("Loginout");
			} else {
				msg = "登录失败!";
			}
			JOptionPane.showMessageDialog(null, msg);
		} else if (button.getText() == "Loginout") {
			client.Disconnect();
			button.setText("登录");
		}
	}

	private void btnSendHandler() {
		String msg = "";
		if (txtSrcNo.getText() == null || txtSrcNo.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "原号码不能为空!");
			return;
		}
		if (txtDestNo.getText() == null || txtDestNo.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "目标号码不能为空!");
			return;
		}
		if (txtContent.getText() == null || txtContent.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "发送内容不能为空!");
			return;
		}

		StringBuilder msgID = new StringBuilder();
		int[] sendResult = smgpService.SendSms(txtSrcNo.getText(),
				txtDestNo.getText(), txtContent.getText(), true, msgID);

		if (!msgID.toString().equals("")) {
			txtMsg.append("短消息流水号:" + msgID.toString() + "\r\n");
		}

		for (int i = 0; i < sendResult.length; i++) {
			if (sendResult[i] == 0) {
				msg = "短信" + i + ":成功!";
			} else {
				msg = "短信" + i + ":失败!返回错误代码:" + sendResult[i];
			}
			JOptionPane.showMessageDialog(null, msg);
		}
	}

	private void btnSendLongHandler() {
		if (txtSrcNo.getText() == null || txtSrcNo.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "原号码不能为空!");
			return;
		}
		if (txtDestNo.getText() == null || txtDestNo.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "目标号码不能为空!");
			return;
		}
		if (txtContent.getText() == null || txtContent.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "发送内容不能为空!");
			return;
		}

		StringBuilder msgID = new StringBuilder();
		int[] sendResult = smgpService.SendLongSms(txtSrcNo.getText(),
				txtDestNo.getText(), txtContent.getText(), true, msgID);

		if (!msgID.toString().equals("")) {
			txtMsg.append("短消息流水号:" + msgID.toString() + "\r\n");
		}
		int errorCount = 0;

		for (int i = 0; i < sendResult.length; i++) {
			if (sendResult[i] != 0) {
				errorCount++;
				JOptionPane.showMessageDialog(null, "短信" + i + ":失败!返回错误代码:"
						+ sendResult[i]);
			}
		}
		if (errorCount == 0) {
			JOptionPane.showMessageDialog(null, "发送成功!");
		}
	}

	private void btnGetSmsHandler() {
		ArrayList<Sms> smsDeliverList = new ArrayList<Sms>();
		smgpService.RecvSms(smsDeliverList);
		if (!smsDeliverList.isEmpty()) {
			for (Sms de : smsDeliverList) {
				txtMsg.append("=======返回短信息列表 ========" + "\r\n发信人:"
						+ de.getSrcTermID().trim() + "\r\n收信人:"
						+ de.getDestTermID().trim() + "\r\n发送内容:"
						+ de.getMsgContent().trim() + "\r\n收信时间:"
						+ de.getRecvTime().trim()
						+ "\r\n===========结束==========\r\n");
			}
		} else {
			JOptionPane.showMessageDialog(null, "短信列表是空的!");
		}
	}

	private void btnGetReportHandler() {
		ArrayList<Report> smsReportList = new ArrayList<Report>();
		smgpService.RecvSmsReport(smsReportList);
		if (!smsReportList.isEmpty()) {
			for (Report re : smsReportList) {
				txtMsg.append("=======信息状态报告列表========" + "\r\n发信人:"
						+ re.getSrcTermID().trim() + "\r\n收信人:"
						+ re.getSrcTermID().trim() + "\r\n短消息流水号:"
						+ re.getMsgID().trim() + "\r\n提交时间:"
						+ re.getSubmit_date().trim() + "\r\n短信到达时间:"
						+ re.getDone_date().trim() + "\r\n短信内容:"
						+ re.getTxt().trim() + "\r\n短信最终状态:" + re.getStat()
						+ "\r\n==========结束==========\r\n");
			}
		} else {
			JOptionPane.showMessageDialog(null, "信息状态报告列表是空的!");
		}
	}

	private void btnExitHandler() {
		if (client.getIsLogined()) {
			smgpService.Disconnect();
		}

		System.exit(1);
	}

}
