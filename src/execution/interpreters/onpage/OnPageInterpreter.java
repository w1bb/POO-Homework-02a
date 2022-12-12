package execution.interpreters.onpage;

import execution.interpreters.GeneralInterpreter;
import execution.pages.PageQuery;
import execution.pages.PageResponse;

public class OnPageInterpreter implements GeneralInterpreter {

    @Override
    public PageResponse executeAction(PageQuery pq) {
        return pq.getCurrentPage().execute(pq);
    }
}
