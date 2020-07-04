package iconToImage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import iconPainter.IconColorEnum.eEditColor;

public class IconToImage {

	static BufferedImage img;
	
	@SuppressWarnings("resource")
	public static BufferedImage getImg(String fileAddress, int scaleX, int scaleY) {
		String w = fileAddress.substring(fileAddress.length()-9, fileAddress.length()-7);
		String h = fileAddress.substring(fileAddress.length()-6, fileAddress.length()-4);
		BufferedImage image = new BufferedImage(Integer.parseInt(w)*scaleX, Integer.parseInt(h)*scaleY, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.createGraphics(); 
		g.setColor(Color.WHITE);
		g.fillRect(0, 0,Integer.parseInt(w)*scaleX, Integer.parseInt(h)*scaleY);
		try {
			Scanner sc = new Scanner(new File(fileAddress));
			int id;
			int x, y;
			while (sc.hasNext()) {
				id = sc.nextInt();
				x = sc.nextInt()*scaleX;
				y = sc.nextInt()*scaleY;
				for (eEditColor ecolor : eEditColor.values()) {
					if (ecolor.getID()==id) {
						g.setColor(ecolor.getColor());
						g.fillRect(x,y,scaleX,scaleY);
						break;
					}
				}
			}
		} catch (FileNotFoundException e) {e.printStackTrace();}
		return image;
	}
	
}
