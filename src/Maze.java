// TO DO
// how to declare type of ArrayList ?
// Add unit tests
// move character only if on path

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Maze extends MazeRunnerScene{
	private int numRow; // Number of rows of cells in maze
	private int numCol; // Number of columns of cells in maze
	private boolean[][] layout; // which cells are walls vs. paths in maze
	private int startX; // Start cell X pos
	private int startY; // Start cell Y pos
	private int blockSize;
	
// Constructor
	public Maze(){
		startX = this.getStartX();
		startY = this.getStartY();
		blockSize = this.getBlockSize();
		
		numRow = this.getHeight() / this.getBlockSize();
		numCol = this.getWidth() / this.getBlockSize();
	}
	
// Mutator Methods
	
	public void setLayout(boolean[][] l)
	{
		layout = l;
	}
	
// Primary Methods

	public Group getLayout()
	{
		Group group = new Group();
		Rectangle block;
		for(int i = 0; i < numRow; i++)
		{
			for(int j = 0; j < numCol; j++)
			{
				 if (layout[i][j])
				 {
					 block = new Rectangle(j * blockSize, 
							 i * blockSize, 
							 blockSize, 
							 blockSize);
					 block.setFill(Color.WHITE);
					 group.getChildren().add(block);
				 }
			}
		}
		return group;
	}
	
	// Generates random maze
	private void generateMaze()
	{
		// Start with a grid full of walls
		// default of boolean is false, so false = wall
		
		// create ArrayList to store walls adjacent to given cell
		ArrayList<int[]> walls = new ArrayList<int[]>();
		
		// Pick a cell, mark it as part of the maze, add walls of cell to wall list
		// add adjacent walls to stack
		int[] curr = {startX, startY};
		layout[curr[0]][curr[1]] = true;
		addWalls(curr[0], curr[1], walls);
		// While there are walls in the list...
		while(walls.size() > 0) {
			// pick a random wall from the list
			int temp = (int) (Math.random() * walls.size());
			int tempX = walls.get(temp)[0];
			int tempY = walls.get(temp)[1];
			// If only one of the two cells that the wall divides is visited:
			if(makePathHa(curr[0], curr[1])) {
				// Make the wall a passage
				layout[tempX][tempY] = true;
				// Mark the unvisited cell as part of the maze
				// Add the neighboring walls of the cell to the wall list
				addWalls(tempX, tempY, walls);
				// Remove the wall from the list
				walls.remove(temp);
			}
		}
	}
	
	// adds cells adjacent to given cells that are walls to wall list
	public void addWalls(int x, int y, ArrayList walls)
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