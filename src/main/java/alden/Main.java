package alden;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
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
 * Main GUI application class for the Alden task management system.
 * Handles the graphical user interface and user interactions for task management.
 * Provides functionality for displaying tasks, processing commands, and managing
 * the application lifecycle.
 */
public class Main extends Application {
    private static final String FILE_PATH = "./data/Alden.txt";
    private static final double WINDOW_WIDTH = 400.0;
    private static final double WINDOW_HEIGHT = 600.0;
    private static final double SCROLL_PANE_WIDTH = 385;
    private static final double SCROLL_PANE_HEIGHT = 535;
    private static final double INPUT_FIELD_WIDTH = 325.0;
    private static final double SEND_BUTTON_WIDTH = 55.0;
    private static final double LAYOUT_PADDING = 1.0;
    private static final int EXIT_DELAY_MS = 1500;

    private final Image userImage;
    private final Image aldenImage;
    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField inputField;

    /**
     * Constructs a new Main application instance.
     * Initializes the user and Alden avatar images.
     */
    public Main() {
        this.userImage = new Image(this.getClass().getResourceAsStream("/images/UserLogo.jpg"));
        this.aldenImage = new Image(this.getClass().getResourceAsStream("/images/DukeLogo.png"));
    }

    /**
     * Initializes and starts the Alden GUI application.
     * Sets up the main window, controls, and event handlers.
     *
     * @param stage The primary stage for the application window
     */
    @Override
    public void start(Stage stage) {
        initializeComponents();
        setupMainLayout(stage);
        setupEventHandlers();
        loadTasksAndShowWelcome();
    }

    /**
     * Initializes the core application components including tasks,
     * storage, UI elements, and containers.
     */
    private void initializeComponents() {
        tasks = new TaskList();
        storage = new Storage(FILE_PATH);
        ui = new Ui();
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        inputField = new TextField();
    }

    /**
     * Sets up the main application window and layout.
     * Configures the stage, scene, and UI component properties.
     *
     * @param stage The primary stage for the application window
     */
    private void setupMainLayout(Stage stage) {
        Button sendButton = createSendButton();
        AnchorPane mainLayout = createMainLayout(sendButton);
        configureStage(stage, mainLayout);
        configureLayoutComponents(mainLayout, sendButton);
    }

    /**
     * Creates and configures the send button.
     *
     * @return The configured send button
     */
    private Button createSendButton() {
        Button sendButton = new Button("Send");
        sendButton.setPrefWidth(SEND_BUTTON_WIDTH);
        return sendButton;
    }

    /**
     * Creates the main layout container and adds UI components.
     *
     * @param sendButton The send button to add to the layout
     * @return The configured main layout
     */
    private AnchorPane createMainLayout(Button sendButton) {
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, inputField, sendButton);
        mainLayout.setPrefSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        return mainLayout;
    }

    /**
     * Configures the primary stage with the application scene.
     *
     * @param stage The primary stage to configure
     * @param mainLayout The main layout to set as the scene's root
     */
    private void configureStage(Stage stage, AnchorPane mainLayout) {
        Scene scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.setTitle("Alden Task Manager");
        stage.setResizable(false);
        stage.setMinHeight(WINDOW_HEIGHT);
        stage.setMinWidth(WINDOW_WIDTH);
        stage.show();
    }

    /**
     * Configures the layout components including scroll pane, dialog container,
     * and input field with their respective properties.
     *
     * @param mainLayout The main layout containing the components
     * @param sendButton The send button to configure within the layout
     */
    private void configureLayoutComponents(AnchorPane mainLayout, Button sendButton) {
        configureScrollPane();
        configureDialogContainer();
        configureInputField();
        setLayoutAnchors(sendButton);
    }

    /**
     * Configures the scroll pane properties.
     */
    private void configureScrollPane() {
        scrollPane.setPrefSize(SCROLL_PANE_WIDTH, SCROLL_PANE_HEIGHT);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
    }

    /**
     * Configures the dialog container properties.
     */
    private void configureDialogContainer() {
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
    }

    /**
     * Configures the input field properties.
     */
    private void configureInputField() {
        inputField.setPrefWidth(INPUT_FIELD_WIDTH);
    }

    /**
     * Sets the anchor points for layout components.
     *
     * @param sendButton The send button to position in the layout
     */
    private void setLayoutAnchors(Button sendButton) {
        AnchorPane.setTopAnchor(scrollPane, LAYOUT_PADDING);
        AnchorPane.setBottomAnchor(sendButton, LAYOUT_PADDING);
        AnchorPane.setRightAnchor(sendButton, LAYOUT_PADDING);
        AnchorPane.setLeftAnchor(inputField, LAYOUT_PADDING);
        AnchorPane.setBottomAnchor(inputField, LAYOUT_PADDING);
    }

    /**
     * Sets up event handlers for the send button and input field.
     */
    private void setupEventHandlers() {
        Button sendButton = (Button) scrollPane.getParent().getChildrenUnmodifiable().stream()
                .filter(node -> node instanceof Button)
                .findFirst()
                .orElseThrow();
        sendButton.setOnAction(e -> handleInput());
        inputField.setOnAction(e -> handleInput());
    }

    /**
     * Loads existing tasks and displays the welcome message.
     */
    private void loadTasksAndShowWelcome() {
        ui.setGuiMode(dialogContainer);
        storage.load(tasks);
        ui.showWelcome();
    }

    /**
     * Handles user input from the text field.
     * Processes commands and updates the UI accordingly.
     */
    private void handleInput() {
        String input = inputField.getText().trim();
        if (!input.isEmpty()) {
            processUserInput(input);
            inputField.clear();
        }
    }

    /**
     * Processes the user input and executes appropriate commands.
     *
     * @param input The user input to process
     */
    private void processUserInput(String input) {
        try {
            addToDialogContainer(new DialogBox(input, userImage, true));
            if (input.equalsIgnoreCase("bye")) {
                handleExitCommand();
            } else {
                executeCommand(input);
            }
        } catch (AldenException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Executes the command parsed from user input.
     *
     * @param input The user input to parse and execute
     * @throws AldenException If there is an error executing the command
     */
    private void executeCommand(String input) throws AldenException {
        Command command = Parser.parse(input);
        command.execute(tasks, ui, storage);
    }

    /**
     * Handles the exit command by showing goodbye message and scheduling application exit.
     */
    private void handleExitCommand() {
        ui.showGoodbye();
        scheduleApplicationExit();
    }

    /**
     * Schedules the application to exit after a brief delay.
     */
    private void scheduleApplicationExit() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(Platform::exit);
            }
        }, EXIT_DELAY_MS);
    }

    /**
     * Adds a dialog box to the dialog container and scrolls to the bottom.
     *
     * @param dialogBox The dialog box to add
     */
    private void addToDialogContainer(DialogBox dialogBox) {
        dialogContainer.getChildren().add(dialogBox);
        scrollPane.setVvalue(1.0);
    }

    /**
     * Main method to launch the Alden application.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        launch(args);
    }
}
