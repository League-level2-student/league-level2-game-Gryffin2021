import java.awt.*;
public class Projectile1 extends GameObject{
double xSpeed = 0;
double ySpeed = 0;
double maxSpeed = 20;
	Projectile1(int x, int y) {
		super(x, y, 5, 10);
		// TODO Auto-generated constructor stub
		speed = 10;
	}
	
	void update(){
		x += xSpeed * maxSpeed;
		y += ySpeed * maxSpeed;
	}
	
	void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x - width / 2, y - height / 2, width, height);
	}
}
