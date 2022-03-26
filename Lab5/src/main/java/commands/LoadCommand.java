package commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.InvalidCatalogException;
import model.Catalog;

import java.io.File;
import java.io.IOException;

public class LoadCommand extends Command {
    private String path;

    public LoadCommand(String path) {
        this.path = path;
    }

    public void solve() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            catalog = objectMapper.readValue(new File(path), Catalog.class);
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
    }
}
