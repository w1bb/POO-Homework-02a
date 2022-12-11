package fileio;

public final class ActionsFiltersInput {
    ActionsFiltersSortInput sortInput;
    ActionsFiltersContainsInput containsInput;

    public ActionsFiltersInput() {
    }

    public ActionsFiltersSortInput getSort() {
        return this.sortInput;
    }

    public void setSort(ActionsFiltersSortInput sort) {
        this.sortInput = sort;
    }

    public ActionsFiltersContainsInput getContains() {
        return this.containsInput;
    }

    public void setSort(ActionsFiltersContainsInput contains) {
        this.containsInput = contains;
    }
}
