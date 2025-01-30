package Alden;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {
    private static final String FILE_PATH = "./data/Alden.txt";
    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private TextArea outputArea;
    private TextField inputField;

    @Override
    public void start(Stage stage) {
        // Initialize components
        tasks = new TaskList();
        storage = new Storage(FILE_PATH);
        ui = new Ui();

        // Create GUI components
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setPrefRowCount(20);
        outputArea.setWrapText(true);

        HBox inputBox = new HBox(10);
        inputField = new TextField();
        Button sendButton = new Button("Send");
        inputBox.getChildren().addAll(inputField, sendButton);

        root.getChildren().addAll(outputArea, inputBox);

        // Set up UI and load tasks
        ui.setGuiMode(outputArea);
        storage.load(tasks);
        ui.showWelcome();

        // Handle input
        sendButton.setOnAction(e -> handleInput());
        inputField.setOnAction(e -> handleInput());

        // Set up the scene
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Alden Task Manager");
        stage.setScene(scene);
        stage.show();
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