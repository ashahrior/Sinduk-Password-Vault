package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import account_credentials.Accounts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import account_credentials.AccountLogin;

public class LoginController implements Initializable {

    @FXML
    private AnchorPane login;

    @FXML
    private Button signup;

    @FXML
    private Button signin;

    @FXML
    private TextField name;

    @FXML
    private PasswordField pass;

    @FXML
    private ImageView logo;


    private String getName() {
        return name.getText();
    }


    private String getPass() { return pass.getText(); }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //primaryStage.setTitle("Sinduk");

    }

    @FXML
    public void signInAction(javafx.event.ActionEvent actionEvent) {
        String name = getName();
        String password = getPass();
        Accounts accounts = AccountLogin.attemptLogin(name, password);
        if(accounts!=null) {
            System.out.println("Login successful.");
            login.getScene().getWindow().hide();
            Parent root = null;
            FXMLLoader loader=null;
            try {
                loader = new FXMLLoader(getClass().getResource("/FXML/HomePage.fxml"));
                root = (Parent) loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            HomePageController homePageController = loader.getController();
            homePageController.setUserInfo(accounts);
            Scene scene = new Scene(root);
            Stage homePage = new Stage();
            homePage.setTitle("Sinduk - Records");
            homePage.setScene(scene);
            homePage.setResizable(false);
            homePage.show();
        }
        else System.out.println("Login failed.");
    }


    @FXML
    public void signUpAction(javafx.event.ActionEvent actionEvent) throws IOException {
        login.getScene().getWindow().hide();

        Stage signup = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/SignUP.fxml"));
        Scene scene = new Scene(root);
        //signup.initStyle(StageStyle.UNDECORATED);
        signup.setTitle("Sinduk - SignUP");
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);
    }
}
