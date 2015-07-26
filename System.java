import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.LWJGLException;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.*;
import org.lwjgl.*;

import java.util.Timer.*;
import java.lang.System.*;

public class System {
	private ArrayList<Point> listOfPoints;
	private int width;
	private int height;
	private float gravity;
	private float friction;
	private boolean destroying;
	
	public System(int width, int height, float gravity, float friction) {
		this.listOfPoints = new ArrayList<Point>();
		this.width = width;
		this.height = height;
		this.gravity = gravity;
		this.friction = friction;
		this.destroying = false;
		
	}

	public boolean isDestroying() {
		return destroying;
	}

	public void setDestroying(boolean destroying) {
		this.destroying = destroying;
	}

	public void searchToDestroy()
	{
		
		for(int i =0;i<this.listOfPoints.size();i++)
			if(this.listOfPoints.get(i).isDestroy())
			{
				this.listOfPoints.remove(i);
				Random r = new Random();
				java.lang.System.out.println("Destroying" + i);
				int xX = r.nextInt(this.width);
				int yY = r.nextInt(this.height);
				int oldX = xX;// + r.nextInt(1) ;
				int oldY = yY;// + r.nextInt(2) + 1;
				double size = r.nextGaussian()*1.2 +19;
				Point p = new Point(xX, yY, oldX, oldY, (float)size,//r.nextFloat() * 20 + 10,
						1,r.nextBoolean()?0:1,1f,r.nextBoolean()?-r.nextFloat()*2:r.nextFloat()*2);
				this.addPoint(p);
				
			}
		
	}
	
	public void checkCollision() {
		for (int i = 0; i < this.listOfPoints.size(); i++) {

			for (int j = i + 1; j < this.listOfPoints.size(); j++) {

				double x = this.listOfPoints.get(i).getX()
						- this.listOfPoints.get(j).getX();
				double y = this.listOfPoints.get(i).getY()
						- this.listOfPoints.get(j).getY();

				double length = Math.sqrt(x * x + y * y);

				double target = this.listOfPoints.get(i).getRadius()
						+ this.listOfPoints.get(j).getRadius();

				if (length < target) {

					double Px = this.listOfPoints.get(i).getX()
							+ this.listOfPoints.get(j).getX();
					double Py = this.listOfPoints.get(i).getY()
							+ this.listOfPoints.get(j).getY();
					
					Px /= 2;
					Py /= 2;
					
					double forceFactor = target - length;
					//java.lang.System.out.format("Factor: %.3f \n",(forceFactor / target));
					
					if(Math.abs(forceFactor / target)> 0.8 && this.isDestroying()){
						this.listOfPoints.get(j).setDestroy(true);
						this.listOfPoints.get(i).setDestroy(true);
						//java.lang.System.out.println("Destroyed" + i + j);
					}
					
					
					float color = 1.0f-(1.0f/(float)forceFactor);
					glColor3f(1.0f,0 ,0);
					
					
					Point.drawFilledCircle(Px, Py,5*forceFactor,4);
					
					//java.lang.System.out.println(forceFactor/target);
					double factor = 0.3 * (length - target) / length;

					this.listOfPoints.get(i).setX(
							this.listOfPoints.get(i).getX() - x * factor);
					this.listOfPoints.get(i).setY(
							this.listOfPoints.get(i).getY() - y * factor);

					this.listOfPoints.get(j).setX(
							this.listOfPoints.get(j).getX() + x * factor);
					this.listOfPoints.get(j).setY(
							this.listOfPoints.get(j).getY() + y * factor);

				}
				

			}
			
			searchToDestroy();
		}
		
	}

	public ArrayList<Point> getListOfPoints() {
		return listOfPoints;
	}

	public void setListOfPoints(ArrayList<Point> listOfPoints) {
		this.listOfPoints = listOfPoints;
	}

	public void checkCollisionOfBoundaries() {
		for (Point p : this.listOfPoints) {

			if (p.getX() - p.getRadius() < 0){
				p.setX(p.getRadius());
				//p.setDestroy(true);
			}
			else if (p.getX() + p.getRadius() > this.width){
				p.setX(this.width - p.getRadius());
				//p.setDestroy(true);
			}

			if (p.getY() + p.getRadius() > this.height)
				p.setY(this.height - p.getRadius());
			else if (p.getY() - p.getRadius() < 0)
				p.setY(p.getRadius());
		}
	}

	public void addPoint(Point p) {
		this.listOfPoints.add(p);
	}

	public void applyGravity() {
		for (Point p : this.listOfPoints) {
			p.setYa(p.getYa() + this.gravity);
		}
	}
	
	
	public void appplyForces()
	{
		for (Point p : this.listOfPoints) {
			p.setXa(p.getXa() + p.getForce());
		}
	}
	public Point searchPoint(int x, int y) {
		
		for (Point p : this.listOfPoints) {
			if (x < p.getX() + p.getRadius() && x > p.getX() - p.getRadius()
					&& y < p.getY() + p.getRadius()
					&& y > p.getY() - p.getRadius())
			{
				
				return p;
			}
				
		}
		
		return null;
	}

	public void applyDirerction(Point p, double force) {
		if (p != null)
			p.setXa(p.getXa()+ force);
	}

	public void draw() {
		for (Point p : this.listOfPoints) {
			p.draw();
		}
	}

	public void accelerate(double delta) {
		for (Point p : this.listOfPoints) {
			p.accelerate(delta);
		}
	}

	public void updatePosition(double delta) {
		for (Point p : this.listOfPoints) {
			p.updatePosition(delta);
		}
	}

	public void step(int steps) {
		
		double delta = 1 / (double) steps;
		
		for (int i = 0; i < steps; i++) {
			this.applyGravity();
			appplyForces();
			this.accelerate(delta);
			this.checkCollision();
			this.checkCollisionOfBoundaries();
			this.updatePosition(delta);
		}
		
		
		
		this.draw();
		
		this.checkCollision();
	}

	public boolean isOverlapping(int x, int y) {

		for (Point p : this.listOfPoints) {

			if (Math.sqrt(Math.pow(p.getX() - x, 2) + Math.pow(p.getY() - y, 2)) < p
					.getRadius() * 2)
				return true;
		}

		return false;
	}

}
