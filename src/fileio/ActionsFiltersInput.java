package fileio;

public final class ActionsFiltersInput {
    ActionsFiltersSortInput sort;
    ActionsFiltersContainsInput contains;

    public ActionsFiltersInput() {
    }

    public ActionsFiltersSortInput getSort() {
        return this.sort;
    }

    public void setSort(ActionsFiltersSortInput sort) {
        this.sort = sort;
    }

    public ActionsFiltersContainsInput getContains() {
        return this.contains;
    }

    public void setContains(ActionsFiltersContainsInput contains) {
        this.contains = contains;
    }
}
