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
	private Cell[][] layout; // which cells are walls vs paths in maze, true == path
	private int[] endPoint;
	
// Constructor
	public Maze(){
		super();
		numRow = super.HEIGHT / super.BLOCKSIZE;
		numCol = super.WIDTH / super.BLOCKSIZE;
		layout = new Cell[numRow][numCol];
		for(int i = 0; i < numCol; i++) {
			for(int j = 0; j < numRow; j++) {
				int[] tempLoc = {i, j};
				layout[j][i] = new Cell(false, tempLoc);
			}
		}
		generateMaze();
		findEndPoint();
	}
	
// Mutator Methods
	
	public void setLayout(Cell[][] l)
	{
		layout = l;
	}
	
// Accessor Methods	
	
	public Cell[][] getLayout()
	{
		return layout;
	}
	
	public int[] getEndPoint()
	{
		return endPoint;
	}
	
// Primary Methods

	public Group getLayoutGraphic()
	{
		Group group = new Group();
		Rectangle block;
		for(int i = 0; i < numRow; i++)
		{
			for(int j = 0; j < numCol; j++)
			{
				 if (layout[i][j].getPath())
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
		Rectangle finish = new Rectangle(endPoint[0] * BLOCKSIZE, endPoint[1] * BLOCKSIZE, BLOCKSIZE, BLOCKSIZE);
		finish.setFill(Color.RED);
		group.getChildren().add(finish);
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
		layout[STARTLOC[1]][STARTLOC[0]].setPath(true);
		layout[STARTLOC[1]][STARTLOC[0]].setParent(null);
		addWalls(STARTLOC, walls);
		// While there are walls in the list...
		while(walls.size() > 0) {
			// pick a random wall from the list
			int temp = (int) (Math.random() * walls.size());
			int[] tempLoc = walls.get(temp);
			Cell tempCell = layout[tempLoc[1]][tempLoc[0]];
			// If the cell on the other side of the wall is not part of the maze, get rid of wall and make them both part of maze
			if(makePathHa(tempCell)) {
				// Make the wall a passage
				tempCell.setPath(true);
				// Mark the unvisited cell as part of the maze
				int[] opposite = tempCell.getOpposite();
				Cell opp = layout[opposite[1]][opposite[0]];
				opp.setParent(tempLoc);
				opp.setPath(true);
				// Add the neighboring walls of the cell to the wall list
				addWalls(opposite, walls);
			}
			// Remove the wall from the list
			walls.remove(temp);
		}
	}
	
	private void findEndPoint() {
		for(int i = numCol - 1; i >= 0; i--) {
			for(int j = numRow - 1; j >= 0; j--) {
				if (layout[j][i].getPath()) {
					endPoint = new int[2];
					endPoint[0] = i;
					endPoint[1] = j;
					return;
				}
			}
		}
	}
	
	// adds cells adjacent to given cells that are walls to wall list
	public void addWalls(int[] loc, ArrayList walls)
	{
		int x = loc[0];
		int y = loc[1];
		int[][] neighbors = { {x - 1, y}, 
							  {x + 1, y},
							  {x, y - 1},
							  {x, y + 1}};
		
		for(int i = 0; i < 4; i++) {
			try {
				Cell temp = layout[neighbors[i][1]][neighbors[i][0]];
				if(!(temp.getPath())) {
					walls.add(temp.getLocation());
					temp.setParent(loc);
					temp.setOpposite();
				}
			}
			catch (Exception ArrayIndexOutOfBoundsException) {
				continue;
			}
		}
	}
	
	// return true if
	private boolean makePathHa(Cell t){
		try {
			if(layout[t.getOpposite()[1]][t.getOpposite()[0]].getPath()) return false;
			else return true;
		}
		catch (Exception ArrayIndexOutOfBoundsException) {
			return false;
		}
	}
}