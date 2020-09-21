package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.*;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;

import account_credentials.AccountCreation;
import javafx.stage.StageStyle;

public class SignUPController implements Initializable {

    @FXML
    private AnchorPane signup;

    @FXML
    private TextField name;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password, confirm_pass;

    @FXML
    private Button register;

    @FXML
    private Button back;

    private String getUserName() {
        return name.getText();
    }

    private String getEmail() {
        return email.getText();
    }

    private String getPassword() {
        return password.getText();
    }

    private String getPassword2() {
        return confirm_pass.getText();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void backToLogin(ActionEvent actionEvent) throws IOException {
        signup.getScene().getWindow().hide();

        Stage signin = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/LoginMain.fxml"));
        Scene scene = new Scene(root);
        //signin.initStyle(StageStyle.UNDECORATED);
        signin.setTitle("Sinduk");
        signin.setScene(scene);
        signin.show();
        signin.setResizable(false);
    }

    @FXML
    public void completeRegistration(ActionEvent actionEvent) throws  IOException {
        String username = getUserName();
        String email_address = getEmail();
        String password_1 = getPassword();
        String password_2 = getPassword2();

        boolean registrationStatus = AccountCreation.createAccount(username, email_address, password_1, password_2);

        if(registrationStatus) System.out.println("Account created.");
        else System.out.println("Account creation failed");

    }
}
