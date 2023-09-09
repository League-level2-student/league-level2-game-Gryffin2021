import javax.swing.JFrame;

public class SuperShip {
	JFrame frame;
	GamePanel panel;
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 1000;
	public static void main(String[] args) {
		SuperShip player = new SuperShip();
		player.setup();
	}	
	SuperShip() {
		frame = new JFrame();
		panel = new GamePanel();
	}
	void setup() {
		frame.add(panel);
		frame.addKeyListener(panel);
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
