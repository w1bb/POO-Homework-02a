package execution.pages.auth;

import com.fasterxml.jackson.databind.node.ObjectNode;
import execution.movies.Movie;
import execution.movies.MoviesDB;
import execution.pages.Page;
import execution.pages.PageFactory;
import execution.pages.PageQuery;
import execution.pages.PageResponse;
import execution.users.User;
import fileio.ActionsInput;

import java.util.ArrayList;
import java.util.Arrays;

public class MoviesPage extends Page {
    private static MoviesPage instance = null;

    private MoviesPage() {
        super("movies",
                new ArrayList<>(Arrays.asList(
                        "auth-homepage",
                        "movies",
                        "see details",
                        "logout")));
    }

    public static MoviesPage getInstance() {
        if (instance == null) {
            instance = new MoviesPage();
        }
        return instance;
    }

    private PageResponse executeSearch(PageQuery pq) {
        PageResponse pageResponse = new PageResponse();
        ObjectNode objectNode = objectMapper.createObjectNode();
        MoviesDB searchedMovies = pq.getMoviesDB().search(
                pq.getCurrentActionsInput().getStartsWith(), pq.getCurrentUser());
        objectNode.set("currentMoviesList", searchedMovies.toArrayNode());
        pageResponse.setNewUser(pq.getCurrentUser());
        pageResponse.setActionOutput(objectNode);
        return pageResponse;
    }

    private PageResponse executeFilter(PageQuery pq) {
        PageResponse pageResponse = new PageResponse();
        ActionsInput actionsInput = pq.getCurrentActionsInput();

        Boolean sortRatingAscending = null;
        Boolean sortDurationAscending = null;
        if (actionsInput.getFilters().getSort() != null) {
            String actionsInputSR = actionsInput.getFilters().getSort().getRating();
            if (actionsInputSR.equals("increasing")) {
                sortRatingAscending = true;
            } else if (actionsInputSR.equals("decreasing")) {
                sortRatingAscending = false;
            }

            String actionsInputSD = actionsInput.getFilters().getSort().getDuration();
            if (actionsInputSD.equals("increasing")) {
                sortDurationAscending = true;
            } else if (actionsInputSD.equals("decreasing")) {
                sortDurationAscending = false;
            }
        }
        ArrayList<String> containsActors = null;
        ArrayList<String> containsGenres = null;
        if (actionsInput.getFilters().getContains() != null) {
            containsActors = actionsInput.getFilters().getContains().getActors();
            containsGenres = actionsInput.getFilters().getContains().getGenre();
        }

        MoviesDB curatedList = pq.getMoviesDB().filter(
                sortRatingAscending, sortDurationAscending,
                containsActors, containsGenres,
                pq.getCurrentUser());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.set("currentMoviesList", curatedList.toArrayNode());
        pageResponse.setNewUser(pq.getCurrentUser());
        pageResponse.setActionOutput(objectNode);
        return pageResponse;
    }

    public PageResponse execute(PageQuery pq) {
        return switch (pq.getCurrentActionsInput().getFeature()) {
            case "search" -> executeSearch(pq);
            case "filter" -> executeFilter(pq);
            default -> null;
        };
    }

    public PageResponse afterEnter(PageQuery pq) {
        PageResponse pageResponse = new PageResponse();
        ObjectNode objectNode = objectMapper.createObjectNode();
        MoviesDB curatedList = pq.getMoviesDB().search("", pq.getCurrentUser());
        objectNode.set("currentMoviesList", curatedList.toArrayNode());
        pageResponse.setNewUser(pq.getCurrentUser());
        pageResponse.setActionOutput(objectNode);
        return pageResponse;
    }
}