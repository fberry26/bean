package komorebi.bean.engine;
import java.awt.Rectangle;
import java.util.Random;

import org.lwjgl.input.Keyboard;

public class Game {

	private static Square[][] squares;
	
	private static Rectangle bean = new Rectangle(24, 32);
	private static Rectangle futureBean = new Rectangle(24,32);
	
	private static int dx = 0, dy = 0, prevDy;
	private static double m;
	
	private static boolean jumpKeyDown = false;
	private static int jumpCount = 0;
	
	static int frame = 1;
	static int increment = 1;
	
	
	private static boolean isOnGround = false;
	private static boolean isOnTreadmill = false;
	private static boolean isStarted = false;
	
	private static Random random = new Random();
	
	private static TileColor randomColor;
	
	private static boolean cannotGoRight, cannotGoLeft;

	
	public Game() {
		
	}
	
	/**
	 * Begins a new "life" for the bean and resets the color scheme
	 */
	public static void start() {
		squares = Map.getSquares();
		
		randomColor = TileColor.getCorrespondingColor(random.nextInt(8));
		
		isStarted = true;
		
		for (int i=0; i<20; i++)
		{
			for (int j=0; j<16; j++)
			{
				if (squares[i][j].getType()==TileType.BEAN) {
					bean.setLocation(squares[i][j].getX(), squares[i][j].getY());
				} else if (squares[i][j].getType()==TileType.TILE)
				{
					squares[i][j].setColor(randomColor);
				}
				
			}
		}
		
		Main.setBackground(BackgroundColor.getCorrespondingColor(randomColor));
		
	}
	
	/**
	 * Tracks user's keyboard input
	 */
	public static void getInput() {
		
		prevDy = dy;
		
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT) && !cannotGoLeft)dx = -4;
		else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT) && !isOnTreadmill && !cannotGoRight) dx = 4;
		else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT) && !cannotGoRight) dx=2;
		else if (dx>0) dx--;
		else if (dx<0 && !isOnTreadmill) dx++;
		
		if (Keyboard.isKeyDown(Keyboard.KEY_UP) && !jumpKeyDown && jumpCount<2)
		{
			jumpKeyDown = true;
			isOnGround = false;
			isOnTreadmill = false;
			
			dy = 15;	
			jumpCount++;
			
		} else if (!Keyboard.isKeyDown(Keyboard.KEY_UP)) jumpKeyDown = false;
		
		if (!isOnGround && dy>-32) dy--; 
		else jumpCount = 0;
		
	}
	
	/**
	 * Updates the bean's behavior based on the user's input
	 */
	public static void update() {
		
		futureBean.setLocation(bean.x+dx, bean.y+dy);
		
		for (int i=0; i<20; i++)
		{
			for (int j=0; j<16; j++)
			{
				if (squares[i][j].box.intersects(futureBean) && !squares[i][j].canBePassed())
				{
					
					//falling straight down
					if (dy<0)
					{			
						
						
						
						
						
						//System.out.println("Down");
						int u = (bean.y/32);
						u = u*32;
						
						if (prevDy!=0) 
						{
							dy = 0;
							
							bean.y = u;
							isOnGround = true;
							cannotGoRight=false;
							cannotGoLeft=false;
							System.out.println(futureBean.getLocation());
						} else 
						{
							
							/*
							 * locx and locy locate the square BELOW the square the bean is in
							 */
							int locx=bean.x/32;
							int locy=bean.y/32-1;
							
							//if the square below is nonexistent
							if (locy==-1) locy+=16;
							
							if (!squares[locx][locy].canBePassed())
							{
								dx=0;
								dy=0;
								isOnGround = true;
								cannotGoRight=false;
								cannotGoLeft=false;
							} else
							{
								if (dx>0) {
									dx=0;
									cannotGoRight=true;
								} else if (dx<0)
								{
									dx=0;
									cannotGoLeft=true;
								}
								
								
							}
							
							
						}
						
						dx = 0;
												
						
						
						
											
						
					//jumping straight up
					} else if (dy>0)
					{
						//System.out.println("Up");
						int u;
						
						if ((bean.y%32)==0) u=bean.y/32-1;
						else u = (bean.y/32);
						
						u = u*32;
						
						dy = 0;
						dx=0;

						bean.y = u+32;
						jumpCount = 2;
						
					//running straight right
					} else if (dx>0)
					{
						//System.out.println("Right");
						int t = bean.x/32;
						t = t*32;
						
						dx=0;
						bean.x = t+8;
											
					} else if (dx<0)
					{
						//System.out.println("Left");
						int t = bean.x/32;
						t=t*32;
						dx=0;
						bean.x = t;
						
					}
				}
			}
		}
	
		
		
		
		if (isOnGround) {
			outerLoop:
			for (int i=0; i<20; i++)
			{
				for (int j=0; j<16; j++)
				{
					if (dy<=0 && squares[i][j].boxAbove.intersects(bean) && !squares[i][j].canBePassed()) {
						
						if (squares[i][j].getType()==TileType.TREADMILL_LEFT || squares[i][j].getType()==TileType.TREADMILL_CENTER || squares[i][j].getType()==TileType.TREADMILL_RIGHT)
						{
							if (!isOnTreadmill) dx=-2;
							
							isOnTreadmill = true;
						}else 
						{
							isOnTreadmill = false;
						}
						break outerLoop;
						
					} 
					
					
					if (j==15 && i==19) {
						isOnGround = false;
						isOnTreadmill = false;
					}
						
				
				}
			}
			
			
		} 
			
		
		
		
		/*
		for (int i=0; i<20; i++)
		{
			for (int j=0; j<16; j++)
			{		
				if (dy>0 && bean.intersects(squares[i][j].boxBelow) && !squares[i][j].canBePassed()) {
					int u = bean.y/32;
					u = u*32;
					
					if ((bean.y-u)<dy) {
						dy=0;
						bean.y = u+32;
						jumpCount = 2;
						
						if (bean.intersects(squares[i][j].box)) bean.y-=32;
						
					}
				} else if (dx<0 && bean.intersects(squares[i][j].boxRight) && !squares[i][j].canBePassed())
				{
					
					int t = bean.x/32;
					t=t*32;
					
					if ((bean.x-t)<=4) {
						dx=0;
						bean.x = t;
						
					}
				} else if (dx>0 && bean.intersects(squares[i][j].boxLeft) && !squares[i][j].canBePassed())
				{
					int t = bean.x/32;
					t = t*32;
					
					if ((bean.x-t)<=4)
					{
						dx=0;
						bean.x = t+4;
					}
				} else if (bean.intersects(squares[i][j].box) && !squares[i][j].canBePassed()) {
					int u = bean.y/32;
					u = u*32;
					
					bean.y = u+32;
				}
				
				if (bean.intersects(squares[i][j].box)) {
					
					if (squares[i][j].getType() == TileType.FLAG) System.out.println("You win");
					else if (squares[i][j].isLethal()){
						System.out.println("You dead");
						Game.respawn();
					}
					
				}
				
					
			}
		
		}
		
		*/
	
		
		bean.x += dx;
		bean.y += dy;
			
		if (bean.y>=512) bean.y-=512;
		if (bean.y<0) bean.y+=512;
	}
	
	/**
	 * Renders all occupied squares
	 */
	public static void render() {
		
		if (frame>8) frame = 1;
		
		for (int i=0; i<20; i++)
		{
			for (int j=0; j<16; j++)
			{
				if (squares[i][j].getType()==TileType.TREADMILL_LEFT || squares[i][j].getType()==TileType.TREADMILL_CENTER || squares[i][j].getType()==TileType.TREADMILL_RIGHT)
				{
					squares[i][j].animate(frame);
				}
				else if (squares[i][j].getType()!=TileType.BLANK && squares[i][j].getType()!=TileType.BEAN) squares[i][j].render();
			}
		}
		
		if (increment>4) {
			frame++;
			increment = 0;
		} else
		{
			increment++;
		}
		
		
		Draw.drawSquare(TileType.BEAN, (int) bean.getX(), (int) bean.getY(), 2);
	}
	
	/**
	 * Ends the "life" of the bean and returns to edit mode
	 */
	public static void end() {
		isStarted = false;
		dx=0;
		dy=0;
		
		for (int i=0; i<20; i++)
		{
			for (int j=0; j<16; j++)
			{
				if (squares[i][j].getType()==TileType.TILE) squares[i][j].setColor(TileColor.RED);
			}
		}
		
		Main.setBackground(BackgroundColor.STANDARD);
		
	}
	
	/**
	 * @return Whether the game is active
	 */
	public static boolean getIsStarted() 
	{
		return isStarted;
	}
	
	/**
	 * Ends and restarts the game
	 */
	public static void respawn() {
		end();
		start();
	}
	
}
