import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class JS6_Picasso extends Application
{
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		Pane root = new Pane();
		Scene scene = new Scene(root, 500, 500);
		
		for (int i = 0; i < 500; i++) {
			int rand = (int) (Math.random() * 3);
			int red = (int) (Math.random() * 256);
			int green = (int) (Math.random() * 256);
			int blue = (int) (Math.random() * 256);
			int x = (int) (Math.random() * 500);
			int y = (int) (Math.random() * 500);
			int size = (int) (Math.random() * 50);
			
			if (rand == 0) {
				drawSquare(red, green, blue, x, y, size, root);
			} else if (rand == 1) {
				drawCircle(red, green, blue, x, y, size, root);
			} else {
				drawPacman(red, green, blue, x, y, size, root);
			}
		}
		
		primaryStage.setTitle("Picasso");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void drawSquare(int red, int green, int blue, int x, int y, int sideLength, Pane root) {
		Rectangle square = new Rectangle(x, y, sideLength, sideLength);
		square.setFill(Color.rgb(red, green, blue));
		root.getChildren().add(square);
	}
	
	public void drawCircle(int red, int green, int blue, int x, int y, int diameter, Pane root) {
		Circle circle = new Circle(x + diameter / 2.0, y + diameter / 2.0, diameter / 2.0);
		circle.setFill(Color.rgb(red, green, blue));
		root.getChildren().add(circle);
	}
	
	public void drawPacman(int red, int green, int blue, int x, int y, int diameter, Pane root) {
		Arc pacman = new Arc(x + diameter / 2.0, y + diameter / 2.0, diameter / 2.0, diameter / 2.0, 50, 270);
		pacman.setFill(Color.rgb(red, green, blue));
		pacman.setType(ArcType.ROUND);
		root.getChildren().add(pacman);
	}
}

/*
// Original
import java.awt.*;
import java.applet.Applet;

public class JS6_Picasso extends Applet
{
    public void paint(Graphics g) {
        for (int i = 0; i < 500; i++) {
            int rand = (int)(Math.random() * 3);
            int red = (int)(Math.random() * 256);
            int green = (int)(Math.random() * 256);
            int blue = (int)(Math.random() * 256);
            int x = (int)(Math.random() * 500);
            int y = (int)(Math.random() * 500);
            int size = (int)(Math.random() * 50);

            if (rand == 0) {
                drawSquare(red, green, blue, x, y, size, g);
            } else if (rand == 1) {
                int diameter = (int)(Math.random() * 50);
                drawCircle(red, green, blue, x, y, size, g);
            } else {
                drawPacman(red, green, blue, x, y, size, g);
            }

        }
    }

    public void drawSquare(int red, int green, int blue, int x, int y, int sideLength, Graphics g) {
        Color c = new Color(red, green, blue);
        g.setColor(c);
        g.fillRect(x, y, sideLength, sideLength);
    }

    public void drawCircle(int red, int green, int blue, int x, int y, int diameter, Graphics g) {
        Color c = new Color(red, green, blue);
        g.setColor(c);
        g.fillOval(x, y, diameter, diameter);
    }

    public void drawPacman(int red, int green, int blue, int x, int y, int diameter, Graphics g) {
        Color c = new Color(red, green, blue);
        g.setColor(c);
        g.fillArc(x, y, diameter, diameter, 50, 270);
    }
}
 */
