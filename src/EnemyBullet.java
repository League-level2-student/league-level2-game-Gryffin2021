import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class EnemyBullet extends GameObject{
	int d;
	int speed;
	
	
	EnemyBullet(int x, int y, int direction, int speed1) {
		super(x, y, 15, 15);
		speed = speed1 + 3;
		d = direction;
	}
	
	void update() {
		if(d == 0) {
		y+=speed;
		}else if(d == 1) {
			y-=speed;
		}else if(d == 2) {
			x+=speed;
		}else if(d == 3) {
			x-=speed;
		}
		super.update();
	}
	
	void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(collisionBox.x, collisionBox.y, collisionBox.width, collisionBox.height);
	}
}
