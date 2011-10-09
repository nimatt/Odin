package muninn.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/**
 * Provides two text areas for output, the areas are selectable by tabs
 */
public class TextPanel extends JPanel {
	
	/**
	 * Serial of the component
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Space between top and text
	 */
	private static final int TOP_INDENT = 2;
	/**
	 * Space between left edge and text
	 */
	private static final int LEFT_INDENT = 5;
	/**
	 * Space between bottom and text
	 */
	private static final int BOTTOM_INDENT = 2;
	/**
	 * Space between text and right edge
	 */
	private static final int RIGHT_INDENT = 5;
	
	/**
	 * Main panel
	 */
	private SideTabbedPanel panel = new SideTabbedPanel("System", "Output");
	/**
	 * TextArea for system output
	 */
	private JTextArea systemArea = new JTextArea();
	/**
	 * TextArea for output from Huginn
	 */
	private JTextArea outputArea = new JTextArea();
	
	/**
	 * Default constructor, creates a tabbed panel with two text areas
	 */
	public TextPanel() {
		setLayout(new BorderLayout());
		add(panel);
		
		systemArea.setBorder(new EmptyBorder(TOP_INDENT, LEFT_INDENT, BOTTOM_INDENT, RIGHT_INDENT));
		outputArea.setBorder(new EmptyBorder(TOP_INDENT, LEFT_INDENT, BOTTOM_INDENT, RIGHT_INDENT));
		
		systemArea.setEditable(false);
		outputArea.setEditable(false);
		
		systemArea.setText("Welcome to Odin/Muninn!\n");
		outputArea.setText("Output from Huginn\n------------------------------------------------\n\n");
		
		JScrollPane systemPane = new JScrollPane(systemArea);
		JScrollPane outputPane = new JScrollPane(outputArea);
		
		panel.add(systemPane, 0);
		panel.add(outputPane, 1);
	}
	
	/**
	 * Add string to system output area
	 * @param s	String to add to output
	 */
	public void addToSystemOutput(String s) {
		systemArea.append(s);
	}
	
	/**
	 * Add string to Huginn output area
	 * @param s	String to append to output
	 */
	public void addToHuginnOutput(String s) {
		outputArea.append(s);
	}
	
	/**
	 * Clears the system output
	 */
	public void clearSystemOutput() {
		systemArea.setText("");
	}
	
	/**
	 * Clears the Huginn output
	 */
	public void clearHuginnOutput() {
		outputArea.setText("");
	}
}
