package commands;

import model.Item;
import model.LocationType;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ViewCommand extends Command {
    private Item item;

    public ViewCommand(Item item) {
        this.item = item;
    }

    public void solve() {
        Desktop desktop = Desktop.getDesktop();
        try {
            if (item.getLocationType() == LocationType.WEB) {
                URI uri = new URI(item.getLocation());
                desktop.browse(uri);
            } else {
                File file = new File(item.getLocation());
                desktop.open(file);
            }
        } catch (URISyntaxException | FileNotFoundException exception) {
            System.err.println(exception.getMessage());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
