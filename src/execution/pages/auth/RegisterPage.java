package execution.pages.auth;

import execution.pages.Page;
import execution.users.User;
import fileio.ActionsInput;

import java.util.ArrayList;
import java.util.Arrays;

public class RegisterPage extends Page {
    private RegisterPage instance = null;
    private RegisterPage() {
        super("register", new ArrayList<>(Arrays.asList(AuthHomePage.getInstance())));
    }

    public RegisterPage getInstance() {
        if (instance == null)
            instance = new RegisterPage();
        return instance;
    }

    @Override
    public Page accept(User user, ActionsInput actionsInput) {
        // TODO
        return null;
    }
}