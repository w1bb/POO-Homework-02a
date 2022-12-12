package execution.pages.unauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import execution.pages.Page;
import execution.pages.PageFactory;
import execution.pages.PageQuery;
import execution.pages.PageResponse;
import execution.users.User;
import fileio.ActionsInput;
import fileio.CredentialsInput;
import fileio.UsersInput;

import java.util.ArrayList;
import java.util.Arrays;

public final class RegisterPage extends Page {
    private static RegisterPage instance = null;

    private RegisterPage() {
        super("register",
                new ArrayList<>(Arrays.asList(
                        "login")));
    }

    public static RegisterPage getInstance() {
        if (instance == null) {
            instance = new RegisterPage();
        }
        return instance;
    }

    private PageResponse executeRegister(PageQuery pq) {
        CredentialsInput newUserCredentials = pq.getCurrentActionsInput().getCredentials();
        UsersInput newUserInput = new UsersInput();
        newUserInput.setCredentials(newUserCredentials);
        User newUser = newUserInput.toUser();
        if (pq.getUsersDB().checkName(newUser.getName())) {
            return PageResponse.getErrorPageResponse();
        }

        // Add new user and change current user
        pq.getUsersDB().add(newUser);
        PageResponse pageResponse = new PageResponse();
        pageResponse.setNewPage(PageFactory.getPage("auth-homepage"));
        pageResponse.setNewUser(newUser);
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.set("currentUser", newUser.toObjectNode());
        pageResponse.setActionOutput(objectNode);
        return pageResponse;
    }

    public PageResponse execute(PageQuery pq) {
        return pq.getCurrentActionsInput().getFeature().equals("register") ?
                executeRegister(pq) : PageResponse.getErrorPageResponse();
    }

    public PageResponse afterEnter(PageQuery pq) {
        // This class does not include an afterEnter method.
        return null;
    }
}
