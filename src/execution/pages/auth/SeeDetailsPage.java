package execution.pages.auth;

import execution.pages.Page;
import execution.pages.PageResponse;
import execution.users.User;
import fileio.ActionsInput;

import java.util.ArrayList;
import java.util.Arrays;

public class SeeDetailsPage extends Page {
    private static SeeDetailsPage instance = null;

    private SeeDetailsPage() {
        super("see details",
                new ArrayList<>(Arrays.asList(
                        "auth-homepage",
                        "movies",
                        "upgrades",
                        "logout")));
    }

    public static SeeDetailsPage getInstance() {
        if (instance == null) {
            instance = new SeeDetailsPage();
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