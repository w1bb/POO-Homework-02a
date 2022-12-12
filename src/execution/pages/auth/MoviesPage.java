package execution.pages.auth;

import execution.pages.Page;
import execution.pages.PageQuery;
import execution.pages.PageResponse;
import execution.users.User;
import fileio.ActionsInput;

import java.util.ArrayList;
import java.util.Arrays;

public class MoviesPage extends Page {
    private static MoviesPage instance = null;

    private MoviesPage() {
        super("movies",
                new ArrayList<>(Arrays.asList(
                        "auth-homepage",
                        "see details",
                        "logout")));
    }

    public static MoviesPage getInstance() {
        if (instance == null) {
            instance = new MoviesPage();
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