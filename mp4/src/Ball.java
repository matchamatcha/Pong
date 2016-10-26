//package com.edu4java.minitennis8;
//this is the ball class
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {
	private static final int DIAMETER = 30;
	//initializers
	int x = 50;
	int y = 50;
	int xa = 1;
	int ya = 1;
	private Game game;

	public Ball(Game game) {
		this.game = game;
	}
		//controls ball's movement
		void move() {
		boolean changeDirection = true;
		
		if (x + xa < 0){
			xa = game.speed; //to the right
		}
		else if (x + xa > game.getWidth() - DIAMETER){
			xa = -game.speed; //to the left
		}
		else if (y + ya < 0){
			game.racquet.score++; //updates score
			ya = game.speed; //down
		}
		else if (y + ya > game.getHeight() - DIAMETER){
			game.racquet2.score++;
			ya = -game.speed; //up
		}
		else if (collision(game.racquet)){ //moves down if it collides with upper racquet
			ya = game.speed;
			y = game.racquet.getBotY() + DIAMETER;	
			game.speed++; //game speed increases as it collides w/ racquet
		}
		else if (collision(game.racquet2)){
			ya = -game.speed; //up if lower collision
			y = game.racquet2.getTopY() - DIAMETER;
			game.speed++; //game speed increases as it collides w/ racquet
		}else 
			changeDirection = false;
		
		if (changeDirection) 
			Sound.BALL.play();
		x = x + xa;
		y = y + ya;
	}

	private boolean collision(Racquet r) { //checks for collision between sprites
		return r.getBounds().intersects(getBounds());
	}
	
	public void paint(Graphics2D g) {
		g.fillOval(x, y, DIAMETER, DIAMETER);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
}