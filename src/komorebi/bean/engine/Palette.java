package komorebi.bean.engine;
import java.awt.Rectangle;

public class Palette {

	public static Square[] squares = new Square[18];
	
	public static void render()
	{
		for (int i=0; i<18; i++) {
		
			squares[i].render();
			
		}
	}
	
	public static void create() {
		squares[0] = new Square(TileType.TILE, 672, 480, 2);
		squares[1] = new Square(TileType.BEAN, 672, 448, 2);
		squares[2] = new Square(TileType.FLAG, 704, 448, 2);
		squares[3] = new Square(TileType.BLANK, 704, 480, 2);
		squares[4] = new Square(TileType.SPIKE_END_LEFT, 672, 416, 2);
		squares[5] = new Square(TileType.SPIKE_MIDDLE, 704, 416, 2);
		squares[6] = new Square(TileType.SPIKE_END_RIGHT, 672, 384, 2);
		squares[7] = new Square(TileType.SPIKE_SINGLE, 704, 384, 2);
		squares[8] = new Square(TileType.GATE, 672, 352, 2);
		squares[9] = new Square(TileType.SINGLE_BEAM, 704, 352, 2);
		squares[10] = new Square(TileType.BUTTON, 672, 320, 2);
		squares[11] = new Square(TileType.LADDER, 704, 320, 2);
		squares[12] = new Square(TileType.TREADMILL_LEFT, 672, 288, 2);
		squares[13] = new Square(TileType.TREADMILL_CENTER, 704, 288, 2);
		squares[14] = new Square(TileType.TREADMILL_RIGHT, 672, 256, 2);
		squares[15] = new Square(TileType.DOUBLE_BEAM_TOP, 704, 256, 2);
		squares[16] = new Square(TileType.TURRET, 672, 224, 2);
		squares[17] = new Square(TileType.DOUBLE_BEAM_BOTTOM, 704, 224, 2);
	}
	
	public static Square intersectsSquare(Rectangle click) 
	{
		
		for (int i=0; i<18; i++)
		{

			if (squares[i].area.intersects(click)) return squares[i];
			
		}
		
		return null;
		
	}
}
