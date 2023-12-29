import java.awt.Color;

import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.Timer;


public class EnemyBullet extends GameObject{
	int d;
	int speed;
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	
	
	EnemyBullet(int x, int y, int direction, int speed1) {
		super(x, y, 25, 25);
		speed = speed1 + 3;
		d = direction;
		if (needImage) {
		    loadImage ("enemybullet.png");
		}
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
		if (gotImage) {
        	g.drawImage(image, x, y, 25, 25, null);
       } else {
		g.setColor(Color.RED);
		g.fillRect(collisionBox.x, collisionBox.y, collisionBox.width, collisionBox.height);
       }
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
