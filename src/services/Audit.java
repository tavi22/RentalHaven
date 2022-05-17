package services;

import java.io.*;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Audit {
    private FileWriter csvWriter;
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static Audit instance = null;
    public static Audit getInstance() {
        if (instance == null)
            instance = new Audit();

        return instance;
    }

    public Audit() {
        String pathToCsv = "src/data/audit.csv";
        File csvFile = new File(pathToCsv);
        if (csvFile.isFile()) {
            try {
                csvWriter = new FileWriter("audit.csv", true);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

            try {
                csvWriter = new FileWriter("audit.csv");
                csvWriter.append("Action");
                csvWriter.append(",");
                csvWriter.append("Timestamp");
                csvWriter.append("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void logServiceAction(String action) throws IOException {
        csvWriter.append(action);
        csvWriter.append(",");
        csvWriter.append(formatter.format(LocalDateTime.now()));
        csvWriter.append("\n");
        csvWriter.flush();
    }

}
