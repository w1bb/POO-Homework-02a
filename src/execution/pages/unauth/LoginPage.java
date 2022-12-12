package execution.pages.unauth;

import execution.pages.Page;
import execution.pages.PageResponse;
import execution.users.User;
import fileio.ActionsInput;

public final class LoginPage extends Page {
    private static LoginPage instance = null;

    private LoginPage() {
        super("login", null);
    }

    public static LoginPage getInstance() {
        if (instance == null) {
            instance = new LoginPage();
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
