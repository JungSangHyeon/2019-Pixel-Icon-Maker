package testView;

import java.awt.BorderLayout;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class PainterMainFrame extends JFrame{

	PainterMainPanel PainterMainPanel;
	
	public PainterMainFrame() {
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		PainterMainPanel = new PainterMainPanel();
		this.add(PainterMainPanel, BorderLayout.CENTER);
		
		this.setVisible(true);
	}

	public void initiate() {
		PainterMainPanel.initiate();
	}
}
