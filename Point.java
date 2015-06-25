import org.lwjgl.LWJGLException;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.*;
import org.lwjgl.*;


public class Point {
	private int x;
	private int y;
	private int oldX;
	private int oldY;
	
	
	Point(int x,int y,int oldx,int oldy)
	{
		this.x = x;
		this.y = y;
		this.oldX = oldx;
		this.oldY = oldy;
	}
	
	
	
	
	
	public void draw()
	{
		glBegin(GL_QUADS);
			glVertex2i(this.x,this.y);
			glVertex2i(this.x+10,this.y);
			glVertex2i(this.x+10,this.y+10);
			glVertex2i(this.x,this.y+10);
		glEnd();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getOldX() {
		return oldX;
	}

	public void setOldX(int oldX) {
		this.oldX = oldX;
	}

	public int getOldY() {
		return oldY;
	}

	public void setOldY(int oldY) {
		this.oldY = oldY;
	}
	
	
}
