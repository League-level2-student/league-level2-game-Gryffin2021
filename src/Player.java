import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject {
	boolean up = false;
	boolean down = false;
	boolean left = false;
	boolean right = false;
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
	 if(y > 0)
        y+=speed;
    }
 public void up() {
       y-=speed;
    }
 
	void draw(Graphics g) {
		g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
        
	}
	
	void update() {
		if(up) {
			up();
		}
		if(down) {
			down();
		}
		if(left) {
			left();
		}
		if(right) {
			right();
		}
	}


}
