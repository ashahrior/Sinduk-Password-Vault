package account_credentials;

public class Account {
    private String username;
    private String email_address;
    private String password;

    public Account(String username, String email_address, String password) {
        this.username = username;
        this.email_address = email_address;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
