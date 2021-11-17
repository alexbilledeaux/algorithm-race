import java.awt.Point;

public class AlgorithmRace {
	
	static int x = 25;
	static int y = 25;
	static int numOfObstacles = (x + y) * ((x+y)/25);
	
	public static void main(String[] args) {
		Map map = new Map(x, y);
		Point actorPos = map.AddEntity(new Actor());
		Point destPos = map.AddEntity(new Destination());
		for (int i = 0; i < numOfObstacles; i++) {
			map.AddEntity(new Obstacle());
		}
		
		map.PrintMap();
		Actor a = (Actor) map.GetEntityAtLocation(actorPos);
		Destination d = (Destination) map.GetEntityAtLocation(destPos);
		a.addToDestinationQueue(d);
		a.BranchBoundMemoPathfinding();
	}

}
