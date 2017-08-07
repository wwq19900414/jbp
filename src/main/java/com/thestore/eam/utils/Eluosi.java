package com.thestore.eam.utils;

import java.util.Random;

class Eluosi {
	private int rownum, colnum;
	private int i;
	public int wwq[][];
	private int time1;
	private int count_6;
	private int count_5;
	private int count_4;
	private int count_3;
	private int count_2;
	private int count_1;
	private int count;
	private int count_over;
	private int count_hang;
	private int floor;
	private int score;
	private int y2;
	private int x2;
	private boolean running;
	private boolean isBottom;
	private boolean pause;

	public int getScore() {
		return score;
	}

	public int getFloor() {
		return floor;
	}

	public boolean isRunning() {
		return running;
	}

	public boolean isPause() {
		return pause;
	}

	public int getTime() {
		return time1;
	}

	// Implementation
	public Eluosi() {
		wwq = new int[18][10];
		x2 = 0;
		y2 = 5;
		score = 0;
		floor = 0;
		count_hang = 0;
		count_over = 0;
		isBottom = false;
		running = true;
		time1 = 1000;
		i = 0;
		count = 0;
		count_1 = 0;
		count_2 = 0;
		count_3 = 0;
		count_4 = 0;
		count_5 = 0;
		count_6 = 0;
		rownum = wwq.length;
		colnum = wwq[0].length;
	}

	// ///////////////////////////////////////////////////////////////////////////
	// Eluosi message handlers
	// Operations
	public void Down() {
		int lx2, ly2;
		lx2 = x2;
		ly2 = y2;

		if (running) {
			switch (count % 7) {
			case 0:
				if (x2 < rownum - 2 && wwq[x2 + 2][y2] == 0 && wwq[x2 + 2][y2 + 1] == 0) {
					x2++;
					wwq[lx2][ly2] = 0;
					wwq[lx2 + 1][ly2] = 0;
					wwq[lx2][ly2 + 1] = 0;
					wwq[lx2 + 1][ly2 + 1] = 0;
				} else {
					isBottom = true;
					x2 = 1;
					y2 = 5;
					Random e = new Random();
					count = e.nextInt(190) % 7;

				}
				break;

			case 1:
				switch (count_1 % 2) {
				case 0:
					if (x2 < rownum - 1 && wwq[x2 + 1][y2 - 1] == 0 && wwq[x2 + 1][y2] == 0 && wwq[x2 + 1][y2 + 1] == 0
							&& wwq[x2 + 1][y2 + 2] == 0) {
						x2++;
						wwq[lx2][ly2 - 1] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2][ly2 + 1] = 0;
						wwq[lx2][ly2 + 2] = 0;
					} else {
						isBottom = true;
						x2 = 1;
						y2 = 5;
						Random e = new Random();
						count = e.nextInt(190) % 7;
					}
					break;

				case 1:
					if (x2 < rownum - 3 && wwq[x2 + 3][y2] == 0) {
						x2++;
						wwq[lx2 - 1][ly2] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2 + 1][ly2] = 0;
						wwq[lx2 + 2][ly2] = 0;
					} else {
						isBottom = true;
						x2 = 1;
						y2 = 5;
						Random e = new Random();
						count = e.nextInt(190) % 7;
					}
					break;
				}
				break;

			case 2:
				switch (count_2 % 4) {
				case 0:
					if (x2 < rownum - 2 && wwq[x2 + 2][y2 - 1] == 0 && wwq[x2 + 1][y2] == 0 && wwq[x2 + 1][y2 + 1] == 0) {
						x2++;
						wwq[lx2 + 1][ly2 - 1] = 0;
						wwq[lx2][ly2 - 1] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2][ly2 + 1] = 0;
					} else {
						isBottom = true;
						x2 = 1;
						y2 = 5;
						Random e = new Random();
						count = e.nextInt(190) % 7;
					}
					break;

				case 1:
					if (x2 < rownum - 2 && wwq[x2 + 2][y2] == 0 && wwq[x2 + 2][y2 + 1] == 0) {
						x2++;
						wwq[lx2 - 1][ly2] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2 + 1][ly2] = 0;
						wwq[lx2 + 1][ly2 + 1] = 0;
					} else {
						isBottom = true;
						x2 = 1;
						y2 = 5;
						Random e = new Random();
						count = e.nextInt(190) % 7;
					}
					break;

				case 2:
					if (x2 < rownum - 1 && wwq[x2 + 1][y2 - 1] == 0 && wwq[x2 + 1][y2] == 0 && wwq[x2 + 1][y2 + 1] == 0) {
						x2++;
						wwq[lx2][ly2 - 1] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2][ly2 + 1] = 0;
						wwq[lx2 - 1][ly2 + 1] = 0;
					} else {
						isBottom = true;
						x2 = 1;
						y2 = 5;
						Random e = new Random();
						count = e.nextInt(190) % 7;
					}
					break;

				case 3:
					if (x2 < rownum - 2 && wwq[x2 + 2][y2] == 0 && wwq[x2][y2 - 1] == 0) {
						x2++;
						wwq[lx2 - 1][ly2 - 1] = 0;
						wwq[lx2 - 1][ly2] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2 + 1][ly2] = 0;
					} else {
						isBottom = true;
						x2 = 1;
						y2 = 5;
						Random e = new Random();
						count = e.nextInt(190) % 7;
					}
					break;
				}
				break;

			case 3:
				switch (count_3 % 4) {
				case 0:
					if (x2 < rownum - 2 && wwq[x2 + 2][y2] == 0 && wwq[x2 + 1][y2 - 1] == 0 && wwq[x2 + 1][y2 + 1] == 0) {
						x2++;
						wwq[lx2][ly2 - 1] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2][ly2 + 1] = 0;
						wwq[lx2 + 1][ly2] = 0;
					} else {
						isBottom = true;
						x2 = 1;
						y2 = 5;
						Random e = new Random();
						count = e.nextInt(190) % 7;
					}
					break;

				case 1:
					if (x2 < rownum - 2 && wwq[x2 + 2][y2] == 0 && wwq[x2 + 1][y2 + 1] == 0) {
						x2++;
						wwq[lx2 - 1][ly2] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2 + 1][ly2] = 0;
						wwq[lx2][ly2 + 1] = 0;
					} else {
						isBottom = true;
						x2 = 1;
						y2 = 5;
						Random e = new Random();
						count = e.nextInt(190) % 7;
					}
					break;

				case 2:
					if (x2 < rownum - 1 && wwq[x2 + 1][y2 - 1] == 0 && wwq[x2 + 1][y2] == 0 && wwq[x2 + 1][y2 + 1] == 0) {
						x2++;
						wwq[lx2][ly2 - 1] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2][ly2 + 1] = 0;
						wwq[lx2 - 1][ly2] = 0;
					} else {
						isBottom = true;
						x2 = 1;
						y2 = 5;
						Random e = new Random();
						count = e.nextInt(190) % 7;
					}
					break;

				case 3:
					if (x2 < rownum - 2 && wwq[x2 + 1][y2 - 1] == 0 && wwq[x2 + 2][y2] == 0) {
						x2++;
						wwq[lx2 - 1][ly2] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2 + 1][ly2] = 0;
						wwq[lx2][ly2 - 1] = 0;
					} else {
						isBottom = true;
						x2 = 1;
						y2 = 5;
						Random e = new Random();
						count = e.nextInt(190) % 7;
					}
					break;
				}
				break;

			case 4:
				switch (count_4 % 4) {
				case 0:
					if (x2 < rownum - 2 && wwq[x2 + 1][y2 - 1] == 0 && wwq[x2 + 1][y2] == 0 && wwq[x2 + 2][y2 + 1] == 0) {
						x2++;
						wwq[lx2][ly2 - 1] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2][ly2 + 1] = 0;
						wwq[lx2 + 1][ly2 + 1] = 0;
					} else {
						isBottom = true;
						x2 = 1;
						y2 = 5;
						Random e = new Random();
						count = e.nextInt(190) % 7;
					}
					break;

				case 1:
					if (x2 < rownum - 2 && wwq[x2 + 2][y2 - 1] == 0 && wwq[x2 + 2][y2] == 0) {
						x2++;
						wwq[lx2 - 1][ly2] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2 + 1][ly2] = 0;
						wwq[lx2 + 1][ly2 - 1] = 0;
					} else {
						isBottom = true;
						x2 = 1;
						y2 = 5;
						Random e = new Random();
						count = e.nextInt(190) % 7;
					}
					break;

				case 2:
					if (x2 < rownum - 1 && wwq[x2 + 1][y2 - 1] == 0 && wwq[x2 + 1][y2] == 0 && wwq[x2 + 1][y2 + 1] == 0) {
						x2++;
						wwq[lx2 - 1][ly2 - 1] = 0;
						wwq[lx2][ly2 - 1] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2][ly2 + 1] = 0;
					} else {
						isBottom = true;
						x2 = 1;
						y2 = 5;
						Random e = new Random();
						count = e.nextInt(190) % 7;
					}

					break;

				case 3:
					if (x2 < rownum - 2 && wwq[x2 + 2][y2] == 0 && wwq[x2][y2 + 1] == 0) {
						x2++;
						wwq[lx2 - 1][ly2] = 0;
						wwq[lx2 - 1][ly2 + 1] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2 + 1][ly2] = 0;
					} else {
						isBottom = true;
						x2 = 1;
						y2 = 5;
						Random e = new Random();
						count = e.nextInt(190) % 7;
					}

					break;
				}
				break;

			case 5:
				switch (count_5 % 2) {
				case 0:
					if (x2 < rownum - 2 && wwq[x2 + 2][y2 - 1] == 0 && wwq[x2 + 2][y2] == 0 && wwq[x2 + 1][y2 + 1] == 0) {
						x2++;
						wwq[lx2 + 1][y2 - 1] = 0;
						wwq[lx2 + 1][ly2] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2][ly2 + 1] = 0;
					} else {
						isBottom = true;
						x2 = 1;
						y2 = 5;
						Random e = new Random();
						count = e.nextInt(190) % 7;
					}

					break;

				case 1:
					if (x2 < rownum - 2 && wwq[x2 + 1][y2] == 0 && wwq[x2 + 2][y2 + 1] == 0) {
						x2++;
						wwq[lx2 - 1][ly2] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2][ly2 + 1] = 0;
						wwq[lx2 + 1][ly2 + 1] = 0;
					} else {
						isBottom = true;
						x2 = 1;
						y2 = 5;
						Random e = new Random();
						count = e.nextInt(190) % 7;
					}

					break;
				}
				break;

			case 6:
				switch (count_6 % 2) {
				case 0:
					if (x2 < rownum - 2 && wwq[x2 + 1][y2 - 1] == 0 && wwq[x2 + 2][y2] == 0 && wwq[x2 + 2][y2 + 1] == 0) {
						x2++;
						wwq[lx2][ly2 - 1] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2 + 1][ly2] = 0;
						wwq[lx2 + 1][ly2 + 1] = 0;
					} else {
						isBottom = true;
						x2 = 1;
						y2 = 5;
						Random e = new Random();
						count = e.nextInt(190) % 7;
					}

					break;

				case 1:
					if (x2 < rownum - 2 && wwq[x2 + 1][y2] == 0 && wwq[x2 + 2][y2 - 1] == 0) {
						x2++;
						wwq[lx2 - 1][ly2] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2][ly2 - 1] = 0;
						wwq[lx2 + 1][ly2 - 1] = 0;
					} else {
						isBottom = true;
						x2 = 1;
						y2 = 5;
						Random e = new Random();
						count = e.nextInt(190) % 7;
					}

					break;
				}
				break;
			}

			if (isBottom) {
				int i, j, k, l;
				for (i = 0; i < rownum; i++) {
					for (j = 0; j < colnum; j++) {
						if (wwq[i][j] == 0)
							break;
					}
					if (j == colnum) {
						count_hang++;
						for (k = i; k > 0; k--) {
							for (l = 0; l < colnum; l++) {
								wwq[k][l] = wwq[k - 1][l];
								wwq[0][l] = 0;
							}
						}
					}
				}
				isBottom = false;
			}

			// 凑齐一整行消行得分
			switch (count_hang) {
			case 0:
				break;
			case 1:
				score += 10;
				count_hang = 0;
				break;
			case 2:
				score += 30;
				count_hang = 0;
				break;
			case 3:
				score += 50;
				count_hang = 0;
				break;
			case 4:
				score += 100;
				count_hang = 0;
				break;
			}

			if (score < 100)
				floor = 0;
			else if (score < 500)
				floor = 1;
			else if (score < 1000)
				floor = 2;
			else if (score < 2000)
				floor = 3;
			else if (score < 4000)
				floor = 4;
			else
				floor = 5;

			switch (floor) {
			case 0:
				time1 = 1000;
				break;
			case 1:
				time1 = 800;
				break;
			case 2:
				time1 = 700;
				break;
			case 3:
				time1 = 600;
				break;
			case 4:
				time1 = 500;
				break;
			case 5:
				time1 = 300;
				break;
			}

			for (int i = rownum - 1; i >= 0; i--)// 判断是否游戏结束
			{
				for (int j = 0; j < colnum; j++)
					if (wwq[i][j] > 0) {
						count_over++;
						break;
					}

			}
			if (count_over >= rownum - 1)
				running = false;
			else
				count_over = 0;
		}
	}

	public void Left() {
		int lx2, ly2;
		lx2 = x2;
		ly2 = y2;

		if (running) {

			switch (count % 7) {
			case 0:
				if (y2 > 0 && wwq[x2][y2 - 1] == 0 && wwq[x2 + 1][y2 - 1] == 0) {
					y2--;
					wwq[lx2][ly2] = 0;
					wwq[lx2 + 1][ly2] = 0;
					wwq[lx2][ly2 + 1] = 0;
					wwq[lx2 + 1][ly2 + 1] = 0;
				}
				break;

			case 1:
				switch (count_1 % 2) {
				case 0:
					if (y2 > 1 && wwq[x2][y2 - 2] == 0) {
						y2--;
						wwq[lx2][ly2 - 1] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2][ly2 + 1] = 0;
						wwq[lx2][ly2 + 2] = 0;
					}
					break;

				case 1:
					if (y2 > 0 && wwq[x2][y2 - 1] == 0 && wwq[x2 - 1][y2 - 1] == 0 && wwq[x2 + 1][y2 - 1] == 0
							&& wwq[x2 + 2][y2 - 1] == 0) {
						y2--;
						wwq[lx2 - 1][ly2] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2 + 1][ly2] = 0;
						wwq[lx2 + 2][ly2] = 0;
					}
					break;
				}
				break;

			case 2:
				switch (count_2 % 4) {
				case 0:
					if (y2 > 1 && wwq[x2][y2 - 2] == 0 && wwq[x2 + 1][y2 - 2] == 0) {
						y2--;
						wwq[lx2 + 1][ly2 - 1] = 0;
						wwq[lx2][ly2 - 1] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2][ly2 + 1] = 0;
					}
					break;

				case 1:
					if (y2 > 0 && wwq[x2 - 1][y2 - 1] == 0 && wwq[x2][y2 - 1] == 0 && wwq[x2 + 1][y2 - 1] == 0) {
						y2--;
						wwq[lx2 - 1][ly2] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2 + 1][ly2] = 0;
						wwq[lx2 + 1][ly2 + 1] = 0;
					}
					break;

				case 2:
					if (y2 > 1 && wwq[x2][y2 - 2] == 0 && wwq[x2 - 1][y2] == 0) {
						y2--;
						wwq[lx2][ly2 - 1] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2][ly2 + 1] = 0;
						wwq[lx2 - 1][ly2 + 1] = 0;
					}
					break;

				case 3:
					if (y2 > 1 && wwq[x2][y2 - 1] == 0 && wwq[x2 + 1][y2 - 1] == 0 && wwq[x2 - 1][y2 - 2] == 0) {
						y2--;
						wwq[lx2 - 1][ly2 - 1] = 0;
						wwq[lx2 - 1][ly2] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2 + 1][ly2] = 0;
					}
					break;
				}
				break;

			case 3:
				switch (count_3 % 4) {
				case 0:
					if (y2 > 1 && wwq[x2][y2 - 2] == 0 && wwq[x2 + 1][y2 - 1] == 0) {
						y2--;
						wwq[lx2][ly2 - 1] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2][ly2 + 1] = 0;
						wwq[lx2 + 1][ly2] = 0;
					}
					break;

				case 1:
					if (y2 > 0 && wwq[x2][y2 - 1] == 0 && wwq[x2 + 1][y2 - 1] == 0 && wwq[x2 - 1][y2 - 1] == 0) {
						y2--;
						wwq[lx2 - 1][ly2] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2 + 1][ly2] = 0;
						wwq[lx2][ly2 + 1] = 0;
					}
					break;

				case 2:
					if (y2 > 1 && wwq[x2][y2 - 2] == 0 && wwq[x2 - 1][y2 - 1] == 0) {
						y2--;
						wwq[lx2][ly2 - 1] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2][ly2 + 1] = 0;
						wwq[lx2 - 1][ly2] = 0;
					}
					break;

				case 3:
					if (y2 > 1 && wwq[x2][y2 - 2] == 0 && wwq[x2 - 1][y2 - 1] == 0 && wwq[x2 + 1][y2 - 1] == 0) {
						y2--;
						wwq[lx2 - 1][ly2] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2 + 1][ly2] = 0;
						wwq[lx2][ly2 - 1] = 0;
					}
					break;
				}
				break;

			case 4:
				switch (count_4 % 4) {
				case 0:
					if (y2 > 1 && wwq[x2][y2 - 2] == 0 && wwq[x2 + 1][y2] == 0) {
						y2--;
						wwq[lx2][ly2 - 1] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2][ly2 + 1] = 0;
						wwq[lx2 + 1][ly2 + 1] = 0;
					}
					break;

				case 1:
					if (y2 > 1 && wwq[x2 - 1][y2 - 1] == 0 && wwq[x2][y2 - 1] == 0 && wwq[x2 + 1][y2 - 2] == 0) {
						y2--;
						wwq[lx2 - 1][ly2] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2 + 1][ly2] = 0;
						wwq[lx2 + 1][ly2 - 1] = 0;
					}
					break;

				case 2:
					if (y2 > 1 && wwq[x2][y2 - 2] == 0 && wwq[x2 - 1][y2 - 2] == 0) {
						y2--;
						wwq[lx2 - 1][ly2 - 1] = 0;
						wwq[lx2][ly2 - 1] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2][ly2 + 1] = 0;
					}
					break;

				case 3:
					if (y2 > 0 && wwq[x2][y2 - 1] == 0 && wwq[x2 + 1][y2 - 1] == 0 && wwq[x2 - 1][y2 - 1] == 0) {
						y2--;
						wwq[lx2 - 1][ly2] = 0;
						wwq[lx2 - 1][ly2 + 1] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2 + 1][ly2] = 0;
					}
					break;
				}
				break;

			case 5:
				switch (count_5 % 2) {
				case 0:
					if (y2 > 1 && wwq[x2][y2 - 1] == 0 && wwq[x2 + 1][y2 - 2] == 0) {
						y2--;
						wwq[lx2 + 1][y2 - 1] = 0;
						wwq[lx2 + 1][ly2] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2][ly2 + 1] = 0;
					}
					break;

				case 1:
					if (y2 > 0 && wwq[x2][y2 - 1] == 0 && wwq[x2 - 1][y2 - 1] == 0 && wwq[x2 + 1][y2] == 0) {
						y2--;
						wwq[lx2 - 1][ly2] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2][ly2 + 1] = 0;
						wwq[lx2 + 1][ly2 + 1] = 0;
					}
					break;
				}
				break;

			case 6:
				switch (count_6 % 2) {
				case 0:
					if (y2 > 1 && wwq[x2][y2 - 2] == 0 && wwq[x2 + 1][y2 - 1] == 0) {
						y2--;
						wwq[lx2][ly2 - 1] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2 + 1][ly2] = 0;
						wwq[lx2 + 1][ly2 + 1] = 0;
					}
					break;

				case 1:
					if (y2 > 1 && wwq[x2][y2 - 2] == 0 && wwq[x2 + 1][y2 - 2] == 0 && wwq[x2 - 1][y2 - 1] == 0) {
						y2--;
						wwq[lx2 - 1][ly2] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2][ly2 - 1] = 0;
						wwq[lx2 + 1][ly2 - 1] = 0;
					}
					break;
				}
				break;
			}
		}
	}

	public void Right() {
		int lx2, ly2;
		lx2 = x2;
		ly2 = y2;

		if (running) {

			switch (count % 7) {
			case 0:
				if (y2 < colnum - 2 && wwq[x2][y2 + 2] == 0 && wwq[x2 + 1][y2 + 2] == 0) {
					y2++;
					wwq[lx2][ly2] = 0;
					wwq[lx2 + 1][ly2] = 0;
					wwq[lx2][ly2 + 1] = 0;
					wwq[lx2 + 1][ly2 + 1] = 0;
				}
				break;

			case 1:
				switch (count_1 % 2) {
				case 0:
					if (y2 < colnum - 3 && wwq[x2][y2 + 3] == 0) {
						y2++;
						wwq[lx2][ly2 - 1] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2][ly2 + 1] = 0;
						wwq[lx2][ly2 + 2] = 0;
					}
					break;

				case 1:
					if (y2 < colnum - 1 && wwq[x2 - 1][y2 + 1] == 0 && wwq[x2][y2 + 1] == 0 && wwq[x2 + 1][y2 + 1] == 0
							&& wwq[x2 + 2][y2 + 1] == 0) {
						y2++;
						wwq[lx2 - 1][ly2] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2 + 1][ly2] = 0;
						wwq[lx2 + 2][ly2] = 0;
					}
					break;
				}
				break;

			case 2:
				switch (count_2 % 4) {
				case 0:
					if (y2 < colnum - 2 && wwq[x2][y2 + 2] == 0 && wwq[x2 + 1][y2] == 0) {
						y2++;
						wwq[lx2 + 1][ly2 - 1] = 0;
						wwq[lx2][ly2 - 1] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2][ly2 + 1] = 0;
					}
					break;

				case 1:
					if (y2 < colnum - 2 && wwq[x2 - 1][y2 + 1] == 0 && wwq[x2][y2 + 1] == 0 && wwq[x2 + 1][y2 + 2] == 0) {
						y2++;
						wwq[lx2 - 1][ly2] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2 + 1][ly2] = 0;
						wwq[lx2 + 1][ly2 + 1] = 0;
					}
					break;

				case 2:
					if (y2 < colnum - 2 && wwq[x2 - 1][y2 + 2] == 0 && wwq[x2][y2 + 2] == 0) {
						y2++;
						wwq[lx2][ly2 - 1] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2][ly2 + 1] = 0;
						wwq[lx2 - 1][ly2 + 1] = 0;
					}
					break;

				case 3:
					if (y2 < colnum - 1 && wwq[x2 - 1][y2 + 1] == 0 && wwq[x2][y2 + 1] == 0 && wwq[x2 + 1][y2 + 1] == 0) {
						y2++;
						wwq[lx2 - 1][ly2 - 1] = 0;
						wwq[lx2 - 1][ly2] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2 + 1][ly2] = 0;
					}
					break;
				}
				break;

			case 3:
				switch (count_3 % 4) {
				case 0:
					if (y2 < colnum - 2 && wwq[x2][y2 + 2] == 0 && wwq[x2 + 1][y2 + 1] == 0) {
						y2++;
						wwq[lx2][ly2 - 1] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2][ly2 + 1] = 0;
						wwq[lx2 + 1][ly2] = 0;
					}
					break;

				case 1:
					if (y2 < colnum - 2 && wwq[x2 - 1][y2 + 1] == 0 && wwq[x2][y2 + 2] == 0 && wwq[x2 + 1][y2 + 1] == 0) {
						y2++;
						wwq[lx2 - 1][ly2] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2 + 1][ly2] = 0;
						wwq[lx2][ly2 + 1] = 0;
					}
					break;

				case 2:
					if (y2 < colnum - 2 && wwq[x2][y2 + 2] == 0 && wwq[x2 - 1][y2 + 1] == 0) {
						y2++;
						wwq[lx2][ly2 - 1] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2][ly2 + 1] = 0;
						wwq[lx2 - 1][ly2] = 0;
					}
					break;

				case 3:
					if (y2 < colnum - 1 && wwq[x2 - 1][y2 + 1] == 0 && wwq[x2][y2 + 1] == 0 && wwq[x2 + 1][y2 + 1] == 0) {
						y2++;
						wwq[lx2 - 1][ly2] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2 + 1][ly2] = 0;
						wwq[lx2][ly2 - 1] = 0;
					}
					break;
				}
				break;

			case 4:
				switch (count_4 % 4) {
				case 0:
					if (y2 < colnum - 2 && wwq[x2][y2 + 2] == 0 && wwq[x2 + 1][y2 + 2] == 0) {
						y2++;
						wwq[lx2][ly2 - 1] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2][ly2 + 1] = 0;
						wwq[lx2 + 1][ly2 + 1] = 0;
					}
					break;

				case 1:
					if (y2 < colnum - 1 && wwq[x2 - 1][y2 + 1] == 0 && wwq[x2][y2 + 1] == 0 && wwq[x2 + 1][y2 + 1] == 0) {
						y2++;
						wwq[lx2 - 1][ly2] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2 + 1][ly2] = 0;
						wwq[lx2 + 1][ly2 - 1] = 0;
					}
					break;

				case 2:
					if (y2 < colnum - 2 && wwq[x2 - 1][y2] == 0 && wwq[x2][y2 + 2] == 0) {
						y2++;
						wwq[lx2 - 1][ly2 - 1] = 0;
						wwq[lx2][ly2 - 1] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2][ly2 + 1] = 0;
					}
					break;

				case 3:
					if (y2 < colnum - 2 && wwq[x2 - 1][y2 + 2] == 0 && wwq[x2][y2 + 1] == 0 && wwq[x2 + 1][y2 + 1] == 0) {
						y2++;
						wwq[lx2 - 1][ly2] = 0;
						wwq[lx2 - 1][ly2 + 1] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2 + 1][ly2] = 0;
					}
					break;
				}
				break;

			case 5:
				switch (count_5 % 2) {
				case 0:
					if (y2 < colnum - 2 && wwq[x2][y2 + 2] == 0 && wwq[x2 + 1][y2 + 1] == 0) {
						y2++;
						wwq[lx2 + 1][ly2 - 1] = 0;
						wwq[lx2 + 1][ly2] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2][ly2 + 1] = 0;
					}
					break;

				case 1:
					if (y2 < colnum - 2 && wwq[x2 - 1][y2 + 1] == 0 && wwq[x2][y2 + 2] == 0 && wwq[x2 + 1][y2 + 2] == 0) {
						y2++;
						wwq[lx2 - 1][ly2] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2][ly2 + 1] = 0;
						wwq[lx2 + 1][ly2 + 1] = 0;
					}
					break;
				}
				break;

			case 6:
				switch (count_6 % 2) {
				case 0:
					if (y2 < colnum - 2 && wwq[x2][y2 + 1] == 0 && wwq[x2 + 1][y2 + 2] == 0) {
						y2++;
						wwq[lx2][ly2 - 1] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2 + 1][ly2] = 0;
						wwq[lx2 + 1][ly2 + 1] = 0;
					}
					break;

				case 1:
					if (y2 < colnum - 1 && wwq[x2 - 1][y2 + 1] == 0 && wwq[x2][y2 + 1] == 0 && wwq[x2 + 1][y2] == 0) {
						y2++;
						wwq[lx2 - 1][ly2] = 0;
						wwq[lx2][ly2] = 0;
						wwq[lx2][ly2 - 1] = 0;
						wwq[lx2 + 1][ly2 - 1] = 0;
					}
					break;
				}
				break;
			}
		}
	}

	public void Bian() {
		if (running) {

			switch (count % 7) {
			case 0:
				// if(y2>0 && y2<8 && x2<13 && wwq[x2][y2-1]!=1 &&
				// wwq[x2][y2+2]!=1){
				// count++;
				// wwq[x2][y2]=0;
				// wwq[x2+1][y2]=0;
				// wwq[x2][y2+1]=0;
				// wwq[x2+1][y2+1]=0;
				// }
				break;

			case 1:
				switch (count_1 % 2) {
				case 0:
					if (x2 < rownum - 2 && wwq[x2 - 1][y2] == 0 && wwq[x2 + 1][y2] == 0 && wwq[x2 + 2][y2] == 0) {
						count_1++;
						wwq[x2][y2 - 1] = 0;
						wwq[x2][y2] = 0;
						wwq[x2][y2 + 1] = 0;
						wwq[x2][y2 + 2] = 0;
					}
					break;
				case 1:
					if (y2 > 0 && y2 < colnum - 2 && wwq[x2][y2 - 1] == 0 && wwq[x2][y2 + 1] == 0
							&& wwq[x2][y2 + 2] == 0) {
						count_1++;
						wwq[x2 - 1][y2] = 0;
						wwq[x2][y2] = 0;
						wwq[x2 + 1][y2] = 0;
						wwq[x2 + 2][y2] = 0;
					}
					break;
				}
				break;

			case 2:
				switch (count_2 % 4) {
				case 0:
					if (wwq[x2 - 1][y2] == 0 && wwq[x2 + 1][y2] == 0 && wwq[x2 + 1][y2 + 1] == 0) {
						count_2++;
						wwq[x2 + 1][y2 - 1] = 0;
						wwq[x2][y2 - 1] = 0;
						wwq[x2][y2] = 0;
						wwq[x2][y2 + 1] = 0;
					}
					break;

				case 1:
					if (y2 > 0 && wwq[x2][y2 - 1] == 0 && wwq[x2][y2 + 1] == 0 && wwq[x2 - 1][y2 + 1] == 0) {
						count_2++;
						wwq[x2 - 1][y2] = 0;
						wwq[x2][y2] = 0;
						wwq[x2 + 1][y2] = 0;
						wwq[x2 + 1][y2 + 1] = 0;
					}
					break;

				case 2:
					if (x2 < rownum - 1 && wwq[x2 - 1][y2 - 1] == 0 && wwq[x2 - 1][y2] == 0 && wwq[x2 + 1][y2] == 0) {
						count_2++;
						wwq[x2][y2 - 1] = 0;
						wwq[x2][y2] = 0;
						wwq[x2][y2 + 1] = 0;
						wwq[x2 - 1][y2 + 1] = 0;
					}
					break;

				case 3:
					if (y2 < colnum - 1 && wwq[x2 + 1][y2 - 1] == 0 && wwq[x2][y2 - 1] == 0 && wwq[x2][y2 + 1] == 0) {
						count_2++;
						wwq[x2 - 1][y2 - 1] = 0;
						wwq[x2 - 1][y2] = 0;
						wwq[x2][y2] = 0;
						wwq[x2 + 1][y2] = 0;
					}
					break;
				}
				break;

			case 3:
				switch (count_3 % 4) {
				case 0:
					if (wwq[x2 - 1][y2] == 0) {
						count_3++;
						wwq[x2][y2 - 1] = 0;
						wwq[x2][y2] = 0;
						wwq[x2][y2 + 1] = 0;
						wwq[x2 + 1][y2] = 0;
					}
					break;

				case 1:
					if (y2 > 0 && wwq[x2][y2 - 1] == 0) {
						count_3++;
						wwq[x2 - 1][y2] = 0;
						wwq[x2][y2] = 0;
						wwq[x2 + 1][y2] = 0;
						wwq[x2][y2 + 1] = 0;
					}
					break;

				case 2:
					if (x2 < rownum - 1 && wwq[x2 + 1][y2] == 0) {
						count_3++;
						wwq[x2][y2 - 1] = 0;
						wwq[x2][y2] = 0;
						wwq[x2][y2 + 1] = 0;
						wwq[x2 - 1][y2] = 0;
					}
					break;

				case 3:
					if (y2 < colnum - 1 && wwq[x2][y2 + 1] == 0) {
						count_3++;
						wwq[x2 - 1][y2] = 0;
						wwq[x2][y2] = 0;
						wwq[x2 + 1][y2] = 0;
						wwq[x2][y2 - 1] = 0;
					}
					break;
				}
				break;

			case 4:
				switch (count_4 % 4) {
				case 0:
					if (wwq[x2 - 1][y2] == 0 && wwq[x2 + 1][y2] == 0 && wwq[x2 + 1][y2 - 1] == 0) {
						count_4++;
						wwq[x2][y2 - 1] = 0;
						wwq[x2][y2] = 0;
						wwq[x2][y2 + 1] = 0;
						wwq[x2 + 1][y2 + 1] = 0;
					}
					break;

				case 1:
					if (y2 < colnum - 1 && wwq[x2 - 1][y2 - 1] == 0 && wwq[x2][y2 - 1] == 0 && wwq[x2][y2 + 1] == 0) {
						count_4++;
						wwq[x2 - 1][y2] = 0;
						wwq[x2][y2] = 0;
						wwq[x2 + 1][y2] = 0;
						wwq[x2 + 1][y2 - 1] = 0;
					}
					break;

				case 2:
					if (x2 < rownum - 1 && wwq[x2 - 1][y2] == 0 && wwq[x2 - 1][y2 + 1] == 0 && wwq[x2 + 1][y2] == 0) {
						count_4++;
						wwq[x2 - 1][y2 - 1] = 0;
						wwq[x2][y2 - 1] = 0;
						wwq[x2][y2] = 0;
						wwq[x2][y2 + 1] = 0;
					}
					break;

				case 3:
					if (y2 > 0 && wwq[x2][y2 - 1] == 0 && wwq[x2][y2 + 1] == 0 && wwq[x2 + 1][y2 + 1] == 0) {
						count_4++;
						wwq[x2 - 1][y2] = 0;
						wwq[x2 - 1][y2 + 1] = 0;
						wwq[x2][y2] = 0;
						wwq[x2 + 1][y2] = 0;
					}
					break;
				}
				break;

			case 5:
				switch (count_5 % 2) {
				case 0:
					if (wwq[x2 - 1][y2] == 0 && wwq[x2 + 1][y2 + 1] == 0) {
						count_5++;
						wwq[x2 + 1][y2 - 1] = 0;
						wwq[x2 + 1][y2] = 0;
						wwq[x2][y2] = 0;
						wwq[x2][y2 + 1] = 0;
					}
					break;

				case 1:
					if (y2 > 0 && wwq[x2 + 1][y2 - 1] == 0 && wwq[x2 + 1][y2] == 0) {
						count_5++;
						wwq[x2 - 1][y2] = 0;
						wwq[x2][y2] = 0;
						wwq[x2][y2 + 1] = 0;
						wwq[x2 + 1][y2 + 1] = 0;
					}
					break;
				}
				break;

			case 6:
				switch (count_6 % 2) {
				case 0:
					if (wwq[x2 - 1][y2] == 0 && wwq[x2 + 1][y2 - 1] == 0) {
						count_6++;
						wwq[x2][y2 - 1] = 0;
						wwq[x2][y2] = 0;
						wwq[x2 + 1][y2] = 0;
						wwq[x2 + 1][y2 + 1] = 0;
					}
					break;

				case 1:
					if (y2 < colnum - 1 && wwq[x2 + 1][y2] == 0 && wwq[x2 + 1][y2 + 1] == 0) {
						count_6++;
						wwq[x2 - 1][y2] = 0;
						wwq[x2][y2] = 0;
						wwq[x2][y2 - 1] = 0;
						wwq[x2 + 1][y2 - 1] = 0;
					}
					break;
				}
				break;
			}

		}
	}

	public void Pause() {
		i++;
		if (i % 2 == 1) {
			running = false;
			pause = true;
		} else {
			running = true;
			pause = false;
		}
	}

	/**
	 * 标记出当前方块
	 */
	public void drawFangKuai() {
		switch (count % 7) {
		case 0:
			wwq[x2][y2] = 7;
			wwq[x2 + 1][y2] = 7;
			wwq[x2][y2 + 1] = 7;
			wwq[x2 + 1][y2 + 1] = 7;
			break;

		case 1:
			switch (count_1 % 2) {
			case 0:
				wwq[x2][y2 - 1] = 1;
				wwq[x2][y2] = 1;
				wwq[x2][y2 + 1] = 1;
				wwq[x2][y2 + 2] = 1;
				break;
			case 1:
				wwq[x2 - 1][y2] = 1;
				wwq[x2][y2] = 1;
				wwq[x2 + 1][y2] = 1;
				wwq[x2 + 2][y2] = 1;
				break;
			}
			break;

		case 2:
			switch (count_2 % 4) {
			case 0:
				wwq[x2 + 1][y2 - 1] = 2;
				wwq[x2][y2 - 1] = 2;
				wwq[x2][y2] = 2;
				wwq[x2][y2 + 1] = 2;
				break;

			case 1:
				wwq[x2 - 1][y2] = 2;
				wwq[x2][y2] = 2;
				wwq[x2 + 1][y2] = 2;
				wwq[x2 + 1][y2 + 1] = 2;
				break;

			case 2:
				wwq[x2][y2 - 1] = 2;
				wwq[x2][y2] = 2;
				wwq[x2][y2 + 1] = 2;
				wwq[x2 - 1][y2 + 1] = 2;
				break;

			case 3:
				wwq[x2 - 1][y2 - 1] = 2;
				wwq[x2 - 1][y2] = 2;
				wwq[x2][y2] = 2;
				wwq[x2 + 1][y2] = 2;
				break;
			}
			break;

		case 3:
			switch (count_3 % 4) {
			case 0:
				wwq[x2][y2 - 1] = 3;
				wwq[x2][y2] = 3;
				wwq[x2][y2 + 1] = 3;
				wwq[x2 + 1][y2] = 3;
				break;

			case 1:
				wwq[x2 - 1][y2] = 3;
				wwq[x2][y2] = 3;
				wwq[x2 + 1][y2] = 3;
				wwq[x2][y2 + 1] = 3;
				break;

			case 2:
				wwq[x2][y2 - 1] = 3;
				wwq[x2][y2] = 3;
				wwq[x2][y2 + 1] = 3;
				wwq[x2 - 1][y2] = 3;
				break;

			case 3:
				wwq[x2 - 1][y2] = 3;
				wwq[x2][y2] = 3;
				wwq[x2 + 1][y2] = 3;
				wwq[x2][y2 - 1] = 3;
				break;
			}
			break;

		case 4:
			switch (count_4 % 4) {
			case 0:
				wwq[x2][y2 - 1] = 4;
				wwq[x2][y2] = 4;
				wwq[x2][y2 + 1] = 4;
				wwq[x2 + 1][y2 + 1] = 4;
				break;

			case 1:
				wwq[x2 - 1][y2] = 4;
				wwq[x2][y2] = 4;
				wwq[x2 + 1][y2] = 4;
				wwq[x2 + 1][y2 - 1] = 4;
				break;

			case 2:
				wwq[x2 - 1][y2 - 1] = 4;
				wwq[x2][y2 - 1] = 4;
				wwq[x2][y2] = 4;
				wwq[x2][y2 + 1] = 4;
				break;

			case 3:
				wwq[x2 - 1][y2] = 4;
				wwq[x2 - 1][y2 + 1] = 4;
				wwq[x2][y2] = 4;
				wwq[x2 + 1][y2] = 4;
				break;
			}
			break;

		case 5:
			switch (count_5 % 2) {
			case 0:
				wwq[x2 + 1][y2 - 1] = 5;
				wwq[x2 + 1][y2] = 5;
				wwq[x2][y2] = 5;
				wwq[x2][y2 + 1] = 5;
				break;

			case 1:
				wwq[x2 - 1][y2] = 5;
				wwq[x2][y2] = 5;
				wwq[x2][y2 + 1] = 5;
				wwq[x2 + 1][y2 + 1] = 5;
				break;
			}
			break;

		case 6:
			switch (count_6 % 2) {
			case 0:
				wwq[x2][y2 - 1] = 6;
				wwq[x2][y2] = 6;
				wwq[x2 + 1][y2] = 6;
				wwq[x2 + 1][y2 + 1] = 6;
				break;

			case 1:
				wwq[x2 - 1][y2] = 6;
				wwq[x2][y2] = 6;
				wwq[x2][y2 - 1] = 6;
				wwq[x2 + 1][y2 - 1] = 6;
				break;
			}
			break;
		}
	}

	public void ShuaXin() {
		x2 = 0;
		y2 = 5;
		count = 0;
		score = 0;
		floor = 0;
		time1 = 1000;
		running = true;
		count_over = 0;
		count_hang = 0;

		for (int i = 0; i < rownum; i++)
			for (int j = 0; j < colnum; j++)
				wwq[i][j] = 0;
	}
}