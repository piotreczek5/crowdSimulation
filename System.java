import java.util.ArrayList;

import org.lwjgl.LWJGLException;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.*;
import org.lwjgl.*;

public class System {
	private ArrayList<Point> listOfPoints;
	
	private int width;
	private int height;
	private double gravity;
	private double friction;
	
	public System(int width,int height,double gravity,double friction)
	{
		this.listOfPoints = new ArrayList<Point>();
		this.width = width;
		this.height = height;
		this.gravity = gravity;
		this.friction = friction;
		
	}
	
	public void addPoint(Point p)
	{
		this.listOfPoints.add(p);
	}
	
	public void draw()
	{
		for(Point p:this.listOfPoints)
		{
			p.draw();
		}
	}
	
	public void updatePosition()
	{
		
		for(Point p:this.listOfPoints)
		{
			int vx = (int)((p.getX() - p.getOldX()));
			int vy = (int)((p.getY() - p.getOldY()));
			
			p.setOldX(p.getX());
			p.setOldY(p.getY());
			
			p.setX(p.getX()+vx);
			p.setY(p.getY()+vy);
			p.setY(p.getY()+(int)this.gravity);
			
			if(p.getX() > this.width)
			{
				p.setX(width);
				p.setOldX(p.getX()+vx);
				
			}else if(p.getX() < 0)
			{
				p.setX(0);
				p.setOldX(p.getX()+vx);
			}
			
			if(p.getY() > this.height)
			{
				p.setY(height);
				p.setOldY(p.getY()+vy);
				
			}else if(p.getY() < 0)
			{
				p.setY(0);
				p.setOldY(p.getY()+vy);
			}
			
		}
		
		
		
		
		
	}
}
