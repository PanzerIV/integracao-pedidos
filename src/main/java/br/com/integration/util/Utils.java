package br.com.integration.util;

import lombok.extern.slf4j.Slf4j;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

@Slf4j
public final class Utils {

    private Utils() {
    }

    public static List<String> readLinesFromTextFile(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(fileName));
    }

    public static BigDecimal formartAmount(double amount) {
        return BigDecimal.valueOf(amount).setScale(2, RoundingMode.HALF_UP);
    }

    public static LocalDate stringToLocalDate(String date) {
        var year = Integer.parseInt(date.substring(0, 4));
        var month = Integer.parseInt(date.substring(4, 6));
        var day = Integer.parseInt(date.substring(6));

        return LocalDate.of(year, month, day);
    }

    public static void writeFile(String content, String filePath) {
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(content);
            file.flush();
        } catch (IOException e) {
            log.error("Erro ao gerar o arquivo :", filePath);
        }
    }
}
