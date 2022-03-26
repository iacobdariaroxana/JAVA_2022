package commands;

import model.Catalog;

public abstract class Command {
    protected Catalog catalog;

    protected Command() {

    }

    protected Command(Catalog catalog) {
        this.catalog = catalog;
    }

    public abstract void solve();

    public Catalog getCatalog(){
        return catalog;
    }
}
