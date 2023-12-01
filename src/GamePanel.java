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
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
//check bullet spawn
public class GamePanel extends JPanel implements ActionListener, KeyListener, MouseListener{
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	final int PAUSE = 3;
	final int GUIDE = 4;
	final int CREDITS = 5;
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
	
	GamePanel(){
		titleFont = new Font("Arial", Font.PLAIN, 48);
		smallerFont = new Font("Arial", Font.PLAIN, 12);
		frameDraw = new Timer(1000/60, this);
		frameDraw.start();
		score1 = new Timer(1000, this);
		bulletSpawn = new Timer(bulletTime, om);
	}
	
	void updateMenuState() {  }
	void updateGameState() {
		om.update();  
	player.update(); 
	if(player.isActive == false) {
		currentState = END;
		}
	}
	void updateEndState()  {  }
	
	@Override
	public void paintComponent(Graphics g){
		if(currentState == MENU){
			drawMenuState(g);
		}else if(currentState == GAME){
			drawGameState(g);
		}else if(currentState == END){
			drawEndState(g);
		}else if(currentState == PAUSE) {
			drawPauseState(g);
		}else if(currentState == GUIDE) {
			drawGuideState(g);
		}else if(currentState == CREDITS) {
			drawCreditsState(g);
		}
	}
	
	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 1000, 1000);  
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("[Place Holder Menu]", 25, 200);
		g.setFont(smallerFont);
		g.drawString("Press ENTER to begin...", 178, 300);
		g.drawString("Press SPACE for instructions", 163, 400);
		g.drawString("Press SHIFT for credits", 178, 500);
	}
	void drawGuideState(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, 1000, 1000); 
	}
	void drawGameState(Graphics g) { 
		player.draw(g);
	om.draw(g);
	System.out.println(player.x + " " + player.y + " " + player.down);
	if(player.y > 900){
    	player.down = false;
    }
	if(player.y < 20) {
		player.up = false;
	}
	if(player.x < 10) {
		player.left = false;
	}
	if(player.x > 970) {
		player.right = false;
	}
	g.setFont(smallerFont);
	String ssscore = ("" + sscore);
	g.drawString(ssscore, 15, 15);
	}
	void drawPauseState(Graphics g)  {
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, 1000, 1000); 
	}
	void drawEndState(Graphics g)  {
		g.setColor(Color.RED);
		g.fillRect(0, 0, 1000, 1000); 
	}
	void drawCreditsState(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, 1000, 1000); 
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
		if (arg0.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END) {
		    	playSound("stateChange.wav");
		        currentState = MENU;
		    }else if(currentState == GAME){
		    	playSound("stateChange.wav");
		    	currentState = PAUSE;
		    }else if(currentState == PAUSE){
		    	playSound("stateChange.wav");
			    	currentState = GAME;
		    }else if (currentState == MENU){
		    	playSound("stateChange.wav");
		        currentState++;
		        bulletSpawn.stop();
		        startGame();
		    }
		}
		if (arg0.getKeyCode()==KeyEvent.VK_SPACE) {
			if(currentState == MENU) {
				playSound("stateChange.wav");
				currentState = GUIDE;
			}else if(currentState == GUIDE) {
				playSound("stateChange.wav");
				currentState = MENU;
			}
		}
		if (arg0.getKeyCode()==KeyEvent.VK_SHIFT) {
			if(currentState == MENU) {
				playSound("stateChange.wav");
				currentState = CREDITS;
			}else if(currentState == CREDITS) {
				playSound("stateChange.wav");
				currentState = MENU;
			}
		}
		    if(currentState == GAME) {
		    if (arg0.getKeyCode()==KeyEvent.VK_DOWN && player.y < 900) {
		    	player.down = true;
		    }else if(arg0.getKeyCode()==KeyEvent.VK_DOWN && player.y > 900){
		    	player.down = false;
		    }
		    if (arg0.getKeyCode()==KeyEvent.VK_UP && player.y > 20) {
		    	player.up = true;
		    }else if(arg0.getKeyCode()==KeyEvent.VK_UP && player.y < 20){
		    	player.up = false;
		    }
		    if (arg0.getKeyCode()==KeyEvent.VK_LEFT && player.x > 10) {
		        player.left = true;
		    }else if(arg0.getKeyCode()==KeyEvent.VK_LEFT && player.x < 10){
		    	player.left = false;
		    }
		    if (arg0.getKeyCode()==KeyEvent.VK_RIGHT && player.x < 970) {
		        player.right = true;
		    }else if (arg0.getKeyCode()==KeyEvent.VK_RIGHT && player.x > 970) {
		    	player.right = false;
		    }
		}
	
	}
	

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyCode()==KeyEvent.VK_DOWN) {
	    	player.down = false;
	    }
	    if (arg0.getKeyCode()==KeyEvent.VK_UP) {
	    	player.up = false;
	    }
	    if (arg0.getKeyCode()==KeyEvent.VK_LEFT) {
	        player.left = false;
	    }
	    if (arg0.getKeyCode()==KeyEvent.VK_RIGHT) {
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
		if(arg0.getSource() == frameDraw) {
		repaint();
		if(currentState == MENU){
		    updateMenuState();
		}else if(currentState == GAME){
		    updateGameState();
		}else if(currentState == END){
		    updateEndState();
		}
		}else if(arg0.getSource() == score1) {
			sscore ++;
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
				Bullet projectile = new Bullet(player.x, player.y);

				int xdif = e.getX() - player.x;
				int ydif = e.getY() - player.y;

				double angle = Math.atan2((double) ydif, (double) xdif);

				projectile.xSpeed = Math.cos(angle);
				projectile.ySpeed = Math.sin(angle);
				om.addProjectile(projectile);
				if(currentState == GAME) {
					playSound("fire_player.aiff");
				}
			}
	public void playSound(String soundFile) {
		String path = "src/";
			File sound = new File(path+soundFile);
			if (sound.exists()) {
				new Thread(() -> {
				try {
					Clip clip = AudioSystem.getClip();
					clip.open(AudioSystem.getAudioInputStream(sound));
					clip.start();
					Thread.sleep(clip.getMicrosecondLength()/1000);
				}
				catch (Exception e) {
					System.out.println("Could not play this sound");
				}}).start();
	 		}
			else {
				System.out.println("File does not exist");
			}
	}
}

	

