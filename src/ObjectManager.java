import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {
	
	ArrayList<Projectile1> projectiles = new ArrayList<Projectile1>();
	ArrayList<enemyBullet> EBs = new ArrayList<enemyBullet>();
	Random random = new Random();

	private Player player;
	private static int score = 0;
	

	ObjectManager(Player player) {
		this.player = player;
	}

	void addProjectile(Projectile1 projectile) {
		projectiles.add(projectile);
	}
	
	void addEBs() {
		EBs.add(new enemyBullet(random.nextInt(1000), 0, 50, 50));
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
			for (int i1 = 0; i1 < EBs.size(); i1++) {
				EBs.get(i1).update();
		}
	}

	void draw(Graphics g) {
		for (int i1 = 0; i1 < projectiles.size(); i1++) {
			projectiles.get(i1).draw(g);
		}
		for (int i1 = 0; i1 < EBs.size(); i1++) {
			EBs.get(i1).draw(g);
		}
	}

	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		addEBs();
		GamePanel.bulletTime -= 100;
		if(GamePanel.bulletTime < 500) {
			GamePanel.bulletTime += 100;
		}
	}

	public static int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}