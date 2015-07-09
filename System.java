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

	public System(int width, int height, float gravity, float friction) {
		this.listOfPoints = new ArrayList<Point>();
		listOfSticks = new ArrayList<Stick>();
		this.width = width;
		this.height = height;
		this.gravity = gravity;
		this.friction = friction;

	}

	public void checkCollision() {
		for (int i = 0; i < this.listOfPoints.size(); i++)
			for (int k = i + 1; k < this.listOfPoints.size(); k++) {
				if (Math.sqrt(Math.pow(this.listOfPoints.get(k).getX()
						- this.listOfPoints.get(i).getX(), 2)
						+ // this.listOfPoints.get(i).getX() ==
							// this.listOfPoints.get(k).getX() &&
						Math.pow(this.listOfPoints.get(k).getY()
								- this.listOfPoints.get(i).getY(), 2)) < 200) {

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
					// -------------

					/*
					 * mov1x *= 20; mov1y *= 20; mov2x *= 20; mov2y *= 20;
					 */
					
					glBegin(GL_LINES);
					glVertex2i((int) iX, (int) iY);
					glVertex2i((int) (mov1x + iX), (int) (mov1y + iY)); // move
																		// vector
																		// I
					glEnd();

					glBegin(GL_LINES);
					glVertex2i((int) kX, (int) kY);
					glVertex2i((int) mov2x + (int) kX, (int) mov2y + (int) kY); // move
																				// vector
																				// K
					glEnd();
					
					
					// connecting vectors
					double con1x = kX - iX;
					double con1y = kY - iY;

					double con2x = iX - kX;
					double con2y = iY - kY;

					glBegin(GL_LINES);
					glVertex2i((int) iX, (int) iY);
					glVertex2i((int) con1x + (int) iX, (int) con1y + (int) iY);
					glEnd();



					// -------------
					

					// normalizing vectors

					con1x /= 2;
					con1y /= 2;

					con2x /= 2;
					con2y /= 2;

					// --------------

						glColor3f(1.0f, 1.0f, 1.0f);

					glBegin(GL_LINES);
					glVertex2i((int) iX, (int) iY);
					glVertex2i((int) con1x + (int) iX, (int) con1y + (int) iY);
					glEnd();
					glColor3f(1.0f, 0.0f, 0.9f);
					glBegin(GL_LINES);
					glVertex2i((int) kX, (int) kY);
					glVertex2i((int) con2x + (int) kX, (int) con2y + (int) kY);
					glEnd();
					
					// getting points P1 and P2

					double P1x = con1x + iX;
					double P1y = con1y + iY;
					double P2x = con2x + kX;
					double P2y = con2y + kY;

					// ----------------
					// final vectors to move

					//
					double fin1X = mov1x + iX - P1x;
					double fin1Y = mov1y + iY - P1y;
					double fin2X = mov2x + kX - P2x;
					double fin2Y = mov2y + kY - P2y;

					glColor3f(1.0f, 1.0f, 0.0f);

					glBegin(GL_LINES);
					glVertex2i((int) P1x, (int) P1y);
					glVertex2i((int) P1x + (int) fin1X, (int) P1y + (int) fin1Y);
					glEnd();

					glBegin(GL_LINES);
					glVertex2i((int) P2x, (int) P2y);
					glVertex2i((int) P2x + (int) fin2X, (int) P2y + (int) fin2Y);
					glEnd();
				 
					// ---------------

					// normalizing final points
					// Divisor

					double div1x;// con1x / mov1x;
					double div2x;// con2x / mov2x;
					double div1y ;
					double div2y ;
					
					div1x = mov1x == 0 ? 0.0 : (fin1X / mov1x);
					div2x = mov2x == 0 ? 0.0 : (fin2X / mov2x);
					div1y = mov1y == 0 ? 0.0 : (fin1Y / mov1y);
					div2y = mov2y == 0 ? 0.0 : (fin2Y / mov2y);

					if (div1x != 0) {
						fin1X /= div1x;
						fin1Y /= div1x;
					}else
					{
						fin1X /= div1y;
						fin1Y /= div1y;
					}

					if (div2x != 0) {
						fin2X /= div2x;
						fin2Y /= div2x;
					}else
					{
						fin2X /= div2y;
						fin2Y /= div2y;
					}
					// --------------------------

					// point of oldXs and oldYs

					double point1X;
					double point1Y;
					double point2X;
					double point2Y;

					// xs---------------------------------------------------------------

					if (mov1x > 0 && mov2x < 0 || mov1x < 0 && mov2x > 0) {
						java.lang.System.out.println("I X");
						point1X = iX + (fin1X);
						point2X = kX + (fin2X);
					} else if (mov1x > 0 && mov2x > 0 || mov1x < 0 && mov2x < 0) {
						if (mov1x > 0) {
							java.lang.System.out.println("II X");
							point1X = iX + (fin1X);
							point2X = kX - (fin2X);
						} else {
							java.lang.System.out.println("III X");
							point1X = iX - (fin1X);
							point2X = kX + (fin2X);
						}

					} else {
						java.lang.System.out.println("IV X");
						point1X = iX + (fin1X);
						point2X = kX + (fin2X);
					}

					// ------------------------------------------------------------------

					// ys---------------------------------------------------------------

					if (mov1y > 0 && mov2y < 0 || mov1y < 0 && mov2y > 0) {
						java.lang.System.out.println("I Y");
						point1Y = iY + (fin1Y);
						point2Y = kY + (fin2Y);
					} else if (mov1y > 0 && mov2y > 0 || mov1y < 0 && mov2y < 0) {
						if (mov1y > 0) {
							java.lang.System.out.println("II Y");
							point1Y = iY - (fin1Y);
							point2Y = kY + (fin2Y);
						} else {
							java.lang.System.out.println("III Y");
							point1Y = iY + (fin1Y);
							point2Y = kY - (fin2Y);
						}

					} else {
						java.lang.System.out.println("IV Y");
						point1Y = iY - (fin1Y);
						point2Y = kY - (fin2Y);
					}

					// ------------------------------------------------------------------

					java.lang.System.out.println("fin1x: " + (int) fin1X
							+ " fin1Y: " + (int) fin1Y + "fin2x: "
							+ (int) fin2X + " fin2Y: " + (int) fin2Y);
					java.lang.System.out.println("point1X: " + (int) point1X
							+ " point1Y: " + (int) point1Y + "point2X: "
							+ (int) point2X + " point2X: " + (int) point2Y);
					// --------------------------------

					this.listOfPoints.get(i).setOldX(point1X);
					this.listOfPoints.get(i).setOldY(point1Y);
					this.listOfPoints.get(k).setOldX(point2X);
					this.listOfPoints.get(k).setOldY(point2Y);

					glColor3f(1.0f, 1.0f, 1.0f);
					// Point.DrawCircle(P1x, P1y, 10, 10);
					// Point.DrawCircle(P2x, P2y, 10, 10);

					glColor3f(this.listOfPoints.get(i).getRed(),
							this.listOfPoints.get(i).getGreen(),
							this.listOfPoints.get(i).getBlue());
					Point.DrawCircle(point1X, point1Y, 4, 3);
					glColor3f(this.listOfPoints.get(k).getRed(),
							this.listOfPoints.get(k).getGreen(),
							this.listOfPoints.get(k).getBlue());
					Point.DrawCircle(point2X, point2Y, 4, 6);

					/*
					 * int iOldX = this.listOfPoints.get(i).getOldX(); int iOldY
					 * = this.listOfPoints.get(i).getOldY(); int kOldX =
					 * this.listOfPoints.get(k).getOldX(); int kOldY =
					 * this.listOfPoints.get(k).getOldY();
					 * 
					 * 
					 * this.listOfPoints.get(i).setOldX(kOldX);
					 * this.listOfPoints.get(i).setOldY(kOldY);
					 * 
					 * this.listOfPoints.get(k).setOldX(iOldX);
					 * this.listOfPoints.get(k).setOldY(iOldY);
					 */

				}

			}
	}

	public void addPoint(Point p) {
		this.listOfPoints.add(p);
	}

	public void addStick(Stick s) {
		this.listOfSticks.add(s);
	}

	public void draw() {
		for (Point p : this.listOfPoints) {
			p.draw();
		}
		for (Stick s : this.listOfSticks) {
			s.draw();
		}
	}

	public boolean isOverlapping(int x, int y, int r) {

		for (Point p : this.listOfPoints) {
			if (Math.sqrt(Math.pow(p.getX() - x, 2) + Math.pow(p.getY() - y, 2)) < 2*r+5)
				return true;
		}

		return false;
	}

	public void updatePosition() {

		// java.lang.System.out.println("Delta: "+deltaTime);

		for (Point p : this.listOfPoints) {
			
			double vx = (this.friction * (p.getX() - p.getOldX()));
			double vy = (this.friction * (p.getY() - p.getOldY()));

			p.setOldX(p.getX());
			p.setOldY(p.getY());

			p.setX((p.getX() + vx));
			p.setY((p.getY() + (vy + gravity)));

			if (p.getX() > this.width) {
				p.setX(width);
				p.setOldX(p.getX() + vx);

			} else if (p.getX() < 0) {
				p.setX(0);
				p.setOldX(p.getX() + vx);
			}

			if (p.getY() > this.height) {
				p.setY(height);
				p.setOldY(p.getY() + vy);

			} else if (p.getY() < 0) {
				p.setY(0);
				p.setOldY(p.getY() + vy);
			}

		}

	}
}
