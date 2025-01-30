package Alden;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a dialog box in the chat interface, containing text and an image.
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Constructs a new DialogBox with the given text and image, for Alden.
     *
     * @param s The text content of the dialog box.
     * @param i The image to display in the dialog box.
     */
    public DialogBox(String s, Image i) {
        this(s, i, false);
    }

    /**
     * Constructs a new DialogBox with the given text, image, and user indicator.
     *
     * @param s      The text content of the dialog box.
     * @param i      The image to display in the dialog box.
     * @param isUser True if the dialog box is for the user, false for Alden.
     */
    public DialogBox(String s, Image i, boolean isUser) {
        text = new Label(s);
        text.setWrapText(true); // Allows text to wrap within the dialog box
        displayPicture = new ImageView(i);
        displayPicture.setFitWidth(50.0);
        displayPicture.setFitHeight(50.0);
        displayPicture.setPreserveRatio(true);

        this.setPadding(new Insets(5));
        this.setSpacing(10);

        if (isUser) {
            this.setAlignment(Pos.TOP_RIGHT);
            this.getChildren().addAll(text, displayPicture);
        } else {
            this.setAlignment(Pos.TOP_LEFT);
            this.getChildren().addAll(displayPicture, text);
        }
    }
}