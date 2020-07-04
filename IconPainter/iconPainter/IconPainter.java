package iconPainter;

import java.awt.Graphics2D;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import iconPainter.IconColorEnum.eEditColor;

public class IconPainter {

	public static void IconPaint(Graphics2D g2d, String fileAddress, Point startPoint, int scale) {
		try {
			Scanner sc = new Scanner(new File(fileAddress));
			int id;
			int x, y;
			while (sc.hasNext()) {
				id = sc.nextInt();
				x = startPoint.x + sc.nextInt()*scale;
				y = startPoint.y + sc.nextInt()*scale;
				for (eEditColor ecolor : eEditColor.values()) {
					if (ecolor.getID()==id) {
						g2d.setColor(ecolor.getColor());
						g2d.fillRect(x,y,scale,scale);
						break;
					}
				}
			}
			
		} catch (FileNotFoundException e) {e.printStackTrace();}
	}
	
}
