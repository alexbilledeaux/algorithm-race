import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Map {

	private int width;
	private int length;
	private List<Entity> entities = new ArrayList<Entity>();
	
	public Map(int w, int l) {
		width = w;
		length = l;
	}
	
	public Point AddEntity(Entity e) {
		int randomX, randomY;
		randomX = ThreadLocalRandom.current().nextInt(0, width + 1);
		randomY = ThreadLocalRandom.current().nextInt(0, length + 1);
		Point pos = AddEntityAtLocation(e, new Point(randomX, randomY));
		if (e instanceof Actor)
			((Actor) e).setMap(this);
		return pos;
	}
	
	public void PrintMap() {
		for (int in = 0; in < length + 1; in++) {
			for (int i = 0; i < width + 1; i++) {
				Entity e = null;
				for (int z = 0; z < entities.size(); z++) {
					if (entities.get(z).getPosition().getX() == i && entities.get(z).getPosition().getY() == in) {
						e = entities.get(z);
					}
				}
				if (e instanceof Obstacle) {
					System.out.printf("[X]");
				} else if (e instanceof Actor) {
					System.out.printf("[A]");
				} else if (e instanceof Destination) {
					System.out.printf("[O]");
				}
				else {
					System.out.printf("[ ]");
				}
			}
			System.out.printf("\n");
		}
	}
	
	public int getLength() {
		return length;
	}
	
	public int getWidth() {
		return width;
	}
	
	public Entity GetEntityAtLocation(Point pos) {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).getPosition().equals(pos)) {
				return entities.get(i);
			}
		}
		return null;
	}
	
	private Point AddEntityAtLocation(Entity e, Point pos) {
		if (pos.getX() < 0 || pos.getY() < 0 || pos.getX() > width || pos.getY() > length) {
			throw new IllegalArgumentException("Entity position is off the Map.");
		}
		Point finalPos = pos;
		int nudgeDirection = 0;
		while (GetEntityAtLocation(finalPos) != null) {
			nudgeDirection = ThreadLocalRandom.current().nextInt(0, 4);
			switch(nudgeDirection) {
				case 0:
					if (finalPos.getX() < length)
						finalPos.x++;
					break;
				case 1:
					if (finalPos.getX() > 0)
						finalPos.x--;
					break;
				case 2:
					if (finalPos.getY() < length)
						finalPos.y++;
					break;
				case 3:
					if (finalPos.getY() > 0)
						finalPos.y--;
					break;
			}
		}
		e.setPosition(finalPos);
		entities.add(e);
		return finalPos;
	}
	
}
