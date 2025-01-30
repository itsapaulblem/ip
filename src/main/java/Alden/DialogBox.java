package Alden;

import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    public DialogBox(String s, Image i) {
        text = new Label(s);
        displayPicture = new ImageView(i);

        //Styling the dialog box
        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        this.setAlignment(Pos.TOP_LEFT); // Align to the left for user messages

        this.getChildren().addAll(displayPicture, text); // Display image first, then text
        this.setSpacing(10); //Add spacing
        this.setPadding(new Insets(10)); // Add padding
    }


    public DialogBox(String s, Image i, boolean isUser) {
        text = new Label(s);
        displayPicture = new ImageView(i);

        //Styling the dialog box
        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        if (isUser) {
            this.setAlignment(Pos.TOP_RIGHT); // Align to the right for user messages
            this.getChildren().addAll(text, displayPicture); // Display text first, then image
        } else {
            this.setAlignment(Pos.TOP_LEFT); // Align to the left for Alden messages
            this.getChildren().addAll(displayPicture, text); // Display image first, then text
        }
        this.setSpacing(10); //Add spacing
        this.setPadding(new Insets(10)); // Add padding

    }
}