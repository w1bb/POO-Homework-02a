package execution.interpreters;

import com.fasterxml.jackson.databind.node.ObjectNode;
import execution.pages.Page;
import execution.pages.PageQuery;
import execution.pages.PageResponse;
import execution.users.User;
import fileio.ActionsInput;

public interface GeneralInterpreter {
    PageResponse executeAction(PageQuery pq);
}
