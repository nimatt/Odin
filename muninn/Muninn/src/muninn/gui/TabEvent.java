/**
 * Copyright 2011 Mattias Nilsson, Niklas Olsson
 * 
 * This file is part of Muninn.
 *
 * Muninn is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Muninn is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Muninn.  If not, see <http://www.gnu.org/licenses/>.
 */
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
