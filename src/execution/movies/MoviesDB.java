package execution.movies;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import execution.users.User;

import java.util.ArrayList;

public final class MoviesDB {
    private final ArrayList<Movie> movies;

    public MoviesDB() {
        movies = new ArrayList<>();
    }

    public void add(Movie movie) {
        movies.add(movie);
    }

    public MoviesDB search(final String startsWith,
                           final User forUser) {
        MoviesDB moviesDB = new MoviesDB();
        for (Movie movie : movies) {
            if (movie.getName().startsWith(startsWith)
                    && !movie.isBannedForUser(forUser)) {
                moviesDB.add(movie);
            }
        }
        return moviesDB;
    }

    public Movie searchExact(final String movieName, User forUser) {
        for (Movie movie : movies) {
            if (movie.getName().equals(movieName)) {
                return movie.isBannedForUser(forUser) ? null : movie;
            }
        }
        return null;
    }

    private void selfSort(final Boolean sortRatingAscending, final Boolean sortDurationAscending) {
        movies.sort((a, b) -> {
            if (sortRatingAscending != null) {
                final int aRatingInt = (int) Math.round(a.computeRating() * 100.0);
                final int bRatingInt = (int) Math.round(b.computeRating() * 100.0);
                return (sortRatingAscending ? 1 : -1) * (aRatingInt - bRatingInt);
            }
            if (sortDurationAscending != null) {
                return (sortDurationAscending ? 1 : -1) * (a.getDuration() - b.getDuration());
            }
            // This should never be reached!
            return 0;
        });
    }

    public MoviesDB filter(final Boolean sortRatingAscending, final Boolean sortDurationAscending,
                           final ArrayList<String> actors, final ArrayList<String> genres,
                           final User forUser) {
        MoviesDB filteredDB = new MoviesDB();
        for (Movie movie : movies) {
            boolean addable = !movie.isBannedForUser(forUser);
            if (!addable) {
                continue;
            }
            // Check each actor
            if (actors != null) {
                for (String neededActor : actors) {
                    if (!movie.getActors().contains(neededActor)) {
                        addable = false;
                        break;
                    }
                }
                if (!addable) {
                    continue;
                }
            }
            // Check each genre
            if (genres != null) {
                for (String neededGenre : genres) {
                    if (!movie.getGenres().contains(neededGenre)) {
                        addable = false;
                        break;
                    }
                }
                if (!addable) {
                    continue;
                }
            }
            // Add good movies
            filteredDB.add(movie);
        }
        // Sort the movies and return
        filteredDB.selfSort(sortRatingAscending, sortDurationAscending);
        return filteredDB;
    }

    public ArrayNode toArrayNode() {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode returnNode = objectMapper.createArrayNode();
        for (Movie movie : movies) {
            returnNode.add(movie.toObjectNode());
        }
        return returnNode;
    }
}
