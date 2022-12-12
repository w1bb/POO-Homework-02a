package execution.pages.unauth;

import com.fasterxml.jackson.databind.node.ObjectNode;
import execution.pages.Page;
import execution.pages.PageFactory;
import execution.pages.PageQuery;
import execution.pages.PageResponse;
import execution.users.User;
import fileio.ActionsInput;
import fileio.CredentialsInput;

import java.util.ArrayList;
import java.util.Arrays;

public final class LoginPage extends Page {
    private static LoginPage instance = null;

    private LoginPage() {
        super("login",
                new ArrayList<>(Arrays.asList(
                        "register")));
    }

    public static LoginPage getInstance() {
        if (instance == null) {
            instance = new LoginPage();
        }
        return instance;
    }

    private PageResponse executeLogin(PageQuery pq) {
        CredentialsInput searchedUserCredentials = pq.getCurrentActionsInput().getCredentials();
        User newUser = pq.getUsersDB().search(searchedUserCredentials.getName(), searchedUserCredentials.getPassword());
        if (newUser == null) {
            return PageResponse.getErrorPageResponse();
        }

        // Change current user
        PageResponse pageResponse = new PageResponse();
        pageResponse.setNewPage(PageFactory.getPage("auth-homepage"));
        pageResponse.setNewUser(newUser);
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.set("currentUser", newUser.toObjectNode());
        pageResponse.setActionOutput(objectNode);
        return pageResponse;
    }

    public PageResponse execute(PageQuery pq) {
        return pq.getCurrentActionsInput().getFeature().equals("login") ?
                executeLogin(pq) : PageResponse.getErrorPageResponse();
    }

    public PageResponse afterEnter(PageQuery pq) {
        // This class does not include an afterEnter method.
        return null;
    }
}
