import java.util.ArrayList;

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

	public System(int width, int height, float gravity, float friction) {
		this.listOfPoints = new ArrayList<Point>();
		this.width = width;
		this.height = height;
		this.gravity = gravity;
		this.friction = friction;
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

					double factor = 0.1 * (length - target) / length;

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

			if (p.getX() - p.getRadius() < 0)
				p.setX(p.getRadius());
			else if (p.getX() + p.getRadius() > this.width)
				p.setX(this.width - p.getRadius());

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

	public Point searchPoint(int x, int y) {
		
		for (Point p : this.listOfPoints) {
			if (x < p.getX() + p.getRadius() && x > p.getX() - p.getRadius()
					&& y < p.getY() + p.getRadius()
					&& y > p.getY() - p.getRadius())
			{
				java.lang.System.out.println("Found");
				return p;
			}
				
		}
		java.lang.System.out.println("Not Found");
		return null;
	}

	public void applyDirerction(Point p) {
		if (p != null)
			p.setXa(p.getXa()+ 800);
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
			this.accelerate(delta);
			this.checkCollision();
			this.checkCollisionOfBoundaries();
			this.updatePosition(delta);
		}
		this.draw();
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
