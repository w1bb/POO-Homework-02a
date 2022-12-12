package execution.pages;

import com.fasterxml.jackson.databind.node.ObjectNode;
import execution.users.User;
import fileio.ActionsInput;

import java.util.ArrayList;

public abstract class Page {
    protected String name;
    protected ArrayList<String> visitablePages;

    public Page(String name, ArrayList<String> visitablePages) {
        this.name = name;
        this.visitablePages = visitablePages;
    }

    public Boolean canVisit(String name) {
        return visitablePages.contains(name);
    }

    public abstract PageResponse execute(User currentUser, ActionsInput action);
    public abstract PageResponse afterEnter(PageResponse pageResponse, ActionsInput actionBefore);
}
