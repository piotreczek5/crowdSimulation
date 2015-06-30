import org.lwjgl.LWJGLException;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.*;
import org.lwjgl.util.Color;
import org.lwjgl.*;


public class Point {
	private int x;
	private int y;
	private int oldX;
	private int oldY;
	private float red;
	private float green;
	private float blue;
	
	Point(int x,int y,int oldx,int oldy,float red,float blue, float green)
	{
		this.x = x;
		this.y = y;
		this.oldX = oldx;
		this.oldY = oldy;
		this.red = red;
		this.blue = blue;
		this.green = green;
	}
	
	
	
	
	
	public void draw()
	{
		
		glColor3f(this.red,this.green,this.blue);
		
		/*
		glBegin(GL_QUADS);
			glVertex2i(this.x-5,this.y-5);
			glVertex2i(this.x+5,this.y-5);
			glVertex2i(this.x+5,this.y+5);
			glVertex2i(this.x-5,this.y+5);
		glEnd();*/
		
		this.DrawCircle(this.x, this.y, 2, 8);
	}
	
	
	void DrawCircle(float cx, float cy, float r, int num_segments) 
	{ 
		glBegin(GL_LINE_LOOP); 
		for(int ii = 0; ii < num_segments; ii++) 
		{ 
			float theta = 2.0f * 3.1415926f * (float)ii / (float)num_segments;//get the current angle 

			float x = r *(float)Math.cos(theta);//calculate the x component 
			float y = r *(float) Math.sin(theta);//calculate the y component 

			glVertex2f(x + cx, y + cy);//output vertex 

		} 
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
