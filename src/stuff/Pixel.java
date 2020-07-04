package stuff;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

import stuff.Enums.eColor;

public class Pixel {

	Rectangle rect;
	eColor color = eColor.Color1;
	Point p;
	
	public Pixel(int x, int y, int w, int h, int pixelX, int pixelY) {
		rect = new Rectangle(x,y,w,h);
		p = new Point(pixelX, pixelY);
	}
	
	public Rectangle getRect() {return rect;}
	public Point getPoint() {return p;}
	public eColor getColor() {return color;}
	public void setColor(eColor nowColor) {this.color=nowColor;}
	public boolean isPressed(Point2D.Float point) {return rect.contains(point);}
	
}
