package account_credentials;

import database.DBAccountInsertion;

public class AccountCreation {

    private static boolean validateCredentials(String username, String email_address, String password_1, String password_2) {
        if(!CredentialValidation.validate_user(username)) {
            System.out.println("Invalid username");
            return false;
        }

        if(!CredentialValidation.validate_email(email_address)) {
            System.out.println("Invalid email");
            return false;
        }

        if(!CredentialValidation.password_matcher(password_1, password_2)) {
            System.out.println("Passwords don't match");
            return false;
        }

        if(!CredentialValidation.validate_password(password_1)) {
            System.out.println("Invalid password");
            return false;
        }
        return true;
    }

    public static boolean createAccount(String username, String email, String password1, String password2) {
        if (validateCredentials(username, email, password1, password2)) {
            Accounts accounts = new Accounts(username, email, password1);
            DBAccountInsertion dbinsertion = new DBAccountInsertion(accounts);
            return dbinsertion.insert_account();
        }
        return false;
    }
}
