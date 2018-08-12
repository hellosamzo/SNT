package GUI;

import javafx.application.Application;
import javafx.stage.Stage;

public class Notes extends Application{
	
	Stage window;

	@Override
	public void start(Stage PrimaryStage) throws Exception {
		window = PrimaryStage;
        window.setTitle("Notes");
	}

}
