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
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
    private Label searchCountLabel;

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
    private Button deleteButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button showPassButton;

    @FXML
    private PasswordField webUserPassField;

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


    private void populateTable(boolean status) {
        //web_name, web_address, web_user_email, web_password, id;
        webNameCol.setCellValueFactory(new PropertyValueFactory<>("web_name"));
        webAddCol.setCellValueFactory(new PropertyValueFactory<>("web_address"));
        webUserCol.setCellValueFactory(new PropertyValueFactory<>("web_user_email"));
        webPassCol.setCellValueFactory(new PropertyValueFactory<>("web_password"));
        dataIDCol.setCellValueFactory(new PropertyValueFactory<>("data_id_col"));

        AccountTableDataManager accountTableDataManager = new AccountTableDataManager(accountLabel.getText());

        ArrayList<AccountTableDataModel> arrayList;
        if(status) arrayList = accountTableDataManager.fetchTableView();
        else {
            arrayList = accountTableDataManager.searchTable(searchText.getText());
            searchCountLabel.setText("Search results found: "+Integer.toString(arrayList.size()));
            searchCountLabel.setVisible(true);
        }

        ObservableList<AccountTableDataModel> observableList = FXCollections.observableArrayList();
        observableList.addAll(arrayList);

        dataTable.setItems(observableList);
        setRecordsLabel();
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
        webUserPassField.clear();
        //setVerifyPassVisibility(false);
        searchText.clear();
        searchCountLabel.setVisible(false);
        populateTable(true);
    }


    private boolean hasEmptyField() {

        if(webNameText.getText().length()==0)
            return false;
        if(webAddressText.getText().length()==0)
            return false;
        if(webUserMailText.getText().length()==0)
            return false;
        if(webUserPassField.getText().length()==0)
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
        populateTable(true);
        setRecordsLabel();
    }


    private void tableRowSelection() {

        dataTable.setOnMousePressed(event -> {
            try {
                webNameText.setText(dataTable.getSelectionModel().getSelectedItem().getWeb_name());
                webAddressText.setText(dataTable.getSelectionModel().getSelectedItem().getWeb_address());
                webUserMailText.setText(dataTable.getSelectionModel().getSelectedItem().getWeb_user_email());
                webUserPassText.setText(dataTable.getSelectionModel().getSelectedItem().getWeb_password());
                webUserPassField.setText(dataTable.getSelectionModel().getSelectedItem().getWeb_password());
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
        searchCountLabel.setVisible(false);
        tableRowSelection();
    }


    @FXML
    private void saveAction(ActionEvent actionEvent) {

        if(!hasEmptyField()) {
            System.out.println("No field can be empty");
            return;
        }

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
                populateTable(true);
                clearAll();
            }
            else System.out.println("Saving failed");

        }
    }


    @FXML
    void updateAction(ActionEvent actionEvent) {

        if(!hasEmptyField()) {
            System.out.println("No field can be empty");
            return;
        }

       if(verifyPass()) {
           AccountTableDataManager accountTableDataManager = new AccountTableDataManager(accountLabel.getText());

           //String account, String web_name, String web_add, String mail, String pass
           boolean status = accountTableDataManager.updateTable(
                   webNameText.getText(),
                   webAddressText.getText(),
                   webUserMailText.getText(),
                   webUserPassText.getText(),
                   dataIDText.getText()
           );
           if(status) {
               System.out.println("Updated successfully");
               populateTable(true);
               clearAll();
           }
           else System.out.println("Updating failed");
       }
    }


    @FXML
    void deleteAction(ActionEvent actionEvent) {

        if(!hasEmptyField()) {
            System.out.println("No field can be empty");
            return;
        }

        if(verifyPass()) {
            AccountTableDataManager accountTableDataManager = new AccountTableDataManager(accountLabel.getText());

            //String account, String web_name, String web_add, String mail, String pass
            boolean status = accountTableDataManager.deleteFromTable(dataIDText.getText());
            if(status) {
                System.out.println("Deleted successfully");
                populateTable(true);
                clearAll();
            }
            else System.out.println("Deletion failed");
        }
    }


    @FXML
    void clearAction(ActionEvent actionEvent) {
        clearAll();
    }


    @FXML
    void showPassAction(ActionEvent actionEvent) {
        if(!webUserPassField.getText().isEmpty() && showPassButton.getText().equals("Show") && verifyPass()) {
            webUserPassField.setVisible(false);
            webUserPassText.setText(webUserPassField.getText());
            showPassButton.setText("Hide");
        }
        else if(showPassButton.getText().equals("Hide")) {
            webUserPassField.setVisible(true);
            showPassButton.setText("Show");
        }
    }


    @FXML
    void typedSearchAction(KeyEvent keyEvent) {
        populateTable(false);
    }


    @FXML
    void searchInputSense(InputMethodEvent inputMethodEvent) {
        if(searchText.equals("")) {
            searchCountLabel.setVisible(false);
        }
    }


    @FXML
    void passTextTypeAction(KeyEvent keyEvent) {
        webUserPassField.setText(webUserPassText.getText());
        //System.out.println(webUserPassField.getText());
    }


    @FXML
    public void passFieldTypeAction(KeyEvent keyEvent) {
        webUserPassText.setText(webUserPassField.getText());
        //System.out.println(webUserPassText.getText());
    }

}
