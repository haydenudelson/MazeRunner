import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import junit.framework.Assert;

class MazeTest {

	@Test
	void testAddWalls1() {
		Maze tempMaze = new Maze();
		boolean[][] tempLayout = {{true, false}, {false, false}};
		tempMaze.setLayout(tempLayout);
		ArrayList<int[]> actual = new ArrayList<int[]>();
		
		tempMaze.addWalls(0, 0, actual);
		
		ArrayList<int[]> expected = new ArrayList<int[]>();
		int[] tempArray1 = {0, 1};
		int[] tempArray2 = {1, 0};
		int[] tempArray3 = {1, 1};
		expected.add(tempArray1);
		expected.add(tempArray2);
		expected.add(tempArray3);
		System.out.println("hello");
		for(int i = 0; i < expected.size(); i++) {
			for(int j = 0; j < 2; j++) {
				Assert.assertEquals(expected.get(i)[j], actual.get(i)[j]);
			}
		}
		
		Assert.assertEquals(expected.size(), actual.size());
		
	}
	
	/*
	// Test if no walls to be added
	@Test
	void testAddWalls2() {
		Maze tempMaze = new Maze();
		boolean[][] tempLayout = {{true, true}, {true, true}};
		tempMaze.setLayout(tempLayout);
		ArrayList<int[]> actual = new ArrayList<int[]>();
		
		tempMaze.addWalls(0, 0, actual);
		
		ArrayList<int[]> expected = new ArrayList<int[]>();
		
		for(int i = 0; i < expected.size(); i++) {
			for(int j = 0; j < 2; j++) {
				Assert.assertEquals(expected.get(i)[j], actual.get(i)[j]);
			}
		}
		
		Assert.assertEquals(expected.size(), actual.size());
	}
	*/
}
