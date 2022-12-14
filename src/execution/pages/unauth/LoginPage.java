package execution.pages.unauth;

import com.fasterxml.jackson.databind.node.ObjectNode;
import execution.pages.Page;
import execution.pages.PageFactory;
import execution.pages.PageQuery;
import execution.pages.PageResponse;
import execution.users.User;
import fileio.CredentialsInput;

import java.util.ArrayList;
import java.util.Arrays;

public final class LoginPage extends Page {
    private static LoginPage instance = null;

    private LoginPage() {
        super("login",
                new ArrayList<>(Arrays.asList(
                        "login",
                        "register")));
    }

    /**
     * This function is used for the singleton design patterns and returns the only (real) instance
     * of this page.
     * @return The (only) instance of the page.
     */
    public static LoginPage getInstance() {
        if (instance == null) {
            instance = new LoginPage();
        }
        return instance;
    }

    /**
     * This method executes the "login" feature on the current page.
     * @param pq The structure containing relevant information for the current request.
     * @return A PageResponse object containing useful information about the request.
     */
    private PageResponse executeLogin(final PageQuery pq) {
        CredentialsInput searchedUserCredentials = pq.getCurrentActionsInput().getCredentials();
        User newUser = pq.getUsersDB().search(searchedUserCredentials.getName(),
                searchedUserCredentials.getPassword());
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

    /**
     * This method executes a feature on the current page.
     * @param pq The structure containing relevant information for the current request.
     * @return A PageResponse object containing useful information about the request.
     */
    public PageResponse execute(final PageQuery pq) {
        return pq.getCurrentActionsInput().getFeature().equals("login")
                ? executeLogin(pq) : PageResponse.getErrorPageResponse();
    }

    /**
     * This method executes after a given page was just visited. In this case, nothing happens.
     * @param pq The structure containing relevant information for the current request.
     * @return A PageResponse object containing useful information about the request.
     */
    public PageResponse afterEnter(final PageQuery pq) {
        // This class does not include an afterEnter method.
        return null;
    }
}
