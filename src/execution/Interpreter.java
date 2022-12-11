package execution;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import execution.movies.MoviesDB;
import execution.users.User;
import execution.users.UsersDB;
import fileio.ActionsInput;
import fileio.Input;
import fileio.UsersInput;

import java.util.ArrayList;

public final class Interpreter {
    private UsersDB usersDB;
    private MoviesDB moviesDB;
    private User currentUser;

    private ArrayList<ActionsInput> allActionsInputs;

    public Interpreter(Input input) {
        this.usersDB = new UsersDB();
        this.moviesDB = new MoviesDB();
        input.getUsers().forEach(usersInput -> usersDB.add(usersInput.toUser()));
        input.getMovies().forEach(movieInput -> moviesDB.add(movieInput.toMovie()));
        allActionsInputs = input.getActions();
        currentUser = null;
    }

    private ObjectNode executeChangePage(ActionsInput actionsInput) {

    }

    private ObjectNode executeOnPage(ActionsInput actionsInput) {

    }

    private ObjectNode executeAction(ActionsInput actionsInput) {
        if (actionsInput.getType().equals("change page")) {
            return executeChangePage(actionsInput);
        } else if (actionsInput.getType().equals("on page")) {
            return executeOnPage(actionsInput);
        }
        // This should NEVER be reached
        return null;
    }

    public ArrayNode runActions() {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode returnNode = objectMapper.createArrayNode();
        for (ActionsInput actionsInput : allActionsInputs) {
            ObjectNode objectNode = executeAction(actionsInput);
            if (objectNode == null) {
                // This should NEVER be reached
                System.out.println("Critical! A command was not found - '"
                        + actionsInput.toString() + "'.");
            } else {
                returnNode.add(objectNode);
            }
        }
        return returnNode;
    }
}
