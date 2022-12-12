package execution.pages;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import execution.users.User;

public final class PageResponse {
    private Page newPage;
    private User newUser;
    private ObjectNode actionOutput;

    private static ObjectMapper objectMapper = new ObjectMapper();

    public PageResponse() {
        this.newPage = null;
        this.newUser = null;
        this.actionOutput = null;
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

    public static PageResponse getErrorPageResponse() {
        PageResponse pageResponse = new PageResponse();
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("error", "Error");
        pageResponse.actionOutput = objectNode;
        return pageResponse;
    }
}
