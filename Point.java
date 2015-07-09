import org.lwjgl.LWJGLException;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.*;
import org.lwjgl.util.Color;
import org.lwjgl.*;


public class Point {
	private double x;
	private double y;
	private double oldX;
	private double oldY;
	private float red;
	
	public float getRed() {
		return red;
	}





	public void setRed(float red) {
		this.red = red;
	}





	public float getGreen() {
		return green;
	}





	public void setGreen(float green) {
		this.green = green;
	}





	public float getBlue() {
		return blue;
	}





	public void setBlue(float blue) {
		this.blue = blue;
	}

	private float green;
	private float blue;
	
	Point(double x,double y,double oldx,double oldy,float red,float blue, float green)
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
		
		double x = this.x - this.oldX;
		double y = this.y - this.oldY;
		
		x *=20;
		y *=20;
		
		
		//glBegin(GL_LINES);
		//	glVertex2i((int)this.x,(int)this.y);
		//	glVertex2i((int)(this.x+x),(int)(this.y+y));
		//glEnd();
		
		
		
		
		
			
		
		this.DrawCircle(this.x, this.y, 100, 40);
	}
	
	
	static void DrawCircle(double cx, double cy, float r, int num_segments) 
	{ 
		glBegin(GL_LINE_LOOP); 
		for(int ii = 0; ii < num_segments; ii++) 
		{ 
			float theta = 2.0f * 3.1415926f * (float)ii / (float)num_segments;//get the current angle 

			float x = r *(float)Math.cos(theta);//calculate the x component 
			float y = r *(float) Math.sin(theta);//calculate the y component 

			glVertex2f((float)x + (float)cx, (float)y + (float)cy);//output vertex 

		} 
		glEnd(); 
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getOldX() {
		return oldX;
	}

	public void setOldX(double f) {
		this.oldX = f;
	}

	public double getOldY() {
		return oldY;
	}

	public void setOldY(double oldY) {
		this.oldY = oldY;
	}
	
	
}
