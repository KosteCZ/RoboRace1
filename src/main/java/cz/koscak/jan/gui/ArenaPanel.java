package cz.koscak.jan.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cz.koscak.jan.core.Game;
import cz.koscak.jan.model.Arena;

@SuppressWarnings("serial")
public class ArenaPanel extends JPanel {

	private static final String BUTTON_PAUSE_NEW_GAME = "New game";
	private static final String BUTTON_PAUSE_START = "Start";
	private static final String BUTTON_PAUSE_PAUSE = "Pause";
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 1000;
	
	private static final int TILE_WIDTH = Arena.ARENA_WIDTH / 12;
	private static final int TILE_HEIGHT = Arena.ARENA_HEIGHT / 12;
			
	private Game game;
	private JLabel labelBlueAliveText;
	private JLabel labelRedAliveText;
	private JLabel labelBlueAliveNumber;
	private JLabel labelRedAliveNumber;
	private JLabel blueAILabel;
	private JLabel redAILabel;
	private JComboBox<String> blueAIComboBox;
	private JComboBox<String> redAIComboBox;
	private JButton buttonPause;
	
	public ArenaPanel(Game game) {
		
		this.game = game;
		
		this.setLayout(null);

		//setBorder(BorderFactory.createLineBorder(Color.BLACK));
    	setDoubleBuffered(true);
    	
		createPauseButton();
		
		createLabel();
		
		createBlueAISelection();
		createRedAISelection();
		
    }

    private void createLabel() {
    	
    	labelBlueAliveText = new JLabel("BLUE alive: ");
    	labelBlueAliveText.setForeground(Color.BLUE);
    	//labelBlueAliveText.setBounds(10, 10, 100, 30);
    	labelBlueAliveText.setBounds(10, 20, 100, 30);
    	labelBlueAliveText.setHorizontalAlignment(JLabel.LEFT);
    	labelBlueAliveText.setVerticalAlignment(JLabel.CENTER);
    	add(labelBlueAliveText);
		
    	labelBlueAliveNumber = new JLabel("BLUE alive: " + 5);
    	labelBlueAliveNumber.setForeground(Color.BLUE);
    	//labelBlueAliveNumber.setBounds(10, 10, 100, 30);
    	labelBlueAliveNumber.setBounds(10 + 200, 20, 20, 30);
    	labelBlueAliveNumber.setHorizontalAlignment(JLabel.RIGHT);
    	labelBlueAliveNumber.setVerticalAlignment(JLabel.CENTER);
    	add(labelBlueAliveNumber);
		
    	labelRedAliveText = new JLabel("RED alive: ");
    	labelRedAliveText.setForeground(Color.RED);
    	//labelRedAliveText.setBounds(WIDTH - 110, 10, 100, 30);
    	labelRedAliveText.setBounds(WIDTH - 110 - 123, 20, 100, 30);
    	labelRedAliveText.setHorizontalAlignment(JLabel.LEFT);
    	labelRedAliveText.setVerticalAlignment(JLabel.CENTER);
    	add(labelRedAliveText);
		
    	labelRedAliveNumber = new JLabel("RED alive: " + 5);
    	labelRedAliveNumber.setForeground(Color.RED);
    	//labelRedAliveNumber.setBounds(WIDTH - 110, 10, 100, 30);
    	labelRedAliveNumber.setBounds(WIDTH - 110, 20, 100, 30);
    	labelRedAliveNumber.setHorizontalAlignment(JLabel.RIGHT);
    	labelRedAliveNumber.setVerticalAlignment(JLabel.CENTER);
    	add(labelRedAliveNumber);
		
	}

	public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

    public void paintComponent(Graphics g) {
    	
        super.paintComponent(g);       

        //g.drawString("This is my custom Panel!", 10, 20);
		//g.drawOval(0, 0, 4, 4);
        
        paintArena(g);
        
		game.paint(g);
		
		printTexts(g);
		
    }
    
	private void printTexts(Graphics g) {
		
		if (game.isEnd() && !game.isBeforeFirstGame()) {
			
			g.setColor(Color.BLACK);
			g.setFont(new Font("Dialog", Font.BOLD, 16));
			g.drawString("END", (ArenaPanel.WIDTH / 2) - 15, (ArenaPanel.HEIGHT / 2) - 10);
			
			buttonPause.setText(BUTTON_PAUSE_NEW_GAME);
			
		}
		
	}

	private void paintArena(Graphics g) {

		g.drawRect(Arena.ARENA_X, Arena.ARENA_Y, Arena.ARENA_WIDTH, Arena.ARENA_HEIGHT);
		
		System.out.println();
		
		for (int i = 1; i < 12; i++) {
			g.drawLine(50, 50 + i * TILE_WIDTH, 50 + Arena.ARENA_WIDTH, 50 + i * TILE_HEIGHT); // Horizontal lines
			g.drawLine(50 + i * TILE_HEIGHT, 50, 50 + i * TILE_HEIGHT, 50 + Arena.ARENA_HEIGHT);
		}
		
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				g.drawImage(getImage("test3"), 51 + (i * TILE_WIDTH), 51 + (j * TILE_HEIGHT), TILE_WIDTH - 1, TILE_HEIGHT - 1, this);	
			}
		}
		//g.drawImage(getImage("test1"), 51, 51, tileWidth, tileHeight, this);
		
		g.drawImage(getImage("beltRL1"), 51, 51, TILE_WIDTH - 1, TILE_HEIGHT - 1, this);
		
		g.drawImage(getImage("beltRL1"), 51 + TILE_WIDTH, 51, TILE_WIDTH - 1, TILE_HEIGHT - 1, this);
		g.drawImage(getImage("wallT1"), 51 + TILE_WIDTH, 51, TILE_WIDTH - 1, TILE_HEIGHT - 1, this);

		g.drawImage(getImage("beltRL1"), 51 + TILE_WIDTH * 2, 51, TILE_WIDTH - 1, TILE_HEIGHT - 1, this);
		g.drawImage(getImage("wallT2"), 51 + TILE_WIDTH * 2, 51, TILE_WIDTH - 1, TILE_HEIGHT - 1, this);

		g.drawImage(getImage("beltRL1"), 51 + TILE_WIDTH * 3, 51, TILE_WIDTH - 1, TILE_HEIGHT - 1, this);
		g.drawImage(getImage("wallT3"), 51 + TILE_WIDTH * 3, 51, TILE_WIDTH - 1, TILE_HEIGHT - 1, this);

		g.drawImage(getImage("robot1"), 51 + TILE_WIDTH * 4, 51, TILE_WIDTH - 1, TILE_HEIGHT - 1, this);
		
		paintImage(g, 1, 1, "Text_test_1");
		paintImage(g, 1, 2, "Text_test_1");

	}
	
	private void paintImage(Graphics g, int x, int y, String imageName) {
		
		BufferedImage image = getImage(imageName);
		System.out.println("Image: " + image);
		
		if (image != null) {
		
			g.drawImage(image, 51 + (TILE_WIDTH * x), 51 + (TILE_HEIGHT * y), TILE_WIDTH - 1, TILE_HEIGHT - 1, this);
		
		} else {
			
			g.drawString(imageName, 51 + (TILE_WIDTH * x) + 3, 51 + (TILE_HEIGHT * y) + (TILE_HEIGHT / 2));
			
		}
		
	}
	
	static BufferedImage getImage(String name) {
		
		BufferedImage image = null;
		
		try { 
			InputStream in = new FileInputStream(new File("img/" + name + ".png"));
			
			image = ImageIO.read(in);
			
		} catch (IOException e) { 
			System.err.println("Error: " + e);
		}
		
		return image;
		
	}
	
	private void createPauseButton() {
		
		buttonPause = new JButton(BUTTON_PAUSE_NEW_GAME);
		buttonPause.setBounds(WIDTH / 2 - 50, 10, 100, 30);
		
		buttonPause.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				boolean paused = game.tooglePaused();
				if (paused) {
					game.setBeforeFirstGameToFalse();
					buttonPause.setText(BUTTON_PAUSE_START);
					/*if (game.isEnd() && !game.isBeforeFirstGame()) {
					game.setEnd(false);
					game.startNewGame();
					}*/
				} else {
					game.setEnd(false);
					buttonPause.setText(BUTTON_PAUSE_PAUSE);
				}
			}

		});
		
		add(buttonPause);
		
	}
	
	private void createBlueAISelection() {
		
		blueAILabel = new JLabel("BLUE AI: ", JLabel.LEFT);
		blueAILabel.setForeground(Color.BLUE);
    	blueAILabel.setBounds(10, 5 + 5, 80, 10);   	
		add(blueAILabel);
		
	}
	
	private void createRedAISelection() {
		
		redAILabel = new JLabel("RED AI: ", JLabel.RIGHT);
		redAILabel.setForeground(Color.RED);
		redAILabel.setBounds(WIDTH - 270, 5 + 5, 80, 10);   	
		add(redAILabel);
		
	}
	
}