package execution.pages;

import execution.users.User;
import fileio.ActionsInput;

import java.util.ArrayList;

public abstract class Page {
    private String name;
    private final ArrayList<Page> accessiblePages;

    public Page(String name, ArrayList<Page> accessiblePages) {
        this.name = name;
        this.accessiblePages = accessiblePages;
    }

    public abstract Page accept(User user, ActionsInput actionsInput);
}
