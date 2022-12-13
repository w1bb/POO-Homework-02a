package execution.movies;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import execution.ErrorType;
import execution.users.User;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class Movie {
    private final String name;
    private final int year;
    private final int duration;
    private final ArrayList<String> genres;
    private final ArrayList<String> actors;
    private final ArrayList<String> countriesBanned;

    private ArrayList<User> watched;
    private ArrayList<User> likes;
    private ArrayList<User> dislikes;
    private HashMap<User, Integer> ratings;

    public Movie(final String name, final int year, final int duration,
                 final ArrayList<String> genres, final ArrayList<String> actors,
                 final ArrayList<String> countriesBanned) {
        this.name = name;
        this.year = year;
        this.duration = duration;
        this.genres = genres;
        this.actors = actors;
        this.countriesBanned = countriesBanned;
        this.likes = new ArrayList<>();
        this.dislikes = new ArrayList<>();
        this.watched = new ArrayList<>();
        this.ratings = new HashMap<>();
    }

    public String getName() {
        return this.name;
    }

    public boolean containsYear(int year) {
        return this.year == year;
    }

    public boolean containsDuration(int duration) {
        return this.duration == duration;
    }

    public boolean containsGenre(ArrayList<String> genreSearch) {
        for (String genreName : genreSearch) {
            if (!this.genres.contains(genreName)) {
                return false;
            }
        }
        return true;
    }

    public boolean containsActors(ArrayList<String> actorsSearch) {
        for (String actorName : actorsSearch) {
            if (!this.actors.contains(actorName)) {
                return false;
            }
        }
        return true;
    }

    public boolean isBannedForUser(User user) {
        return this.countriesBanned.contains(user.getCountry());
    }

    public ArrayList<String> getCountriesBanned() {
        return this.countriesBanned;
    }

    public ArrayList<String> getActors() {
        return this.actors;
    }

    public ArrayList<String> getGenres() {
        return this.genres;
    }

    public ErrorType like(User user) {
        if (!watched.contains(user)) {
            return ErrorType.ERROR_USER_NOT_WATCH_MOVIE;
        }
        dislikes.remove(user);
        if (!likes.contains(user)) {
            likes.add(user);
        }
        return ErrorType.NO_ERROR;
    }

    public ErrorType dislike(User user) {
        if (!watched.contains(user)) {
            return ErrorType.ERROR_USER_NOT_WATCH_MOVIE;
        }
        likes.remove(user);
        if (!dislikes.contains(user)) {
            dislikes.add(user);
        }
        return ErrorType.NO_ERROR;
    }

    public int getLikeCount() {
        return likes.size();
    }

    public int getDislikeCount() {
        return dislikes.size();
    }

    public void rate(User user, int rating) {
        ratings.put(user, rating);
    }

    public double computeRating() {
        double rating = 0;
        for (Map.Entry<User, Integer> x : ratings.entrySet()) {
            rating += (double) x.getValue();
        }
        return (ratings.size() == 0) ? 0 : rating / ratings.size();
    }

    public int getDuration() {
        return duration;
    }

    public ObjectNode toObjectNode() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode returnNode = objectMapper.createObjectNode();
        returnNode.put("name", name);
        returnNode.put("year", year);
        returnNode.put("duration", duration);
        returnNode.set("genres", objectMapper.valueToTree(genres));
        returnNode.set("actors", objectMapper.valueToTree(actors));
        returnNode.set("countriesBanned", objectMapper.valueToTree(countriesBanned));
        returnNode.put("numLikes", likes.size());
        double computedRating = Math.round(computeRating() * 100.0);
        if (computedRating < 1e-6)
            returnNode.put("rating", 0);
        else
            returnNode.put("rating", computedRating / 100);
        returnNode.put("numRatings", ratings.size());
        return returnNode;
    }
}
