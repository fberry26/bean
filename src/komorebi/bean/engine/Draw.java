package komorebi.bean.engine;
import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTexParameteri;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class Draw {
	
	public static Texture[] textures = new Texture[5];
	public static int[] imgX = new int[5];
	public static int[] imgY = new int[5];
	
	public static ArrayList<String> keys = new ArrayList<String>();
	
	public Draw() {
		
	}
	
	/**
	 * Draws a square or rectangular shape to be rendered on the display
	 * @param type Type of tile to be drawn
	 * @param x Bottom-left horizontal coordinate of the square
	 * @param y Bottom-left vertical coordinate of the square
	 * @param scalar Scale of the tile to be multiplied by (1 = 16 x 16 square)
	 */
	public static void drawSquare(TileType type, float x, float y, int scalar) {
	
			int texId, texX, texY, texSx, texSy, sx, sy;
			
			switch (type)
			{
			case BLANK:
				texX=0;
				texY=0;
				texSx=16;
				texSy=16;
				
				sx = 16*scalar;
				sy = 16*scalar;
				break;
			case TILE:
				texX = 0;
				texY = 16;
				texSx=16;
				texSy=32;
				
				sx = 16*scalar;
				sy = 16*scalar;
				break;
			case BEAN:
				texX = 0;
				texY = 0;
				texSx=12;
				texSy=16;
				
				sx = 12*scalar;
				sy = 16*scalar;
				break;
			case FLAG:
				texX = 96;
				texY = 0;
				texSx = 108;
				texSy = 16;
				
				sx = 12*scalar;
				sy = 16*scalar;
				break;
			case SPIKE_END_LEFT:
				texX = 112;
				texY = 16;
				texSx = 128;
				texSy = 32;
				
				sx = 16*scalar;
				sy = 16*scalar;
				break;
			case SPIKE_MIDDLE:
				texX = 64;
				texY = 16;
				texSx = 80;
				texSy = 32;
				
				sx = 16*scalar;
				sy = 16*scalar;
				break;
			case SPIKE_END_RIGHT:
				texX = 96;
				texY = 16;
				texSx = 112;
				texSy = 32;
				
				sx = 16*scalar;
				sy = 16*scalar;
				break;
			case SPIKE_SINGLE:
				texX = 80;
				texY = 16;
				texSx = 96;
				texSy=32;
				
				sx = 16*scalar;
				sy = 16*scalar;
				break;
			case GATE:
				texX = 128;
				texY = 16;
				texSx = 132;
				texSy=32;
				
				sx = 4*scalar;
				sy = 16*scalar;
				break;
			case SINGLE_BEAM:
				texX = 124;
				texY = 0;
				texSx = 132;
				texSy=16;
				
				sx = 8*scalar;
				sy = 16*scalar;
				break;
			case BUTTON:
				texX = 134;
				texY = 28;
				texSx = 142;
				texSy=32;
				
				sx = 8*scalar;
				sy = 4*scalar;
				break;
			case LADDER:
				texX = 128;
				texY = 128;
				texSx = 144;
				texSy=144;
				
				sx = 16*scalar;
				sy = 16*scalar;
				break;
			case TREADMILL_LEFT:
				texX = 0;
				texY = 160;
				texSx = 16;
				texSy= 176;
				
				sx = 16*scalar;
				sy = 16*scalar;
				break;
			case TREADMILL_CENTER:
				texX = 16;
				texY = 160;
				texSx = 32;
				texSy=176;
				
				sx = 16*scalar;
				sy = 16*scalar;
				break;
			case TREADMILL_RIGHT:
				texX = 32;
				texY = 160;
				texSx = 48;
				texSy=176;
				
				sx = 16*scalar;
				sy = 16*scalar;
				break;
			case DOUBLE_BEAM_TOP:
				texX = 144;
				texY = 32;
				texSx = 152;
				texSy = 48;
				
				sx = 8*scalar;
				sy = 16*scalar;
				break;
			case DOUBLE_BEAM_BOTTOM:
				texX = 144;
				texY = 48;
				texSx = 152;
				texSy=64;
				
				sx = 8*scalar;
				sy = 16*scalar;
				break;
			case TURRET:
				texX = 108;
				texY = 4;
				texSx = 124;
				texSy=16;
				
				sx = 12*scalar;
				sy = 16*scalar;
				break;
			default:
				texX = 0;
				texY = 0;
				texSx=0;
				texSy=0;
				
				sx = 16*scalar;
				sy = 16*scalar;
				break;
			
			}
			
			if (type==TileType.BLANK) texId = 1;
			else texId = 0;
			
			draw(x, y, (float) sx, (float) sy, texX, texY, texSx, texSy, texId);
		
	}
	
	/**
	 * Loads the necessary textures to be used within the draw class.  Must be called before the draw class is used
	 */
	public static void loadTextures() {
		try {
			Texture t1 = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/" + "Spreadsheet" + ".png")));
			textures[0] = t1;
			Texture t2 = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/" + "Blank" + ".png")));
			textures[1] = t2;
			Texture t3 = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/" + "Selector" + ".png")));
			textures[2] = t3;
			Texture t4 = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/" + "Play" + ".png")));
			textures[3] = t4;
			Texture t5 = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/" + "Stop" + ".png")));
			textures[4] = t5;
			
			imgX[0] = t1.getImageWidth();
			imgY[0] = t1.getImageHeight();
			
			imgX[1] = t2.getImageWidth();
			imgY[1] = t2.getImageHeight();
	
			imgX[2] = t3.getImageWidth();
			imgY[2] = t3.getImageHeight();
			
			imgX[3] = t4.getImageWidth();
			imgY[3] = t4.getImageHeight();
			
			imgX[4] = t5.getImageWidth();
			imgY[4] = t5.getImageHeight();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Draws the green tile-selector on the palette
	 * @param x The bottom-left horizontal coordinate
	 * @param y The bottom-left vertical coordinate
	 */
	public static void drawMenuItem(MenuItem m, int x, int y, int scalar)
	{
		
		int texX = 0, texY = 0, texSx = 0, texSy = 0, sx = 0, sy = 0, texId = 0;
		
		switch (m)
		{
		case LABEL:
			break;
		case PLAY:
			texX=0;
			texY=0;
			texSx=32;
			texSy=32;
			
			sx =16*scalar;
			sy=16*scalar;
			
			texId = 3;
			break;
		case SELECTOR:
			texX=0;
			texY=0;
			texSx=32;
			texSy=32;
			
			sx =16*scalar;
			sy=16*scalar;
			
			texId = 2;
			break;
		case STOP:
			texX=0;
			texY=0;
			texSx=32;
			texSy=32;
			
			sx =16*scalar;
			sy=16*scalar;
			
			texId = 4;
			break;
		default:
			sx = 0;
			sy = 0;
			texX=0;
			texY=0;
			texSx=0;
			texSy=0;
			break;
		
		}
		
		
		draw(x, y, sx, sy, texX, texY, texSx, texSy, texId);
		
	}
	

	/**
	 * An adaptation of the drawSquare method which can render the animations of certain tile types
	 * @param type Tile type of the square to be rendered
	 * @param x Bottom-left horizontal coordinate 
	 * @param y Bottom-left vertical coordinate
	 * @param scalar Scale of the tile to be multiplied by (1 = 16 x 16 square)
	 * @param frame Frame of the animation
	 */
public static void drawAnimation(TileType type, float x, float y, int scalar, int frame) {
		
		
			int texId, texX, texY, texSx, texSy, sx, sy;
			
			switch (type)
			{
			case GATE:
				texX = 128;
				texY = 16;
				texSx = 132;
				texSy=32;
				
				sx = 4*scalar;
				sy = 16*scalar;
				break;
			case BUTTON:
				texX = 134;
				texY = 28;
				texSx = 142;
				texSy=32;
				
				sx = 8*scalar;
				sy = 4*scalar;
				break;
			case TREADMILL_LEFT:
				texX = 0;
				texY = 144 + (frame*16);
				texSx = 16;
				texSy= texY + 16;
				
				sx = 16*scalar;
				sy = 16*scalar;
				break;
			case TREADMILL_CENTER:
				texX = 16;
				texY = 144 + (frame*16);
				texSx = 32;
				texSy=texY + 16;
				
				sx = 16*scalar;
				sy = 16*scalar;
				break;
			case TREADMILL_RIGHT:
				texX = 32;
				texY = 144 + (frame*16);
				texSx = 48;
				texSy=texY + 16;
				
				sx = 16*scalar;
				sy = 16*scalar;
				break;
			default:
				texX = 0;
				texY = 0;
				texSx=0;
				texSy=0;
				
				sx = 16*scalar;
				sy = 16*scalar;
				break;
			
			}
			
			if (type==TileType.BLANK) texId = 1;
			else texId = 0;
			
			draw(x, y, sx, sy, texX, texY, texSx, texSy, texId);
			
			
		
	}
	

/**
 * Draws the TileType.TILE square of a specific color
 * @param color Color of the tile to be drawn
 * @param x Bottom-left horizontal coordinate
 * @param y Bottom-left vertical coordinate
 * @param scalar Scale of the tile to be multiplied by (1 = 16 x 16 square)
 */
public static void drawColoredTile(TileColor color, float x, float y, int scalar) {
	
		int texId, texX, texY, texSx, texSy, sx, sy;
		
		
		texX = color.getTexX();
		texY = color.getTexY();
		texSx = color.getTexSx();
		texSy = color.getTexSy();
			
		texId=0;
		sx = 16*scalar;
		sy = 16*scalar;
		
		draw(x, y, sx, sy, texX, texY, texSx, texSy, texId);
	
	}

	public static void draw(float x, float y, float sx, float sy, int texX, int texY, int texSx, int texSy, int texId)
	{
		glPushMatrix();
		{
			
			Texture tex = textures[texId];
			
			glTranslatef((int) x, (int) y, 0);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			tex.bind();
			
			glBegin(GL_QUADS);
			{ 
				glTexCoord2f((float)texX/imgX[texId], (float) texSy/imgY[texId]);
					glVertex2f(0, 0);
				glTexCoord2f((float) texX/imgX[texId], (float) texY/imgY[texId]);
					glVertex2f(0, (int) sy);
				glTexCoord2f((float) texSx/imgX[texId], (float) texY/imgY[texId]);
					glVertex2f((int) sx, (int) sy);
				glTexCoord2f((float) texSx/imgX[texId], (float) texSy/imgY[texId]);
					glVertex2f((int) sx, 0);
			}
			glEnd();
		}
		glPopMatrix();
		}
	
	
}
