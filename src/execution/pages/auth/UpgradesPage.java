package execution.pages.auth;

import execution.pages.Page;
import execution.pages.PageResponse;
import execution.users.User;
import fileio.ActionsInput;

import java.util.ArrayList;
import java.util.Arrays;

public class UpgradesPage extends Page {
    private static UpgradesPage instance = null;

    private UpgradesPage() {
        super("upgrades",
                new ArrayList<>(Arrays.asList(
                        "auth-homepage",
                        "movies",
                        "logout")));
    }

    public static UpgradesPage getInstance() {
        if (instance == null) {
            instance = new UpgradesPage();
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
