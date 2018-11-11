// TO DO
// how to declare type of ArrayList ?
// Add unit tests

import java.util.ArrayList;

public class Maze{
	private int numRow; // Number of rows of cells in maze
	private int numCol; // Number of columns of cells in maze
	private boolean[][] layout; // which cells are walls vs. paths in maze
	private final int STARTX = 0;
	private final int STARTY = 0;
	
	// Default Constructor
	public Maze(){
		numRow = 0;
		numCol = 0;
	}
	
	//Constructs Maze object with set number of cells
	public Maze(int r, int c) {
		numRow = r;
		numCol = c;
	}
	
	// Generates random maze
	private void generateMaze()
	{
		// Start with a grid full of walls
		// default of boolean is false, so false = wall
		
		// create ArrayList to store walls adjacent to given cell
		ArrayList walls = new ArrayList();
		
		// Pick a cell, mark it as part of the maze, add walls of cell to wall list
		// add adjacent walls to stack
		int[] curr = {STARTX, STARTY};
		layout[curr[0]][curr[1]] = true;
		addWalls(curr[0], curr[1], walls);
		// While there are walls in the list...
		while(walls.size() > 0) {
			// pick a random wall from the list
			int temp = (int) (Math.random() * walls.size());
			int tempX = walls.get(temp)[0]
			// If only one of the two cells that the wall divides is visited:
			if(makePathHa(curr[0], curr[1])) {
				
			}
		}
		//		Make the wall a passage and mark the unvisited cell as part of the maze
		//		Add the neighboring walls of the cell to the wall list
		//	Remove the wall from the list
	}
	
	// adds cells adjacent to given cells that are walls to wall list
	private void addWalls(int x, int y, ArrayList walls)
	{
		//consider each cell adjacent to the cell in question
		for(int i = x - 1; i <= x + 1; i++) {
			for(int j = y - 1; j <= y + 1; j++) {
				// if cell is not at same location as primary cell,
				// is not a path, and is inbounds add to wall list
				if (i >= 0 && i <= numCol 
						&& j >= 0 && j <= numRow
						&& i != x && j != y && !(layout[i][j])) {
					int[] temp = {i, j};
					if(layout[x - 1][y - 2] == false) walls.add(temp);
				}
			}
		}
		
	}
	
	// checks to see if more than one adjacent cell is a path
	// If not, returns true
	private boolean makePathHa(int x, int y){
		int pathCount = 0;
		for(int i = x - 1; i <= x + 1; i++) {
			for(int j = y - 1; j <= y + 1; j++) {
				// if more than one cell is a path, return false
				if (i >= 0 && i <= numCol 
						&& j >= 0 && j <= numRow
						&& i != x && j != y && layout[i][j]) {
					if(pathCount >= 1) return false;
					else pathCount++;
				}
			}
		}
		return true;
	}
}