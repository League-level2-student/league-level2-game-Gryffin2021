import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	Font titleFont;
	Font smallerFont;
	Timer frameDraw; 
	Player player = new Player(250, 700, 50, 50);
	
	GamePanel(){
		titleFont = new Font("Arial", Font.PLAIN, 48);
		smallerFont = new Font("Arial", Font.PLAIN, 12);
		frameDraw = new Timer(1000/60, this);
		frameDraw.start();
	}
	
	void updateMenuState() {  }
	void updateGameState() {  }
	void updateEndState()  {  }
	
	@Override
	public void paintComponent(Graphics g){
		if(currentState == MENU){
			drawMenuState(g);
		}else if(currentState == GAME){
			drawGameState(g);
		}else if(currentState == END){
			drawEndState(g);
		}
	}
	
	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 1000, 1000);  
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("SUPER SHIP 199", 25, 200);
		g.setFont(smallerFont);
		g.drawString("Press ENTER to begin...", 178, 300);
		g.drawString("Press SPACE for instructions", 163, 400);
	}
	void drawGameState(Graphics g) { player.draw(g); }
	void drawEndState(Graphics g)  {  }
	
	 
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END) {
		        currentState = MENU;
		    } else {
		        currentState++;
		    }
		    if (arg0.getKeyCode()==KeyEvent.VK_UP) {
		        System.out.println("UP");
		        player.up();
		    }
		    else if (arg0.getKeyCode()==KeyEvent.VK_DOWN) {
		        System.out.println("DOWN");
		        player.down();
		    }
		    else if (arg0.getKeyCode()==KeyEvent.VK_LEFT) {
		        System.out.println("LEFT");
		        player.left();
		    }
		    else if (arg0.getKeyCode()==KeyEvent.VK_RIGHT) {
		        System.out.println("RIGHT");
		        player.right();
		    }
		}   
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
		if(currentState == MENU){
		    updateMenuState();
		}else if(currentState == GAME){
		    updateGameState();
		}else if(currentState == END){
		    updateEndState();
		}
	}

}
