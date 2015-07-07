import java.util.Random;

import org.lwjgl.LWJGLException;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.*;
import org.lwjgl.util.Color;
import org.lwjgl.*;

public class SimpleRenderer {

	private int width;
	private int height;
	public SimpleRenderer(int x, int y)
	{
		this.width = x;
		this.height = y;
		try{
			Display.setDisplayMode(new DisplayMode(x,y));
			Display.setTitle("Hello fuckers");
			Display.create();
		}
		catch(LWJGLException e)
		{
			e.printStackTrace();
		}
		
		Random r = new Random();
		
		
		
		System s = new System(this.width,this.height-2,0.0f,1.0f);
		for(int i=0;i<5;i++)
		{
			
			
			int xX = r.nextInt(this.width);
			int yY = r.nextInt(this.height);
			int oldX = xX + r.nextInt(4)+1;
			int oldY = yY + r.nextInt(4)+1;
			
			while(s.isOverlapping(xX, yY, 50))
			{
				xX = r.nextInt(this.width);
				yY = r.nextInt(this.height);
			}
			Point p = new Point(xX,yY,oldX,oldY,r.nextFloat(),r.nextFloat(),r.nextFloat());
			
			//Point p = new Point(xX,yY,oldX,oldY,1.0f,1.0f,1.0f);
			s.addPoint(p);
		}
		//Point p = new Point(154,100,150,106,1.0f,1.0f,1.0f);
		//Point p2 = new Point(150,400,156s,406,1.0f,0.0f,1.0f);
		
		//Point p3 = new Point(300,300,305,300,1.0f,1.0f,1.0f);
		//Point p4 = new Point(600,300,605,300,1.0f,0.0f,1.0f);
		//s.addPoint(p);
		//s.addPoint(p2);
		
		//s.addPoint(p3);
		//s.addPoint(p4);
		//Point p1 = new Point(100,100,101,101);
		//Point p2 = new Point(200,200,201,201);
		//Stick stick = new Stick(p1,p2);
		
		//s.addStick(stick);
		// sng poome space for initialization opengl
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0,x,y,0,1,-1);
		glMatrixMode(GL_MODELVIEW);
		
		final double DESIRED_FPS = 70;
		final double MS_PER_SEC= 1000;
		final double DESIRED_FRAMETIME = MS_PER_SEC / DESIRED_FPS;
		
		double prevTime = java.lang.System.currentTimeMillis();
		
		while(!Display.isCloseRequested())	// rendering
		{
			double newTime = java.lang.System.currentTimeMillis();
			double frameTime = newTime - prevTime;
			
			prevTime = newTime;
			
			double deltaTime = frameTime / DESIRED_FRAMETIME;
			
			//java.lang.System.out.println("DESIRED FRAMERATE:" +DESIRED_FRAMETIME+"newTime: "+newTime+"prevTime: "+prevTime+" frameTime: "+frameTime+" deltaTime: "+deltaTime);
			
			glClear(GL_COLOR_BUFFER_BIT);
			
			
			s.updatePosition((float)deltaTime);
			s.draw();//
			s.checkCollision();
			Display.update();
			Display.sync(60);
		}
		
		Display.destroy();
	}
	
	


}
