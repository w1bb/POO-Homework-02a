package execution.pages.auth;

import execution.pages.Page;
import execution.pages.PageQuery;
import execution.pages.PageResponse;
import execution.users.User;
import fileio.ActionsInput;

import java.util.ArrayList;
import java.util.Arrays;

public class UpgradesPage extends Page {
    private static UpgradesPage instance = null;

    private UpgradesPage() {
        super("upgrades",
                new ArrayList<>(Arrays.asList(
                        "auth-homepage",
                        "movies",
                        "logout")));
    }

    public static UpgradesPage getInstance() {
        if (instance == null) {
            instance = new UpgradesPage();
        }
        return instance;
    }

    private PageResponse executeBuyTokens(PageQuery pq) {
        PageResponse pageResponse = new PageResponse();
        User currentUser = pq.getCurrentUser();
        pageResponse.setNewUser(currentUser);
        boolean bought = currentUser.buyTokens(Integer.parseInt(pq.getCurrentActionsInput().getCount()));
        return bought ? pageResponse : PageResponse.getErrorPageResponse();
    }

    private PageResponse executeBuyPremiumAccount(PageQuery pq) {
        PageResponse pageResponse = new PageResponse();
        User currentUser = pq.getCurrentUser();
        pageResponse.setNewUser(currentUser);
        boolean bought = currentUser.buyPremium();
        return bought ? pageResponse : PageResponse.getErrorPageResponse();
    }

    public PageResponse execute(PageQuery pq) {
        return switch (pq.getCurrentActionsInput().getFeature()) {
            case "buy tokens" -> executeBuyTokens(pq);
            case "buy premium account" -> executeBuyPremiumAccount(pq);
            default -> PageResponse.getErrorPageResponse();
        };
    }

    public PageResponse afterEnter(PageQuery pq) {
        // This class does not include an afterEnter method.
        return null;
    }
}
