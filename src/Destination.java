import java.awt.Point;
import java.util.concurrent.ThreadLocalRandom;

public class Destination implements Entity {
	private int x, y;
	private int id;
	
	public Destination() {
		id = ThreadLocalRandom.current().nextInt();
	}
	
	public Point getPosition() {
		return new Point(x, y);
	}
	
	public void setPosition(Point pos) {
		x = (int) pos.getX();
		y = (int) pos.getY();
	}
	
	public int getId() {
		return id;
	}
	
	public void display() {
		System.out.printf("[O]");
	}
}
