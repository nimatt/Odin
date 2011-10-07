/**
 * Main frame of the Muninn application.
 */
package muninn.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

/**
 * @author Mattias Nilsson
 *
 */
public class MainFrame extends JFrame {

	private static final int WIDTH = 900;
	private static final int HEIGHT = 600;
	private static final int DIVIDER_POS = 2*HEIGHT/3;
	private static final int DIVIDER_SIZE = 4;
	private static final String FRAME_TITLE = "Muninn";

	private StatusBar statusBar = new StatusBar();
	private Toolbar toolbar = new Toolbar();
	private TextPanel textPanel = new TextPanel();
	private GraphPanel graphPanel = new GraphPanel();
	
	public MainFrame() {
		initFrame();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				setVisible(true);
			}
		});
	}
	
	private void initFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(FRAME_TITLE);
		setSize(WIDTH, HEIGHT);
		
		createMenu();
		
		setLayout(new BorderLayout());
		add(statusBar, BorderLayout.SOUTH);
		add(toolbar, BorderLayout.WEST);
		
		JSplitPane graphText = new JSplitPane();
		
		graphText.setOrientation(JSplitPane.VERTICAL_SPLIT);
		graphText.add(graphPanel, JSplitPane.TOP);
		graphText.add(textPanel, JSplitPane.BOTTOM);
		graphText.setDividerLocation(DIVIDER_POS);
		graphText.setDividerSize(DIVIDER_SIZE);
		add(graphText, BorderLayout.CENTER);
	}
	
	private void createMenu() {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		JMenuItem quitItem = new JMenuItem("Quit", KeyEvent.VK_Q);
		quitItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				exit();
			}
		});
		quitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				ActionEvent.CTRL_MASK));
		fileMenu.add(quitItem);
		menuBar.add(fileMenu);
		
		JMenu helpMenu = new JMenu("Help");
		JMenuItem aboutItem = new JMenuItem("About");
		helpMenu.add(aboutItem);
		menuBar.add(helpMenu);
		
		setJMenuBar(menuBar);
	}
	
	private void exit() {
		System.exit(0);
	}
}