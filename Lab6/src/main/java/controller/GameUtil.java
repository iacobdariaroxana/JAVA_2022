package controller;

import java.io.*;

public class GameUtil {

    public void save(Controller controller, String path) {
        try {
            FileOutputStream fileStream = new FileOutputStream(path);
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
            objectStream.writeObject(controller);
            objectStream.close();
            fileStream.close();
        } catch (IOException exception) {
            System.err.println(exception);
        }
    }

    public Controller load(String path) {
        Controller controller = null;
        try {
            FileInputStream fileStream = new FileInputStream(path);
            ObjectInputStream objectStream = new ObjectInputStream(fileStream);
            controller = (Controller) objectStream.readObject();
        } catch (FileNotFoundException exception) {
            System.err.println(exception.getMessage());
        } catch (IOException exception) {
            System.err.println(exception.toString());
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        return controller;
    }

/*    public void saveJson(String path) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        try {
            objectMapper.writeValue(new File(path), frame.getController());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Controller loadJson(String path) {
        ObjectMapper objectMapper = new ObjectMapper();
        Controller controller = null;
        try {
            controller= objectMapper.readValue(new File(path), Controller.class);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        return controller;
    }*/
}
