package execution.pages.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import execution.movies.Movie;
import execution.pages.Page;
import execution.pages.PageQuery;
import execution.pages.PageResponse;
import execution.users.User;
import fileio.ActionsInput;

import java.util.ArrayList;
import java.util.Arrays;

public final class SeeDetailsPage extends Page {
    private static SeeDetailsPage instance = null;
    private Movie currentMovie;

    private SeeDetailsPage() {
        super("see details",
                new ArrayList<>(Arrays.asList(
                        "auth-homepage",
                        "movies",
                        "upgrades",
                        "logout")));
        currentMovie = null;
    }

    public static SeeDetailsPage getInstance() {
        if (instance == null) {
            instance = new SeeDetailsPage();
        }
        return instance;
    }

    private PageResponse executePurchase(PageQuery pq) {
        // Check if it has already been purchased
        User currentUser = pq.getCurrentUser();
        if (currentUser.getPurchasedMovies().contains(currentMovie)) {
            // This should NEVER be reached TODO
        }

        ArrayList<Movie> purchasedMovies = currentUser.getPurchasedMovies();
        if (currentUser.getNumFreePremiumMovies() > 0) {
            // It is already free
            currentUser.setNumFreePremiumMovies(currentUser.getNumFreePremiumMovies() - 1);
            purchasedMovies.add(currentMovie);
        } else if (currentUser.getTokensCount() > 0) {
            // Buy with tokens
            currentUser.setTokensCount(currentUser.getTokensCount() - 1);
            purchasedMovies.add(currentMovie);
        } else {
            // Cannot buy
            return PageResponse.getErrorPageResponse();
        }
        PageResponse pageResponse = new PageResponse();
        pageResponse.setNewPage(this);
        pageResponse.setNewUser(currentUser);
        pageResponse.setActionOutput(null);
        return pageResponse;
    }

    private PageResponse executeWatch(PageQuery pq) {
        User currentUser = pq.getCurrentUser();
        if (!currentUser.getPurchasedMovies().contains(currentMovie)) {
            return PageResponse.getErrorPageResponse();
        }
        if (!currentUser.getWatchedMovies().contains(currentMovie)) {
            currentUser.getWatchedMovies().add(currentMovie);
        }
        PageResponse pageResponse = new PageResponse();
        pageResponse.setNewPage(this);
        pageResponse.setNewUser(currentUser);
        pageResponse.setActionOutput(null);
        return pageResponse;
    }

    private PageResponse executeLike(PageQuery pq) {
        User currentUser = pq.getCurrentUser();
        if (!currentUser.getPurchasedMovies().contains(currentMovie)
                || !currentUser.getWatchedMovies().contains(currentMovie)) {
            return PageResponse.getErrorPageResponse();
        }
        if (!currentUser.getLikedMovies().contains(currentMovie)) {
            currentUser.getLikedMovies().add(currentMovie);
            currentMovie.like(currentUser);
        }
        PageResponse pageResponse = new PageResponse();
        pageResponse.setNewPage(this);
        pageResponse.setNewUser(currentUser);
        pageResponse.setActionOutput(null);
        return pageResponse;
    }

    private PageResponse executeRate(PageQuery pq) {
        User currentUser = pq.getCurrentUser();
        if (!currentUser.getPurchasedMovies().contains(currentMovie)
                || !currentUser.getWatchedMovies().contains(currentMovie)) {
            return PageResponse.getErrorPageResponse();
        }
        if (!currentUser.getRatedMovies().contains(currentMovie)) {
            currentUser.getRatedMovies().add(currentMovie);
            currentMovie.rate(currentUser, pq.getCurrentActionsInput().getRate());
        }
        PageResponse pageResponse = new PageResponse();
        pageResponse.setNewPage(this);
        pageResponse.setNewUser(currentUser);
        pageResponse.setActionOutput(null);
        return pageResponse;
    }

    public PageResponse execute(PageQuery pq) {
        return switch (pq.getCurrentActionsInput().getFeature()) {
            case "purchase" -> executePurchase(pq);
            case "watch" -> executeWatch(pq);
            case "like" -> executeLike(pq);
            case "rate" -> executeRate(pq);
            default -> null; // This should NEVER be reached
        };
    }

    public PageResponse afterEnter(PageQuery pq) {
        // This class does not include an afterEnter method.
        currentMovie = pq.getMoviesDB().searchExact(
                pq.getCurrentActionsInput().getMovie(),
                pq.getCurrentUser());
        if (currentMovie == null) {
            // This should NEVER be reached (?)
            return PageResponse.getErrorPageResponse();
        }
        return null;
    }
}