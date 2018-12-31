package cz.koscak.jan.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cz.koscak.jan.core.RobotGame;
import cz.koscak.jan.model.Arena;

@SuppressWarnings("serial")
public class MapEditorPanel extends JPanel {

	private static final String BUTTON_PAUSE_NEW_GAME = "New game";
	private static final String BUTTON_ACTION = "Action";
	private static final String BUTTON_PAUSE_START = "Start";
	private static final String BUTTON_PAUSE_PAUSE = "Pause";
	
	public static final int WIDTH = 1300;
	public static final int HEIGHT = 1000;
		
	private static final int MAP_X = Arena.ARENA_X + 1;
	private static final int MAP_Y = Arena.ARENA_Y + 1;
	private static final int TILE_WIDTH = Arena.ARENA_WIDTH / 12;
	private static final int TILE_HEIGHT = Arena.ARENA_HEIGHT / 12;
	
	private JLabel labelBlueAliveText;
	private JLabel labelRedAliveText;
	private JLabel labelBlueAliveNumber;
	private JLabel labelRedAliveNumber;
	private JLabel blueAILabel;
	private JLabel redAILabel;
	private JButton buttonPause;
	private JButton buttonAction;
	
	RobotGame robotGame = new RobotGame();
	
	public MapEditorPanel() {
				
		this.setLayout(null);

		//setBorder(BorderFactory.createLineBorder(Color.BLACK));
    	setDoubleBuffered(true);
    	
		//createPauseButton();
    	createActionButton();
		
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
        
//		printTexts(g);
		
    }
    
/*	private void printTexts(Graphics g) {
		
		if (game.isEnd() && !game.isBeforeFirstGame()) {
			
			g.setColor(Color.BLACK);
			g.setFont(new Font("Dialog", Font.BOLD, 16));
			g.drawString("END", (MapEditorPanel.WIDTH / 2) - 15, (MapEditorPanel.HEIGHT / 2) - 10);
			
			buttonPause.setText(BUTTON_PAUSE_NEW_GAME);
			
		}
		
	}*/

	private void paintArena(Graphics g) {

		g.drawRect(Arena.ARENA_X, Arena.ARENA_Y, Arena.ARENA_WIDTH, Arena.ARENA_HEIGHT);
		
		int tileWidth = Arena.ARENA_WIDTH / 12;
		int tileHeight = Arena.ARENA_HEIGHT / 12;
		
		//System.out.println();
		
		for (int i = 1; i < 12; i++) {
			g.drawLine(50, 50 + i * tileWidth, 50 + Arena.ARENA_WIDTH, 50 + i * tileHeight); // Horizontal lines
			g.drawLine(50 + i * tileHeight, 50, 50 + i * tileHeight, 50 + Arena.ARENA_HEIGHT);
		}
		
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				paintImage(g, i, j, "background");	
			}
		}
		//paintImage(g, 0, 0, "test1");
		
		paintImage(g, 0, 0, "beltRL1");
		
		paintImage(g, 1, 0, "beltRL1");
		paintImage(g, 1, 0, "wallT1");

		paintImage(g, 2, 0, "beltRL1");
		paintImage(g, 2, 0, "wallT2");

		paintImage(g, 3, 0, "beltRL1");
		paintImage(g, 3, 0, "wallT3");

		paintImage(g, 4, 0, "wallT4");
		paintImage(g, 4, 0, "robot1");

		paintImage(g, 0, 1, "Text_test");
		paintImage(g, 1, 1, "Text_test_1");
		
		paintImage(g, 0, 2, "robot1");
		
		//robotGame.moveRobot();

		List<Object>[][] map = robotGame.getMap();
		
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				List<Object> tileObjects = map[i][j];
				if (tileObjects != null && tileObjects.size() > 0 ) {
					paintImage(g, i, j, tileObjects.get(0).toString());
				}
			}
		}
		
	}
	
	private void paintImage(Graphics g, int x, int y, String imageName) {
		
		BufferedImage image = Images.getPreloadedImage(imageName);
		//BufferedImage image = Images.getImage(imageName);
				
		if (image != null) {
		
			//image = Images.rotateImageByDegrees(image, 90.0, this);

			g.drawImage(image, MAP_X + (TILE_WIDTH * x), MAP_Y + (TILE_HEIGHT * y), TILE_WIDTH - 1, TILE_HEIGHT - 1, this);
		
		} else {
			
			g.setColor(Color.RED);
			g.setFont(new Font("default", Font.BOLD, 12));
			
			if (imageName.length() <= 9) {
				g.drawString(imageName, MAP_X + (TILE_WIDTH * x) + 8, MAP_Y + (TILE_HEIGHT * y) + (TILE_HEIGHT / 2) + 4);
			} else {
				g.drawString(imageName.substring(0, 9), MAP_X + (TILE_WIDTH * x) + 8, MAP_Y + (TILE_HEIGHT * y) + (TILE_HEIGHT / 2) - 2);
				g.drawString(imageName.substring(9, imageName.length()), MAP_X + (TILE_WIDTH * x) + 8, MAP_Y + (TILE_HEIGHT * y) + (TILE_HEIGHT / 2) + 10);
			}
			
		}
		
	}
	
	private void createActionButton() {
		
		buttonAction = new JButton(BUTTON_ACTION);
		buttonAction.setBounds(WIDTH / 2 - 50, 10, 100, 30);
		
		buttonAction.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {

				robotGame.doRobotActions();
				
				repaint();
				
			}
			
		});
		
		add(buttonAction);			

	}
	
/*	private void createPauseButton() {
		
		buttonPause = new JButton(BUTTON_PAUSE_NEW_GAME);
		buttonPause.setBounds(WIDTH / 2 - 50, 10, 100, 30);
		
		buttonPause.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				boolean paused = game.tooglePaused();
				if (paused) {
					game.setBeforeFirstGameToFalse();
					buttonPause.setText(BUTTON_PAUSE_START);*/
					/*if (game.isEnd() && !game.isBeforeFirstGame()) {
					game.setEnd(false);
					game.startNewGame();
					}*/
/*				} else {
					game.setEnd(false);
					buttonPause.setText(BUTTON_PAUSE_PAUSE);
				}
			}

		});
		
		add(buttonPause);
		
	}*/
	
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