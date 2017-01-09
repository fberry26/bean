package komorebi.bean.engine;

public class Selector {

	private static int selectedX, selectedY;
	
	public Selector(){
		
	}
	
	public static void create() {
		selectedX = 672;
		selectedY= 480;
	}
	
	public static void render() {
		Draw.drawMenuItem(MenuItem.SELECTOR, selectedX, selectedY, 2);
	}
	
	public static void update(int x, int y)
	{
		selectedX = x;
		selectedY = y;
	}
	
}
