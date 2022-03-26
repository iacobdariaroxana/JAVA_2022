package commands;

import model.Catalog;

public class ListCommand extends Command {
    public ListCommand(Catalog catalog){
        super(catalog);
    }
    @Override
    public void solve() {
        catalog.getItems().forEach(System.out::println);
    }
}
