package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;

import javax.swing.JFrame;

import handler.KeyDispatcher;

@SuppressWarnings("serial")
public class IMMainFrame extends JFrame {

	IMMainPanel iMMainPanel;

	public IMMainFrame() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize.width, screenSize.height);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setLayout(new BorderLayout());
		iMMainPanel = new IMMainPanel();
		this.add(iMMainPanel, BorderLayout.CENTER);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		KeyboardFocusManager manager;
		manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
	    manager.addKeyEventDispatcher(new KeyDispatcher(this));
	    
		this.setVisible(true);
	}

	public void initialize() {iMMainPanel.initialize();}
	public void save() {iMMainPanel.save();}
	public void load() {iMMainPanel.load();}
	public void clear() {iMMainPanel.clear();}
	public void reset() {iMMainPanel.reset();}
	public void addIMG() {iMMainPanel.addIMG();}
	
}
