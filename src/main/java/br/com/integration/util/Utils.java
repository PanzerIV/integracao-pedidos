package br.com.integration.util;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

public final class Utils {

    public static List<String> readLinesFromTextFile(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fileName));
        return lines;
    }

    public static BigDecimal formartAmount(double amount) {
        return new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP);
    }

    public static LocalDate stringToLocalDate(String date){
        var yyyy = Integer.parseInt(date.substring(0,4));
        var MM = Integer.parseInt(date.substring(4,6));
        var dd = Integer.parseInt(date.substring(6));

        return LocalDate.of(yyyy, MM, dd);
    }

    public static void writeFile(String content, String filePath) {
        FileWriter file = null;
        try {
            file = new FileWriter(filePath);
            file.write(content);
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
