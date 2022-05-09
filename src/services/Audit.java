package services;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Audit {
    private FileWriter outputFile;
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static Audit instance = null;
    public static Audit getInstance() {
        if (instance == null)
            instance = new Audit();

        return instance;
    }

    public Audit() {
        try{
            outputFile = new FileWriter("src/data/audit.csv");
            outputFile.append("Action");
            outputFile.append(",");
            outputFile.append("Date");
            outputFile.append("\n");
            outputFile.flush();
        }catch (IOException e){
            System.out.println("Error with audit file.");
            e.printStackTrace();
        }
    }

    public void logServiceAction(String action) throws IOException {
        outputFile.append(action);
        outputFile.append(",");
        outputFile.append(formatter.format(LocalDateTime.now()));
        outputFile.append("\n");
        outputFile.flush();

    }

}
