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
		
		
		
		System s = new System(this.width,this.height-2,0,0.999);
		for(int i=0;i<100;i++)
		{
			int xX = r.nextInt(this.width);
			int yY = r.nextInt(this.height);
			int oldX = xX + r.nextInt(2)+1;
			int oldY = yY + r.nextInt(2)+1;
			Point p = new Point(xX,yY,oldX,oldY,r.nextFloat(),r.nextFloat(),r.nextFloat());
			s.addPoint(p);
		}
		
		//Point p1 = new Point(100,100,101,101);
		//Point p2 = new Point(200,200,201,201);
		//Stick stick = new Stick(p1,p2);
		
		//s.addStick(stick);
		// sng poome space for initialization opengl
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0,x,y,0,1,-1);
		glMatrixMode(GL_MODELVIEW);
		
		
		while(!Display.isCloseRequested())	// rendering
		{
			glClear(GL_COLOR_BUFFER_BIT);
			s.draw();
			
			s.updatePosition();
			for(int i=0;i<2;i++)
				s.checkCollision();
			Display.update();
			Display.sync(60);
		}
		
		Display.destroy();
	}


}
