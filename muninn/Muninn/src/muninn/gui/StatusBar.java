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
