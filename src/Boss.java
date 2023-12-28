import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.Timer;

public class Boss extends GameObject implements ActionListener{
Timer move;
int close1;
Random ran;
int bossWidth = 100;
int bossHeight = 100;
int health = 5;
Font titleFont;
public static BufferedImage image;
public static boolean needImage = true;
public static boolean gotImage = false;
public static boolean flawless = true;
	public Boss(int x, int y, int width, int height) {
		super(x, y, width, height);
		width = 100;
		height = 100;
		bossWidth = width;
		bossHeight = height;
		// TODO Auto-generated constructor stub
		move = new Timer(5000, this);
		move.start();
		ran = new Random();
		titleFont = new Font("Arial", Font.PLAIN, 48);
		if (needImage) {
		    loadImage ("bossship.png");
		}
	}
	
	void draw(Graphics g) {
		if (gotImage) {
        	g.drawImage(image, x, y, 100, 100, null);
       } else {
		g.setColor(Color.RED);
		g.drawRect(x, y, width, height);
		g.setColor(Color.GREEN);
        g.fillRect(collisionBox.x, collisionBox.y, collisionBox.width, collisionBox.height);
        g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString(Integer.toString(health), 25, 200);
       }
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
		if(isActive == true) {
		int rx = ran.nextInt(1000);
		int ry = ran.nextInt(1000);
		double dist = Math.sqrt(Math.pow(rx - Player.xx, 2) - Math.pow(ry - Player.yy, 2));
		if(dist > 150) {
			x = rx;
			y = ry;
			collisionBox.x = x;
			collisionBox.y = y;
		}else {
			moveBoss();
		}
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
