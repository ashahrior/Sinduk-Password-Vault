package account_credentials;

import database.DBTableDataManager;

import java.util.ArrayList;

public class AccountTableDataManager {

    private String userId;

    public AccountTableDataManager(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public ArrayList<AccountTableDataModel>  fetchTableView() {
        ArrayList<AccountTableDataModel> arrayList;
        arrayList = DBTableDataManager.getAllTableData(userId);
        return arrayList;
    }

    public boolean saveToTable(String web_name, String web_add, String mail, String pass) {
        boolean status = DBTableDataManager.insertRow(userId, web_name, web_add, mail, pass);
        return status;
    }

    public boolean updateTable(String web_name, String web_add, String mail, String pass, String entryId) {
        boolean status = DBTableDataManager.updateRow(userId,web_name,web_add,mail,pass,entryId);
        return status;
    }

    public boolean deleteFromTable(String entryId) {
        boolean status = DBTableDataManager.deleteRow(entryId);
        return status;
    }

    public ArrayList<AccountTableDataModel> searchTable(String searchKey) {
        ArrayList<AccountTableDataModel> arrayList;
        arrayList = DBTableDataManager.getSearchData(userId, searchKey);
        return arrayList;
    }

    public String getRecordCount() {
        return DBTableDataManager.getRowCount(userId);
    }

}
