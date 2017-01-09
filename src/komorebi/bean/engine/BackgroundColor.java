package komorebi.bean.engine;

public enum BackgroundColor {
	RED(211f/255f, 118f/255f, 132f/255f),
	ORANGE(211f/255f, 160f/255f, 88f/255f),
	YELLOW(205f/255f, 211f/255f, 86f/255f),
	GREEN(114f/255f,211f/255f,119f/255f),
	BLUE(147f/255f,192f/255f,1f),
	PURPLE(183f/255f,154f/255f,211f/255f),
	PINK(252f/255f,199f/255f,239f/255f),
	WHITE(224f/255f,224f/255f,224f/255f),
	STANDARD(0,0,0);
	
	float r, g, b;
	
	BackgroundColor(float red, float green, float blue)
	{
		this.r = red;
		this.g = green;
		this.b = blue;
		
	}
	
	public static BackgroundColor getCorrespondingColor(TileColor tile)
	{
		switch (tile)
		{
		case BLUE:
			return BackgroundColor.BLUE;
		case GREEN:
			return BackgroundColor.GREEN;
		case ORANGE:
			return BackgroundColor.ORANGE;
		case PINK:
			return BackgroundColor.PINK;
		case PURPLE:
			return BackgroundColor.PURPLE;
		case RED:
			return BackgroundColor.RED;
		case WHITE:
			return BackgroundColor.WHITE;
		case YELLOW:
			return BackgroundColor.YELLOW;
		default:
			return BackgroundColor.STANDARD;
		}
	
	}
	
	public float getR()
	{
		return r;
	}
	
	public float getG()
	{
		return g;
	}
	
	public float getB()
	{
		return b;
	}
}
