package controllers;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;


public class PasswordDialog extends Dialog<String> {
    private final PasswordField passwordField;

    public PasswordDialog() {
        setTitle("Password Verification");
        //setHeaderText("Enter your account password.");

        ButtonType passwordButtonType = new ButtonType("Verify", ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        getDialogPane().getButtonTypes().addAll(passwordButtonType, cancelButton);

        passwordField = new PasswordField();
        passwordField.setPromptText("Enter password");
        passwordField.setStyle("-fx-background-color: #1d2a59; -fx-text-inner-color: white;");

        HBox hBox = new HBox();
        hBox.getChildren().add(passwordField);
        hBox.setPadding(new Insets(20));
        hBox.setStyle("-fx-background-color: #0f1833; -fx-text-inner-color: white;");

        HBox.setHgrow(passwordField, Priority.ALWAYS);

        getDialogPane().setStyle("-fx-background-color: #0f1833; -fx-text-inner-color: white;");
        getDialogPane().setContent(hBox);


        Platform.runLater(passwordField::requestFocus);

        setResultConverter(dialogButton -> {
            if (dialogButton == passwordButtonType) {
                return passwordField.getText();
            }
            return null;
        });
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

}