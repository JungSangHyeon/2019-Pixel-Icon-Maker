package iconPainter;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import iconPainter.IconColorEnum.eEditColor;

public class IconToImage {

	static BufferedImage img;
	
	@SuppressWarnings("resource")
	public static BufferedImage getImg(String fileAddress) {
		String w = fileAddress.substring(fileAddress.length()-9, fileAddress.length()-7);
		String h = fileAddress.substring(fileAddress.length()-6, fileAddress.length()-4);
		BufferedImage image = new BufferedImage(Integer.parseInt(w), Integer.parseInt(h), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = image.createGraphics(); 
//		g2d.fillRect(0, 0,Integer.parseInt(w), Integer.parseInt(h));//BG Color
		try {
			Scanner sc = new Scanner(new File(fileAddress));
			int id, x, y;
			while (sc.hasNext()) {
				id = sc.nextInt();
				x = sc.nextInt();
				y = sc.nextInt();
				for (eEditColor ecolor : eEditColor.values()) {
					if (ecolor.getID()==id) {
						g2d.setColor(ecolor.getColor());
						g2d.fillRect(x,y,1,1);
						break;
					}
				}
			}
			
		} catch (FileNotFoundException e) {System.out.println(fileAddress+"에 아이콘이 없습니다.");}
		return image;
	}
	
}
