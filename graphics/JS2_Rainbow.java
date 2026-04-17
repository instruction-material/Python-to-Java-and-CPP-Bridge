import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;


public class JS2_Rainbow extends Application
{
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		Pane root = new Pane();
		Scene scene = new Scene(root, 400, 400);
		
		// Red arc
		Arc redArc = createColoredArc(50, 50, 300, 300, Color.RED);
		
		// Orange arc
		Arc orangeArc = createColoredArc(75, 75, 250, 250, Color.ORANGE);
		
		// Yellow arc
		Arc yellowArc = createColoredArc(100, 100, 200, 200, Color.YELLOW);
		
		// Green arc
		Arc greenArc = createColoredArc(125, 125, 150, 150, Color.GREEN);
		
		// Blue arc
		Arc blueArc = createColoredArc(150, 150, 100, 100, Color.BLUE);
		
		// Magenta arc
		Arc magentaArc = createColoredArc(175, 175, 50, 50, Color.MAGENTA);
		
		root.getChildren().addAll(redArc, orangeArc, yellowArc, greenArc, blueArc, magentaArc);
		
		primaryStage.setTitle("Rainbow");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private Arc createColoredArc(int x, int y, int width, int height, Color color) {
		Arc arc = new Arc(x + width / 2.0, y + height / 2.0, width / 2.0, height / 2.0, 0, 180);
		arc.setType(ArcType.OPEN);
		arc.setFill(color);
		return arc;
	}
}

/*
// Original
import java.awt.*;
import java.applet.Applet;

public class JS2_Rainbow extends Applet
{
	public void paint(Graphics g) {

		g.setColor(Color.red);
		g.fillArc(50, 50, 300, 300, 0, 180);

		g.setColor(Color.orange);
		g.fillArc(75, 75, 250, 250, 0, 180);

		g.setColor(Color.yellow);
		g.fillArc(100, 100, 200, 200, 0, 180);

		g.setColor(Color.green);
		g.fillArc(125, 125, 150, 150, 0, 180);

		g.setColor(Color.blue);
		g.fillArc(150, 150, 100, 100, 0, 180);

		g.setColor(Color.magenta);
		g.fillArc(175, 175, 50, 50, 0, 180);
	}
}
 */
