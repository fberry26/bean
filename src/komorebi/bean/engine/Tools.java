package komorebi.bean.engine;
import java.awt.Rectangle;

public class Tools {

	private static Rectangle playStopButtonArea;
	
	private static int x = 704;
	private static int y = 0;
	
	public Tools() {
		
	}
	
	public static void create() {
		playStopButtonArea = new Rectangle(32, 32);
		playStopButtonArea.setLocation(x, y);
	}
	
	public static boolean isPlayStopButtonIntersected(Rectangle mouseClick) {
		if (mouseClick.intersects(playStopButtonArea)) return true;
		return false;
	}
	
	
	public static void render(boolean isEditing)
	{
		if (isEditing){
			Draw.drawMenuItem(MenuItem.PLAY, 704, 0, 2);
		}
		else
		{
			Draw.drawMenuItem(MenuItem.STOP, 704, 0, 2);
		}
	}
	
	
}
