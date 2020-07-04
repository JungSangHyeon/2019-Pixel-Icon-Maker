package main;

import java.io.IOException;

import view.IMMainFrame;

public class IconMakerMain {//아이콘 확장 가능하게 만들 것.
	
	public static void main(String[] args) throws IOException {//메인은 아니라, 좀 드럽슴다. 시간 나면 고칠라구요. 
		IMMainFrame mainFrame = new IMMainFrame();
		mainFrame.initialize();
	}
	
}
//그림판 만든 후 다양한 색 선택 가능하게 하기. 툴바의 것 가져다 쓰기.
