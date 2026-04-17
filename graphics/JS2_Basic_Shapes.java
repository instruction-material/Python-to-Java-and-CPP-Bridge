import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class JS2_Basic_Shapes extends Application
{
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		Pane root = new Pane();
		
		// Rectangle
		Rectangle rect = new Rectangle(50, 50, 100, 100);
		rect.setFill(Color.RED);
		
		// Ellipse (for oval)
		Ellipse oval = new Ellipse(250, 100, 50, 50);
		oval.setFill(Color.CYAN);
		
		// Arc
		Arc arc = new Arc(100, 300, 50, 50, 90, 270);
		arc.setFill(Color.GREEN);
		arc.setType(ArcType.ROUND);
		
		root.getChildren().addAll(rect, oval, arc);
		
		Scene scene = new Scene(root, 400, 400);
		primaryStage.setTitle("Basic Shapes");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}

/*
// Original
import java.awt.*;
import java.applet.Applet;

public class JS2_Basic_Shapes extends Applet
{
    public void paint(Graphics g) {
        // fillRect() takes in x, y, width, height
        g.setColor(Color.red);
        g.fillRect(50, 50, 100, 100);

        // fillOval() takes in x, y, width, height
        g.setColor(Color.cyan);
        g.fillOval(200, 50, 100, 100);

        // fillArc() takes in x, y, width, height, startAngle, arcAngle
        g.setColor(Color.green);
        g.fillArc(50, 200, 100, 100, 90, 270);

        // colors: https://docs.oracle.com/javase/7/docs/api/java/awt/Color.html
    }
}
 */
