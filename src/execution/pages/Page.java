package execution.pages;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import execution.users.User;
import fileio.ActionsInput;

import java.util.ArrayList;

public abstract class Page {
    protected String name;
    protected ArrayList<String> visitablePages;
    protected ObjectMapper objectMapper;

    public Page(String name, ArrayList<String> visitablePages) {
        this.name = name;
        this.visitablePages = visitablePages;
        this.objectMapper = new ObjectMapper();
    }

    public Boolean canVisit(String name) {
        return visitablePages.contains(name);
    }

    public abstract PageResponse execute(PageQuery pq);
    public abstract PageResponse afterEnter(PageQuery pq);
}
