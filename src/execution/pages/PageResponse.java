package execution.pages;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import execution.movies.MoviesDB;
import execution.users.User;

public final class PageResponse {
    private Page newPage;
    private User newUser;
    private ObjectNode actionOutput;
    private MoviesDB moviesDBSubset;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public PageResponse() {
        this.newPage = null;
        this.newUser = null;
        this.actionOutput = null;
        this.moviesDBSubset = null;
    }

    public Page getNewPage() {
        return newPage;
    }

    public void setNewPage(final Page newPage) {
        this.newPage = newPage;
    }

    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(final User newUser) {
        this.newUser = newUser;
    }

    public ObjectNode getActionOutput() {
        return actionOutput;
    }

    public void setActionOutput(final ObjectNode actionOutput) {
        this.actionOutput = actionOutput;
    }

    public MoviesDB getMoviesDBSubset() {
        return moviesDBSubset;
    }

    public void setMoviesDBSubset(final MoviesDB moviesDBSubset) {
        this.moviesDBSubset = moviesDBSubset;
    }

    /**
     * This method creates a new PageResponse which only contains the error field.
     * @return The error PageResponse.
     */
    public static PageResponse getErrorPageResponse() {
        PageResponse pageResponse = new PageResponse();
        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
        objectNode.put("error", "Error");
        pageResponse.actionOutput = objectNode;
        return pageResponse;
    }

    @Override
    public String toString() {
        return "PageResponse{"
                + "newPage=" + newPage
                + ", newUser=" + newUser
                + ", actionOutput=" + actionOutput + '}';
    }
}
