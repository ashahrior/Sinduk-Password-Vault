package controllers;

import account_credentials.Account;
import account_credentials.AccountTableDataManager;
import account_credentials.AccountTableDataModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

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
    private TextField dataIDText;

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
    private TableView<AccountTableDataModel> dataTable;

    @FXML
    private TableColumn<AccountTableDataModel, String> webNameCol;

    @FXML
    private TableColumn<AccountTableDataModel, String> webAddCol;

    @FXML
    private TableColumn<AccountTableDataModel, String> webUserCol;

    @FXML
    private TableColumn<AccountTableDataModel, String> webPassCol;

    @FXML
    public TableColumn<AccountTableDataModel,String> dataIDCol;


    private static String loginPassword="";

    private boolean passwordVerified = false;


    private void populateTable() {
        //web_name, web_address, web_user_email, web_password, id;
        webNameCol.setCellValueFactory(new PropertyValueFactory<>("web_name"));
        webAddCol.setCellValueFactory(new PropertyValueFactory<>("web_address"));
        webUserCol.setCellValueFactory(new PropertyValueFactory<>("web_user_email"));
        webPassCol.setCellValueFactory(new PropertyValueFactory<>("web_password"));
        dataIDCol.setCellValueFactory(new PropertyValueFactory<>("data_id_col"));
        AccountTableDataManager accountTableDataManager = new AccountTableDataManager(accountLabel.getText());
        ArrayList<AccountTableDataModel> arrayList = accountTableDataManager.fetchTableView();

        ObservableList<AccountTableDataModel> observableList = FXCollections.observableArrayList();
        observableList.addAll(arrayList);

        dataTable.setItems(observableList);
    }

    private void setRecordsLabel() {
        AccountTableDataManager accountTableDataManager = new AccountTableDataManager(accountLabel.getText());
        recordsLabel.setText(accountTableDataManager.getRecordCount());
    }

    private void clearAll() {
        webNameText.clear();
        webAddressText.clear();
        webUserMailText.clear();
        webUserPassText.clear();
        //setVerifyPassVisibility(false);
    }

    private boolean hasEmptyField() {

        if(webNameText.getText().length()==0)
            return false;
        if(webAddressText.getText().length()==0)
            return false;
        if(webUserMailText.getText().length()==0)
            return false;
        if(webUserPassText.getText().length()==0)
            return false;

        return true;
    }

    private boolean verifyPass() {
        /*TextInputDialog dialog = new TextInputDialog("Account password");
        //dialog.setTitle("Text Input Dialog");

        dialog.setContentText("Confirm with password:");

        Optional<String> result = dialog.showAndWait();
        if(result.isPresent()) {
            System.out.println(result.get());
            System.out.println(loginPassword);
            if(result.get().equals(loginPassword)) {
                System.out.println("passwords match");
                return true;
            }
        }
        System.out.println("password not match");
        return false;*/
        PasswordDialog pd = new PasswordDialog();
        Optional<String> result = pd.showAndWait();

        if(result.isPresent()) {
            if(pd.getPasswordField().getText().equals(loginPassword)) {
                System.out.println("passwords match");
                return true;
            }
        }
        System.out.println("passwords don't match");
        return false;
    }

    public void setUserInfo(Account account) {
        accountLabel.setText(account.getUsername());
        accEmailLabel.setText(account.getEmail_address());
        loginPassword = account.getPassword();
        populateTable();
        setRecordsLabel();
    }

    private void setVerifyPassVisibility(boolean status) {
        accPasswordField.setVisible(status);
        verifyButton.setVisible(status);
    }

    private void tableRowSelection() {

        dataTable.setOnMousePressed(event -> {
            try {
                webNameText.setText(dataTable.getSelectionModel().getSelectedItem().getWeb_name());
                webAddressText.setText(dataTable.getSelectionModel().getSelectedItem().getWeb_address());
                webUserMailText.setText(dataTable.getSelectionModel().getSelectedItem().getWeb_user_email());
                webUserPassText.setText(dataTable.getSelectionModel().getSelectedItem().getWeb_password());
                dataIDText.setText(dataTable.getSelectionModel().getSelectedItem().getData_id_col());
            } catch (Exception e) {
                //e.printStackTrace();
                //System.out.println("No data available");
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //setVerifyPassVisibility(false);
        tableRowSelection();
    }

    @FXML
    void saveAction(ActionEvent actionEvent) {

        if(!hasEmptyField()) {
            System.out.println("No field can be empty");
            return;
        }

        //setVerifyPassVisibility(true);

        if(verifyPass()) {
            AccountTableDataManager accountTableDataManager = new AccountTableDataManager(accountLabel.getText());

            //String account, String web_name, String web_add, String mail, String pass, String dataID
            boolean status = accountTableDataManager.saveToTable (
                    webNameText.getText(),
                    webAddressText.getText(),
                    webUserMailText.getText(),
                    webUserPassText.getText()
            );
            if(status) {
                System.out.println("Saved successfully");
                populateTable();
                clearAll();
            }
            else System.out.println("Saving failed");

        }
    }

    @FXML
    void updateAction(ActionEvent actionEvent) {
        //setVerifyPassVisibility(true);

        AccountTableDataManager accountTableDataManager = new AccountTableDataManager(accountLabel.getText());

        //String account, String web_name, String web_add, String mail, String pass
        accountTableDataManager.updateTable(
                webNameText.getText(),
                webAddressText.getText(),
                webUserMailText.getText(),
                webUserPassText.getText(),
                dataIDText.getText()
        );
    }

    @FXML
    void deleteAction(ActionEvent actionEvent) {
        //setVerifyPassVisibility(true);

        AccountTableDataManager accountTableDataManager = new AccountTableDataManager(accountLabel.getText());

        //String account, String web_name, String web_add, String mail, String pass
        accountTableDataManager.deleteFromTable(
                webNameText.getText(),
                webAddressText.getText(),
                webUserMailText.getText(),
                webUserPassText.getText(),
                dataIDText.getText()
        );
    }

    @FXML
    boolean verifyAction(ActionEvent actionEvent) {
        if(accPasswordField.getText().equals(loginPassword)) {
            passwordVerified = true;
            return passwordVerified;
        }
        accPasswordField.setEditable(false);
        accPasswordField.setText("Incorrect password!");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        accPasswordField.clear();
        accPasswordField.setEditable(true);
        return passwordVerified;
    }

    @FXML
    void searchAction(ActionEvent actionEvent) {

    }

    @FXML
    void clearAction(ActionEvent actionEvent) {
        clearAll();
    }

    @FXML
    void showPassAction(ActionEvent actionEvent) {
    }
}
