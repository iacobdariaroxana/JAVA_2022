package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class MockDictionary implements Dictionary{
    @Test
    public MockDictionary(){

    }
    @Test
    public boolean isWord(String str){
        try{
            Optional<String> opt = Files.lines(Paths.get("words.txt"))
                    .filter(s -> s.equals(str))
                    .findAny();
            if(opt.isEmpty())
                return false;
        }
        catch (IOException exception){
            System.out.println(exception.getMessage());
        }
        return true;
    }
}
