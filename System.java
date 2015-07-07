import java.util.ArrayList;

import org.lwjgl.LWJGLException;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.*;
import org.lwjgl.*;
import java.util.Timer.*;
import java.lang.System.*;

public class System {
	private ArrayList<Point> listOfPoints;
	private ArrayList<Stick> listOfSticks;
	

	private int width;
	private int height;
	private float gravity;
	private float friction;
	
	public System(int width,int height,float gravity,float friction)
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
						Math.pow(this.listOfPoints.get(k).getY() -this.listOfPoints.get(i).getY() , 2) ) < 400
						)
				{
					
					glBegin(GL_QUADS);
					glVertex2i((int)this.listOfPoints.get(i).getX()-5,(int)this.listOfPoints.get(i).getY()-5);
					glVertex2i((int)this.listOfPoints.get(i).getX()+5,(int)this.listOfPoints.get(i).getY()-5);
					glVertex2i((int)this.listOfPoints.get(i).getX()+5,(int)this.listOfPoints.get(i).getY()+5);
					glVertex2i((int)this.listOfPoints.get(i).getX()-5,(int)this.listOfPoints.get(i).getY()+5);
					glEnd();
					
					double iX = this.listOfPoints.get(i).getX();
					double iY = this.listOfPoints.get(i).getY();
					double oldiX = this.listOfPoints.get(i).getOldX();
					double oldiY = this.listOfPoints.get(i).getOldY();
					
					double kX = this.listOfPoints.get(k).getX();
					double kY = this.listOfPoints.get(k).getY();
					double oldkX = this.listOfPoints.get(k).getOldX();
					double oldkY = this.listOfPoints.get(k).getOldY();
					
					
					
					// move vectors
					double mov1x = iX - oldiX; 
					double mov1y = iY - oldiY;
					double mov2x = kX - oldkX;
					double mov2y = kY - oldkY; 
					//-------------
					
					mov1x *= 10;
					mov1y *= 10;
					mov2x *= 10;
					mov2y *= 10;
					
						glBegin(GL_LINES);
					glVertex2i((int)iX,(int)iY);
					glVertex2i((int)(mov1x+iX),(int)(mov1y+iY));  //move vector I
						glEnd();
					
						glBegin(GL_LINES);
						glVertex2i((int)kX,(int)kY);
						glVertex2i((int)mov2x+(int)kX,(int)mov2y+(int)kY);  //move vector K
							glEnd();
					// connecting vectors
					double con1x = kX -iX  ;
					double con1y = kY -iY  ;
					
					double con2x = iX- kX  ;
					double con2y =  iY -kY  ;
					
					
					
					glBegin(GL_LINES);
					glVertex2i((int)iX,(int)iY);
					glVertex2i((int)con1x + (int)iX,(int)con1y+(int)iY);
						glEnd();
					
					//-------------
					//Divisor
					
					double div1 = con1x / mov1x;
					double div2 = con2x / mov2x;

					
					//normalizing vectors
					
					con1x /= div1;
					con1y /= div1;
					
					con2x /= div2;
					con2y /= div2;
					
					//--------------
					
					glColor3f(1.0f,1.0f,1.0f);
					
					glBegin(GL_LINES);
					glVertex2i((int)iX,(int)iY);
					glVertex2i((int)con1x + (int)iX,(int)con1y+(int)iY);
						glEnd();
						
					glBegin(GL_LINES);
						glVertex2i((int)kX,(int)kY);
						glVertex2i((int)con2x + (int)kX,(int)con2y+(int)kY);
							glEnd();
					//getting points P1 and P2 
					
					double P1x = con1x + iX;
					double P1y = con1y + iY;
					double P2x = con2x + kX;
					double P2y = con2y + kY;
					
					
					
					//----------------
					// final vectors to move
					
					//fin1X
					
					// ---------------
							
					
					
					//this.listOfPoints.get(k).setOldX( cur_k_X + (int)finXVec_k);
					//this.listOfPoints.get(k).setOldY( cur_k_Y + (int)finYVec_k);
				
					
					glColor3f(1.0f,1.0f,1.0f);
					Point.DrawCircle(P1x, P1y, 10, 10);
					Point.DrawCircle(P2x, P2y, 10, 10);
					
					
				
					
					
				/*	int iOldX = this.listOfPoints.get(i).getOldX();
					int iOldY = this.listOfPoints.get(i).getOldY();
					int kOldX = this.listOfPoints.get(k).getOldX();
					int kOldY = this.listOfPoints.get(k).getOldY();
					
					
					this.listOfPoints.get(i).setOldX(kOldX);
					this.listOfPoints.get(i).setOldY(kOldY);
					
					this.listOfPoints.get(k).setOldX(iOldX);
					this.listOfPoints.get(k).setOldY(iOldY);
					*/
					
					
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
	
	public void updatePosition(double deltaTime)
	{
		
		java.lang.System.out.println("Delta: "+deltaTime);
		
		for(Point p:this.listOfPoints)
		{
			java.lang.System.out.println("p.getX: "+p.getX());
			java.lang.System.out.println("p.getOldX: "+p.getOldX());
			java.lang.System.out.println("Delta: "+deltaTime);
			java.lang.System.out.println("  "+(p.getX() - p.getOldX())+" * "+deltaTime+" = "+ (p.getX() - p.getOldX()) * deltaTime);
			double vx = (this.friction*(p.getX() - p.getOldX()));
			double vy = (this.friction*(p.getY() - p.getOldY()));
			
			
			p.setOldX(p.getX());
			p.setOldY(p.getY());
			
			p.setX((p.getX()+vx));
			p.setY((p.getY()+(vy+gravity)));
			
			
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
