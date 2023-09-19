import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {
	
	ArrayList<Projectile1> projectiles = new ArrayList<Projectile1>();
	
	Random random = new Random();

	private Player player;
	private static int score = 0;
	

	ObjectManager(Player player) {
		this.player = player;
	}

	void addProjectile(Projectile1 projectile) {
		projectiles.add(projectile);
	}
	
	void checkCollision() {
		
			for(int i1 = 0; i1 < projectiles.size(); i1++) {
				
				}
			}


	void update() {
		checkCollision();
		
			for (int i1 = 0; i1 < projectiles.size(); i1++) {
				projectiles.get(i1).update();
				if (projectiles.get(i1).y < 0) {
					//projectiles.get(i1).isActive = false;
				}
			}
		}
		

	void draw(Graphics g) {
		for (int i1 = 0; i1 < projectiles.size(); i1++) {
			projectiles.get(i1).draw(g);
		}
	}

	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public static int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}