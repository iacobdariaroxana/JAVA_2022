package reflection;

import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipFile;

public class Main {
    public static void displayDirectoryContents(File dir) {
        try {
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    System.out.println("directory: " + file.getCanonicalPath());
                    displayDirectoryContents(file);
                } else {
                    System.out.println("     file: " + file.getCanonicalPath());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DynamicClassAnalyzer dynamicClassAnalyzer = new DynamicClassAnalyzer();
        String pathName = "C:/Users/Roxana/Desktop/An2/SEM2/PA/PA_2022_2B3_IACOB_DARIA-ROXANA/Lab6/target/classes";
        String className = "model.Node";
        dynamicClassAnalyzer.extractInformation(dynamicClassAnalyzer.pathAndClassLoader(pathName, className ));

        pathName = "C:/Users/Roxana/Desktop/An2/SEM2/PA/PA_2022_2B3_IACOB_DARIA-ROXANA/Lab7/target/classes";
        className = "model.Test";
        Class classTest = dynamicClassAnalyzer.pathAndClassLoader(pathName, className );


        dynamicClassAnalyzer.testStaticMethods(dynamicClassAnalyzer.ourClassLoader("reflection.Node"));
        Main.displayDirectoryContents(new File("C:/Users/Roxana/Desktop/An2/SEM2/PA/PA_2022_2B3_IACOB_DARIA-ROXANA/Lab6/target/classes"));
        for(Class myClass : dynamicClassAnalyzer.getClassesFromJarFile(new File("C:/Users/Roxana/Desktop/An2/SEM2/PA/PA_2022_2B3_IACOB_DARIA-ROXANA/Lab7/target/Lab7-1.0-SNAPSHOT.jar"))){
            dynamicClassAnalyzer.createMyPrototype(myClass);
            if(myClass.getConstructors().length != 0) {
                Annotation[] annotations = myClass.getConstructors()[0].getAnnotations();
                if (annotations.length > 0 && annotations[0].annotationType().getName().equals(classTest.getName())) {
                    System.out.println("AM INTRAT");
                }
                try {
                    dynamicClassAnalyzer.testMethods(myClass);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
