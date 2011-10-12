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
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JToolBar;
import javax.swing.border.BevelBorder;

public class Toolbar extends JToolBar {
	
	/**
	 * Serial for the component
	 */
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 40;
	
	public Toolbar() {
		setFloatable(false);
		setBackground(new Color(220, 220, 220));
		setPreferredSize(new Dimension(WIDTH,0));
		setBorder(new BevelBorder(BevelBorder.LOWERED));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		File f = new File("./");
		System.out.println(f.getAbsolutePath());
		
		Image image = null;
		
		try {
			image = ImageIO.read(new File("images/tmpIcon.png"));
		} catch (IOException e) {
			// TODO: Do something fun...
			e.printStackTrace();
		}
		
		ToolBarButton tbb = new ToolBarButton(this, image);
		tbb.setMaximumSize(new Dimension(WIDTH, WIDTH));
		add(tbb);
		tbb = new ToolBarButton(this, image);
		tbb.setMaximumSize(new Dimension(WIDTH, WIDTH));
		add(tbb);
		tbb = new ToolBarButton(this, image);
		tbb.setMaximumSize(new Dimension(WIDTH, WIDTH));
		add(tbb);
		tbb = new ToolBarButton(this, image);
		tbb.setMaximumSize(new Dimension(WIDTH, WIDTH));
		add(tbb);
		tbb = new ToolBarButton(this, image);
		tbb.setMaximumSize(new Dimension(WIDTH, WIDTH));
		add(tbb);
		tbb = new ToolBarButton(this, image);
		tbb.setMaximumSize(new Dimension(WIDTH, WIDTH));
		add(tbb);
		tbb = new ToolBarButton(this, image);
		tbb.setMaximumSize(new Dimension(WIDTH, WIDTH));
		add(tbb);
		tbb = new ToolBarButton(this, image);
		tbb.setMaximumSize(new Dimension(WIDTH, WIDTH));
		add(tbb);
		tbb = new ToolBarButton(this, image);
		tbb.setMaximumSize(new Dimension(WIDTH, WIDTH));
		add(tbb);
	}
}
