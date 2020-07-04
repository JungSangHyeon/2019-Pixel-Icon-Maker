package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import handler.IconMakerMouseHadler;
import stuff.Enums.eColor;
import stuff.MoveAndZoom;
import stuff.Pixel;
import stuff.PixelGlobalData;
import stuff.SaveAndLoad;

@SuppressWarnings("serial")
public class IMMainPanel extends JPanel {

	private int iconW = 1000, iconH = 1000, pixelGap = 1;//프로그래머가 바꿈. 아니면 이상한 사용자가 바꿈.
	
	private int startX, startY;//안바꿈
	private BufferedImage BGimg = null;
	private BufferedImage behindeImg = null;
	private Vector<Pixel> pixelVector;
	private SaveAndLoad saveAndLoad;
	private int wPixelNum = 10, hPixelNum = 10;
	private IconMakerMouseHadler mouseHandler;

	public IMMainPanel() {
		this.setBackground(Color.LIGHT_GRAY);
		
		try {
			this.BGimg = ImageIO.read(new File("IconMakerBG.png"));
			this.behindeImg = ImageIO.read(new File("behindIMG/"+"peqn.png"));
		}catch (Exception e) {}//없으면 그냥 안띄움.
		
		saveAndLoad = new SaveAndLoad();
		pixelVector = new Vector<Pixel>();
		mouseHandler = new IconMakerMouseHadler(this);

		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		this.addMouseWheelListener(mouseHandler);
	}

	public void initialize() {
		pixelVector = new Vector<Pixel>();
		int many = Math.max(wPixelNum, hPixelNum);
		int pixelWH = (iconW - pixelGap * (many + 1)) / many;
		iconW = pixelWH * wPixelNum + pixelGap * (wPixelNum + 1);
		iconH = pixelWH * hPixelNum + pixelGap * (hPixelNum + 1);
		startX = this.getWidth() / 2 - iconW / 2;
		startY = this.getHeight() / 2 - iconH / 2;
		int pixelX = startX;
		int pixelY = startY;

		for (int w = 0; w < wPixelNum; w++) {
			for (int h = 0; h < hPixelNum; h++) {
				pixelVector.add(new Pixel(pixelX, pixelY, pixelWH, pixelWH, w, h));
				pixelY += pixelGap + pixelWH;
			}
			pixelY = startY;
			pixelX += pixelGap + pixelWH;// 곱하기 안쓰려고!
		}
		repaint();
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		if(BGimg!=null) {
			g.drawImage(BGimg, this.getWidth()/2 - BGimg.getWidth()/2, this.getHeight()/2 - BGimg.getHeight()/2, null);
		}	
		
		g2d.setTransform(MoveAndZoom.getAT());
		g2d.setColor(Color.gray);
		g2d.fill(new Rectangle(startX - pixelGap, startY - pixelGap, iconW, iconH));

		if(BGimg!=null) {
			g.drawImage(behindeImg, startX - pixelGap, startY - pixelGap, iconW, iconH, null);
		}
		
		for (Pixel pixel : pixelVector) {
			g2d.setColor(pixel.getColor().getColor());
			g2d.fill(pixel.getRect());
		}
		g2d.setTransform(new AffineTransform());
	}

	public void paintPixel(Point2D.Float point) {
		for (Pixel pixel : pixelVector) {
			if (pixel.isPressed(point)) {
				pixel.setColor(PixelGlobalData.getNowColor());
				repaint();
				return;
			}
		}
	}

	public void clear() {
		for (Pixel pixel : pixelVector) {
			pixel.setColor(eColor.Color1);
		}
		saveAndLoad.clear();
		repaint();
	}

	public void save() {saveAndLoad.save(pixelVector, wPixelNum, hPixelNum);}
	public void load() {
		Point p = saveAndLoad.load(pixelVector);
		wPixelNum =  p.x;
		hPixelNum =  p.y;
		initialize();
		saveAndLoad.update(pixelVector);
	}
	
	public void reset() {
		JTextField xField = new JTextField(5);
		JTextField yField = new JTextField(5);

		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel("x:"));
		myPanel.add(xField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("y:"));
		myPanel.add(yField);

		int result = JOptionPane.showConfirmDialog(null, myPanel, "x,y 픽셀개수 입력", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
			wPixelNum =  Integer.parseInt(xField.getText());
			hPixelNum =  Integer.parseInt(yField.getText());
			initialize();
		}
	}

	public void addIMG() {
		String[] buttons = { "뒤에꺼 지워", "불러와", "취소!" };
		int num = JOptionPane.showOptionDialog(null, "뭐 어떻게 해줄까", "이미지 로드", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, null);
		if(num==0) {
			this.behindeImg = null;
		}else if(num==1) {
			try {this.behindeImg = ImageIO.read(saveAndLoad.loadImg());}
			catch (IOException e1) {	e1.printStackTrace();}
		}
		repaint();
	}
}
