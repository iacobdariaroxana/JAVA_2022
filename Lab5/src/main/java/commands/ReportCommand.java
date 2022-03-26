package commands;

import freemarker.template.*;
import model.Catalog;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class ReportCommand extends Command {
    public ReportCommand(Catalog catalog) {
        super(catalog);
    }

    public void solve() {
        try{
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
            cfg.setDirectoryForTemplateLoading(new File("D:/report"));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);
            cfg.setWrapUncheckedExceptions(true);
            cfg.setFallbackOnNullLoopVariable(false);

            Map<String, Object> templateData = new HashMap<>();
            templateData.put("catalogName", catalog.getName());

            templateData.put("items", catalog.getItems());
            Template template = cfg.getTemplate("myTemplate.ftl");

            PrintStream htmlReport = new PrintStream(new FileOutputStream("D:/report/index.html"));
            PrintStream oldOut = System.out;
            System.setOut(htmlReport);
            Writer out = new OutputStreamWriter(System.out);
            template.process(templateData, out);
            System.setOut(oldOut);
            Desktop.getDesktop().browse(new URI("D:/report/index.html"));
        }
        catch (IOException | TemplateException | URISyntaxException e){
            System.out.println(e.getMessage());
        }
    }
}
