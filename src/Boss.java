import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Boss extends GameObject implements ActionListener{
Timer move;
int health = 100;
int close1;
	public Boss(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		move = new Timer(10000, this);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		double dist = Math.sqrt(Math.pow(x - Player.xx, 2) - Math.pow(y - Player.yy, 2));
	}

}
