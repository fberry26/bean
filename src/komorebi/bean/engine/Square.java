package komorebi.bean.engine;
import java.awt.Dimension;
import java.awt.Rectangle;

public class Square {
	
	private TileType myType;
	public Rectangle area = new Rectangle();
	
	public Rectangle boxBelow = new Rectangle();
	public Rectangle boxAbove = new Rectangle();
	public Rectangle boxLeft = new Rectangle();
	public Rectangle boxRight =  new Rectangle();
	
	public Rectangle box = new Rectangle();
	
	private boolean isPassable;
	private boolean isLethal;
	
	private int pixX, pixY;
	private int offX, offY;
	
	
	private TileColor color;
	private int scalar;
	
	/**
	 * Creates an instance of a square on the map or palette
	 * @param type The tipe of tile to be created
	 * @param pixX The bottom-left horizontal coordinate (in pixels) of the square
	 * @param pixY The bottom-left vertical coordinate (in pixels) of the square
	 * @param scalar Scale of the tile to be multiplied by (1 = 16 x 16 square)
	 */
	public Square(TileType type, int pixX, int pixY, int scalar) {

		area.setLocation(pixX, pixY);
		area.setSize(new Dimension(16*scalar, 16*scalar));
		
		myType = type;
		setAttributes(type);
		this.pixX = pixX;
		this.pixY = pixY;
		this.scalar = scalar;
	}
	
	/**
	 * Sets the behaviors of the tile based on its type
	 * @param type Type of type to be set
	 */
	public void setAttributes(TileType type) {
		switch (type)
		{
		case BEAN:
			offX=2;
			offY=0;
			
			boxAbove.setSize(new Dimension(0, 0));
			boxAbove.setLocation(pixX+offX, pixY+offY+32);
			
			isPassable = true;
			isLethal = false;
			break;
		case FLAG:
			offX=2;
			offY=0;
			
			boxBelow.setSize(new Dimension(0, 0));
			boxBelow.setLocation(0, 0);
			
			boxAbove.setSize(new Dimension(0, 0));
			boxAbove.setLocation(0, 0);
			
			boxLeft.setSize(new Dimension(0, 0));
			boxLeft.setLocation(0, 0);
			
			boxRight.setSize(new Dimension(0, 0));
			boxRight.setLocation(0, 0);
			
			box.setSize(new Dimension(24, 32));
			box.setLocation(pixX+offX, pixY+offY);
			
			isPassable = true;
			isLethal = false;
			break;
		case GATE:
			offX=6;
			offY=0;
			
			boxBelow.setSize(new Dimension(8, 32));
			boxBelow.setLocation(pixX+offX, pixY+offY-32);
			
			boxAbove.setSize(new Dimension(8, 32));
			boxAbove.setLocation(pixX+offX, pixY+offY+32);
			
			isPassable = false;
			isLethal = false;
			break;
		case SINGLE_BEAM:
			offX=4;
			offY=0;
			
			boxBelow.setSize(new Dimension(16, 32));
			boxBelow.setLocation(pixX+offX, pixY+offY-32);
			
			
			boxAbove.setSize(new Dimension(16, 32));
			boxAbove.setLocation(pixX+offX, pixY+offY+32);
			
			isPassable = false;
			isLethal = true;
			break;
		case BUTTON:
			offX=4;
			offY=0;
			
			boxBelow.setSize(new Dimension(16, 8));
			boxBelow.setLocation(pixX+offX, pixY+offY-8);
			
			boxAbove.setSize(new Dimension(16, 8));
			boxAbove.setLocation(pixX+offX, pixY+offY+8);
			
			isLethal = false;
			isPassable = true;
			break;
		case DOUBLE_BEAM_TOP:
			offX=4;
			offY=0;
			
			boxBelow.setSize(new Dimension(16, 32));
			boxBelow.setLocation(pixX+offX, pixY+offY-32);
			
			boxAbove.setSize(new Dimension(16, 32));
			boxAbove.setLocation(pixX+offX, pixY+offY+32);
			
			isPassable = false;
			isLethal = true;
			break;
		case DOUBLE_BEAM_BOTTOM:
			offX=4;
			offY=0;
			
			boxBelow.setSize(new Dimension(16, 32));
			boxBelow.setLocation(pixX+offX, pixY+offY-32);
			
			boxAbove.setSize(new Dimension(16, 32));
			boxAbove.setLocation(pixX+offX, pixY+offY+32);
			
			isPassable = false;
			isLethal = true;
			break;
		case TURRET:
			offX = 2;
			offY = 0;
			isPassable = false;
			isLethal = false;
		
			
			boxBelow.setSize(new Dimension(16, 32));
			boxBelow.setLocation(pixX+offX, pixY+offY-32);
			
			boxAbove.setSize(new Dimension(16, 32));
			boxAbove.setLocation(pixX+offX, pixY+offY+32);
			
		case BLANK:
			offX=0;
			offY=0;
			
			isPassable = true;
			isLethal = false;
			
			//after this
			
			boxBelow.setSize(new Dimension(16, 32));
			boxBelow.setLocation(pixX+offX, pixY+offY-32);
			
			boxAbove.setSize(new Dimension(16, 32));
			boxAbove.setLocation(pixX+offX, pixY+offY+32);
			break;
		case TILE:
			offX=0;
			offY=0;
			
			boxBelow.setSize(new Dimension(32, 32));
			boxBelow.setLocation(pixX+offX, pixY+offY-32);
			
			boxAbove.setSize(new Dimension(32, 32));
			boxAbove.setLocation(pixX+offX, pixY+offY+32);
			
			boxLeft.setSize(new Dimension(32, 32));
			boxLeft.setLocation(pixX+offX-32, pixY+offY);
			
			boxRight.setSize(new Dimension(32, 32));
			boxRight.setLocation(pixX+offX+32, pixY+offY);
			
			box.setSize(new Dimension(32, 32));
			box.setLocation(pixX+offX, pixY+offY);
			
			isPassable = false;
			isLethal = false;
			
			break;
		case TREADMILL_LEFT: case TREADMILL_CENTER: case TREADMILL_RIGHT:
			boxBelow.setSize(new Dimension(32, 32));
			boxBelow.setLocation(pixX+offX, pixY+offY-32);
			
			boxAbove.setSize(new Dimension(32, 32));
			boxAbove.setLocation(pixX+offX, pixY+offY+32);
			
			boxLeft.setSize(new Dimension(32, 32));
			boxLeft.setLocation(pixX+offX-32, pixY+offY);
			
			boxRight.setSize(new Dimension(32, 32));
			boxRight.setLocation(pixX+offX+32, pixY+offY);
			
			box.setSize(new Dimension(32, 32));
			box.setLocation(pixX+offX, pixY+offY);
			
			isPassable = false;
			isLethal = false;
			break;
		case SPIKE_SINGLE:
		case SPIKE_END_LEFT:
		case SPIKE_END_RIGHT:
		case SPIKE_MIDDLE:
			offX=0;
			offY=0;
			
			box.setSize(new Dimension(32, 32));
			box.setLocation(pixX+offX, pixY+offY);
			
			isPassable = false;
			isLethal = true;
			break;
		default:
			offX=0;
			offY=0;
			isPassable = true;
			isLethal = false;
			
			boxAbove.setSize(new Dimension(32, 32));
			boxAbove.setLocation(pixX+offX, pixY+offY+32);
			break;
			
		}
		
		if (pixY==0) boxBelow.setLocation(pixX, 456);
	}
	
	/**
	 * Renders the tiles on the display
	 */
	public void render() {
		if (this.myType==TileType.TILE && color!=null) render(color);
		else Draw.drawSquare(myType, pixX+offX, pixY+offY, scalar);
	}
	
	/**
	 * Renders a TileType.TILE square of a specific color
	 * @param color Color of the tile
	 */
	public void render(TileColor color)
	{
		Draw.drawColoredTile(color, pixX+offX, pixY+offY, scalar);
	}
	
	
	/**
	 * Renders a specified frame of an animation
	 * @param frame Frame of the animation
	 */
	public void animate(int frame)
	{
		Draw.drawAnimation(myType, pixX+offX, pixY+offY, scalar, frame);
		
	}
	
	/**
	 * Sets the TileType of the square
	 * @param t Type of the square to be set
	 */
	public void setType(TileType t) {
		this.myType = t;
		setAttributes(t);
	}
	
	/**
	 * 
	 * @return The current type of the tile
	 */
	public TileType getType()
	{
		return this.myType;
	}
	
	/**
	 * 
	 * @return Bottom-left horizontal coordinate (in pixels) of the square
	 */
	public int getX()
	{
		return this.pixX;
	}
	
	/**
	 * 
	 * @return Bottom-left vertical coordinate (in pixels) of the square
	 */
	public int getY()
	{
		return this.pixY;
	}
	
	/**
	 * 
	 * @return Whether the square is opaque or passable
	 */
	public boolean canBePassed()
	{
		return isPassable;
	}
	
	public boolean isLethal() {
		return isLethal;
	}
	
	public void setColor(TileColor newColor) {
		color = newColor;
	}
}
