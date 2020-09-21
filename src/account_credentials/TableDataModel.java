package account_credentials;

public class TableDataModel {
    private String web_name, web_address, web_user_email, web_password;

    public TableDataModel(String web_name, String web_address, String web_user_email, String web_password) {
        this.web_name = web_name;
        this.web_address = web_address;
        this.web_user_email = web_user_email;
        this.web_password = web_password;
    }

    public String getWeb_name() { return web_name; }

    public void setWeb_name(String web_name) {
        this.web_name = web_name;
    }

    public String getWeb_address() {
        return web_address;
    }

    public void setWeb_address(String web_address) {
        this.web_address = web_address;
    }

    public String getWeb_user_email() {
        return web_user_email;
    }

    public void setWeb_user_email(String web_user_email) {
        this.web_user_email = web_user_email;
    }

    public String getWeb_password() {
        return web_password;
    }

    public void setWeb_password(String web_password) {
        this.web_password = web_password;
    }
}
