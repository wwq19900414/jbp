package com.wuwenqi.java.observer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * 在JDK中，观察者模式也得到了普遍的应用。一个典型的应用便是Swing框架的JButton的事件处理机制
 * 
 * @author wuwenqi
 * 
 */
public class BtnListener implements ActionListener {

	/**
	 * 按钮被单击时，执行的具体逻辑
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("click");
	}

	public static void main(String[] wwq) {
		JFrame jf = new JFrame();
		JButton btn = new JButton("Click ME");
		btn.addActionListener(new BtnListener());

		jf.add(btn);
		jf.pack();
		jf.setVisible(true);
	}

}
