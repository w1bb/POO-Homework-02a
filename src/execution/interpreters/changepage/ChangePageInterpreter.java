package execution.interpreters.changepage;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import execution.interpreters.GeneralInterpreter;
import execution.movies.MoviesDB;
import execution.pages.Page;
import execution.pages.PageFactory;
import execution.pages.PageQuery;
import execution.pages.PageResponse;
import execution.users.User;
import execution.users.UsersDB;
import fileio.ActionsInput;

public class ChangePageInterpreter implements GeneralInterpreter {
    private UsersDB usersDB;
    private MoviesDB moviesDB;
    private ObjectMapper objectMapper;

    public ChangePageInterpreter(UsersDB usersDB, MoviesDB moviesDB) {
        this.usersDB = usersDB;
        this.moviesDB = moviesDB;
        objectMapper = new ObjectMapper();
    }

    @Override
    public PageResponse executeAction(PageQuery pq) {
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
