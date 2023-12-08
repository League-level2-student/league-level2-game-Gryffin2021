//cool worm
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {
	
	ArrayList<Bullet> projectiles = new ArrayList<Bullet>();
	ArrayList<EnemyBullet> EBs = new ArrayList<EnemyBullet>();
	Boss boss = new Boss(450, 250, 100, 100);
	Random random = new Random();
	Random direction = new Random();
	Random speedA = new Random();
	int currentDamage = 1;
	int d;
	int s;
	private static int score = 0;
	void addProjectile(Bullet projectile) {
		projectiles.add(projectile);
	}
	
	void addEBs() {
		int d = direction.nextInt(4);
		int s = speedA.nextInt(6);
		if(d == 0) {
			EBs.add(new EnemyBullet(random.nextInt(1000), 0, d, s));
		}else if(d == 1) {
			EBs.add(new EnemyBullet(random.nextInt(1000), 1000, d, s));
		}else if(d == 2) {
			EBs.add(new EnemyBullet(0, random.nextInt(1000), d, s));
		}else if(d == 3) {
			EBs.add(new EnemyBullet(1000, random.nextInt(1000), d, s));
		}
	}
	
	void checkCollision() {
		for (int i = 0; i < EBs.size(); i++) {
			if(GamePanel.player.collisionBox.intersects(EBs.get(i).collisionBox)) {
				GamePanel.playSound("gameover.wav");
				GamePanel.player.isActive = false;
				EBs.get(i).isActive = false;
			}
		}
			for(int i1 = 0; i1 < projectiles.size(); i1++) {
				if(boss.collisionBox.intersects(projectiles.get(i1).collisionBox)) {
					boss.health -- ;
					GamePanel.playSound("hit.wav");
					projectiles.get(i1).isActive = false;
					if(boss.health <= 0) {
						System.out.println("id");
						boss.isActive = false;
					}
				}
		}
			if(GamePanel.player.collisionBox.intersects(boss.collisionBox)) {
				GamePanel.playSound("gameover.wav");
				GamePanel.player.isActive = false;
			}
			for (int i = 0; i < EBs.size(); i++) {
				if(boss.collisionBox.intersects(EBs.get(i).collisionBox)) {
					EBs.get(i).isActive = false;
				}
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
		boss.draw(g);
	}
	
	void purgeObjects() {
		for (int i = 0; i < EBs.size(); i++) {
			if (EBs.get(i).isActive == false) {
				EBs.remove(EBs.get(i));
			}
		}
		for (int i1 = 0; i1 < projectiles.size(); i1++) {
			if (projectiles.get(i1).isActive == false) {
				projectiles.remove(projectiles.get(i1));
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