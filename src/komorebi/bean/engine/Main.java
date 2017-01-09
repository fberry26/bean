package komorebi.bean.engine;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import java.awt.Rectangle;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Main {
	
	private static Rectangle click = new Rectangle(1,1);
	private static TileType selectedType = TileType.TILE;
	
	private static Square beanSquare = null;
	private static Square flagSquare = null;
	
	private static boolean isEditing = true;
	private static boolean mouseDown;
	
	public static void main(String[] args) {
		
		initDisplay(); //initializes the GL display
		initGL(); //more initialization crap
		
		try {
			Mouse.create();
			Keyboard.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		Draw.loadTextures();
		
		Map.create();
		Palette.create();
		Selector.create();
		Tools.create();
		
		render(); //rendering loop while level editor runs
		cleanup(); 
		
	}
	
	private static void initDisplay() { //initializes the LWJGL display
		try {
			Display.setDisplayMode(new DisplayMode(736, 512));
			Display.setTitle("Bean Level Editor"); //sizes and sets title of main display
			Display.create();
			Display.setVSyncEnabled(true);
			
			
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	private static void initGL() { //initializes the graphics field
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 736, 0, 512, -1, 1); //creates the graphic layout (3d array)
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND); //allows transparency
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glClearColor(0, 0, 0, 1); //rgb, opacity
		glDisable(GL_DEPTH_TEST); 
		
	}
	
	private static void render() {
		
		while (!Display.isCloseRequested()) { //edit loop

			glClear(GL_COLOR_BUFFER_BIT); //clears screen
			glLoadIdentity();
			
			
			
			//render whatever here
			
			if (isEditing)
			{
				getEditInput();
				Map.render();
				Palette.render();
				Selector.render();
				Tools.render(true);
			}
			else
			{
				Game.getInput();
				Game.update();
				getPlayInput();
				Tools.render(false);
				Game.render();
			}
			
			Display.update(); //updates display
			Display.sync(60);
		
		}

	}
	
	private static void cleanup() {
		Display.destroy(); //gets rid of LWJGL
		System.exit(0); //safety net for outstanding threads
	}
	
	private static void getEditInput() {
		if (Mouse.isButtonDown(0)) {
			click.setLocation(Mouse.getX(), Mouse.getY());
			
			if (Map.intersectsSquare(click)!=null)
			{
				Square s = Map.intersectsSquare(click);
				s.setType(selectedType);
				
				if (selectedType==TileType.BEAN && beanSquare!=null & !s.equals(beanSquare))
				{
					beanSquare.setType(TileType.BLANK);
					beanSquare=s;
				}
				
				if (s.equals(beanSquare) && selectedType!=TileType.BEAN)
				{
					beanSquare=null;
				}
				
				if (beanSquare==null && selectedType==TileType.BEAN)
				{
					beanSquare = s;
				}
				
				if (selectedType==TileType.FLAG && flagSquare!=null & !s.equals(flagSquare))
				{
					flagSquare.setType(TileType.BLANK);
					flagSquare=s;
				}
				
				if (s.equals(flagSquare) && selectedType!=TileType.FLAG)
				{
					flagSquare=null;
				}
				
				if (flagSquare==null && selectedType==TileType.FLAG)
				{
					flagSquare = s;
				}
				
				
			}
			else if (Palette.intersectsSquare(click)!=null)
			{
				selectedType = Palette.intersectsSquare(click).getType();
				Selector.update(Palette.intersectsSquare(click).getX(), Palette.intersectsSquare(click).getY());
			}
			else if (!mouseDown && Tools.isPlayStopButtonIntersected(click)) {
				isEditing = false;
				mouseDown = true;
				Game.start();
			}
			
		} 
		else
		{
			mouseDown = false;
		}
		
		
		
		
	}
	
	public static void getPlayInput() {
		
		if (Mouse.isButtonDown(0))
		{
			click.setLocation(Mouse.getX(), Mouse.getY());
			
			if (!mouseDown && Tools.isPlayStopButtonIntersected(click)) 
			{
				isEditing = true;
				mouseDown = true;
				Game.end();
			}
			
		}
		else
		{
			mouseDown = false;
		}
	}
	
	/**
	 * Adjusts the window background to a specified color
	 * @param color The background color to be set
	 */
	public static void setBackground(BackgroundColor color)
	{
		System.out.println(color.getR());
		glClearColor(color.getR(), color.getG(), color.getB(), 1); //rgb, opacity
	}

}
