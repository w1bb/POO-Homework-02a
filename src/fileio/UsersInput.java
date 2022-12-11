package fileio;

import execution.users.User;
import execution.users.UserPremium;
import execution.users.UserStandard;

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
                    new UserStandard(credentialsInput.getName(), credentialsInput.getPassword(),
                            credentialsInput.getCountry(),
                            Integer.parseInt(credentialsInput.getBalance()));
            case "premium" ->
                    new UserPremium(credentialsInput.getName(), credentialsInput.getPassword(),
                            credentialsInput.getCountry(),
                            Integer.parseInt(credentialsInput.getBalance()));
            default -> null;
        };
    }
}
