package Alden;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    public DialogBox(String s, Image i) {
        this(s, i, false);
    }

    public DialogBox(String s, Image i, boolean isUser) {
        text = new Label(s);
        text.setWrapText(true);
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
