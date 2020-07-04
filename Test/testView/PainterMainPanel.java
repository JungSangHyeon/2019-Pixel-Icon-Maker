package testView;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

import iconPainter.IconPainter;
import iconToImage.IconToImage;

@SuppressWarnings("serial")
public class PainterMainPanel extends JPanel{

	public PainterMainPanel() {
		this.setBackground(Color.cyan);
	}

	public void initiate() {
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		
		String realName = "cata";
		int size = 22;
		
		String name = realName+"_"+size+"X"+size+".txt";
		
		IconPainter.IconPaint(g2d, "Icons/"+name, new Point(0,0),1);
		IconPainter.IconPaint(g2d, "Icons/"+name, new Point(40,0),2);
		IconPainter.IconPaint(g2d, "Icons/"+name, new Point(120, 0),3);
		IconPainter.IconPaint(g2d, "Icons/"+name, new Point(240,0),4);
		IconPainter.IconPaint(g2d, "Icons/"+name, new Point(0,200),5);
		IconPainter.IconPaint(g2d, "Icons/"+name, new Point(100,200),7);
		
		g2d.drawImage(IconToImage.getImg("Icons/"+name, 1, 1), 0, 40, null);
	}
	
}
