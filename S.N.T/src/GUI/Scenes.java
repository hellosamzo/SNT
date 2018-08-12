package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 
public class Scenes extends Application
{
	
	Button pingButton;
	Stage window;
	Scene scene1, scene2;
	
    public static void main(String[] args) 
    {
        launch(args);
    }
 
    @Override
    public void start(Stage primaryStage) throws Exception 
    {
    	window = primaryStage;
        window.setTitle("Sam's Networking Tool");
        
        Label label1 = new Label("1st scene ting");
        Button button1 = new Button("go to scene2");
        button1.setOnAction(e -> window.setScene(scene2));
        
        //layout 1 - children are laid out in vertical column
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, button1);
        scene1 = new Scene(layout1, 200, 200);
        
        //button 2
        Button button2 = new Button("go to scene1");
        button2.setOnAction(e -> window.setScene(scene1));
        
        //layout 2
        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);
        scene2 = new Scene(layout2, 200, 200);
        
        window.setScene(scene1);
        window.show();
    }
}