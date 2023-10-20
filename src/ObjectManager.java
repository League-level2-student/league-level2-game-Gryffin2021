import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {
	
	ArrayList<Projectile1> projectiles = new ArrayList<Projectile1>();
	ArrayList<EnemyBullet> EBs = new ArrayList<EnemyBullet>();
	Random random = new Random();
	Random direction = new Random();
	Random speedA = new Random();
	int d;
	int s;
	private Player player;
	private static int score = 0;
	

	ObjectManager(Player player) {
		this.player = player;
	}

	void addProjectile(Projectile1 projectile) {
		projectiles.add(projectile);
	}
	
	void addEBs() {
		int d = direction.nextInt(4);
		int s = speedA.nextInt(6);
		if(d == 0) {
			EBs.add(new EnemyBullet(random.nextInt(1000), 0, 50, 50, d, s));
		}else if(d == 1) {
			EBs.add(new EnemyBullet(random.nextInt(1000), 1000, 50, 50, d, s));
		}else if(d == 2) {
			EBs.add(new EnemyBullet(0, random.nextInt(1000), 50, 50, d, s));
		}else if(d == 3) {
			EBs.add(new EnemyBullet(1000, random.nextInt(1000), 50, 50, d, s));
		}
	}
	
	void checkCollision() {
		for (int i = 0; i < EBs.size(); i++) {
			if(player.collisionBox.intersects(EBs.get(i).collisionBox)) {
				player.isActive = false;
				EBs.get(i).isActive = false;
			}
		}
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
			purgeObjects();
	}

	void draw(Graphics g) {
		for (int i1 = 0; i1 < projectiles.size(); i1++) {
			projectiles.get(i1).draw(g);
		}
		for (int i1 = 0; i1 < EBs.size(); i1++) {
			EBs.get(i1).draw(g);
		}
	}
	
	void purgeObjects() {
		for (int i = 0; i < EBs.size(); i++) {
			if (EBs.get(i).isActive == false) {
				EBs.remove(EBs.get(i));
			}

			for (int i1 = 0; i1 < projectiles.size(); i1++) {
				if (projectiles.get(i1).isActive == false) {
					projectiles.remove(projectiles.get(i1));
				}
			}
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