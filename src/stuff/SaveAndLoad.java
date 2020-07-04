package stuff;

import java.awt.Point;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import stuff.Enums.eColor;

public class SaveAndLoad {
	
	File loadFile;
	
	public void save(Vector<Pixel> pixelVector, int wPixelNum, int hPixelNum) {
		if(loadFile==null) {
			String[] buttons = { "��������", "������!" };
			int num = JOptionPane.showOptionDialog(null, "�� ��� ���ٱ�", "��-��-��", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE, null, buttons, null);
			if(num==0) {
				BufferedWriter out = getNewFile(wPixelNum, hPixelNum);
				writeOnFIle(out, pixelVector);
			}
		}else {
			String[] buttons = { "�����", "���θ������", "������!" };
			int num = JOptionPane.showOptionDialog(null, "�� ��� ���ٱ�", "��-��-��", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, null);
			if (num == 0 || num == 1) {
				try {
					BufferedWriter out = null;
					if (num == 1) {out = getNewFile(wPixelNum, hPixelNum);}
					else {out = new BufferedWriter(new FileWriter(loadFile, false));}
					writeOnFIle(out, pixelVector);
				} catch (IOException e) {System.err.println(e);}// ������ �ִٸ� �޽��� ���
			}
		}
	}

	public Point load(Vector<Pixel> pixelVector) {
		Point xy = null;
		JFileChooser chooser = new JFileChooser("Icons/"); // ��ü ����
		int ret = chooser.showOpenDialog(null); // ����â ����
		if (ret != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "��θ� ���������ʾҽ��ϴ�.", "�� �ֺҷ���?", JOptionPane.WARNING_MESSAGE);
			return null;
		}
		loadFile = chooser.getSelectedFile();
		
		String s = loadFile.getName();
		String w = s.substring(s.length()-9, s.length()-7);
		String h = s.substring(s.length()-6, s.length()-4);
		xy = new Point(Integer.parseInt(w), Integer.parseInt(h));
		return xy;
	}
	
	public void update(Vector<Pixel> pixelVector) {
		for (Pixel pixel : pixelVector) {
			pixel.setColor(eColor.Color1);
		}
		try {
			Scanner sc = new Scanner(loadFile);
			int id = 0;
			int x, y;
			while (sc.hasNext()) {
				id = sc.nextInt();
				x = sc.nextInt();
				y = sc.nextInt();
				for (Pixel pixel : pixelVector) {//��û �������� ������ ������. �ð����� �� ���� ������.
					if (pixel.getPoint().getX() == x && pixel.getPoint().getY() == y) {
						for (eColor ecolor : eColor.values()) {
							if (ecolor.getName()==id) {
								pixel.setColor(ecolor);
								break;// for������?
							}
						}
					}
				}
			}
		} catch (FileNotFoundException e) {e.printStackTrace();}
	}
	
	public File loadImg() {
		JFileChooser chooser = new JFileChooser("behindIMG/");// ��ü ����
		int ret = chooser.showOpenDialog(null); // ����â ����
		if (ret != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "��θ� ���������ʾҽ��ϴ�.", "�� �ֺҷ���?", JOptionPane.WARNING_MESSAGE);
			return null;
		}
		System.out.println(chooser.getSelectedFile().getName());
		return chooser.getSelectedFile();
	}
	
	private BufferedWriter getNewFile(int wPixelNum, int hPixelNum) {
		String name = "";
		while (name.equals("")) {
			name = JOptionPane.showInputDialog(null, "���� �̸� ���� �ϽǶ��?");
			if (name.equals("")) {JOptionPane.showMessageDialog(null, "�̸� �ȶ� ������");}
		}
		try {return new BufferedWriter(new FileWriter("Icons/" + name + "_" + wPixelNum + "X" + hPixelNum + ".txt", false));}
		catch (IOException e) {System.err.println(e);}// ������ �ִٸ� �޽��� ���
		return null;
	}
	
	private void writeOnFIle(BufferedWriter out, Vector<Pixel> pixelVector) {
		try {
			String s = "";
			for (Pixel pixel : pixelVector) {
				if (pixel.getColor() != eColor.Color1) {
					s = pixel.getColor().getName() + " " + (int) pixel.getPoint().getX() + " "+ (int) pixel.getPoint().getY();
					out.write(s);
					out.newLine();
				}
			}
			out.close();
		} catch (IOException e) {System.err.println(e);}// ������ �ִٸ� �޽��� ���
		JOptionPane.showMessageDialog(null, "�ߴ�.");
	}

	public void clear() {
		loadFile = null;
	}
	
}
