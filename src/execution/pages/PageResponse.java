package execution.pages;

import com.fasterxml.jackson.databind.node.ObjectNode;
import execution.users.User;

public final class PageResponse {
    private Page newPage;
    private User newUser;
    private ObjectNode actionOutput;

    public PageResponse() {
    }

    public Page getNewPage() {
        return newPage;
    }

    public void setNewPage(Page newPage) {
        this.newPage = newPage;
    }

    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }

    public ObjectNode getActionOutput() {
        return actionOutput;
    }

    public void setActionOutput(ObjectNode actionOutput) {
        this.actionOutput = actionOutput;
    }
}
