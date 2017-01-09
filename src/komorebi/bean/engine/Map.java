package komorebi.bean.engine;
import java.awt.Rectangle;

public class Map {
	
	//horizontal, veritcal
	public static Square[][] squares = new Square[20][16];
	
	public Map() {
		
	}
	
	public static void render() {
		for (int i=0; i<20; i++) {
			for (int j=0; j<16; j++) {
				squares[i][j].render();
			}
		}
	}

	public static void create() {
		for (int i=0; i<20; i++) {
			for (int j=0; j<16; j++) {
				squares[i][j] = new Square(TileType.BLANK, i*32, j*32, 2);
			}
		}
	}
	
	public static Square intersectsSquare(Rectangle mouseInput)
	{
		for (int i=0; i<20; i++) 
		{
			for (int j=0; j<16; j++)
			{
				if (mouseInput.intersects(squares[i][j].area))
				{
					return squares[i][j];
				}
			}
		}
		
		return null;
		
	}
	
	public static Square[][] getSquares()
	{
		return squares;
	}
}
