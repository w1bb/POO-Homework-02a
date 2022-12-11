package execution.pages.auth;

import execution.pages.Page;
import execution.users.User;
import fileio.ActionsInput;

import java.util.ArrayList;
import java.util.Arrays;

public final class AuthHomePage extends Page {
    private AuthHomePage instance = null;
    private AuthHomePage() {
        super("homepage", new ArrayList<>(Arrays.asList(RegisterPage.getInstance())));
    }

    public AuthHomePage getInstance() {
        if (instance == null)
            instance = new AuthHomePage();
        return instance;
    }

    @Override
    public Page accept(User user, ActionsInput actionsInput) {
        // TODO
        return null;
    }
}
