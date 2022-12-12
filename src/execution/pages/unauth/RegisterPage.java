package execution.pages.unauth;

import execution.pages.Page;
import execution.pages.PageResponse;
import execution.users.User;
import fileio.ActionsInput;

import java.util.ArrayList;
import java.util.Arrays;

public final class RegisterPage extends Page {
    private static RegisterPage instance = null;

    private RegisterPage() {
        super("register", null);
    }

    public static RegisterPage getInstance() {
        if (instance == null) {
            instance = new RegisterPage();
        }
        return instance;
    }

    public PageResponse execute(User currentUser, ActionsInput action) {
        PageResponse pageResponse = new PageResponse();

        // TODO

        return pageResponse;
    }

    public PageResponse afterEnter(PageResponse pageResponse, ActionsInput actionBefore) {
        // This class does not include an afterEnter method.
        return null;
    }
}
