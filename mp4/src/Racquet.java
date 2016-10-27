//package com.edu4java.minitennis8;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
//this creates the racquet class

public class Racquet {
	int Y;
	private static final int WIDTH = 60;
	private static final int HEIGHT = 20;
	int x = 0;
	int xa = 0;
	private Game game;
	int score = 0;

	public Racquet(Game game) {
		this.game = game;
	}
	public Racquet(Game game, int x, int Y) { //overloaded constructor
		this.game = game;
		this.x = x;
		this.Y = Y;
		score = 0;
	}

	// the racquet moves from left to right
	public void move() {
		if (x + xa > 0 && x + xa < game.getWidth() - WIDTH)
			x = x + xa;
	}
	//renders a filled rectangle at (x,Y) with specified dimensions
	public void paint(Graphics2D g) {
		g.fillRect(x, Y, WIDTH, HEIGHT);
	}

	public void keyReleased(KeyEvent e) {
		xa = 0;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_A)
			xa = -game.speed; //left move
		if (e.getKeyCode() == KeyEvent.VK_D)
			xa = game.speed; //right move
		if (e.getKeyCode() == KeyEvent.VK_Z)
			game.speed+=2; //power up
	}
	
	public void keyReleased2(KeyEvent e) {
		xa = 0;
	}

	public void keyPressed2(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			xa = -game.speed; //left
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			xa = game.speed; //right
		if (e.getKeyCode() == KeyEvent.VK_M)
			game.speed+=2; //power up
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
	/*
	 *
	 * in business java, using game.speed or direct access is not considered correct, 
	 * because the use of getters and setters is practiced to encapsulate the properties.
	 * It is however common in game development because of its efficiency
	 */

	public Rectangle getBounds() { //racquet's position
		return new Rectangle(x, Y, WIDTH, HEIGHT);
	}

	public int getTopY() {
		return Y - HEIGHT;
	}
	public int getBotY() {
		return Y;
	}
}
