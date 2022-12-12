package execution.pages;

import execution.movies.MoviesDB;
import execution.users.User;
import execution.users.UsersDB;
import fileio.ActionsInput;

import javax.swing.*;

public final class PageQuery {
    private PageResponse oldPageResponse;
    private Page currentPage;
    private User currentUser;
    private ActionsInput currentActionsInput;
    private UsersDB usersDB;
    private MoviesDB moviesDB;

    public PageQuery() {
    }

    public PageResponse getOldPageResponse() {
        return oldPageResponse;
    }

    public void setOldPageResponse(PageResponse oldPageResponse) {
        this.oldPageResponse = oldPageResponse;
    }

    public Page getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Page currentPage) {
        this.currentPage = currentPage;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public ActionsInput getCurrentActionsInput() {
        return currentActionsInput;
    }

    public void setCurrentActionsInput(ActionsInput currentActionsInput) {
        this.currentActionsInput = currentActionsInput;
    }

    public UsersDB getUsersDB() {
        return usersDB;
    }

    public void setUsersDB(UsersDB usersDB) {
        this.usersDB = usersDB;
    }

    public MoviesDB getMoviesDB() {
        return moviesDB;
    }

    public void setMoviesDB(MoviesDB moviesDB) {
        this.moviesDB = moviesDB;
    }

    @Override
    public String toString() {
        return "PageQuery{" +
                "oldPageResponse=" + oldPageResponse +
                ", currentPage=" + currentPage +
                ", currentUser=" + currentUser +
                ", currentActionsInput=" + currentActionsInput +
                ", usersDB=" + usersDB +
                ", moviesDB=" + moviesDB +
                '}';
    }
}
