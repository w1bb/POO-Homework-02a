package fileio;

public class ActionsInput {
    private String count;
    private int rate;
    private String type;
    private String page;
    private String feature;
    private String startsWith;
    private String movie;
    private String objectType;
    private CredentialsInput credentialsInput;
    private ActionsFiltersInput filtersInput;

    public ActionsInput() {
    }

    public String getCount() {
        return this.count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public int getRate() {
        return this.rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPage() {
        return this.page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getFeature() {
        return this.feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getStartsWith() {
        return this.startsWith;
    }

    public void setStartsWith(String startsWith) {
        this.startsWith = startsWith;
    }

    public String getMovie() {
        return this.movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getObjectType() {
        return this.objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public CredentialsInput getCredentials() {
        return this.credentialsInput;
    }

    public void setCredentials(CredentialsInput credentials) {
        this.credentialsInput = credentials;
    }

    public ActionsFiltersInput getFilters() {
        return this.filtersInput;
    }

    public void setFilters(ActionsFiltersInput filters) {
        this.filtersInput = filters;
    }

    @Override
    public String toString() {
        return "ActionsInput{" +
                "count=" + count +
                ", rate=" + rate +
                ", type='" + type + '\'' +
                ", page='" + page + '\'' +
                ", feature='" + feature + '\'' +
                ", startsWith='" + startsWith + '\'' +
                ", movie='" + movie + '\'' +
                ", objectType='" + objectType + '\'' +
                ", credentialsInput=" + credentialsInput +
                ", filtersInput=" + filtersInput +
                '}';
    }
}
