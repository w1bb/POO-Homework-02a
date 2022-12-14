package execution.interpreters.changepage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import execution.interpreters.GeneralInterpreter;
import execution.pages.PageFactory;
import execution.pages.PageQuery;
import execution.pages.PageResponse;

public final class ChangePageInterpreter implements GeneralInterpreter {
    private final ObjectMapper objectMapper;

    public ChangePageInterpreter() {
        objectMapper = new ObjectMapper();
    }

    /**
     * This method executes a given action.
     * @param pq The action to be executed, alongside other useful information.
     * @return A PageResponse containing the information required by the Interpreter.
     */
    @Override
    public PageResponse executeAction(final PageQuery pq) {
        PageResponse pageResponse = new PageResponse();
        if (pq.getCurrentPage().canVisit(pq.getCurrentActionsInput().getPage())) {
            pageResponse.setNewPage(PageFactory.getPage(pq.getCurrentActionsInput().getPage()));
            pageResponse.setNewUser(pq.getCurrentUser());
            pageResponse.setMoviesDBSubset(pq.getMoviesDBSubset());
            pageResponse.setActionOutput(null);
        } else {
            pageResponse.setNewPage(pq.getCurrentPage());
            pageResponse.setNewUser(pq.getCurrentUser());
            pageResponse.setMoviesDBSubset(pq.getMoviesDBSubset());
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("error", "Error");
            pageResponse.setActionOutput(objectNode);
        }
        return pageResponse;
    }


}
