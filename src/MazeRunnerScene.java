import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.event.EventHandler;

public class MazeRunnerScene extends Application{
	
// Instance Variables
	
	private final int BLOCKSIZE = 30; // size of blocks in maze
	private final int VELOCITY = 5; // velocity of player
	private final int WIDTH = 1200; // width of screen/maze
	private final int HEIGHT = 800; // height of screen/maze
	private final int RADIUS = 5; // radius of player
	private final int STARTX = 0; // player starting x position
	private final int STARTY = 0; // player starting y position
	
	private Circle circle;
	private Maze maze;
	
// Constructor
	
	public MazeRunnerScene()
	{
		// Create player object
		circle = new Circle(BLOCKSIZE / 2, BLOCKSIZE / 2, RADIUS);
		circle.setFill(Color.SIENNA);
		
		maze = new Maze();
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
		return STARTX;
	}
	
	public int getStartY()
	{
		return STARTY;
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
		Group root = new Group(maze.getLayout(), circle);
	
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
		// if right arrow key pressed, move circle VELOCITY pixels
		if(e.getCode() == KeyCode.RIGHT && circle.getCenterX() < WIDTH - RADIUS) circle.setCenterX(circle.getCenterX() + VELOCITY);
		else if(e.getCode() == KeyCode.LEFT && circle.getCenterX() > RADIUS) circle.setCenterX(circle.getCenterX() - VELOCITY);
		else if(e.getCode() == KeyCode.DOWN && circle.getCenterY() < HEIGHT - RADIUS) circle.setCenterY(circle.getCenterY() + VELOCITY);
		else if(e.getCode() == KeyCode.UP && circle.getCenterY() > RADIUS) circle.setCenterY(circle.getCenterY() - VELOCITY);
	}
	
	private Group printMaze()
	{
		Group maze = new Group();
		Rectangle block;
		for(int i = 0; i < HEIGHT / BLOCKSIZE; i++)
		{
			for(int j = 0; j < WIDTH / BLOCKSIZE; j++)
			{
				 if (Math.random() <= 0.2)
				 {
					 block = new Rectangle(j * BLOCKSIZE, 
							 i * BLOCKSIZE, 
							 BLOCKSIZE, 
							 BLOCKSIZE);
					 block.setFill(Color.WHITE);
					 maze.getChildren().add(block);
				 }
			}
		}
		return maze;
	}
	
	public static void main(String args[]) {
		launch(args);
	}
}
