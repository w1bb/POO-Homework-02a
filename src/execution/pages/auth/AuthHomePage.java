package execution.pages.auth;

import execution.pages.Page;
import execution.pages.PageQuery;
import execution.pages.PageResponse;
import execution.users.User;
import fileio.ActionsInput;

import java.util.ArrayList;
import java.util.Arrays;

public final class AuthHomePage extends Page {
    private static AuthHomePage instance = null;

    private AuthHomePage() {
        super("auth-homepage",
                new ArrayList<>(Arrays.asList(
                        "movies",
                        "upgrades",
                        "logout")));
    }

    public static AuthHomePage getInstance() {
        if (instance == null) {
            instance = new AuthHomePage();
        }
        return instance;
    }

    public PageResponse execute(PageQuery pq) {
        PageResponse pageResponse = new PageResponse();

        // TODO

        return pageResponse;
    }

    public PageResponse afterEnter(PageQuery pq) {
        // This class does not include an afterEnter method.
        return null;
    }
}
