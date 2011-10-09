package muninn.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class TextPanel extends JPanel {
	
	public TextPanel() {
		setBackground(Color.GREEN);
		setLayout(new BorderLayout());
		
		Tabs tabs = new Tabs("Output", "System");
		
		add(tabs, BorderLayout.EAST);
	}
}
