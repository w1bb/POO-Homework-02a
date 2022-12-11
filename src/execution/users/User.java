package execution.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import execution.AccountType;
import execution.movies.Movie;

import java.util.ArrayList;

public abstract class User {
    protected final String name;
    protected String password;
    protected AccountType accountType;
    protected String country;
    protected int balance;

    protected int tokensCount;
    protected int numFreePremiumMovies;

    protected ArrayList<Movie> purchasedMovies;
    protected ArrayList<Movie> watchedMovies;
    protected ArrayList<Movie> likedMovies;
    protected ArrayList<Movie> ratedMovies;

    User(final String name, String password, AccountType accountType, String country, int balance) {
        this.name = name;
        this.password = password;
        this.accountType = accountType;
        this.country = country;
        this.balance = balance;

        this.tokensCount = 0;
        this.numFreePremiumMovies = 0;
        this.purchasedMovies = new ArrayList<>();
        this.watchedMovies = new ArrayList<>();
        this.likedMovies = new ArrayList<>();
        this.ratedMovies = new ArrayList<>();
    }

    public final String getName() {
        return this.name;
    }

    public final String getPassword() {
        return this.password;
    }

    public final String getCountry() {
        return this.country;
    }

    public final ObjectNode toObjectNode() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode returnNode = objectMapper.createObjectNode();

        ObjectNode credentialsNode = objectMapper.createObjectNode();
        credentialsNode.put("name", name);
        credentialsNode.put("password", password);
        credentialsNode.put("accountType", accountType.toString());
        credentialsNode.put("country", country);
        credentialsNode.put("balance", balance);
        returnNode.set("credentials", credentialsNode);

        returnNode.put("tokensCount", tokensCount);
        returnNode.put("numFreePremiumMovies", numFreePremiumMovies);

        ArrayNode purchasedNode = objectMapper.createArrayNode();
        for (Movie movie : this.purchasedMovies) {
            purchasedNode.add(movie.toObjectNode());
        }
        ArrayNode watchedNode = objectMapper.createArrayNode();
        for (Movie movie : this.watchedMovies) {
            watchedNode.add(movie.toObjectNode());
        }
        ArrayNode likedNode = objectMapper.createArrayNode();
        for (Movie movie : this.likedMovies) {
            likedNode.add(movie.toObjectNode());
        }
        ArrayNode ratedNode = objectMapper.createArrayNode();
        for (Movie movie : this.ratedMovies) {
            ratedNode.add(movie.toObjectNode());
        }
        returnNode.set("purchasedMovies", purchasedNode);
        returnNode.set("watchedMovies", watchedNode);
        returnNode.set("watchedMovies", likedNode);
        returnNode.set("ratedMovies", ratedNode);

        return returnNode;
    }
}
