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
	private double xa;
	private double ya;
	private float radius;
	private float red;
	private float green;
	private float blue;

	Point(double x, double y,double oldX, double oldY,float radius, float red, float blue,
			float green) {
		this.x = x;
		this.y = y;
		this.oldX = oldX;
		this.oldY = oldY;
		this.xa = 0;
		this.ya = 0;
		this.radius = radius;
		this.red = red;
		this.blue = blue;
		this.green = green;
	}

	public void draw() {

		glColor3f(this.red, this.green, this.blue);
		//this.DrawCircle(this.x, this.y, this.radius, 20);
		this.drawFilledCircle(this.x, this.y, this.radius);
		
	}

	public void updatePosition(double delta)
	{
		double x = this.x*2 - this.oldX;
		double y = this.y*2 - this.oldY;
		
		
		//java.lang.System.out.println("x: " + x + "y: "+ y);
		this.oldX = this.x;
		this.oldY = this.y;
		this.x = x;
		this.y = y;
	}
	
	public void accelerate(double delta)
	{
		//java.lang.System.out.println("delta: "+delta);
		this.x += this.xa *delta*delta;
		this.y += this.ya *delta*delta;
		//java.lang.System.out.println("xA: " + this.xa + "yA: "+ this.ya);
		this.xa = 0;
		this.ya = 0;
	}
	
	static void drawFilledCircle(double x, double y, double radius){
		int i;
		int triangleAmount = 30; //# of triangles used to draw circle
		
		//GLfloat radius = 0.8f; //radius
		float twicePi = 2.0f * (float)Math.PI;
		
		glBegin(GL_TRIANGLE_FAN);
			glVertex2f((float)x, (float)y); // center of circle
			for(i = 0; i <= triangleAmount;i++) { 
				glVertex2f(
						(float)x + ((float)radius * (float)Math.cos(i *  twicePi / triangleAmount)), 
						(float)y + ((float)radius * (float)Math.sin(i * twicePi / triangleAmount))
				);
			}
		glEnd();
	}

	static void DrawCircle(double cx, double cy, float r, int num_segments) {
		glBegin(GL_LINE_LOOP);
		for (int ii = 0; ii < num_segments; ii++) {
			float theta = 2.0f * 3.1415926f * (float) ii / (float) num_segments;// get
																				// the
																				// current
																				// angle

			float x = r * (float) Math.cos(theta);// calculate the x component
			float y = r * (float) Math.sin(theta);// calculate the y component

			glVertex2f((float) x + (float) cx, (float) y + (float) cy);// output
																		// vertex
		}
		glEnd();
	}

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
	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public double getXa() {
		return xa;
	}

	public void setXa(double xa) {
		this.xa = xa;
	}

	public double getYa() {
		return ya;
	}

	public void setYa(double ya) {
		this.ya = ya;
	}

}
