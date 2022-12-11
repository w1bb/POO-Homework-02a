package execution.users;

import execution.AccountType;

public final class UserPremium extends User {
    static int BONUS_FREE_MOVIES = 15;

    public UserPremium(String name, String password, String country, int balance) {
        super(name, password, AccountType.PREMIUM, country, balance);
        this.numFreePremiumMovies = UserPremium.BONUS_FREE_MOVIES;
    }
}