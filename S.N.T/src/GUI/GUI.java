package GUI;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

public class GUI extends Application {
	Stage window;
	Scene scene, scene2, pingScene, nsScene, scrapScene, portScene;
	BorderPane mainLayout, pingLayout, nsLayout, scrapLayout, portLayout;
	public static TextField pingOut = new TextField();
	public static TextField nsOut = new TextField();
	public static TextField scrapOut = new TextField();
	public static TextField portOut = new TextField();
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		window = primaryStage;
		window.setTitle("Sam's Networking Tool Version 1.1");

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(10);
		grid.setHgap(10);

		Label nameLabel = new Label("Username:");
		GridPane.setConstraints(nameLabel, 1, 1);

		// name input
		TextField nameInput = new TextField();
		nameInput.setPromptText("username");
		GridPane.setConstraints(nameInput, 2, 1);

		// passw label
		Label passLabel = new Label("Password:");
		GridPane.setConstraints(passLabel, 1, 2);

		// name input
		TextField passInput = new TextField();
		passInput.setPromptText("password");
		GridPane.setConstraints(passInput, 2, 2);

		Button loginButton = new Button("Login");
		GridPane.setConstraints(loginButton, 2, 3);
		// loginButton.setOnAction( e -> window.setScene(scene2));
		loginButton.setOnAction(e -> validate(nameInput, nameInput.getText(), passInput, passInput.getText()));

		grid.getChildren().addAll(nameLabel, nameInput, passLabel, passInput, loginButton);

		scene = new Scene(grid, 400, 200);
		scene.getStylesheets().add("Viper.css");
		window.setScene(scene);
		window.show();

		// file menu
		Menu fileMenu = new Menu("File");
		// menu items

		MenuItem newNote = new MenuItem("New Note...");
		fileMenu.getItems().add(newNote);
		newNote.setOnAction(e -> {
			try {
				notes();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		fileMenu.getItems().add(new SeparatorMenuItem());
		MenuItem config = new MenuItem("Import...");
		config.setDisable(true);
		fileMenu.getItems().add(config);
		fileMenu.getItems().add(new SeparatorMenuItem());

		MenuItem quit = new MenuItem("Quit...");
		quit.setOnAction(e -> System.exit(0));
		fileMenu.getItems().add(quit);

		// help menu
		Menu helpMenu = new Menu("Help");
		// menu items
		MenuItem helpPlz = new MenuItem("About Sam's Networking Tool");
		helpPlz.setOnAction(e -> AlertBox.display("About",
				" This program was made to assist me in learning JavaFx\n and networking in Java "));
		helpMenu.getItems().add(helpPlz);

		MenuBar menubar = new MenuBar();
		menubar.getMenus().addAll(fileMenu, helpMenu);
		MenuBar pingmenu = new MenuBar();
		pingmenu.getMenus().addAll(fileMenu, helpMenu);
		MenuBar nsmenu = new MenuBar();
		nsmenu.getMenus().addAll(fileMenu, helpMenu);
		MenuBar scrapmenu = new MenuBar();
		scrapmenu.getMenus().addAll(fileMenu, helpMenu);
		MenuBar portmenu = new MenuBar();
		portmenu.getMenus().addAll(fileMenu, helpMenu);

		// main menu layout
		mainLayout = new BorderPane();
		mainLayout.setLeft(addVBox());
		mainLayout.setTop(menubar);
		// mainLayout.getChildren().add(ping);

		scene2 = new Scene(mainLayout, 400, 200);
		scene2.getStylesheets().add("Viper.css");

		// ping layout
		
		pingLayout = new BorderPane();
		pingLayout.setBottom(addBackButtonVbox());
		pingLayout.setTop(pingmenu);
		pingLayout.setCenter(addAll(pingOut, "IP/Hostname to Ping", "Output of Ping", "Execute Ping", "output", "IP/hostname", 1));
		pingScene = new Scene(pingLayout, 500, 300);
		pingScene.getStylesheets().add("Viper.css");

		// ns layout
		nsLayout = new BorderPane();
		nsLayout.setBottom(addBackButtonVbox());
		nsLayout.setTop(nsmenu);
		nsLayout.setCenter(addAll(nsOut, "IP/Hostname to NSLookup", "Output of NSLookup", "Execute NSLookup", "output", "IP/Hostname", 2));
		nsScene = new Scene(nsLayout, 600, 600);
		nsScene.getStylesheets().add("Viper.css");

		// scrapper layout
		scrapLayout = new BorderPane();
		scrapLayout.setBottom(addBackButtonVbox());
		scrapLayout.setTop(scrapmenu);
		scrapLayout.setCenter(addAll(scrapOut, "URL to Web Scrap", "Output of Scrap", "Execute Web Scrap", "output", "URL", 3));
		scrapScene = new Scene(scrapLayout, 600, 600);
		scrapScene.getStylesheets().add("Viper.css");

		// port scanner layout
		portLayout = new BorderPane();
		portLayout.setBottom(addBackButtonVbox());
		portLayout.setTop(portmenu);
		portLayout.setCenter(addAll(portOut, "Host to Port Scan", "Output of Port Scan", "Execute Port Scanning", "output", "Host to Port Scan", 4));
		portScene = new Scene(portLayout, 600, 600);
		portScene.getStylesheets().add("Viper.css");
	}

	private void notes() throws IOException {
		Process p = Runtime.getRuntime().exec("notepad.exe");

	}


	private void validate(TextField nameInput, String message, TextField passInput, String password) {
		if (message.equals("admin") && password.equals("password")) {
			window.setScene(scene2);
		} else if (message.equals("sam") && password.equals("password")) {
			window.setScene(scene2);
		} else {
			AlertBox.display("Error", "Wrong username and/or password.");
		}
	}

	private Node addBackButtonVbox() {
		HBox vbox = new HBox();
		vbox.setPadding(new Insets(15, 12, 15, 12));
		vbox.setSpacing(10);

		Button pingBack = new Button("Back");

		pingBack.setOnAction(e -> window.setScene(scene2));
		vbox.setAlignment(Pos.BASELINE_CENTER);
		vbox.getChildren().add(pingBack);

		return vbox;
	}

	public VBox addVBox() {
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(10));
		vbox.setSpacing(8);

		Button test = new Button("Ping");
		test.setOnAction(e -> window.setScene(pingScene));
		Button pls = new Button("NS Lookup");
		pls.setOnAction(e -> window.setScene(nsScene));
		Button scrap = new Button("Website Scrapper");
		scrap.setOnAction(e -> window.setScene(scrapScene));
		Button scan = new Button("Port Scanner");
		scan.setOnAction(e -> window.setScene(portScene));

		vbox.getChildren().addAll(test, pls, scrap, scan);

		return vbox;
	}
	
	private Node addAll(TextField outInput, String inLab, String outLab, String exec, String outPrompt, String inPrompt, int choice) {
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(10);
		grid.setHgap(10);

		Label inLabel = new Label(inLab);
		GridPane.setConstraints(inLabel, 0, 0);

		Label outLabel = new Label(outLab);
		GridPane.setConstraints(outLabel, 0, 9);

		Button execute = new Button(exec);
		GridPane.setConstraints(execute, 0, 2);

		// name input
		outInput.setPromptText(outPrompt);
		GridPane.setConstraints(outInput, 0, 10);
		outInput.setPrefWidth(500);
		outInput.setPrefHeight(50);
		outInput.setAlignment(Pos.CENTER);
		outInput.setEditable(false);

		TextField input = new TextField();
		input.setPromptText(inPrompt);
		GridPane.setConstraints(input, 0, 1);
		input.setMaxWidth(500);

		switch(choice) {
		case 1: execute.setOnAction(e -> Backend.ping(input.getText()));
		break;
		case 2: execute.setOnAction(e -> Backend.NSLookup(input.getText()));
		break;
		case 3: execute.setOnAction(e -> {
			try {
				Backend.pyWebScrapper();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		case 4: execute.setOnAction(e -> Backend.portScanner(input.getText()));
		}

		grid.getChildren().addAll(execute, inLabel, input, outLabel, outInput);

		return grid;
	}
}