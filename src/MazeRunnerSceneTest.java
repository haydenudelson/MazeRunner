import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import junit.framework.Assert;

class MazeTest {

	@Test
	void testAddWalls() {
		//fail("Not yet implemented");
		Maze tempMaze = new Maze();
		boolean[][] tempLayout = {{false, true}, {true, false}};
		tempMaze.setLayout(tempLayout);
		ArrayList<int[]> actual = new ArrayList<int[]>();
		
		tempMaze.addWalls(0, 0, actual);
		
		/*ArrayList<int[]> expected = new ArrayList<int[]>();
		int[] tempArray = {1, 1};
		expected.add(tempArray);
		
		Assert.assertEquals(expected, actual);*/
	}
	

}
