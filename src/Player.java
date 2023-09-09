import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject {
int speed;
	Player(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		speed = 10;
	}
	
	public void right() {
        x+=speed;
    }
 public void left() {
        x-=speed;
    }
 public void down() {
        y+=speed;
    }
 public void up() {
       x-=speed;
    }
 
	void draw(Graphics g) {
		g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
	}

}
