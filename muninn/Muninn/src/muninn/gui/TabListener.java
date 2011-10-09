package muninn.gui;

/**
 * Simple interface for listening to events from Tabs
 */
public interface TabListener {
	/**
	 * A tab has been selected
	 * @param te Container for information about the event
	 */
	public void tabSelected(TabEvent te);
}
