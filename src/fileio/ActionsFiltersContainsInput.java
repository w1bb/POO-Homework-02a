package fileio;

import java.util.ArrayList;

public final class ActionsFiltersContainsInput {
    private ArrayList<String> actors;
    private ArrayList<String> genre;

    public ActionsFiltersContainsInput() {
    }

    public ArrayList<String> getActors() {
        return this.actors;
    }

    public void setActors(ArrayList<String> actors) {
        this.actors = actors;
    }

    public ArrayList<String> getGenre() {
        return this.genre;
    }

    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
    }
}
