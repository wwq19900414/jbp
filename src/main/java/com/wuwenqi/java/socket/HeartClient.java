package com.wuwenqi.java.socket;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.thestore.eam.utils.DateUtils;

public class HeartClient extends Frame {
	public List<String> messageList = new ArrayList<String>();
	public String userName = "ClientUser" + System.currentTimeMillis();
	public static final String SERVER_HOST = "127.0.0.1";
	public static final int SERVER_PORT = 414;

	/**
	 * 
	 */
	private static final long serialVersionUID = -4632063132955272586L;
	/*
	 * 成员方法出场...
	 */
	private TextField tfText;
	private TextArea taContent;
	private Socket s;
	private DataOutputStream dos;
	private DataInputStream dis;

	/**
	 * 注意，入口... ^^
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new HeartClient().launchFrame();

	}

	/**
	 * Loading GU
	 */
	public void launchFrame() {
		tfText = new TextField();
		taContent = new TextArea();
		taContent.setBackground(Color.LIGHT_GRAY);

		this.setTitle("本地聊天客戶端，用戶名【" + userName + "】");
		this.setSize(300, 300);
		this.setLocation(300, 300);
		this.tfText.addActionListener(new TFListener());
		this.add(tfText, BorderLayout.SOUTH);
		this.add(taContent, BorderLayout.CENTER);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.pack();
		this.connect();
		this.setVisible(true);
	}

	/**
	 * 我在努力地连接服务器中...
	 */
	public void connect() {
		try {
			s = new Socket(SERVER_HOST, SERVER_PORT);
			dos = new DataOutputStream(s.getOutputStream());
			dis = new DataInputStream(s.getInputStream());
			new Thread(new SendThread()).start();
			// dos.writeUTF("Hello,i find u!");
		} catch (UnknownHostException e) {
			System.out.println("UnknownHostException");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		} finally {
			// 关闭啥尼...
		}

	}

	/**
	 * 额不会傻等着tfText(TextField tfText的监听器类)
	 */
	class TFListener implements ActionListener {
		private String str;

		@Override
		public void actionPerformed(ActionEvent e) {
			str = tfText.getText().trim();
			str = "【" + DateUtils.getStringDate() + "】" + userName + " 曰：\n" + str + "\n";
			tfText.setText("");
			try {
				dos.writeUTF(str);
			} catch (IOException e1) {
				System.out.println("IOException");
				e1.printStackTrace();
			}
		}

	}

	/**
	 * 客户端接收消息的线程呦...
	 * 
	 */
	class SendThread implements Runnable {
		private String str;
		private boolean iConnect = false;

		public void run() {
			iConnect = true;
			recMsg();

		}

		/**
		 * 消息，看招，哪里跑...（客户端接收消息的实现）
		 * 
		 * @throws IOException
		 */
		public void recMsg() {
			try {
				while (iConnect) {
					str = dis.readUTF();
					messageList.add(str);
					StringBuilder content = new StringBuilder();
					for (String message : messageList) {
						content.append(message);
					}
					taContent.setText(content.toString());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}
