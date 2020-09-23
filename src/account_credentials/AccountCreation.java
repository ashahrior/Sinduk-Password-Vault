package account_credentials;

import database.DBAccountCreator;

public class AccountCreation {

    private static boolean validateCredentials(String username, String email_address, String password_1, String password_2) {
        if(!AccountAccessCredentialValidation.validate_user(username)) {
            System.out.println("Invalid username");
            return false;
        }

        if(!AccountAccessCredentialValidation.validate_email(email_address)) {
            System.out.println("Invalid email");
            return false;
        }

        if(!AccountAccessCredentialValidation.password_matcher(password_1, password_2)) {
            System.out.println("Passwords don't match");
            return false;
        }

        if(!AccountAccessCredentialValidation.validate_password(password_1)) {
            System.out.println("Invalid password");
            return false;
        }
        return true;
    }

    public static boolean createAccount(String username, String email, String password1, String password2) {
        if (validateCredentials(username, email, password1, password2)) {
            Account account = new Account(username, email, password1);
            DBAccountCreator dbainsertion = new DBAccountCreator(account);
            return dbainsertion.insert_account();
        }
        return false;
    }
}
