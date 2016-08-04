package com.yuhui;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

import com.shouyi.SMGP_API.SmsClient;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class SMGP extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JScrollPane jScrollPane2;
	private JScrollPane jScrollPane1;
	private JPasswordField jPasswordField1;
	private JTextArea txtMsg;
	private JButton btnGetReport;
	private JButton btnGetSms;
	private JButton btnExit;
	private JButton btnSendLong;
	private JButton btnSend;
	private JLabel jLabel7;
	private JTextField txtDestNo;
	private JLabel jLabel6;
	private JTextField txtSrcNo;
	private JTextArea txtContent;
	private JTextField txtUsername;
	private JTextField txtPort;
	private JTextField txtIP;
	private JButton btnLogin;
	private static SmsClient client;

	public SMGP() {
		super();
		InitGui();
		AddListener();

	}

	/**
	 * 主方法入口，显示界面
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				client = new SmsClient();
				SMGP inst = new SMGP();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
				inst.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						if (client.getIsLogined()) {
							client.Disconnect();
						}
						System.exit(1);
					}
				});
			}
		});
	}

	private void InitGui() {
		setPreferredSize(new Dimension(600, 360));
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setTitle("短信接口");
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("服务器IP:");
				jLabel1.setBounds(12, 22, 58, 15);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("端口号:");
				jLabel2.setBounds(12, 49, 58, 15);
			}
			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3);
				jLabel3.setText("登录账号:");
				jLabel3.setBounds(12, 76, 70, 15);
			}
			{
				jLabel4 = new JLabel();
				getContentPane().add(jLabel4);
				jLabel4.setText("密码:");
				jLabel4.setBounds(12, 103, 70, 15);
			}
			{
				btnLogin = new JButton();
				getContentPane().add(btnLogin);
				btnLogin.setText("登录");
				btnLogin.setBounds(82, 133, 108, 22);
			}
			{
				txtIP = new JTextField();
				getContentPane().add(txtIP);
				txtIP.setBounds(82, 19, 108, 21);
				txtIP.setText("125.88.123.137");
				txtIP.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 1,
						false));
			}
			{
				txtPort = new JTextField();
				getContentPane().add(txtPort);
				txtPort.setBounds(82, 46, 108, 22);
				txtPort.setText("3058");
				txtPort.setBorder(new LineBorder(new java.awt.Color(0, 0, 0),
						1, false));
			}
			{
				txtUsername = new JTextField();
				getContentPane().add(txtUsername);
				txtUsername.setBounds(82, 73, 108, 22);
				txtUsername.setBorder(new LineBorder(
						new java.awt.Color(0, 0, 0), 1, false));
			}
			{
				jLabel5 = new JLabel();
				getContentPane().add(jLabel5);
				jLabel5.setText("原号码:");
				jLabel5.setBounds(12, 173, 42, 15);
			}
			{
				txtSrcNo = new JTextField();
				getContentPane().add(txtSrcNo);
				txtSrcNo.setBounds(82, 170, 124, 22);
				txtSrcNo.setBorder(new LineBorder(new java.awt.Color(0, 0, 0),
						1, false));
				txtSrcNo.addKeyListener(new OnlyNumber());
			}
			{
				jLabel6 = new JLabel();
				getContentPane().add(jLabel6);
				jLabel6.setText("目标号码:");
				jLabel6.setBounds(243, 173, 61, 15);
			}
			{
				txtDestNo = new JTextField();
				getContentPane().add(txtDestNo);
				txtDestNo.setBounds(302, 170, 129, 22);
				txtDestNo.setBorder(new LineBorder(new java.awt.Color(0, 0, 0),
						1, false));
				txtDestNo.addKeyListener(new OnlyNumber());

			}
			{
				jLabel7 = new JLabel();
				getContentPane().add(jLabel7);
				jLabel7.setText("发送内容:");
				jLabel7.setBounds(12, 221, 56, 15);
			}
			{
				btnSend = new JButton();
				getContentPane().add(btnSend);
				btnSend.setText("发送");
				btnSend.setBounds(12, 275, 70, 22);
			}
			{
				btnSendLong = new JButton();
				btnSendLong.setPreferredSize(new Dimension(39, 9));
				getContentPane().add(btnSendLong);
				btnSendLong.setText("发送长短信");
				btnSendLong.setBounds(90, 275, 105, 22);
			}
			{
				btnExit = new JButton();
				getContentPane().add(btnExit);
				btnExit.setText("退出");
				btnExit.setBounds(453, 275, 61, 22);
			}
			{
				btnGetSms = new JButton();
				btnGetSms.setMinimumSize(new Dimension(36, 9));
				btnGetSms.setMaximumSize(new Dimension(36, 9));
				btnGetSms.setPreferredSize(new Dimension(36, 9));
				getContentPane().add(btnGetSms);
				btnGetSms.setText("返回短信");
				btnGetSms.setBounds(205, 275, 99, 22);
			}
			{
				btnGetReport = new JButton();
				getContentPane().add(btnGetReport);
				btnGetReport.setText("返回状态报告");
				btnGetReport.setBounds(314, 275, 117, 22);
			}
			{
				jPasswordField1 = new JPasswordField();
				getContentPane().add(jPasswordField1);
				jPasswordField1.setBounds(82, 101, 107, 22);
				jPasswordField1.setName("txtPwd");
				jPasswordField1.setBorder(new LineBorder(new java.awt.Color(0,
						0, 0), 1, false));
			}
			{
				jScrollPane1 = new JScrollPane();
				getContentPane().add(jScrollPane1);
				jScrollPane1.setBounds(200, 19, 310, 137);
				{
					txtMsg = new JTextArea();
					jScrollPane1.setViewportView(txtMsg);
					txtMsg.setBounds(66, 331, 250, 137);
					txtMsg.setEditable(false);
				}
			}
			{
				jScrollPane2 = new JScrollPane();
				getContentPane().add(jScrollPane2);
				jScrollPane2.setBounds(82, 198, 428, 65);
				{
					txtContent = new JTextArea();
					txtContent.setText("JAVA API发出的测试短信！");
					jScrollPane2.setViewportView(txtContent);
					txtContent.setBounds(45, 323, 386, 63);
				}
			}

			pack();
		} catch (Exception e) {

		}
	}

	private void AddListener() {
		btnLogin.addActionListener(new Handler(client, btnLogin, txtSrcNo,
				txtDestNo, txtContent, txtUsername, txtPort, txtIP,
				jPasswordField1, txtMsg));
		btnSend.addActionListener(new Handler(client, btnLogin, txtSrcNo,
				txtDestNo, txtContent, txtUsername, txtPort, txtIP,
				jPasswordField1, txtMsg));
		btnSendLong.addActionListener(new Handler(client, btnLogin, txtSrcNo,
				txtDestNo, txtContent, txtUsername, txtPort, txtIP,
				jPasswordField1, txtMsg));
		btnGetSms.addActionListener(new Handler(client, btnLogin, txtSrcNo,
				txtDestNo, txtContent, txtUsername, txtPort, txtIP,
				jPasswordField1, txtMsg));
		btnGetReport.addActionListener(new Handler(client, btnLogin, txtSrcNo,
				txtDestNo, txtContent, txtUsername, txtPort, txtIP,
				jPasswordField1, txtMsg));
		btnExit.addActionListener(new Handler(client, btnLogin, txtSrcNo,
				txtDestNo, txtContent, txtUsername, txtPort, txtIP,
				jPasswordField1, txtMsg));

	}

	private class OnlyNumber implements KeyListener {

		// 限制只输入数字
		@Override
		public void keyTyped(KeyEvent e) {
			int keyChar = e.getKeyChar();
			if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {

			} else {
				e.consume(); // 关键，屏蔽掉非法输入
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
			
		}

		@Override
		public void keyReleased(KeyEvent e) {

		}

	}

}
