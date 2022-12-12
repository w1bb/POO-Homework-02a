package execution.pages.auth;

import execution.pages.Page;
import execution.pages.PageFactory;
import execution.pages.PageQuery;
import execution.pages.PageResponse;
import execution.users.User;
import fileio.ActionsInput;

import java.util.ArrayList;
import java.util.Arrays;

public class LogoutPage extends Page {
    private static LogoutPage instance = null;

    private LogoutPage() {
        super("logout", null);
    }

    public static LogoutPage getInstance() {
        if (instance == null) {
            instance = new LogoutPage();
        }
        return instance;
    }

    public PageResponse execute(PageQuery pq) {
        // This class does not include an execute method.
        return null;
    }

    public PageResponse afterEnter(PageQuery pq) {
        PageResponse resultPageResponse = new PageResponse();
        resultPageResponse.setNewPage(PageFactory.getPage("unauth-homepage"));
        return resultPageResponse;
    }
}
