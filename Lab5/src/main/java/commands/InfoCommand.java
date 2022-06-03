package commands;

import model.Catalog;
import model.Item;
import model.LocationType;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.*;

public class InfoCommand extends Command {
    public InfoCommand(Catalog catalog) {
        super(catalog);
    }

    public void utilWeb(String url) {
        HttpGet httpget = new HttpGet(url);
        HttpEntity entity = null;
        HttpClient client = HttpClientBuilder.create().build();
        try {
            HttpResponse response = client.execute(httpget);
            entity = response.getEntity();
            if (entity != null) {
                InputStream inputStream = entity.getContent();
                BodyContentHandler handler = new BodyContentHandler();
                Metadata metadata = new Metadata();
                Parser parser = new AutoDetectParser();
                parser.parse(inputStream, handler, metadata, new ParseContext());
                String[] metadataNames = metadata.names();
                for (String name : metadataNames) {
                    System.out.println(name + ":   " + metadata.get(name));
                }
                System.out.println();
            }
        } catch (IOException | SAXException | TikaException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void utilLocal(String pathName) {
        File file = new File(pathName);

        Parser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        try (FileInputStream inputStream = new FileInputStream(file)) {
            ParseContext context = new ParseContext();
            parser.parse(inputStream, handler, metadata, context);
            String[] metadataNames = metadata.names();
            for (String name : metadataNames) {
                System.out.println(name + ": " + metadata.get(name));
            }
            System.out.println();

        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        } catch (TikaException | SAXException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void solve() {
        for (Item item : catalog.getItems()) {
            if (item.getLocationType() == LocationType.LOCAL) {
                System.out.println("Item:" + item.getTitle());
                utilLocal(item.getLocation());
            } else if (item.getLocationType() == LocationType.WEB) {
                System.out.println("Item:" + item.getTitle());
                utilWeb(item.getLocation());
            }
        }
    }
}
