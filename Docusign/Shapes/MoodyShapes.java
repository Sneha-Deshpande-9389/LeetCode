package Docusign.Shapes;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MoodyShapes extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        
        // Create a circle
        Circle circle = new Circle(100, 100, 50);
        circle.setFill(Color.GREEN);
        circle.setOnMouseEntered(event -> circle.setFill(Color.YELLOW));
        circle.setOnMouseExited(event -> circle.setFill(Color.GREEN));

        // Create a rectangle
        Rectangle rectangle = new Rectangle(200, 100, 100, 60);
        rectangle.setFill(Color.BLUE);
        rectangle.setOnMouseEntered(event -> rectangle.setFill(Color.ORANGE));
        rectangle.setOnMouseExited(event -> rectangle.setFill(Color.BLUE));

        // Add shapes to the pane
        pane.getChildren().addAll(circle, rectangle);

        // Set up the scene and stage
        Scene scene = new Scene(pane, 400, 300);
        primaryStage.setTitle("Moody Shapes Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
