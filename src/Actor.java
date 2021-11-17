import java.awt.Point;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public class Actor implements Entity {
	
	private int x, y;
	private int id;
	private Vector<Point> bestPath;
	private Vector<Destination> destinationQueue = new Vector<Destination>();
	private Map mapSnapshot;
	
	public Actor () {
		id = ThreadLocalRandom.current().nextInt();
	}
	
	public Actor(Actor a) {
		x = a.x;
		y = a.y;
		id = a.id;
	}
	
	public void BranchBoundMemoPathfinding() {
		
		for (int i = 0; i < destinationQueue.size(); i++) {
			long timeAtStart = System.nanoTime();
			BranchBoundMemoPathfinding(destinationQueue.get(i), this.getPosition(), new Vector<Point>());
			long timeAtEnd = System.nanoTime();
			System.out.print("Path to destination: ");
			
			if (bestPath != null) {
				displayReadablePath(bestPath);
			} else {
				System.out.print("No path");
			}
			
			System.out.print(". Execution time: " + (timeAtEnd - timeAtStart));
			System.lineSeparator();
		}
	}
	
	public Path DijkstraPathfinding(Destination d) {
		Path path = new Path();
		return path;
	}
	
	public Point getPosition() {
		return new Point(x, y);
	}
	
	public void setPosition(Point pos) {
		x = (int) pos.getX();
		y = (int) pos.getY();
	}
	
	public void setMap(Map m) {
		mapSnapshot = m;
	}
	
	public int getId() {
		return id;
	}
	
	public void addToDestinationQueue (Destination d) {
		destinationQueue.add(d);
	}
	
	private void displayReadablePath(Vector<Point> p) {
		for (int z = 0; z < p.size(); z++) {
			if (p.get(z).x > 0)
				System.out.print("R ");
			if (p.get(z).x < 0)
				System.out.print("L ");
			if (p.get(z).y > 0)
				System.out.print("D ");
			if (p.get(z).y < 0)
				System.out.print("U ");
		}
	}
	
	private void BranchBoundMemoPathfinding(Destination d, Point currentPosition, Vector<Point> currentPath) {
		
		//double currentDistance =  Math.abs(currentPosition.y - d.getPosition().getY()) + Math.abs(currentPosition.x - d.getPosition().getX());
		
		if (currentPosition.x < 0 || currentPosition.y < 0 || currentPosition.x > mapSnapshot.getWidth() || currentPosition.y > mapSnapshot.getLength()) {
			//System.out.println("Off the map. Stopping.");
			return;
		} else if (mapSnapshot.GetEntityAtLocation(currentPosition) instanceof Obstacle)  {
			//System.out.println("Hit an obstacle at " + a.getPosition()  + ". Stopping.");
			return;
		} else if (currentPath.size() > 200 || ( bestPath != null && currentPath.size() > bestPath.size())) {
			//System.out.println("Ran out of time. Stopping.");
			return;
		} else if(mapSnapshot.GetEntityAtLocation(currentPosition) == d) {
			if (bestPath == null || currentPath.size() < bestPath.size())
				bestPath = currentPath;
			return;
		}  else if (currentPath.size() > 0 && (Math.abs(currentPosition.y - currentPath.lastElement().y - d.getPosition().getY()) < Math.abs(currentPosition.y - d.getPosition().getY()))) {
			//System.out.println("Moving away from the Y of destination. Stopping.");
			return;
		} else if (currentPath.size() > 0 && (Math.abs(currentPosition.x - currentPath.lastElement().x - d.getPosition().getX()) < Math.abs(currentPosition.x - d.getPosition().getX()))) {
			//System.out.println("Moving away from the X of destination. Stopping.");
			return;
		} else {
			
			Point PosAfterMoveUp = new Point(currentPosition);
			PosAfterMoveUp.y++;
			Vector<Point> currentPathUp = (Vector<Point>) currentPath.clone();
			currentPathUp.add(new Point(0,1));
			BranchBoundMemoPathfinding(d, PosAfterMoveUp, currentPathUp);
	
			Point PosAfterMoveDown = new Point(currentPosition);
			PosAfterMoveDown.y--;
			Vector<Point> currentPathDown = (Vector<Point>) currentPath.clone();
			currentPathDown.add(new Point(0, -1));
			BranchBoundMemoPathfinding(d, PosAfterMoveDown, currentPathDown);
	
				
			Point PosAfterMoveLeft = new Point(currentPosition);
			PosAfterMoveLeft.x--;
			Vector<Point> currentPathLeft = (Vector<Point>) currentPath.clone();
			currentPathLeft.add(new Point(-1, 0));
			BranchBoundMemoPathfinding(d, PosAfterMoveLeft, currentPathLeft);
		
			Point PosAfterMoveRight = new Point(currentPosition);
			PosAfterMoveRight.x++;
			Vector<Point> currentPathRight = (Vector<Point>) currentPath.clone();
			currentPathRight.add(new Point(1,0));
			BranchBoundMemoPathfinding(d, PosAfterMoveRight, currentPathRight);
		}
	}
	
}
