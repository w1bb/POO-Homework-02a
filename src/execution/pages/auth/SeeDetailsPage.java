package execution.pages.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import execution.AccountType;
import execution.movies.Movie;
import execution.movies.MoviesDB;
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
//            return PageResponse.getErrorPageResponse();
            // TODO
        }

        ArrayList<Movie> purchasedMovies = currentUser.getPurchasedMovies();
        if (currentUser.getNumFreePremiumMovies() > 0 && currentUser.getAccountType() == AccountType.PREMIUM) {
            // It is already free
            currentUser.setNumFreePremiumMovies(currentUser.getNumFreePremiumMovies() - 1);
            purchasedMovies.add(currentMovie);
        } else if (currentUser.getTokensCount() >= currentMovie.getTokensCost()) {
            // Buy with tokens
            currentUser.setTokensCount(currentUser.getTokensCount() - currentMovie.getTokensCost());
            purchasedMovies.add(currentMovie);
        } else {
            // Cannot buy
            return PageResponse.getErrorPageResponse();
        }
        PageResponse pageResponse = new PageResponse();
//        pageResponse.setNewPage(this);
        pageResponse.setNewUser(currentUser);
        pageResponse.setActionOutput(getCurrentMovieAsObjectNode());
        return pageResponse;
    }

    private PageResponse executeWatch(PageQuery pq) {
        User currentUser = pq.getCurrentUser();
        if (!currentUser.getPurchasedMovies().contains(currentMovie)) {
            return PageResponse.getErrorPageResponse();
        }
//        if (currentUser.getWatchedMovies().contains(currentMovie)) {
//            return PageResponse.getErrorPageResponse();
//        }
        currentMovie.watch(currentUser);
        PageResponse pageResponse = new PageResponse();
//        pageResponse.setNewPage(this);
        pageResponse.setNewUser(currentUser);
        pageResponse.setActionOutput(getCurrentMovieAsObjectNode());
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
        } else {
//            return PageResponse.getErrorPageResponse();
        }
        PageResponse pageResponse = new PageResponse();
//        pageResponse.setNewPage(this);
        pageResponse.setNewUser(currentUser);
        pageResponse.setActionOutput(getCurrentMovieAsObjectNode());
        return pageResponse;
    }

    private PageResponse executeRate(PageQuery pq) {
        User currentUser = pq.getCurrentUser();
        if (pq.getCurrentActionsInput().getRate() < 1
            || pq.getCurrentActionsInput().getRate() > 5) {
            return PageResponse.getErrorPageResponse();
        }
        if (!currentUser.getPurchasedMovies().contains(currentMovie)
                || !currentUser.getWatchedMovies().contains(currentMovie)) {
            return PageResponse.getErrorPageResponse();
        }
        if (!currentUser.getRatedMovies().contains(currentMovie)) {
            currentUser.getRatedMovies().add(currentMovie);
            currentMovie.rate(currentUser, pq.getCurrentActionsInput().getRate());
        } else {
//            return PageResponse.getErrorPageResponse();
        }
        PageResponse pageResponse = new PageResponse();
//        pageResponse.setNewPage(this);
        pageResponse.setNewUser(currentUser);
        pageResponse.setActionOutput(getCurrentMovieAsObjectNode());
        return pageResponse;
    }

    public PageResponse execute(PageQuery pq) {
        if (currentMovie == null) {
            return PageResponse.getErrorPageResponse();
        }
        return switch (pq.getCurrentActionsInput().getFeature()) {
            case "purchase" -> executePurchase(pq);
            case "watch" -> executeWatch(pq);
            case "like" -> executeLike(pq);
            case "rate" -> executeRate(pq);
            default -> PageResponse.getErrorPageResponse(); // This should NEVER be reached
        };
    }

    public PageResponse afterEnter(PageQuery pq) {
        // This class does not include an afterEnter method.
        MoviesDB moviesDB = (pq.getMoviesDBSubset() == null) ? pq.getMoviesDB() : pq.getMoviesDBSubset();
        currentMovie = moviesDB.searchExact(pq.getCurrentActionsInput().getMovie(), pq.getCurrentUser());
        if (currentMovie == null) {
            // This should NEVER be reached (?)
            return PageResponse.getErrorPageResponse();
        }
        PageResponse pageResponse = new PageResponse();
        pageResponse.setNewUser(pq.getCurrentUser());
        pageResponse.setActionOutput(getCurrentMovieAsObjectNode());
        return pageResponse;
    }

    private ObjectNode getCurrentMovieAsObjectNode() {
        ObjectNode objectNode = objectMapper.createObjectNode();
        ArrayNode arrayNode = objectMapper.createArrayNode();
        arrayNode.add(currentMovie.toObjectNode());
        objectNode.set("currentMoviesList", arrayNode);
        return objectNode;
    }
}