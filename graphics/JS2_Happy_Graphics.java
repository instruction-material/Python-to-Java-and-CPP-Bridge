import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class JS2_Happy_Graphics extends Application
{
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		Pane root = new Pane();
		
		// Smiley Face
		Rectangle leftEye = new Rectangle(200, 25, 10, 100);
		Rectangle rightEye = new Rectangle(300, 25, 10, 100);
		Arc smile = new Arc(250, 175, 150, 75, 180, 180);
		smile.setType(ArcType.OPEN);
		smile.setFill(Color.BLACK);
		
		// Pac-Men
		Arc pacMan1 = new Arc(175, 375, 75, 75, 50, 270);
		pacMan1.setFill(Color.YELLOW);
		pacMan1.setType(ArcType.ROUND);
		
		Arc pacMan2 = new Arc(325, 375, 75, 75, 220, 270);
		pacMan2.setFill(Color.YELLOW);
		pacMan2.setType(ArcType.ROUND);
		
		root.getChildren().addAll(leftEye, rightEye, smile, pacMan1, pacMan2);
		
		Scene scene = new Scene(root, 500, 500);
		primaryStage.setTitle("Happy Graphics");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}

/*
// Original
import java.awt.*;
import java.applet.Applet;

public class JS2_Happy_Graphics extends Applet
{
    public void paint(Graphics g) {
        // smiley face
        g.fillRect(200, 25, 10, 100);
        g.fillRect(300, 25, 10, 100);
        g.fillArc(100, 100, 300, 150, 180, 180);

        // pac men
        g.setColor(Color.yellow);
        g.fillArc(100, 300, 150, 150, 50, 270);
        g.fillArc(250, 300, 150, 150, 220, 270);
    }
}
 */
