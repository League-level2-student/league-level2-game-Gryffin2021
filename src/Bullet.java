import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
public class Bullet extends GameObject{
double xSpeed = 0;
double ySpeed = 0;
double maxSpeed = 20;
public static BufferedImage image;
public static boolean needImage = true;
public static boolean gotImage = false;
	Bullet(int x, int y) {
		super(x, y, 20, 20);
		// TODO Auto-generated constructor stub
		speed = 10;
		if (needImage) {
		    loadImage ("playerbullet.png");
		}
	}
	
	void update(){
		super.update();
		x += xSpeed * maxSpeed;
		y += ySpeed * maxSpeed;
	}
	
	void draw(Graphics g) {
		if (gotImage) {
        	g.drawImage(image, x, y, 20, 20, null);
       } else {
		g.setColor(Color.GREEN);
		g.fillRect(x - width / 2, y - height / 2, width, height);
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
