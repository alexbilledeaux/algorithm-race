import java.awt.Point;
import java.util.concurrent.ThreadLocalRandom;

public class Obstacle implements Entity {
	
	private int x, y;
	
	public Obstacle() {
		
	}
	
	public Point getPosition() {
		return new Point(x, y);
	}
	
	public void setPosition(Point pos) {
		x = (int) pos.getX();
		y = (int) pos.getY();
	}
	
	public void display() {
		System.out.printf("[X]");
	}
	
}
