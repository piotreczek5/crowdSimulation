
import org.lwjgl.LWJGLException;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.*;
import org.lwjgl.*;

public class Stick {
	
	private Point p1;
	private Point p2;
	private int lenght;
	
	public Stick(Point p1,Point p2)
	{
		this.p1 = p1;
		this.p2 = p2;
		this.lenght = this.length();
	}
	
	public int length()
	{
		double dist = Math.sqrt(Math.pow((this.p1.getX()-this.p2.getX()), 2) - Math.pow((this.p1.getY()-this.p2.getY()), 2));
		return (int)dist;
	}
	
	public void updateSticks()
	{
		double dist = this.length();
		double difference =  this.lenght - dist;
		double percentage = difference / dist / 2;
		
		int dx = this.p1.getX() - this.p2.getX();
		int dy = this.p1.getY() - this.p2.getY();
		
		int offsetX = (int)(dx * percentage);
		int offsetY = (int)(dy * percentage);
	
		
		this.p1.setX(this.p1.getX()-offsetX);
		this.p1.setY(this.p1.getY()-offsetY);
		this.p2.setX(this.p2.getX()+offsetX);
		this.p2.setY(this.p2.getY()+offsetY);
		
	}
	public void draw()
	{
	glBegin(GL_LINES);
		glVertex2i(this.p1.getX(),this.p1.getY());
		glVertex2i(this.p2.getX(),this.p2.getY());
	glEnd();
	}
	

}
