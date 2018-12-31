// TO DO
// how to declare type of ArrayList ?
// Add unit tests
// move character only if on path
// false = wall

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Maze extends MazeRunnerScene{
	private int numRow; // Number of rows of cells in maze
	private int numCol; // Number of columns of cells in maze
	private boolean[][] layout; // which cells are walls vs paths in maze, true == path
	
// Constructor
	public Maze(){
		super();
		numRow = super.HEIGHT / super.BLOCKSIZE;
		numCol = super.WIDTH / super.BLOCKSIZE;
		layout = new boolean[numRow][numCol];
		generateMaze();
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
					 block = new Rectangle(j * BLOCKSIZE, 
							 i * BLOCKSIZE, 
							 BLOCKSIZE, 
							 BLOCKSIZE);
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
		System.out.println(numRow);
		System.out.println(numCol);
		// Start with a grid full of walls
		// default of boolean is false, so false = wall
		
		// create ArrayList to store walls adjacent to given cell
		ArrayList<int[]> walls = new ArrayList<int[]>();
		
		// Pick a cell, mark it as part of the maze, add walls of cell to wall list
		// add adjacent walls to stack
		layout[STARTY][STARTX] = true;
		addWalls(STARTX, STARTY, walls);
		// While there are walls in the list...
		int tempCount = 0;
		while(walls.size() > 0) {
			// pick a random wall from the list
			int temp = (int) (Math.random() * walls.size());
			int tempX = walls.get(temp)[0];
			int tempY = walls.get(temp)[1];
			// If it's only adjacent to one path
			if(makePathHa(tempX, tempY)) {
				// Make the wall a passage
				layout[tempY][tempX] = true;
				// Mark the unvisited cell as part of the maze
				// Add the neighboring walls of the cell to the wall list
				addWalls(tempX, tempY, walls);
				// Remove the wall from the list
			}
			walls.remove(temp);
			tempCount++;
			System.out.println("Number of Loops: " + tempCount);
		}
	}
	
	// adds cells adjacent to given cells that are walls to wall list
	public void addWalls(int x, int y, ArrayList walls)
	{
		int[] temp1 = {x - 1, y};
		int[] temp2 = {x + 1, y};
		int[] temp3 = {x, y - 1};
		int[] temp4 = {x, y + 1};
		
		if(x <= 0 && y <= 0) {
			if (!layout[y][x + 1]) walls.add(temp2);
			if (!layout[y + 1][x]) walls.add(temp4);
		}
		else if (x <= 0 && y >= (numRow - 1)) {
			if (!layout[y][x + 1]) walls.add(temp2);
			if (!layout[y - 1][x]) walls.add(temp3);
		}
		else if (x >= (numCol - 1) && y <= 0) {
			if (!layout[y][x - 1]) walls.add(temp1);
			if (!layout[y + 1][x]) walls.add(temp4);
		}
		else if (x >= (numCol - 1) && y >= (numRow - 1)) {
			if (!layout[y][x - 1]) walls.add(temp1);
			if (!layout[y - 1][x]) walls.add(temp3);
		}
		else if (x <= 0) {
			if (!layout[y][x + 1]) walls.add(temp2);
			if (!layout[y - 1][x]) walls.add(temp3);
			if (!layout[y + 1][x]) walls.add(temp4);
		}
		else if (y <= 0) {
			if (!layout[y][x - 1]) walls.add(temp1);
			if (!layout[y][x + 1]) walls.add(temp2);
			if (!layout[y + 1][x]) walls.add(temp4);
		}
		else if (x >= (numCol - 1)) {
			walls.add(temp1);
			if (!layout[y - 1][x]) walls.add(temp3);
			if (!layout[y + 1][x]) walls.add(temp4);
		}
		else if (y >= (numRow - 1)) {
			walls.add(temp1);
			if (!layout[y][x + 1]) walls.add(temp2);
			if (!layout[y - 1][x]) walls.add(temp3);
		}
		else {
			walls.add(temp1);
			if (!layout[y][x + 1]) walls.add(temp2);
			if (!layout[y - 1][x]) walls.add(temp3);
			if (!layout[y + 1][x]) walls.add(temp4);
		} 
	}
	
	// checks to see if more than one adjacent cell is a path
	// If not, returns true
	private boolean makePathHa(int x, int y){
		int pathCount = 0;
		
		if (x <= 0 && y <= 0) {
			if(layout[y][x + 1]) pathCount++;
			if(layout[y + 1][x]) pathCount++;
			if (pathCount > 1) return false;
			else return true;
		}
		
		else if (x <= 0 && y >= (numRow - 1)) {
			if(layout[y][x + 1]) pathCount++;
			if(layout[y - 1][x]) pathCount++;
			if (pathCount > 1) return false;
			else return true;
		}	
		
		else if (x >= (numCol - 1) && y <= 0) {
			if(layout[y][x - 1]) pathCount++;
			if(layout[y + 1][x]) pathCount++;
			if (pathCount > 1) return false;
			else return true;
		}
		
		else if (x >= (numCol - 1) && y >= (numRow - 1)) {
			if(layout[y][x - 1]) pathCount++;
			if(layout[y - 1][x]) pathCount++;
			if (pathCount > 1) return false;
			else return true;
		}
		
		else if (x <= 0) {
			if(layout[y][x + 1]) pathCount++;
			if(layout[y + 1][x]) pathCount++;
			if(layout[y - 1][x]) pathCount++;
			if(pathCount > 1) return false;
			else return true;
		}
		
		else if (y <= 0) {
			if(layout[y][x + 1]) pathCount++;
			if(layout[y + 1][x]) pathCount++;
			if(layout[y][x - 1]) pathCount++;
			if(pathCount > 1) return false;
			else return true;
		}
		
		else if (x >= (numCol - 1)) {
			if(layout[y - 1][x]) pathCount++;
			if(layout[y + 1][x]) pathCount++;
			if(layout[y][x - 1]) pathCount++;
			if(pathCount > 1) return false;
			else return true;
		}
		
		else if (y >= (numRow - 1)) {
			if(layout[y - 1][x]) pathCount++;
			if(layout[y][x + 1]) pathCount++;
			if(layout[y][x - 1]) pathCount++;
			if(pathCount > 1) return false;
			else return true;
		}
		
		else {
			if(layout[y - 1][x]) pathCount++;
			if(layout[y][x + 1]) pathCount++;
			if(layout[y][x - 1]) pathCount++;
			if(layout[y + 1][x]) pathCount++;
			if(pathCount > 1) return false;
			else return true;
		}
	}
}