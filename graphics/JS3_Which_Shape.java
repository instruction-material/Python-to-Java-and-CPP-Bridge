import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class JS3_Which_Shape extends Application
{
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		Pane root = new Pane();
		Scene scene = new Scene(root, 500, 500);
		
		String shape = "circle"; // You can change this to "square" or "pacman" to see different shapes
		
		if (shape.equals("circle")) {
			Ellipse circle = new Ellipse(250, 250, 50, 50);
			circle.setFill(Color.CYAN);
			root.getChildren().add(circle);
		} else if (shape.equals("square")) {
			Rectangle square = new Rectangle(200, 200, 100, 100);
			square.setFill(Color.RED);
			root.getChildren().add(square);
		} else if (shape.equals("pacman")) {
			Arc pacman = new Arc(250, 250, 50, 50, 45, 270);
			pacman.setFill(Color.YELLOW);
			pacman.setType(ArcType.ROUND);
			root.getChildren().add(pacman);
		}
		
		primaryStage.setTitle("Which Shape");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}


/*
// Original
import java.awt.*;
import java.applet.Applet;

public class JS3_Which_Shape extends Applet
{

    public void paint(Graphics g) {
        String shape = "circle";

        if (shape.equals("circle"))
        {
            g.setColor(Color.cyan);
            g.fillOval(200, 200, 100, 100);
        }
        else if (shape.equals("square"))
        {
            g.setColor(Color.red);
            g.fillRect(200, 200, 100, 100);
        }
        else if (shape.equals("pacman"))
        {
            g.setColor(Color.yellow);
            g.fillArc(200, 200, 100, 100, 45, 270);
        }
    }
}
 */
