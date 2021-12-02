import java.awt.Point;

public class Node {
	private Boolean visited;
	private Integer shortestDistanceFromOrigin;
	private String idOfShortestNode;
	private Point position;
	private String id;
	
	Node(Point pos) {
		visited = false;
		shortestDistanceFromOrigin = Integer.MAX_VALUE;
		position = pos;
		id = "x" + pos.x + "y" + pos.y;
	}
	
	public String getId() {
		return id;
	}
	
	public Boolean getVisited()
	{
		return visited;
	}
	
	public Point getPosition() {
		return position;
	}
	
	public void setVisited(Boolean v) {
		visited = v;
	}
	
	public Integer getShortestDistanceFromOrigin() {
		return shortestDistanceFromOrigin;
	}
	
	public String getShortestNodeId() {
		return idOfShortestNode;
	}
	
	public void setShortestDistanceFromOrigin(int sd, String sn) {
		shortestDistanceFromOrigin = sd;
		idOfShortestNode = sn;
	}
}
