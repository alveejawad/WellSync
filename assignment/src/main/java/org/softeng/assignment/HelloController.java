package org.softeng.assignment;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.softeng.assignment.logic.UserAuthenticationHandler;
import org.softeng.assignment.logic.exceptions.InvalidCredentialsException;
import org.softeng.assignment.objects.UserCredentials;

import java.io.IOException;

public class HelloController {
    @FXML

    public Button loginButton;
    @FXML
    public Label errorLabel;
    private UserAuthenticationHandler loginHandler;
    @FXML
    private Label welcomeText;


    @FXML
    private TextField userEmail;

    @FXML
    private TextField userPassword;

    public HelloController() {
        loginHandler = new UserAuthenticationHandler();
    }


    @FXML
    private void handleLoginButtonAction() {
        String email = userEmail.getText();
        String password = userPassword.getText();

        try {
            UserCredentials.Role loginRole = loginHandler.login(new UserCredentials(email, password, "UNSPECIFIED"));

            errorLabel.setVisible(false);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("assignment-done.fxml"));
            Parent root = loader.load();

            DoneController controller = loader.getController();
            // Pass the string parameter and print it
            controller.setLabelText("Assignment done! The role of the logged in user is: " + loginRole.name());

            // Get the current stage
            Stage stage = (Stage) loginButton.getScene().getWindow();

            // Set the new scene onto the stage
            Scene scene = new Scene(root, 500, 500);
            stage.setTitle("Done!");
            stage.setScene(scene);
        } catch (InvalidCredentialsException e) {
            errorLabel.setVisible(true);
            //Reset login fields
            resetLoginFields();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void resetLoginFields() {
        userEmail.clear();
        userPassword.clear();
    }

}