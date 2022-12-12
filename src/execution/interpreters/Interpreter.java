package execution.interpreters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import execution.interpreters.changepage.ChangePageInterpreter;
import execution.interpreters.onpage.OnPageInterpreter;
import execution.movies.MoviesDB;
import execution.users.User;
import execution.users.UsersDB;
import fileio.ActionsInput;
import fileio.Input;

import java.util.ArrayList;

public final class Interpreter {
    private UsersDB usersDB;
    private MoviesDB moviesDB;
    private User currentUser;

    private ArrayList<ActionsInput> allActionsInputs;

    private ChangePageInterpreter changePageInterpreter;

    private OnPageInterpreter onPageInterpreter;

    public Interpreter(Input input) {
        this.usersDB = new UsersDB();
        this.moviesDB = new MoviesDB();
        input.getUsers().forEach(usersInput -> usersDB.add(usersInput.toUser()));
        input.getMovies().forEach(movieInput -> moviesDB.add(movieInput.toMovie()));
        allActionsInputs = input.getActions();
        currentUser = null;

        changePageInterpreter = new ChangePageInterpreter();
        onPageInterpreter = new OnPageInterpreter();
    }



    private ObjectNode executeAction(ActionsInput actionsInput) {
        if (actionsInput.getType().equals("change page")) {
            return changePageInterpreter.execute(actionsInput);
        } else if (actionsInput.getType().equals("on page")) {
            return onPageInterpreter.execute(actionsInput);
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
