
public class Cell {
	
	private boolean path;
	private int[] location;
	private int[] parent;
	private int[] opposite;
	
// Constructors
	
	public Cell() {
		location = new int[2];
		parent = new int[2];
		opposite = new int[2];
		
	}
	
	public Cell(boolean p, int[] l) {
		path = p;
		location = l;
	}
	
// Accessor Methods
	
	public boolean getPath() {
		return path;
	}
	
	public int[] getLocation() {
		return location;
	}
	
	public int[] getParent() {
		return parent;
	}
	
	public int[] getOpposite() {
		return opposite;
	}
	
// Mutator Methods
	
	public void setPath(boolean b) {
		path = b;
	}
	
	public void setLocation(int[] l) {
		location = l;
	}
	
	public void setParent(int[] p) {
		parent = p;
	}
	
	public void setOpposite(int[] o) {
		opposite = o;
	}
	
	public int[] setOpposite() {
		opposite = new int[2];
		opposite[0] = (parent[0] - location[0]) * -1 + location[0];
		opposite[1] = (parent[1] - location[1]) * -1 + location[1];
		return opposite;
	}

}
