import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class enemyBullet extends GameObject{
	
	
	enemyBullet(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 5;
	}
	
	void update() {
		y+=speed;
		super.update();
	}
	
	void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, 5, 10);
	}
}
