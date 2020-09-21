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

    private void populateTable() {
        //web_name, web_address, web_user_email, web_password;
        webNameCol.setCellValueFactory(new PropertyValueFactory<>("web_name"));
        webAddCol.setCellValueFactory(new PropertyValueFactory<>("web_address"));
        webUserCol.setCellValueFactory(new PropertyValueFactory<>("web_user_email"));
        webPassCol.setCellValueFactory(new PropertyValueFactory<>("web_password"));
        System.out.println(accountLabel.getText());
        TableDataManipulation tableDataManipulation = new TableDataManipulation(accountLabel.getText());

        ArrayList<TableDataModel> arrayList = tableDataManipulation.fetchTableView();

        observableList.addAll(arrayList);

        dataTable.setItems(observableList);
    }

    public void setUserInfo(Accounts account) {
        accountLabel.setText(account.getUsername());
        accEmailLabel.setText(account.getEmail_address());
        populateTable();
        return;
    }

    void setVerifPassVisibility(boolean status) {
        accPasswordField.setVisible(status);
        verifyButton.setVisible(status);
        return;
    }

    void tableRowSelection() {

        dataTable.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    System.out.println(
                            "website title: "+dataTable.getSelectionModel().getSelectedItem().getWeb_name()
                    );
                    System.out.println(
                            "website address: "+dataTable.getSelectionModel().getSelectedItem().getWeb_address()
                    );
                    System.out.println(
                            "website user: "+dataTable.getSelectionModel().getSelectedItem().getWeb_user_email()
                    );
                    System.out.println(
                            "website password: "+dataTable.getSelectionModel().getSelectedItem().getWeb_password()
                    );
                    webNameText.setText(dataTable.getSelectionModel().getSelectedItem().getWeb_name());
                    webAddressText.setText(dataTable.getSelectionModel().getSelectedItem().getWeb_address());
                    webUserMailText.setText(dataTable.getSelectionModel().getSelectedItem().getWeb_user_email());
                    webUserPassText.setText(dataTable.getSelectionModel().getSelectedItem().getWeb_password());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setVerifPassVisibility(false);
        tableRowSelection();
    }

    @FXML
    public void saveAction(ActionEvent actionEvent) {

        /*PasswordDialog pd = new PasswordDialog();
        Optional<String> result = pd.showAndWait();
        result.ifPresent(password -> System.out.println(password));
        */
        accPasswordField.setVisible(true);
        verifyButton.setVisible(true);

    }

    public void updateAction(ActionEvent actionEvent) {
    }

    public void deleteAction(ActionEvent actionEvent) {
    }

    public void verifyAction(ActionEvent actionEvent) {
    }

    public void searchAction(ActionEvent actionEvent) {
    }
}
