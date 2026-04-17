import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class JS4_Paintball extends Application
{
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		Pane root = new Pane();
		Scene scene = new Scene(root, 500, 500);
		
		int x1 = 0;
		int y1 = 0;
		
		int x2 = 250;
		int y2 = 0;
		
		for (int i = 0; i <= 10; i++) {
			Circle circle1 = new Circle(x1 + 12.5, y1 + 12.5, 12.5, Color.CYAN);
			Circle circle2 = new Circle(x2 + 12.5, y2 + 12.5, 12.5, Color.CYAN);
			
			root.getChildren().addAll(circle1, circle2);
			
			x1 += 25;
			y1 += 25;
			
			x2 -= 25;
			y2 += 25;
		}
		
		primaryStage.setTitle("Paintball");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}

/*
// Original
import java.awt.*;
import java.applet.Applet;

public class JS4_Paintball extends Applet
{
    public void paint(Graphics g) {
        int x1 = 0;
        int y1 = 0;

        int x2 = 250;
        int y2 = 0;

        for (int i = 0; i <= 10; i++)
        {
            g.setColor(Color.cyan);
            g.fillOval(x1, y1, 25, 25);
            g.fillOval(x2, y2, 25, 25);

            x1 += 25;
            y1 += 25;

            x2 -= 25;
            y2 += 25;
        }
    }
}
 */
