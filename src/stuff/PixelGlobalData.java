package stuff;

import stuff.Enums.eColor;

public class PixelGlobalData {

	static private eColor nowColor = eColor.Color2;
	public static eColor getNowColor() {return nowColor;}
	public static void setNowColor(eColor nowColor) {PixelGlobalData.nowColor = nowColor;}
	
}
