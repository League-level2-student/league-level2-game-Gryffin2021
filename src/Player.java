import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Player extends GameObject {
	boolean up = false;
	boolean down = false;
	boolean left = false;
	boolean right = false;
	public static double xx;
	public static double yy;
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	public static boolean flawless = true;
	Player(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		speed = 10;
		if (needImage) {
		    loadImage ("spaceship.png");
		}
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
		if (gotImage) {
        	g.drawImage(image, x, y, 50, 50, null);
       } else {
		g.setColor(Color.BLUE);
        g.fillRect(x, y, 25, 25);
        g.setColor(Color.GREEN);
        g.fillRect(collisionBox.x, collisionBox.y, collisionBox.width, collisionBox.height);
       }
	}
	
	void update() {
		super.update();
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
		xx = x;
		yy = y;
	}
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}

}
