package alden;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The main class for the Alden task management application.
 * This class handles the initialization of the GUI and the main application loop.
 */
public class Main extends Application {
    private static final String FILE_PATH = "./data/Alden.txt";

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserLogo.jpg"));
    private final Image aldenImage = new Image(this.getClass().getResourceAsStream("/images/DukeLogo.png"));
    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField inputField;

    /**
     * Starts the Alden application by initializing the GUI and loading tasks.
     *
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        tasks = new TaskList();
        storage = new Storage(FILE_PATH);
        ui = new Ui();

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        inputField = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, inputField, sendButton);

        Scene scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.setTitle("Alden Task Manager");
        stage.show();

        ui.setGuiMode(dialogContainer);
        storage.load(tasks);
        ui.showWelcome();

        sendButton.setOnAction(e -> handleInput());
        inputField.setOnAction(e -> handleInput());

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

    /**
     * Handles user input from the text field.
     */
    private void handleInput() {
        String input = inputField.getText().trim();
        if (!input.isEmpty()) {
            try {
                addToDialogContainer(new DialogBox(input, userImage, true)); // User message
                if (input.equalsIgnoreCase("bye")) {
                    ui.showGoodbye();
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

    /**
     * Adds a dialog box to the dialog container and scrolls to the bottom.
     *
     * @param dialogBox The dialog box to add.
     */
    private void addToDialogContainer(DialogBox dialogBox) {
        dialogContainer.getChildren().add(dialogBox);
        scrollPane.setVvalue(1.0);
    }

    /**
     * Launches the Alden application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        launch(args);
    }
}
