package org.softeng.assignment;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DoneController {
    @FXML
    private Label assignmentDoneLabel;

    public void setLabelText(String text) {
        assignmentDoneLabel.setText(text);
    }
}
