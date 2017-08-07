package com.thestore.eam.utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class EluosiUI extends JPanel implements ActionListener {
	private static final long serialVersionUID = -6197423797849098613L;
	Eluosi game;
	JLabel score = new JLabel("得分");
	JLabel floor = new JLabel("等级");
	Timer time;

	public EluosiUI() {
		this.setLayout(new BorderLayout());
		JPanel npanel = new JPanel(new GridLayout(1, 2));

		score.setFont(new Font("Serif", Font.BOLD, 14));
		floor.setFont(new Font("Serif", Font.BOLD, 14));

		npanel.add(score);
		npanel.add(floor);

		this.add(npanel, BorderLayout.NORTH);
		this.setBackground(Color.black);
		game = new Eluosi();
		time = new Timer(1000, this);
		time.start();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.white);
		ImageIcon image = new ImageIcon("wwq0.jpg");
		image.paintIcon(this, g, -28, 0);
		// image.paintIcon(this,g,50,50);

		if (game.getScore() >= 100 && game.getScore() <= 200) {
			g.setColor(Color.pink);

			g.drawOval(150, 90, 14, 14);
			g.drawOval(170, 70, 20, 20);
			g.drawRoundRect(190, 50, 50, 50, 20, 20);
			g.drawString("加油!", 192, 72);
			g.drawString("看好你哦！！！", 192, 90);
		} else if (game.getScore() >= 500 && game.getScore() <= 700) {
			g.setColor(Color.red);
			g.drawOval(150, 90, 14, 14);
			g.drawOval(170, 70, 20, 20);
			g.drawRoundRect(190, 50, 50, 50, 20, 20);
			g.drawString("真棒!", 192, 72);
			g.drawString("你太帅了", 192, 90);

		} else if (game.getScore() >= 1000 && game.getScore() <= 1300) {
			g.setColor(Color.green);
			g.drawOval(150, 90, 14, 14);
			g.drawOval(170, 70, 20, 20);
			g.drawRoundRect(190, 50, 50, 50, 20, 20);
			g.drawString("太棒了!", 192, 72);
			g.drawString("你这么厉害，你爸妈知道吗？", 192, 90);

		} else if (game.getScore() >= 2000 && game.getScore() <= 2400) {
			g.setColor(Color.blue);
			g.drawOval(150, 90, 14, 14);
			g.drawOval(170, 70, 20, 20);
			g.drawRoundRect(190, 50, 50, 50, 20, 20);
			g.drawString("哦，MyGod!", 192, 72);
			g.drawString("不敢想象啊！！！", 192, 90);

		} else if (game.getScore() >= 4000 && game.getScore() <= 4500) {
			g.setColor(Color.yellow);
			g.drawOval(150, 90, 14, 14);
			g.drawOval(170, 70, 20, 20);
			g.drawRoundRect(190, 50, 50, 50, 20, 20);
			g.drawString("晕了，晕了", 192, 72);
			g.drawString("不是人啊！", 192, 90);

		}

		time.setDelay(game.getTime());
		if (game.isRunning()) {
			game.drawFangKuai();
			drawMap(game.wwq, g);
		} else {
			if (game.isPause()) {
				g.setFont(new Font("Serif", Font.BOLD, 18));
				g.drawString("休息下，喝杯水!!!", 56, 180);
			} else {
				g.setFont(new Font("Serif", Font.BOLD, 18));
				g.drawString("继续战斗!!!", 56, 180);
			}
		}
	}

	/**
	 * 勾画游戏区域
	 */
	public void drawMap(int[][] wwq, Graphics g) {
		int rownum = wwq.length;
		int colnum = wwq[0].length;
		g.drawLine(50, 50, 50 + colnum * 14, 50);
		g.drawLine(50, 50 + rownum * 14, 50 + colnum * 14, 50 + rownum * 14);
		g.drawLine(50, 50, 50, 50 + rownum * 14);
		g.drawLine(50 + colnum * 14, 50, 50 + colnum * 14, 50 + rownum * 14);

		for (int i = 0; i < rownum; i++) {
			for (int j = 0; j < colnum; j++) {
				switch (wwq[i][j]) {
				case 1:
					g.setColor(Color.yellow);
					g.fillRect(50 + j * 14 + 1, 50 + i * 14 + 1, 12, 12);
					break;
				case 2:
					g.setColor(Color.blue);
					g.fillRect(50 + j * 14 + 1, 50 + i * 14 + 1, 12, 12);
					break;
				case 3:
					g.setColor(Color.cyan);
					g.fillRect(50 + j * 14 + 1, 50 + i * 14 + 1, 12, 12);
					break;
				case 4:
					g.setColor(Color.gray);
					g.fillRect(50 + j * 14 + 1, 50 + i * 14 + 1, 12, 12);
					break;
				case 5:
					g.setColor(Color.green);
					g.fillRect(50 + j * 14 + 1, 50 + i * 14 + 1, 12, 12);
					break;
				case 6:
					g.setColor(Color.magenta);
					g.fillRect(50 + j * 14 + 1, 50 + i * 14 + 1, 12, 12);
					break;
				case 7:
					g.setColor(Color.red);
					g.fillRect(50 + j * 14 + 1, 50 + i * 14 + 1, 12, 12);
					break;
				}

			}
		}
	}

	/**
	 * 定时器方法
	 */
	public void actionPerformed(ActionEvent event) {
		game.Down();
		score.setText("得分:" + game.getScore());
		floor.setText("等级:" + game.getFloor());
		repaint();

	}
}