package account_credentials;

import database.DBAccountLogin;

public class AccountLogin {

    static public Accounts attemptLogin(String user, String password) {

        Accounts account = null;
        boolean valid_entry = false, found=false;
        String original_password = "";

        if(password.length()==0) {
            System.out.println("Password field empty!");
            return null;
        }

        if(CredentialValidation.validate_user(user)) {
            System.out.println("username given");
            account = DBAccountLogin.checkByUser(user);
            valid_entry = true;
        }

        if(CredentialValidation.validate_email(user)) {
            System.out.println("email given");
            account = DBAccountLogin.checkByEmail(user);
            valid_entry = true;
        }

        if(valid_entry) {
            if(account==null) {
                System.out.println("Account not found.");
                return null;
            }
            original_password = account.getPassword();
        }
        else {
            System.out.println("User/Email invalid.");
            return null;
        }

        if(original_password.compareTo(password)==0) {
            System.out.println("password match");
            return account;
        }
        System.out.println("Incorrect password");
        return null;
    }
}
