package account_credentials;

import database.DBTableData;

import java.util.ArrayList;

public class TableDataManipulation {

    private static String userId;

    public TableDataManipulation(String userId) {
        this.userId = userId;
    }

    public ArrayList<TableDataModel>  fetchTableView() {
        ArrayList<TableDataModel> arrayList;
        arrayList = DBTableData.getTableData(userId);
        return arrayList;
    }

}
