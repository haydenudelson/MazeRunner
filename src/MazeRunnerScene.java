import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.event.EventHandler;
//extends Application
public class MazeRunnerScene extends Application {
	
// Instance Variables
	
	public final int BLOCKSIZE = 25; // size of blocks in maze
	public final int WIDTH = 1200; // width of screen/maze
	public final int HEIGHT = 800; // height of screen/maze
	public final int[] STARTLOC = {0, 0};
	private final int RADIUS = 8; // radius of player
	private final int VELOCITY = 10; // velocity of player
	
	private Circle circle;
	private Maze maze;
	
// Constructor
	
	public MazeRunnerScene()
	{
		// Create player object
		circle = new Circle(BLOCKSIZE / 2, BLOCKSIZE / 2, RADIUS);
		circle.setFill(Color.SIENNA);
	}
	
// Access Methods
	
	public int getBlockSize()
	{
		return BLOCKSIZE;
	}
	
	public int getHeight()
	{
		return HEIGHT;
	}
	
	public Maze getMaze()
	{
		return maze;
	}
	
	public int getStartX()
	{
		return STARTLOC[0];
	}
	
	public int getStartY()
	{
		return STARTLOC[1];
	}
	
	public int getWidth()
	{
		return WIDTH;
	}
	
	// start method holds all info on javaFX application
	public void start(Stage mainStage){
		// Creates EventHandler that calls moveCircle when keyEvent occurs
		EventHandler<KeyEvent> eventHandler = new EventHandler<KeyEvent>( ) {
			public void handle(KeyEvent e) {moveCircle(e);}
		};
		
		// Creates a group object
		maze = new Maze();
		Group root = new Group(maze.getLayoutGraphic(), circle);
	
		// Creating a scene with group object, height, width
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		
		// when key pressed in scene, eventHandler called
		scene.addEventFilter(KeyEvent.KEY_PRESSED, eventHandler);
		
		// Set title of Stage
		mainStage.setTitle("Maze Runner");
		
		// Set color of scene
		scene.setFill(Color.BLACK);
		
		// Add scene to Stage
		mainStage.setScene(scene);
		
		// Display contents of stage
		mainStage.show();
	}
	
	private void moveCircle(KeyEvent e)
	{
		Cell[][] layout = maze.getLayout();
		if(e.getCode() == KeyCode.RIGHT) {
			if(!(layout[(int) (circle.getCenterY() / BLOCKSIZE)][(int) ((circle.getCenterX() + VELOCITY + RADIUS) / BLOCKSIZE)].getPath()))
				circle.setCenterX((int) (circle.getCenterX() / BLOCKSIZE) * BLOCKSIZE + BLOCKSIZE - RADIUS);
			else 
				circle.setCenterX(circle.getCenterX() + VELOCITY);
		}
		else if(e.getCode() == KeyCode.LEFT) {
			if(!(layout[(int) (circle.getCenterY() / BLOCKSIZE)][(int) ((circle.getCenterX() - VELOCITY - RADIUS) / BLOCKSIZE)].getPath()))
				circle.setCenterX((int) (circle.getCenterX() / BLOCKSIZE) * BLOCKSIZE + RADIUS);
			else circle.setCenterX(circle.getCenterX() - VELOCITY);
		}
		else if(e.getCode() == KeyCode.DOWN) {
			if(!(layout[(int) ((circle.getCenterY() + VELOCITY + RADIUS) / BLOCKSIZE)][(int) (circle.getCenterX() / BLOCKSIZE)].getPath()))
				circle.setCenterY((int) (circle.getCenterY() / BLOCKSIZE) * BLOCKSIZE+ BLOCKSIZE - RADIUS);
			else circle.setCenterY(circle.getCenterY() + VELOCITY);
		}
		else if(e.getCode() == KeyCode.UP) {
			if(!(layout[(int) ((circle.getCenterY() - VELOCITY - RADIUS) / BLOCKSIZE)][(int) ((circle.getCenterX()) / BLOCKSIZE)].getPath()))
				circle.setCenterY((int) (circle.getCenterY() / BLOCKSIZE) * BLOCKSIZE + RADIUS);
			else circle.setCenterY(circle.getCenterY() - VELOCITY);
		}
		// if right arrow key pressed, move circle VELOCITY pixels
		
		if(circle.getCenterX() < RADIUS) circle.setCenterX(RADIUS);
		else if(circle.getCenterX() >= WIDTH - RADIUS) circle.setCenterX(WIDTH - RADIUS);
		
		if(circle.getCenterY() < RADIUS) circle.setCenterY(RADIUS);
		else if(circle.getCenterY() >= HEIGHT - RADIUS) circle.setCenterY(HEIGHT - RADIUS);
	}
	
	public static void main(String args[]) {
		Application.launch(args);
	}
}
