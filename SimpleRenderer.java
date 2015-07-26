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

	public void addPoint(System s, int x, int y, int oX, int oY, float r,
			float red, float green, float blue,double force) {
		Point p = new Point(x, y, oX, oY, r, red, green, blue,force);
		s.addPoint(p);
	}

	public SimpleRenderer(int x, int y) {
		this.width = x;
		this.height = y;
		try {
			Display.setDisplayMode(new DisplayMode(x, y));
			Display.setTitle("Crowd Simulation");
			Display.create();
			Display.setVSyncEnabled(true);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}

		Random r = new Random();

		System s = new System(this.width, this.height - 2, 0.0f, 1.0f);
		for (int i = 0; i <850; i++) {

			int xX = r.nextInt(this.width);
			int yY = r.nextInt(this.height);
			int oldX = xX;// + r.nextInt(1) ;
			int oldY = yY;// + r.nextInt(2) + 1;

			
			
			
			double size = r.nextGaussian()*1.2 +19;
			addPoint(s, xX, yY, oldX, oldY, (float)size,//r.nextFloat() * 20 + 10,
					1,i>400?0:1,1,i>425?-r.nextFloat()*2:r.nextFloat()*2);//r.nextFloat(), r.nextFloat(), r.nextFloat());
					
			
			//addPoint(s, xX, yY, oldX, oldY, (float)size,//r.nextFloat() * 20 + 10,
			//		r.nextFloat(), r.nextFloat(), r.nextFloat(),i>400 ?-r.nextFloat():r.nextFloat());

		}

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, x, y, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);

		boolean isPressed = false;
		while (!Display.isCloseRequested()) // rendering
		{
			
			glClear(GL_COLOR_BUFFER_BIT);
			//glClearColor(1.0f,1.0f,1.0f,1.0f);
			if (Mouse.isButtonDown(0)) {

				// isPressed = true;
				/*int xX = Mouse.getX();
				int yY = this.height - Mouse.getY();

				int oldX = xX;// + r.nextInt(4) - 2;
				int oldY = yY;
				double size = r.nextGaussian()*1.2 +14;
				addPoint(s, xX, yY, oldX, oldY,(float)size,// r.nextFloat() * 20 + 10,
						r.nextFloat(), r.nextFloat(), r.nextFloat(),30);
				*/
				s.setDestroying(true);
				
			}

			if (Mouse.isButtonDown(1)) {

				for (int i = -150; i < 150; i+=10) {
					
				int xX = Mouse.getX();
				int yY = this.height - Mouse.getY();
				s.applyDirerction(s.searchPoint(xX, yY+i),10);
				}
			}

			 java.lang.System.out.println("Number of objects: " +s.getListOfPoints().size() +" Destroying? "+ s.isDestroying());
			s.step(3);

			if(s.isDestroying())
				s.searchToDestroy();
			int xX = Mouse.getX();
			int yY = this.height - Mouse.getY();

			for (int i = -150; i < 150; i+=10) {
				Point k = s.searchPoint(xX, yY+i);
				if (k != null) {
					glColor3f(0.0f, 0.0f, 0.0f);
					Point.DrawCircle(k.getX(), k.getY(), k.getRadius() - 2, 20);
				}
			}

			

			Display.update();
			Display.sync(60);
		}

		Display.destroy();
	}

}
