package com.wuwenqi.java.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class HeartServer {
	public static final int SERVICE_PORT = 414;
	/*
	 * 成员变量闪亮登场
	 */
	List<ClientThread> clients = new ArrayList<ClientThread>();

	/**
	 * 这系入口啊，向这里看齐...
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new HeartServer().start();
	}

	/**
	 * 启动服务器中...
	 * 
	 */
	public void start() {
		try {
			boolean iConnect = false;
			ServerSocket ss = new ServerSocket(SERVICE_PORT);
			iConnect = true;
			while (iConnect) {
				System.out.println("绑定服务器端口成功！");
				Socket s = ss.accept();
				ClientThread currentClient = new ClientThread(s);// 创建线程引用
				System.out.println("发现客户端！");
				clients.add(currentClient);// 把当前客户端加入集合
				new Thread(currentClient).start();
				System.out.println("客户端进程已经启动！");
			}
		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		}
	}

	/**
	 * 给每个客户端留个家（客户端的进程）
	 * 
	 */
	class ClientThread implements Runnable {
		/*
		 * 成员变量又来啦...
		 */
		private Socket s;
		private DataInputStream dis;
		private DataOutputStream dos;
		private String str;
		private boolean iConnect = false;

		/**
		 * 小构一下
		 */
		ClientThread(Socket s) {
			this.s = s;
			iConnect = true;
		}

		public void run() {
			System.out.println("run方法启动了！");
			try {

				while (iConnect) {
					System.out.println("RUN方法中的while循环启动，正在等待客户端的发送消息...");
					dis = new DataInputStream(s.getInputStream());
					str = dis.readUTF();
					System.out.println(str);
					for (int i = 0; i < clients.size(); i++) {
						System.out.println("转发消息中..." + i);
						ClientThread c = clients.get(i);
						c.sendMsg(str);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		/**
		 * 转发消息，我做主... 将送至服务器的消息发送给每个连接到的客户端
		 */
		public void sendMsg(String str) {
			try {
				System.out.println("创建输出管道！");
				dos = new DataOutputStream(this.s.getOutputStream());
				System.out.println("正在向客户端写消息！");
				dos.writeUTF(str);
				System.out.println("正在向客户端写消息成功！");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}
