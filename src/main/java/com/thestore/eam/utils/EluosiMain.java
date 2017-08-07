package com.thestore.eam.utils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class EluosiMain extends KeyAdapter {
	JFrame frame = new JFrame("俄罗斯方块");
	EluosiUI gamePanel = new EluosiUI();

	public EluosiMain() {
		frame.addKeyListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(gamePanel);
		frame.setSize(250, 360);
		frame.setResizable(false);
		frame.setLocation(300, 200);
		frame.setVisible(true);
	}

	public static void main(String[] wwq) {
		new EluosiMain();
	}

	/**
	 * 操作按钮设定
	 */
	public void keyTyped(KeyEvent event) {
		if (event.getKeyChar() == 'a') {
			gamePanel.game.Left();

		} else if (event.getKeyChar() == 'd') {
			gamePanel.game.Right();

		} else if (event.getKeyChar() == 'w') {
			gamePanel.game.Bian();

		} else if (event.getKeyChar() == 's') {
			gamePanel.game.Down();

		} else if (event.getKeyChar() == ' ') {
			gamePanel.game.Pause();

		} else if (event.getKeyChar() == 'b') {
			gamePanel.game.ShuaXin();
		}
		gamePanel.repaint();
	}
}