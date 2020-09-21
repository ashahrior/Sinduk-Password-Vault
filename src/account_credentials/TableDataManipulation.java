package account_credentials;

import database.DBTableData;

import java.util.ArrayList;

public class TableDataManipulation {

    private String userId;

    public TableDataManipulation(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return this.userId;
    }

    public ArrayList<TableDataModel>  fetchTableView() {
        ArrayList<TableDataModel> arrayList;
        arrayList = DBTableData.getTableData(userId);
        return arrayList;
    }

    public boolean updateTable(String web_name, String web_add, String mail, String pass) {

        return true;
    }

    public boolean saveToTable(String web_name, String web_add, String mail, String pass) {

        return true;
    }

    public boolean deleteFromTable(String web_name, String web_add, String mail, String pass) {

        return true;
    }

}
