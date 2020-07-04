package handler;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

import stuff.Enums.eColor;
import stuff.PixelGlobalData;
import view.IMMainFrame;

public class KeyDispatcher implements KeyEventDispatcher {// 키이벤트가 포커스에 상관이 없게 해주는 것?
	
	boolean needWait = false;
	int keyCode = 0;
	IMMainFrame imMainFrame;
	public KeyDispatcher(IMMainFrame imMainFrame) {
		this.imMainFrame=imMainFrame;
	}

	private boolean keyIs(int vkControl) {return keyCode == vkControl;}
	
	public boolean dispatchKeyEvent(KeyEvent e) {
		keyCode = e.getKeyCode();
		if (e.getID() == KeyEvent.KEY_PRESSED) {
			if(keyIs(KeyEvent.VK_ESCAPE)) {
				System.exit(0);
			}
			if(!needWait) {
				if (keyIs(KeyEvent.VK_S)) {
					needWait = true;
					imMainFrame.save();
					needWait = false;
				} else if (keyIs(KeyEvent.VK_L)) {
					needWait = true;
					imMainFrame.load();
					needWait = false;
				} else if (keyIs(KeyEvent.VK_C)) {
					imMainFrame.clear();
				} else if (keyIs(KeyEvent.VK_R)) {
					needWait = true;
					imMainFrame.reset();
					needWait = false;
				}else if (keyIs(KeyEvent.VK_I)) {
					needWait = true;
					imMainFrame.addIMG();
					needWait = false;
				}else {
					try {
						int pressedKey = Integer.parseInt(e.getKeyChar()+"");
						if(-1<pressedKey&&pressedKey<11) {
							PixelGlobalData.setNowColor(eColor.values()[pressedKey-1]);
						}	
					}catch(Exception ee) {}
				}
			}
		}else if (e.getID() == KeyEvent.KEY_RELEASED) {
		}
		return false;
	}
	

}
