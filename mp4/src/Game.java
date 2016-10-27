
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel {
	
	Ball ball = new Ball(this); //pong ball
	Racquet racquet = new Racquet(this, 120,1); // a new racquet at the x coordinate 120 and y coordinate 1
	Racquet racquet2 = new Racquet(this, 120,340); // another racquet where we reuse the Racquet class
	int speed = 1; 
	static boolean start = true; //sentinel value

	public Game() { 
		
		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				racquet.keyReleased(e);
				racquet2.keyReleased2(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				racquet.keyPressed(e);
				racquet2.keyPressed2(e);
			}
		});
		
		setFocusable(true); //if deactivated,racquets will not move
		Sound.BACK.loop(); //plays the bgm
	}
	
	//called whenever needed to move the sprites
	private void move() {
		ball.move();
		racquet.move();
		racquet2.move();
	}

	@Override
	public void paint(Graphics g) { //renders the sprites to the screen
		
		super.paint(g); 
		g.setColor(Color.WHITE); //bg color
		g.fillRect(0, 0, 300, 400 );
		
		g.setColor(Color.GRAY); //center line
		g.fillRect(0, 180, 300, 2 );
		
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON); //this is used to smoothen the borders of the objects
		
		g2d.setColor(Color.GREEN); 
		ball.paint(g2d);	
		g2d.setColor(Color.BLUE);
		racquet.paint(g2d);
		racquet2.paint(g2d);	
		//punctuation for scores
		g2d.setColor(Color.BLUE);
		g2d.setFont(new Font("Verdana", Font.BOLD, 12));
		g2d.drawString("P2: "+String.valueOf(racquet2.getScore()), 240, 176); //displays score of players at (x,y) coords
        g2d.drawString("P1: "+String.valueOf(racquet.getScore()), 240, 196); 
        //keys to be pressed for each player
        g2d.setColor(Color.GRAY);
        g2d.setFont(new Font("Verdana", Font.BOLD, 9));
        g2d.drawString("Keys: A, D, Z", 3, 10);
        g2d.drawString("Keys: LEFT, RIGHT, M ", 3, 360);
              
	}
	
	//called whenever a player hits the limit score of 3
	public void gameOver() {
		Sound.BACK.stop(); //the bgm stops
		Sound.GAMEOVER.play(); //game over badii

		int choice; //to display the winner + retry option
		if (racquet.getScore() >= racquet2.getScore())	{																															
			 choice = JOptionPane.showConfirmDialog(this, "Player 1 Wins!\n Retry?", "Game Over", JOptionPane.YES_NO_OPTION);		
		}else{
			 choice = JOptionPane.showConfirmDialog(this, "Player 2 Wins!\nRetry?", "Game Over", JOptionPane.YES_NO_OPTION);		
		}		
		
		if (choice == JOptionPane.NO_OPTION){ 
			start = false;
			System.exit(ABORT); //exits the game
		} else{ //resets the game
			Sound.BACK.loop();
			start = true; //starts a new game
			racquet.setScore(0); //resets the scores
			racquet2.setScore(0);
			speed = 1;
			ball = new Ball(this);
			
		}
		
	}

	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Pong"); 
		Game game = new Game();
		frame.add(game);
		frame.setSize(300, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		start = true; //sentinel
		while(start == true){
			game.move();
			game.repaint();
			Thread.sleep(10);
			//limit score
			if (game.racquet.getScore()== 3 || game.racquet2.getScore()== 3) {
	            game.gameOver();
	            
	        }
			
		}
	}
}
