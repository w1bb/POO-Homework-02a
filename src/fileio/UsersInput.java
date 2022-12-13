package fileio;

import execution.AccountType;
import execution.users.User;

public class UsersInput {
    private CredentialsInput credentialsInput;

    public UsersInput() {
    }

    public CredentialsInput getCredentials() {
        return credentialsInput;
    }

    public void setCredentials(CredentialsInput credentials) {
        this.credentialsInput = credentials;
    }

    public User toUser() {
        return switch (credentialsInput.getAccountType()) {
            case "standard" ->
                    new User(credentialsInput.getName(), credentialsInput.getPassword(),
                            AccountType.STANDARD,
                            credentialsInput.getCountry(),
                            Integer.parseInt(credentialsInput.getBalance()));
            case "premium" ->
                    new User(credentialsInput.getName(), credentialsInput.getPassword(),
                            AccountType.PREMIUM,
                            credentialsInput.getCountry(),
                            Integer.parseInt(credentialsInput.getBalance()));
            default -> null;
        };
    }
}
