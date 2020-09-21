package controllers;

import account_credentials.Accounts;
import account_credentials.TableDataManipulation;
import account_credentials.TableDataModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    @FXML
    private AnchorPane recordPane;

    @FXML
    private AnchorPane sidePane;

    @FXML
    private Label accountLabel;

    @FXML
    private Label accEmailLabel;

    @FXML
    private Label recordsLabel;

    @FXML
    private Button logoutButton;

    @FXML
    private TextField searchText;

    @FXML
    private TextField webNameText;

    @FXML
    private TextField webAddressText;

    @FXML
    private TextField webUserMailText;

    @FXML
    private TextField webUserPassText;

    @FXML
    private Button searchButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button saveButton;

    @FXML
    public Button clearButton;

    @FXML
    private PasswordField accPasswordField;

    @FXML
    private Button verifyButton;

    @FXML
    private TableView<TableDataModel> dataTable;

    @FXML
    private TableColumn<TableDataModel, String> webNameCol;

    @FXML
    private TableColumn<TableDataModel, String> webAddCol;

    @FXML
    private TableColumn<TableDataModel, String> webUserCol;

    @FXML
    private TableColumn<TableDataModel, String> webPassCol;

    private static ObservableList<TableDataModel> observableList = FXCollections.observableArrayList();

    private static String loginPassword="";


    private void populateTable() {
        //web_name, web_address, web_user_email, web_password;
        webNameCol.setCellValueFactory(new PropertyValueFactory<>("web_name"));
        webAddCol.setCellValueFactory(new PropertyValueFactory<>("web_address"));
        webUserCol.setCellValueFactory(new PropertyValueFactory<>("web_user_email"));
        webPassCol.setCellValueFactory(new PropertyValueFactory<>("web_password"));
        TableDataManipulation tableDataManipulation = new TableDataManipulation(accountLabel.getText());
        ArrayList<TableDataModel> arrayList = tableDataManipulation.fetchTableView();

        observableList.addAll(arrayList);

        dataTable.setItems(observableList);
    }

    public void setUserInfo(Accounts account) {
        accountLabel.setText(account.getUsername());
        accEmailLabel.setText(account.getEmail_address());
        loginPassword = account.getPassword();
        populateTable();
    }

    void setVerifyPassVisibility(boolean status) {
        accPasswordField.setVisible(status);
        verifyButton.setVisible(status);
    }

    void tableRowSelection() {

        dataTable.setOnMousePressed(event -> {
            try {
                webNameText.setText(dataTable.getSelectionModel().getSelectedItem().getWeb_name());
                webAddressText.setText(dataTable.getSelectionModel().getSelectedItem().getWeb_address());
                webUserMailText.setText(dataTable.getSelectionModel().getSelectedItem().getWeb_user_email());
                webUserPassText.setText(dataTable.getSelectionModel().getSelectedItem().getWeb_password());
            } catch (Exception e) {
                //e.printStackTrace();
                //System.out.println("No data available");
            }
        });
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setVerifyPassVisibility(false);
        tableRowSelection();
    }

    @FXML
    public void saveAction(ActionEvent actionEvent) {

        setVerifyPassVisibility(true);

        TableDataManipulation tableDataManipulation = new TableDataManipulation(accountLabel.getText());

        //String account, String web_name, String web_add, String mail, String pass
        tableDataManipulation.updateTable(
                webNameText.getText(),
                webAddressText.getText(),
                webUserMailText.getText(),
                webUserPassText.getText()
        );
        System.out.println(tableDataManipulation.getUserId());
    }

    public void updateAction(ActionEvent actionEvent) {
        setVerifyPassVisibility(true);

        TableDataManipulation tableDataManipulation = new TableDataManipulation(accountLabel.getText());

        //String account, String web_name, String web_add, String mail, String pass
        tableDataManipulation.updateTable(
                webNameText.getText(),
                webAddressText.getText(),
                webUserMailText.getText(),
                webUserPassText.getText()
        );
    }

    public void deleteAction(ActionEvent actionEvent) {
        setVerifyPassVisibility(true);

        TableDataManipulation tableDataManipulation = new TableDataManipulation(accountLabel.getText());

        //String account, String web_name, String web_add, String mail, String pass
        tableDataManipulation.updateTable(
                webNameText.getText(),
                webAddressText.getText(),
                webUserMailText.getText(),
                webUserPassText.getText()
        );
    }

    public void verifyAction(ActionEvent actionEvent) {
    }

    public void searchAction(ActionEvent actionEvent) {

    }

    public void clearAction(ActionEvent actionEvent) {
        webNameText.clear();
        webAddressText.clear();
        webUserMailText.clear();
        webUserPassText.clear();
    }
}
