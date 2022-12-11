package execution.users;

import execution.AccountType;

public final class UserStandard extends User {
    public UserStandard(String name, String password, String country, int balance) {
        super(name, password, AccountType.STANDARD, country, balance);
    }
}
