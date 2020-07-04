package handler;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import stuff.MoveAndZoom;
import view.IMMainPanel;

public class IconMakerMouseHadler implements MouseListener, MouseMotionListener, MouseWheelListener{
	
	private IMMainPanel imMainPanel;
	private boolean moveOn = false;
	
	public IconMakerMouseHadler(IMMainPanel imMainPanel) {
		this.imMainPanel=imMainPanel;
	}

	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			imMainPanel.paintPixel(MoveAndZoom.transformPoint(e.getPoint()));
		}else if (e.getButton() == MouseEvent.BUTTON3) {
			MoveAndZoom.setDragStartPoint(e.getPoint());
			moveOn = true;
		}
	}

	public void mouseReleased(MouseEvent e) {
		moveOn = false;
	}
	
	public void mouseClicked(MouseEvent e) {
	}
	
	public void mouseDragged(MouseEvent e) {
		if(moveOn) {
			MoveAndZoom.moveCamera(e);
			imMainPanel.repaint();
		}else {
			imMainPanel.paintPixel(MoveAndZoom.transformPoint(e.getPoint()));
		}
	}

	public void mouseMoved(MouseEvent e) {
	}
	
	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		MoveAndZoom.zoomCamera(e);		
		imMainPanel.repaint();
	}

}
