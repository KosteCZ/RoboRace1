package cz.koscak.jan.gui;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MapEditorFrame extends JFrame {

	public MapEditorFrame() {

		super("Robot Race 1");

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		this.add(new MapEditorPanel());
		this.pack();
		
		this.setLocationRelativeTo(null);
		
		this.setVisible(true);

		// System.out.println("Frame created.");

	}
	
}
