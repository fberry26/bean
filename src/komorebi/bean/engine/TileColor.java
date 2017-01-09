package komorebi.bean.engine;

public enum TileColor {
	
	RED(0, 16, 16, 32),
	ORANGE(0, 32, 16, 48),
	YELLOW(0, 48, 16, 64),
	GREEN(0, 64, 16, 80),
	BLUE(0, 80, 16, 96),
	PURPLE(0, 96, 16, 112),
	PINK(0, 112, 16, 128),
	WHITE(0, 128, 16, 144);
	
	protected int texX, texY, texSx, texSy;
	
	TileColor(int texX, int texY, int texSx, int texSy)
	{
		this.texX = texX;
		this.texY = texY;
		this.texSx = texSx;
		this.texSy = texSy;
	}
	
	public int getTexX()
	{
		return texX;
	}
	public int getTexY()
	{
		return texY;
	}
	public int getTexSx()
	{
		return texSx;
	}

	public int getTexSy()
	{
		return texSy;
	}
	
	public static TileColor getCorrespondingColor(int i)
	{
		switch (i)
		{
		case 0:
			return RED;
		case 1:
			return ORANGE;
		case 2:
			return YELLOW;
		case 3:
			return GREEN;
		case 4:
			return BLUE;
		case 5:
			return PURPLE;
		case 6:
			return PINK;
		case 7:
			return WHITE;
		default:
			return WHITE;
		}
	
	}

}
