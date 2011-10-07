package muninn.gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class Toolbar extends JPanel {
	
	private static final int WIDTH = 50;
	
	public Toolbar() {
		this.setBackground(Color.YELLOW);
		this.setPreferredSize(new Dimension(WIDTH,0));
	}
}
