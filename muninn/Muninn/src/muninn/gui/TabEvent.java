package muninn.gui;

/**
 * Class that acts as a container for information about a tab event
 *
 */
public class TabEvent {

	/**
	 * Name of the involved tab
	 */
	private String tabName;
	/**
	 * The involved tabs number
	 */
	private int tabNumber;
	
	/**
	 * Creates a TabEvent with the given information
	 * @param tabName	Name of the tab
	 * @param tabNumber	Number of the tab
	 */
	public TabEvent(String tabName, int tabNumber) {
		this.tabName = tabName;
		this.tabNumber = tabNumber;
	}
	
	/**
	 * Returns the name of the involved tab
	 * @return	Tab name
	 */
	public String getTabName() {
		return tabName;
	}
	
	/**
	 * Get the number of the involved tab
	 * @return Tab number
	 */
	public int getTabNumber() {
		return tabNumber;
	}
}
