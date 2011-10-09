package muninn.gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JToolBar;

public class Toolbar extends JToolBar {
	
	/**
	 * Serial for the component
	 */
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 50;
	
	public Toolbar() {
		this.setFloatable(false);
		this.setBackground(new Color(143, 171, 255));
		this.setPreferredSize(new Dimension(WIDTH,0));
	}
}
