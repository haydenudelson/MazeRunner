import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import junit.framework.Assert;

class MazeTest {

	@Test
	void testAddWalls() {
		//fail("Not yet implemented");
		MazeRunnerScene tempMazeRunnerScene = new MazeRunnerScene();
		Maze tempMaze = tempMazeRunnerScene.getMaze();
		boolean[][] tempLayout = {{false, false}, {false, false}};
		tempMaze.setLayout(tempLayout);
		ArrayList<int[]> actual = new ArrayList<int[]>();
		
		tempMaze.addWalls(0, 0, actual);
		
		List<String> temp = new ArrayList<String>(Arrays.asList("1", "2"));
		ArrayList<int[]> expected = new ArrayList<int[]>(Arrays.asList({2, 3}));
	}
	

}
