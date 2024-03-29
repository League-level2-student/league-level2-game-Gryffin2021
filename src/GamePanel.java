import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//check bullet spawn
public class GamePanel extends JPanel implements ActionListener, KeyListener, MouseListener {
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	final int PAUSE = 3;
	final int GUIDE = 4;
	final int CREDITS = 5;
	final int WIN = 6;
	int currentState = MENU;
	int currentBoundsX;
	int currentBoundsY;
	Font titleFont;
	Font smallerFont;
	Timer bulletSpawn;
	Timer frameDraw;
	Timer score1;
	static Player player = new Player(475, 850, 25, 25);
	ObjectManager om = new ObjectManager();
	static int bulletTime = 200;
	int sscore = 0;
	String ssscore = ("" + sscore);
	Timer deathwait;
	Timer winwait;
	Timer soundwait;
	Random ran;
	int billy;
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	public static boolean flawless = true;
	BufferedImage bg1;
	BufferedImage bg2;
	BufferedImage bg3;
	BufferedImage deadplayer;
	BufferedImage deadboss;
	BufferedImage menu;
	BufferedImage pause;
	BufferedImage win;
	BufferedImage lose;
	BufferedImage credit;
	BufferedImage guide;

	GamePanel() {
		titleFont = new Font("Arial", Font.PLAIN, 48);
		smallerFont = new Font("Arial", Font.PLAIN, 12);
		frameDraw = new Timer(1000 / 60, this);
		frameDraw.start();
		score1 = new Timer(1000, this);
		bulletSpawn = new Timer(bulletTime, om);
		deathwait = new Timer(3000, this);
		winwait = new Timer(3000, this);
		soundwait = new Timer(1, this);
		ran = new Random();
		bg1 = loadImage("htrae.png");
		bg2 = loadImage("noom eht.png");
		bg3 = loadImage("???.png");
		deadplayer = loadImage("playerdeath.png");
		deadboss = loadImage("bossdeath.png");
		menu = loadImage("menu.jpeg");
		pause = loadImage("pause proper.jpeg");
		win = loadImage("you win proper.jpeg");
		lose = loadImage("you lost, proper one.jpeg");
		credit = loadImage("credits proper.jpeg");
		guide = loadImage("guide.png");
	}

	void updateMenuState() {
	}

	void updateGameState() {
		om.update();
		player.update();
		if (om.boss.health <= 0) {
			soundwait.start();
			player.isActive = true;
			bulletSpawn.stop();
			winwait.start();
		}
		if (player.isActive == false) {
			deathwait.start();
		}
	}

	void updateEndState() {
	}

	@Override
	public void paintComponent(Graphics g) {
		if (currentState == MENU) {
			drawMenuState(g);
		} else if (currentState == GAME) {
			drawGameState(g);
		} else if (currentState == END) {
			drawEndState(g);
		} else if (currentState == PAUSE) {
			drawPauseState(g);
		} else if (currentState == GUIDE) {
			drawGuideState(g);
		} else if (currentState == CREDITS) {
			drawCreditsState(g);
		}
	}

	void drawMenuState(Graphics g) {
		g.drawImage(menu, 0, 0, 1000, 900, null);
	}

	void drawGuideState(Graphics g) {
		g.drawImage(guide, 0, 0, 1000, 900, null);
	}

	void drawGameState(Graphics g) {
		if (billy == 0) {
			g.drawImage(bg1, 0, 0, 1000, 1000, null);
		} else if (billy == 1) {
			g.drawImage(bg2, 0, 0, 1000, 1000, null);
		} else if (billy == 2) {
			g.drawImage(bg3, 0, 0, 1000, 1000, null);
		}
		if (player.isActive == false) {
			g.drawImage(deadplayer, player.x, player.y, 50, 50, null);
		}else {
		player.draw(g);
		}
		if (om.boss.health > 0) {
		om.draw(g);
		}else if (om.boss.health <= 0) {
			g.drawImage(deadboss, om.boss.x, om.boss.y, 100, 100, null);
		}
		if (player.y > 900) {
			player.down = false;
		}
		if (player.y < 20) {
			player.up = false;
		}
		if (player.x < 10) {
			player.left = false;
		}
		if (player.x > 970) {
			player.right = false;
		}
		g.setFont(smallerFont);
		String ssscore = ("" + sscore);
		g.drawString(ssscore, 15, 15);
	}

	void drawPauseState(Graphics g) {
		g.drawImage(pause, 0, 0, 1000, 1000, null);
	}

	void drawEndState(Graphics g) {
		g.drawImage(lose, 0, 0, 1000, 1000, null);
	}

	void drawWinState(Graphics g) {
		g.drawImage(win, 0, 0, 1000, 1000, null);
	}

	void drawCreditsState(Graphics g) {
		g.drawImage(credit, 0, 0, 1000, 1000, null);
	}

	void startGame() {
		om.boss.isActive = true;
		om.boss.health = 500;
		om.boss.x = 450;
		om.boss.y = 500;
		om.EBs.clear();
		player.isActive = true;
		player.x = 500;
		player.y = 800;
		bulletSpawn.start();
		sscore = 0;
		score1.start();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == END) {
				playSound("stateChange.wav");
				currentState = MENU;
			} else if (currentState == GAME) {
				playSound("stateChange.wav");
				currentState = PAUSE;
				bulletSpawn.stop();
			} else if (currentState == PAUSE) {
				playSound("stateChange.wav");
				currentState = GAME;
				bulletSpawn.start();
			} else if (currentState == MENU) {
				playSound("stateChange.wav");
				currentState++;
				billy = ran.nextInt(3);
				bulletSpawn.stop();
				startGame();
			} else if (currentState == WIN) {
				playSound("stateChange.wav");
				currentState = MENU;
				bulletSpawn.start();
			}
		}
		if (arg0.getKeyCode() == KeyEvent.VK_SPACE) {
			if (currentState == MENU) {
				playSound("stateChange.wav");
				currentState = GUIDE;
			} else if (currentState == GUIDE) {
				playSound("stateChange.wav");
				currentState = MENU;
			}
		}
		if (arg0.getKeyCode() == KeyEvent.VK_SHIFT) {
			if (currentState == MENU) {
				playSound("stateChange.wav");
				currentState = CREDITS;
			} else if (currentState == CREDITS) {
				playSound("stateChange.wav");
				currentState = MENU;
			}
		}
		if (currentState == GAME) {
			if (arg0.getKeyCode() == KeyEvent.VK_DOWN && player.y < 900 && player.isActive == true
					|| arg0.getKeyCode() == KeyEvent.VK_S && player.y < 900 && player.isActive == true) {
				player.down = true;
			} else if (arg0.getKeyCode() == KeyEvent.VK_DOWN && player.y > 900 && player.isActive == true) {
				player.down = false;
			}
			if (arg0.getKeyCode() == KeyEvent.VK_UP && player.y > 20 && player.isActive == true
					|| arg0.getKeyCode() == KeyEvent.VK_W && player.y > 20 && player.isActive == true) {
				player.up = true;
			} else if (arg0.getKeyCode() == KeyEvent.VK_UP && player.y < 20 && player.isActive == true) {
				player.up = false;
			}
			if (arg0.getKeyCode() == KeyEvent.VK_LEFT && player.x > 10 && player.isActive == true
					|| arg0.getKeyCode() == KeyEvent.VK_A && player.x > 10 && player.isActive == true) {
				player.left = true;
			} else if (arg0.getKeyCode() == KeyEvent.VK_LEFT && player.x < 10 && player.isActive == true) {
				player.left = false;
			}
			if (arg0.getKeyCode() == KeyEvent.VK_RIGHT && player.x < 970 && player.isActive == true
					|| arg0.getKeyCode() == KeyEvent.VK_D && player.x < 970 && player.isActive == true) {
				player.right = true;
			} else if (arg0.getKeyCode() == KeyEvent.VK_RIGHT && player.x > 970 && player.isActive == true) {
				player.right = false;
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyCode() == KeyEvent.VK_DOWN || arg0.getKeyCode() == KeyEvent.VK_S) {
			player.down = false;
		}
		if (arg0.getKeyCode() == KeyEvent.VK_UP || arg0.getKeyCode() == KeyEvent.VK_W) {
			player.up = false;
		}
		if (arg0.getKeyCode() == KeyEvent.VK_LEFT || arg0.getKeyCode() == KeyEvent.VK_A) {
			player.left = false;
		}
		if (arg0.getKeyCode() == KeyEvent.VK_RIGHT || arg0.getKeyCode() == KeyEvent.VK_D) {
			player.right = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == frameDraw) {
			repaint();
			if (currentState == MENU) {
				updateMenuState();
			} else if (currentState == GAME) {
				updateGameState();
			} else if (currentState == END) {
				updateEndState();
			}
		} else if (arg0.getSource() == score1) {
			sscore++;
		} else if (arg0.getSource() == deathwait) {
			deathwait.stop();
			currentState = END;
		} else if (arg0.getSource() == winwait) {
			winwait.stop();
			currentState = WIN;
			playSound("win.wav");
		} else if (arg0.getSource() == soundwait) {
			soundwait.stop();
			playSound("bossdeath.wav");
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (player.isActive == true) {
			Bullet projectile = new Bullet(player.x, player.y);

			int xdif = e.getX() - player.x;
			int ydif = e.getY() - player.y;

			double angle = Math.atan2((double) ydif, (double) xdif);

			projectile.xSpeed = Math.cos(angle);
			projectile.ySpeed = Math.sin(angle);
			om.addProjectile(projectile);
			if (currentState == GAME) {
				playSound("fire_player.aiff");
			}
		}
	}

	public static void playSound(String soundFile) {
		String path = "src/";
		File sound = new File(path + soundFile);
		if (sound.exists()) {
			new Thread(() -> {
				try {
					Clip clip = AudioSystem.getClip();
					clip.open(AudioSystem.getAudioInputStream(sound));
					clip.start();
					Thread.sleep(clip.getMicrosecondLength() / 1000);
				} catch (Exception e) {
					System.out.println("Could not play this sound");
				}
			}).start();
		} else {
			System.out.println("File does not exist");
		}
	}

	BufferedImage loadImage(String bg12) {
		BufferedImage temp = null;
		try {
			temp = ImageIO.read(this.getClass().getResourceAsStream(bg12));
		} catch (Exception e) {

		}
		return temp;
	}
}
