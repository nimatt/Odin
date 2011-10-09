package muninn.gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class StatusBar extends JPanel {

	/**
	 * Serial for the component
	 */
	private static final long serialVersionUID = 1L;
	private static final int HEIGHT = 20;
	
	public StatusBar() {
		this.setBackground(Color.DARK_GRAY);
		this.setPreferredSize(new Dimension(0, HEIGHT));
	}
}
