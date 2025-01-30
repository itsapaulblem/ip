package Alden;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private static final String FILE_PATH = "./data/Alden.txt";

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserLogo.jpg"));
    private Image aldenImage = new Image(this.getClass().getResourceAsStream("/images/DukeLogo.png"));
    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private TextArea outputArea;
    private TextField inputField;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private Button sendButton;
    private Scene scene;

    @Override
    public void start(Stage stage) {
        // Initialize components
        tasks = new TaskList();
        storage = new Storage(FILE_PATH);
        ui = new Ui();

        // Create GUI components - Consistent with tutorial structure
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        inputField = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, inputField, sendButton);

        scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.setTitle("Alden Task Manager");
        stage.show();


        // Set up UI and load tasks - After stage.show()
        ui.setGuiMode(dialogContainer); //Changed to dialogContainer
        storage.load(tasks);
        ui.showWelcome();

        // Handle input - After stage.show()
        sendButton.setOnAction(e -> handleInput());
        inputField.setOnAction(e -> handleInput());



        //Formatting the window to look as expected - After stage.show()
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        inputField.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(inputField, 1.0);
        AnchorPane.setBottomAnchor(inputField, 1.0);

    }

    private void handleInput() {
        String input = inputField.getText().trim();
        if (!input.isEmpty()) {
            try {
                if (input.equalsIgnoreCase("bye")) {
                    ui.showGoodbye();
                    // Add slight delay before exit
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    javafx.application.Platform.runLater(() ->
                                            javafx.application.Platform.exit()
                                    );
                                }
                            },
                            1500
                    );
                } else {
                    Command command = Parser.parse(input);
                    command.execute(tasks, ui, storage);
                }
            } catch (AldenException e) {
                ui.showError(e.getMessage());
            }
            inputField.clear();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
