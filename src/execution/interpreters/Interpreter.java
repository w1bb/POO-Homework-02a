package execution.interpreters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import execution.interpreters.changepage.ChangePageInterpreter;
import execution.interpreters.onpage.OnPageInterpreter;
import execution.movies.MoviesDB;
import execution.pages.Page;
import execution.pages.PageQuery;
import execution.pages.PageResponse;
import execution.pages.unauth.UnauthHomePage;
import execution.users.User;
import execution.users.UsersDB;
import fileio.ActionsInput;
import fileio.Input;

import java.util.ArrayList;

public final class Interpreter implements GeneralInterpreter {
    private UsersDB usersDB;
    private MoviesDB moviesDB;

    private ArrayList<ActionsInput> allActionsInputs;

    private ChangePageInterpreter changePageInterpreter;

    private OnPageInterpreter onPageInterpreter;

    public Interpreter(Input input) {
        this.usersDB = new UsersDB();
        this.moviesDB = new MoviesDB();
        input.getUsers().forEach(usersInput -> usersDB.add(usersInput.toUser()));
        input.getMovies().forEach(movieInput -> moviesDB.add(movieInput.toMovie()));
        allActionsInputs = input.getActions();

        changePageInterpreter = new ChangePageInterpreter(usersDB, moviesDB);
        onPageInterpreter = new OnPageInterpreter();
    }

    public PageResponse executeAction(PageQuery pq) {
        if (pq.getCurrentActionsInput().getType().equals("change page")) {
            return changePageInterpreter.executeAction(pq);
        } else if (pq.getCurrentActionsInput().getType().equals("on page")) {
            return onPageInterpreter.executeAction(pq);
        }
        // This should NEVER be reached
        return null;
    }

    public ArrayNode runActions() {
        User currentUser = null;
        Page currentPage = UnauthHomePage.getInstance();

        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode returnNode = objectMapper.createArrayNode();

        PageQuery pq = new PageQuery();
        pq.setMoviesDB(moviesDB);
        pq.setUsersDB(usersDB);
        for (ActionsInput actionsInput : allActionsInputs) {
            pq.setCurrentActionsInput(actionsInput);
            pq.setCurrentPage(currentPage);
            pq.setCurrentUser(currentUser);
            PageResponse pageResponse = executeAction(pq);
            if (pageResponse == null) {
                // This should NEVER be reached
                System.out.println("Critical! A command was not found - '"
                        + actionsInput.toString() + "'.");
                continue;
            }
            while (pageResponse != null) {
                currentUser = pageResponse.getNewUser();
                currentPage = pageResponse.getNewPage();
                pq.setCurrentUser(currentUser);
                pq.setCurrentPage(currentPage);
                if (pageResponse.getActionOutput() != null) {
                    ObjectNode objectNode = pageResponse.getActionOutput();
                    if (!objectNode.has("currentMoviesList")) {
                        objectNode.set("currentMoviesList", objectMapper.createArrayNode());
                    }
                    if (!objectNode.has("currentUser")) {
                        objectNode.set("currentUser", null);
                    }
                    if (!objectNode.has("error")) {
                        objectNode.set("error", null);
                    } else {
                        break;
                    }
                    returnNode.add(objectNode);
                }
                pageResponse = currentPage.afterEnter(pq);
            }
        }
        return returnNode;
    }
}
