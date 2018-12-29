package cz.koscak.jan.core;

import java.awt.Graphics;

public class Game {
	
	public static final int BULLET_SPEED = 4;
	private boolean paused = true;
	private boolean end = true;
	private boolean beforeFirstGame = true;
	
	public Game() {
		
		initGame();
		
	}

	public void startNewGame() {
		
		initGame();
		
	}
		
	public void initGame() {
		
	}
	
	public void setBeforeFirstGameToFalse() {
		beforeFirstGame = false;
	}
	
	public boolean isBeforeFirstGame() {
		return beforeFirstGame;
	}

	public void paint(Graphics graphics) {
		
	}

	public void doActions(int round) {
		
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}
	
	
	public boolean tooglePaused() {
		paused = !paused;
//		System.out.println("Toogle paused: " + paused);
		return paused;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

	public boolean isEnd() {
		return end;
	}
	
}
