package execution.pages.unauth;

import execution.pages.Page;
import execution.pages.PageQuery;
import execution.pages.PageResponse;
import execution.users.User;
import fileio.ActionsInput;

import java.util.ArrayList;
import java.util.Arrays;

public final class UnauthHomePage extends Page {
    private static UnauthHomePage instance = null;

    private UnauthHomePage() {
        super("unauth-homepage",
                new ArrayList<>(Arrays.asList(
                        "login",
                        "register")));
    }

    public static UnauthHomePage getInstance() {
        if (instance == null) {
            instance = new UnauthHomePage();
        }
        return instance;
    }

    public PageResponse execute(PageQuery pq) {
        return PageResponse.getErrorPageResponse();
    }

    public PageResponse afterEnter(PageQuery pq) {
        // This class does not include an afterEnter method.
        return null;
    }
}
