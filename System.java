import java.util.ArrayList;

import org.lwjgl.LWJGLException;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.*;
import org.lwjgl.*;

public class System {
	private ArrayList<Point> listOfPoints;
	private ArrayList<Stick> listOfSticks;
	

	private int width;
	private int height;
	private double gravity;
	private double friction;
	
	public System(int width,int height,double gravity,double friction)
	{
		this.listOfPoints = new ArrayList<Point>();
		listOfSticks =  new ArrayList<Stick>();
		this.width = width;
		this.height = height;
		this.gravity = gravity;
		this.friction = friction;
		
	}
	
	public void checkCollision()
	{
		for(int i=0;i<this.listOfPoints.size();i++)
			for(int k=i+1;k<this.listOfPoints.size();k++)
			{
				if(		Math.sqrt(Math.pow(this.listOfPoints.get(k).getX() -this.listOfPoints.get(i).getX() , 2) +  //this.listOfPoints.get(i).getX() == this.listOfPoints.get(k).getX() &&
						Math.pow(this.listOfPoints.get(k).getY() -this.listOfPoints.get(i).getY() , 2) ) < 4
						)
				{
					
					glBegin(GL_QUADS);
					glVertex2i(this.listOfPoints.get(i).getX()-5,this.listOfPoints.get(i).getY()-5);
					glVertex2i(this.listOfPoints.get(i).getX()+5,this.listOfPoints.get(i).getY()-5);
					glVertex2i(this.listOfPoints.get(i).getX()+5,this.listOfPoints.get(i).getY()+5);
					glVertex2i(this.listOfPoints.get(i).getX()-5,this.listOfPoints.get(i).getY()+5);
					glEnd();
					
					int iOldX = this.listOfPoints.get(i).getOldX();
					int iOldY = this.listOfPoints.get(i).getOldY();
					int kOldX = this.listOfPoints.get(k).getOldX();
					int kOldY = this.listOfPoints.get(k).getOldY();
					
					
					this.listOfPoints.get(i).setOldX(kOldX);
					this.listOfPoints.get(i).setOldY(kOldX);
					
					this.listOfPoints.get(k).setOldX(iOldX);
					this.listOfPoints.get(k).setOldY(iOldY);
					
					
				}
					
			}
	}
	public void addPoint(Point p)
	{
		this.listOfPoints.add(p);
	}
	
	public void addStick(Stick s)
	{
		this.listOfSticks.add(s);
	}
	
	public void draw()
	{
		for(Point p:this.listOfPoints)
		{
			p.draw();
		}
		for(Stick s:this.listOfSticks)
		{
			s.draw();
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
