import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

public class Boss extends GameObject implements ActionListener{
Timer move;
int health = 100;
int close1;
Random ran;
int bossWidth = 100;
int bossHeight = 100;
	public Boss(int x, int y, int width, int height) {
		super(x, y, width, height);
		bossWidth = width;
		bossHeight = height;
		// TODO Auto-generated constructor stub
		move = new Timer(5000, this);
		move.start();
		ran = new Random();
	}
	
	void draw(Graphics g) {
		g.setColor(Color.RED);
		g.drawRect(x, y, width, height);
	}
	
	void update() {
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		moveBoss();
		move.restart();
	}
	public void moveBoss() {
		int rx = ran.nextInt(1000);
		int ry = ran.nextInt(1000);
		double dist = Math.sqrt(Math.pow(rx - Player.xx, 2) - Math.pow(ry - Player.yy, 2));
		if(dist > 150) {
			x = rx;
			y = ry;
		}else {
			moveBoss();
		}
	}
}
