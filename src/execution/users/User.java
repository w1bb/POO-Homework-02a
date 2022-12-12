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

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getTokensCount() {
        return tokensCount;
    }

    public void setTokensCount(int tokensCount) {
        this.tokensCount = tokensCount;
    }

    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    public void setNumFreePremiumMovies(int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
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
        returnNode.set("likedMovies", likedNode);
        returnNode.set("ratedMovies", ratedNode);

        return returnNode;
    }

    public ArrayList<Movie> getPurchasedMovies() {
        return purchasedMovies;
    }

    public void setPurchasedMovies(ArrayList<Movie> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    public ArrayList<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    public void setWatchedMovies(ArrayList<Movie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public ArrayList<Movie> getLikedMovies() {
        return likedMovies;
    }

    public void setLikedMovies(ArrayList<Movie> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public ArrayList<Movie> getRatedMovies() {
        return ratedMovies;
    }

    public void setRatedMovies(ArrayList<Movie> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }
}
