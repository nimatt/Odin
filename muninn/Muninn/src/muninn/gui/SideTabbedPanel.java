package muninn.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

/**
 * Class that provides a number of panels that are selected by tabs displayed
 * on the side.
 *
 */
public class SideTabbedPanel extends JPanel implements TabListener {

	/**
	 * Serial of the component
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Main panel for displaying content
	 */
	private JPanel contentPanel = new JPanel();
	/**
	 * Map containing the different panels for the different tabs, keyed on label
	 */
	private Map<String, JPanel> panelMap = new HashMap<String, JPanel>();
	/**
	 * Tabs for the panel
	 */
	private Tabs tabs;
	/**
	 * Layout model used to be able to display different panel on a single panel
	 */
	private CardLayout cardLayout = new CardLayout();
	/**
	 * Array of labels for the tabs
	 */
	private String[] labels;
	
	/**
	 * Creates a tabbed panel with the tabs on the side. Number of tabs and name 
	 * is given by the arguments
	 * @param labels	Names for the tabs
	 */
	public SideTabbedPanel(String... labels) {
		setLayout(new BorderLayout());
		
		tabs = new Tabs(labels);
		tabs.addTabListener(this);
		
		contentPanel.setLayout(cardLayout);
		
		for (int i=0; i < labels.length; i++) {
			JPanel panel = new JPanel();
			panel.setLayout(new BorderLayout());
			if (panelMap.containsKey(labels[i])) {
				int j = 2;
				while (panelMap.containsKey(labels[i] + " (" + j + ")")) {
					j++;
				}
				labels[i] = labels[i] + " (" + j + ")";
			}
			contentPanel.add(panel, labels[i]);
			panelMap.put(labels[i], panel);
		}
		
		add(tabs, BorderLayout.EAST);
		add(contentPanel, BorderLayout.CENTER);
		
		this.labels = new String[labels.length];
		for (int i = 0; i < labels.length; i++) {
			this.labels[i] = labels[i];
		}
	}
	
	
	@Override
	public Component add(Component comp, int tab) {
		return panelMap.get(labels[tab]).add(comp);
	}
	
	public void tabSelected(TabEvent te) {
		cardLayout.show(contentPanel, labels[te.getTabNumber()]);
	}
}
