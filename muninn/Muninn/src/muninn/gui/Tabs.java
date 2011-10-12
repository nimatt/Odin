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
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.image.VolatileImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Draws and handles mouse input for vertical tabs
 */
public class Tabs extends JPanel {

	/**
	 * Serial for the component
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Width of the tabs
	 */
	private static final int WIDTH = 30;
	/**
	 * Minimum size of the tabs
	 */
	private static final float MINIMUM_TAB_SIZE = 1.5f * WIDTH;
	/**
	 * Extra space for the currently selected tab
	 */
	private static final float SELECTED_SPACE = 0.6f * WIDTH;
	/**
	 * Space between the border of the tab and the text
	 */
	private static final int TEXT_BUFF = 8;
	/**
	 * Color of the text
	 */
	private static final Color TEXT_COLOR = Color.BLACK;
	/**
	 * Color of the background behind the tabs  
	 */
	private static final Color BACKGROUND_COLOR = Color.LIGHT_GRAY;
	
	/**
	 * Labels of the tabs
	 */
	private String[] labels;
	/**
	 * Currently selected tab
	 */
	private int selected = 0;
	private List<TabListener> listeners = new ArrayList<TabListener>();
	
	/**
	 * Constructs new Tabs with the given labels
	 * 
	 * @param labels	Labels of the tabs
	 */
	public Tabs(String... labels) {
		this.labels = new String[labels.length];
		
		for (int i = 0; i < labels.length; i++) {
			this.labels[i] = labels[i];
		}
		
		setPreferredSize(new Dimension(WIDTH + 1, 0));
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				mouseEvent(e);
			}
		});
	}
	
	/**
	 * Takes care of the mouse event where the user might have selected a new tab
	 * @param e	The actual mouse event
	 */
	private void mouseEvent(MouseEvent e) {
		float height = ((float) getHeight() - 1)/(float) labels.length;
		height = Math.max(height, MINIMUM_TAB_SIZE);
		
		int newSelect = (int) (e.getY() / height);
		
		if (newSelect != selected) {
			selected = newSelect;
			if (!listeners.isEmpty()) {
				final TabEvent event = new TabEvent(labels[newSelect], newSelect);
				for (int i = 0; i < listeners.size(); i++) {
					final TabListener list = listeners.get(i);
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							list.tabSelected(event);
						}
					});
				}
			}
			repaint();
		}
	}
	
	/**
	 * Register a TabListener to receive events from the tabs
	 * @param tl	Listener to register
	 */
	public void addTabListener(TabListener tl) {
		if (!listeners.contains(tl)) {
			listeners.add(tl);
		}
	}
	
	/**
	 * Unregister a TabListener so that it no longer receives any events
	 * @param tl	Listener to unregister
	 */
	public void removeTabListener(TabListener tl) {
		listeners.remove(tl);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		float height;
		Paint borderColor = Color.DARK_GRAY;
		Paint selectedPaint = new GradientPaint(WIDTH, 0, new Color(170, 249, 209),
				0, 0, new Color(129, 189, 158), false);
		Paint notSelectedPaint = new GradientPaint(WIDTH, 0, new Color(240, 240, 240),
				0, 0, new Color(190, 190, 190), false);
		
		// - 1 is to make the drawn border visible on the last tab
		height = ((float) getHeight() - 1 - SELECTED_SPACE)/(float) labels.length;
		height = Math.max(height, MINIMUM_TAB_SIZE);
		
		VolatileImage image = createVolatileImage(getWidth(), getHeight());
		
		Graphics2D g2d = (Graphics2D) image.getGraphics();
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2d.setColor(BACKGROUND_COLOR);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		
		GeneralPath path = new GeneralPath();
		
		// Up until selected tab
		for (int tabNr = 0; tabNr < selected; tabNr++) {
			path.reset();
			path.moveTo(0, tabNr * height);
		
			Point2D current = path.getCurrentPoint();
			path.curveTo(WIDTH, current.getY(), 
					WIDTH, current.getY(), 
					WIDTH, current.getY() + WIDTH);
			path.lineTo(WIDTH, current.getY() + height - WIDTH);
			path.lineTo(WIDTH, current.getY() + height + WIDTH);
			path.lineTo(0, current.getY() + height + WIDTH);
			g2d.setPaint(notSelectedPaint);
			g2d.fill(path);
			g2d.setPaint(borderColor);
			g2d.draw(path);
			
			drawVerticalString(g2d, 0, (int) (tabNr * height + TEXT_BUFF), 
					WIDTH, (int) ((tabNr + 1) * height - TEXT_BUFF), labels[tabNr], false);
		}
		
		// After selected tab
		for (int tabNr = labels.length - 1; tabNr > selected; tabNr--) {
			path.reset();
			path.moveTo(0, tabNr * height - WIDTH + SELECTED_SPACE);
		
			Point2D current = path.getCurrentPoint();
			path.lineTo(WIDTH, current.getY());
			path.lineTo(WIDTH, current.getY() + height);
			path.curveTo(WIDTH, current.getY() + height + WIDTH, 
					WIDTH, current.getY() + height + WIDTH, 
					0, current.getY() + height + WIDTH);
			g2d.setPaint(notSelectedPaint);
			g2d.fill(path);
			g2d.setPaint(borderColor);
			g2d.draw(path);
			
			drawVerticalString(g2d, 0, (int) (tabNr * height + SELECTED_SPACE + TEXT_BUFF), 
					WIDTH, (int) ((tabNr + 1) * height + SELECTED_SPACE - TEXT_BUFF), labels[tabNr], false);
		}
		
		path.reset();
		
		// Draw selected
		if (selected < labels.length -1) {
			path.moveTo(0, selected * height);
			Point2D current = path.getCurrentPoint();
			path.curveTo(WIDTH, current.getY(), 
					WIDTH, current.getY(), 
					WIDTH, current.getY() + WIDTH);
			path.lineTo(WIDTH, current.getY() + height - 0.5f * WIDTH);
			current = path.getCurrentPoint();
			path.curveTo(WIDTH, current.getY() + WIDTH, 
					0, current.getY() + 0.8f * WIDTH,
					0, current.getY() + 1.8f * WIDTH);
			g2d.setPaint(selectedPaint);
			g2d.fill(path);
			g2d.setPaint(borderColor);
			g2d.draw(path);
			
			drawVerticalString(g2d, 0, (int) (selected * height + TEXT_BUFF), 
					WIDTH, (int) ((selected + 1) * height - TEXT_BUFF), labels[selected], true);
		}
		else {
			path.moveTo(0, selected * height - 1.2f * WIDTH + SELECTED_SPACE);
			Point2D current = path.getCurrentPoint();
			path.curveTo(0, current.getY() + WIDTH, 
					WIDTH, current.getY() + 0.8f * WIDTH,
					WIDTH, current.getY() + 1.2f * WIDTH);
			path.lineTo(WIDTH, (selected + 1) * height - WIDTH + SELECTED_SPACE);
			current = path.getCurrentPoint();
			path.curveTo(WIDTH, current.getY() + WIDTH, 
					WIDTH, current.getY() + WIDTH, 
					0, current.getY() + WIDTH);
			current = path.getCurrentPoint();
			g2d.setPaint(selectedPaint);
			g2d.fill(path);
			g2d.setPaint(borderColor);
			g2d.draw(path);
			
			drawVerticalString(g2d, 0, (int) (selected * height + TEXT_BUFF + SELECTED_SPACE), 
					WIDTH, (int) ((selected + 1) * height + SELECTED_SPACE - TEXT_BUFF), labels[selected], true);
		}
		
		// Draw base line
		g2d.drawLine(0, 0, 0, getHeight());
		
		g.drawImage(image, 0, 0, null);
	}

	/**
	 * Draws the string vertically within the given bounds 
	 * @param g2d	Graphics used to draw
	 * @param x0	x coordinate of the upper left corner of the bounding rectangle
	 * @param y0	y coordinate of the upper left corner of the bounding rectangle
	 * @param x1	x coordinate of the lower right corner of the bounding rectangle
	 * @param y1	y coordinate of the lower right corner of the bounding rectangle
	 * @param text	String to draw
	 * @param bold	Draws text in bold if true otherwise plain
	 */
	private void drawVerticalString(Graphics2D g2d, float x0, float y0, float x1, float y1, String text, boolean bold) {
		AffineTransform oldTrans = g2d.getTransform();
		Paint oldColor = g2d.getPaint();
		Font oldFont = g2d.getFont();
	    AffineTransform trans = new AffineTransform();
	    
	    g2d.setFont(g2d.getFont().deriveFont(bold?Font.BOLD:Font.PLAIN));
	    char dots = (char) 0x2026; // ...
	    char[] string = text.toCharArray();
	    FontMetrics metrics = g2d.getFontMetrics();
	    
	    if (metrics.charsWidth(string, 0, string.length) > y1 - y0) {
	    	int i = string.length - 1;
	    	int dotWidth = metrics.charWidth(dots);
	    	while (metrics.charsWidth(string, 0, i) > y1 - y0 - dotWidth || i == 0) {
	    		i--;
	    	}
	    	
	    	text = text.substring(0, i) + dots;
	    }
	    
	    trans.setToRotation(Math.PI/2.0);
	    g2d.setTransform(trans);
	    g2d.setPaint(TEXT_COLOR);
	    g2d.drawString(text, y0, -(x1 - x0 - metrics.getHeight() + metrics.getLeading()) / 2.0f);
	    g2d.setTransform(oldTrans);
	    g2d.setPaint(oldColor);
	    g2d.setFont(oldFont);
	}
}
