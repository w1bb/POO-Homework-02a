package execution.users;

import execution.AccountType;

public final class UserPremium extends User {

    public UserPremium(String name, String password, String country, int balance) {
        super(name, password, AccountType.PREMIUM, country, balance);
    }
}