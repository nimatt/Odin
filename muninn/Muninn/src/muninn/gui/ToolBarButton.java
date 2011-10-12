package muninn.gui;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.VolatileImage;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class ToolBarButton extends JPanel implements MouseListener {

	/**
	 * Serial of the component
	 */
	private static final long serialVersionUID = 1L;
	private static final float ARC = 20;
	private static final float BORDER_SPACE = 2;
	
	private static final float IMAGE_SCALE = 0.9f;

	private static final Paint hoverBorderPaint = new Color(111, 163, 136);
	private static final Paint pressedHoverBorderPaint = new Color(111, 163, 136);
	private static final Paint pressedBorderPaint = new Color(136, 136, 136);
	
	private boolean hovering = false;
	private boolean pressed = false;
	private boolean pressing = false;
	private Image image = null;
	
	public ToolBarButton(JComponent parent) {
		setBackground(parent.getBackground());
		addMouseListener(this);
	}
	
	public ToolBarButton(JComponent parent, Image image) {
		this(parent);
		this.image = image;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Paint hoverPaint = new GradientPaint(0, 0, new Color(170, 249, 209),
				0, getHeight(), new Color(129, 189, 158), false);
		Paint pressedHoverPaint = new GradientPaint(0, getHeight(), new Color(138, 201, 169),
				0, 0, new Color(129, 189, 158), false);
		Paint pressedPaint = new GradientPaint(0, getHeight(), new Color(169, 169, 169),
				0, 0, new Color(159, 159, 159), false);
		VolatileImage image = createVolatileImage(getWidth(), getHeight());
		
		Graphics2D g2d = (Graphics2D) image.getGraphics();
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setPaint(getBackground());
		g2d.fillRect(0, 0, getWidth(), getHeight());
		
		if ((!pressing && pressed) || (hovering && pressing && !pressed)) {
			RoundRectangle2D rect = new RoundRectangle2D.Float(BORDER_SPACE, BORDER_SPACE, 
					getWidth() - 2 * BORDER_SPACE, getHeight() - 2 * BORDER_SPACE, ARC, ARC);
			if (hovering) {
				g2d.setPaint(pressedHoverBorderPaint);
			}
			else {
				g2d.setPaint(pressedBorderPaint);
			}
			g2d.draw(new RoundRectangle2D.Float(BORDER_SPACE - 1, BORDER_SPACE - 1, 
					getWidth() - 2 * BORDER_SPACE, getHeight() - 2 * BORDER_SPACE, ARC, ARC));
			if (hovering) {
				g2d.setPaint(pressedHoverPaint);
			}
			else {
				g2d.setPaint(pressedPaint);
			}
			g2d.fill(rect);
		}
		else if (hovering) {
			RoundRectangle2D rect = new RoundRectangle2D.Float(BORDER_SPACE, BORDER_SPACE, 
					getWidth() - 2 * BORDER_SPACE, getHeight() - 2 * BORDER_SPACE, ARC, ARC);
			g2d.setPaint(hoverBorderPaint);
			g2d.draw(rect);
			g2d.setPaint(hoverPaint);
			g2d.fill(rect);
		}
		
		if (this.image != null) {
			g2d.drawImage(this.image, (int) ((1-IMAGE_SCALE) * getWidth() / 2),
					(int) ((1-IMAGE_SCALE) * getHeight() / 2), (int) (IMAGE_SCALE * getWidth()),
					(int) (IMAGE_SCALE * getHeight()), null);
		}
		
		g.drawImage(image, 0, 0, null);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// Nothing to do, handled in down and up
	}

	@Override
	public void mouseEntered(MouseEvent me) {
		hovering = true;
		repaint();
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		hovering = false;
		pressing = false;
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		pressing = true;
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		if (pressing) {
			pressed = !pressed;
			pressing = false;
			repaint();
		}
	}
}
