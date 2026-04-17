import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class JS2_Snowman extends Application
{
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		Pane root = new Pane();
		Scene scene = new Scene(root, 400, 400);
		
		// Snowballs
		Circle topSnowball = new Circle(175, 75, 25);
		topSnowball.setFill(Color.WHITE);
		Circle middleSnowball = new Circle(175, 145, 50);
		middleSnowball.setFill(Color.WHITE);
		Circle bottomSnowball = new Circle(175, 265, 75);
		bottomSnowball.setFill(Color.WHITE);
		
		// Hat
		Rectangle hatTop = new Rectangle(150, 30, 50, 30);
		hatTop.setFill(Color.BLACK);
		Rectangle hatBrim = new Rectangle(140, 60, 70, 10);
		hatBrim.setFill(Color.BLACK);
		
		// Arms
		Rectangle leftArm = new Rectangle(75, 145, 50, 5);
		leftArm.setFill(Color.BLACK);
		Rectangle rightArm = new Rectangle(225, 145, 50, 5);
		rightArm.setFill(Color.BLACK);
		
		// Ribbon on hat
		Rectangle ribbon = new Rectangle(150, 45, 50, 5);
		ribbon.setFill(Color.YELLOW);
		
		root.getChildren().addAll(topSnowball, middleSnowball, bottomSnowball, hatTop, hatBrim, leftArm, rightArm, ribbon);
		
		primaryStage.setTitle("Basic Shapes - Snowman");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}

/*
// Original
import java.awt.*;
import java.applet.Applet;

public class BasicShapes extends Applet {
	public void paint(Graphics g) {

		g.setColor(Color.white);

		// snowballs
		g.fillOval(150, 50, 50, 50);
		g.fillOval(125, 95, 100, 100);
		g.fillOval(100, 190, 150, 150);

		g.setColor(Color.black);

		// hat
		g.fillRect(150, 30, 50, 30);
		g.fillRect(140, 50, 70, 10);

		// arms
		g.fillRect(75, 145, 50, 5);
		g.fillRect(225, 145, 50, 5);

		g.setColor(Color.yellow);

		// ribbon on hat
		g.fillRect(150, 45, 50, 5);

	}
}
 */
