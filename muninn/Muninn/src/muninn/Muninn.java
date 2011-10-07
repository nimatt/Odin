/**
 * Main class for Muninn.
 */
package muninn;

import javax.swing.SwingUtilities;

import muninn.gui.MainFrame;

/**
 * @author Mattias Nilsson
 *
 */
public class Muninn {

	/**
	 * @param args Arguments to program, not used
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainFrame();
			}
		});
	}

}
