package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import exceptions.InvalidCatalogException;

import java.io.File;
import java.io.IOException;

public class CatalogUtil {
    public static void save(Catalog catalog, String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        objectMapper.writeValue(new File(path), catalog);
    }

    public static Catalog load(String path) throws InvalidCatalogException {
        ObjectMapper objectMapper = new ObjectMapper();
        Catalog catalog = null;
        try {
            catalog = objectMapper.readValue(new File(path), Catalog.class);
        } catch (IOException exception) {
            throw new InvalidCatalogException(exception);
        }
        return catalog;
    }
}
