import java.util.Random;

import org.lwjgl.LWJGLException;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;
import org.lwjgl.util.Color;
import org.lwjgl.*;
public class SimpleRenderer {

	private int width;
	private int height;

	public void addPoint(System s,int x,int y, int oX,int oY,float r,float red,float green,float blue)
	{
		Point p = new Point(x, y, oX, oY, r, red,
				green, blue);
		s.addPoint(p);
	}
	
	
	public SimpleRenderer(int x, int y) {
		this.width = x;
		this.height = y;
		try {
			Display.setDisplayMode(new DisplayMode(x, y));
			Display.setTitle("Hello fuckers");
			Display.create();
			 Display.setVSyncEnabled(true);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}

		Random r = new Random();

		System s = new System(this.width, this.height - 2, 0.5f, 1.0f);
		for (int i = 0; i < 200; i++) {

			int xX = r.nextInt(this.width);
			int yY = r.nextInt(this.height);
			int oldX = xX ;//+ r.nextInt(1) ;
			int oldY = yY ;//+ r.nextInt(2) + 1;
			
			/*while (!s.isOverlapping(xX, yY)) {
				xX = r.nextInt(this.width);
				yY = r.nextInt(this.height);
				oldX = xX + r.nextInt(2) + 1;
				oldY = yY + r.nextInt(2) + 1;
			}*/
			
			addPoint(s,xX,yY,oldX,oldY,r.nextFloat()*20+10, r.nextFloat(),r.nextFloat(), r.nextFloat());
			
		}

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, x, y, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);

		boolean isPressed = false;
		while (!Display.isCloseRequested()) // rendering
		{
			glClear(GL_COLOR_BUFFER_BIT);
			if (Mouse.isButtonDown(0) ) {
				
					//isPressed = true;
				    int xX = Mouse.getX();
					int yY = this.height - Mouse.getY();
					
					int oldX = xX;// + r.nextInt(4) - 2;
					int oldY = yY;
					addPoint(s,xX,yY,oldX,oldY,r.nextFloat()*20+10, r.nextFloat(),r.nextFloat(), r.nextFloat());
				}
			
			if (Mouse.isButtonDown(1) ) {
				
			    int xX = Mouse.getX();
				int yY = this.height - Mouse.getY();
				s.applyDirerction(s.searchPoint(xX, yY));
				
			}
			
			
			
		
			
			
				
			
			//java.lang.System.out.println("Number of objects: " + s.getListOfPoints().size());
			s.step(10);
			
			int xX = Mouse.getX();
			int yY = this.height - Mouse.getY();
			Point k  = s.searchPoint(xX, yY);
			if(k != null)
			{
				glColor3f(0.0f,0.0f,0.0f); 
				Point.DrawCircle(k.getX(), k.getY(), k.getRadius()-2,20);
			}
			
			
			Display.update();
			Display.sync(60);
		}

		Display.destroy();
	}

}
