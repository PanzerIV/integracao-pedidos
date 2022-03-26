package br.com.integration;

import br.com.integration.util.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

public class UtilTest {

    @Test
    public void shouldReadLinesFromFile() throws IOException {
        String filePath = getClass().getResource("/data_test.txt").getPath();
        var line = Utils.readLinesFromTextFile(filePath);

        Assertions.assertEquals(line.size(), 13);
        Assertions.assertEquals(line.get(0).trim(),"0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308");
        Assertions.assertEquals(line.get(5).trim(),"0000000080                                 Tabitha Kuhn00000008770000000003      817.1320210612");
        Assertions.assertEquals(line.get(11).trim(),"0000000077                         Mrs. Stephen Trantow00000008320000000006      961.3720210513");
    }

    @Test
    public void shouldFormatAmount(){
        Assertions.assertEquals(Utils.formartAmount(1836.744444), BigDecimal.valueOf(1836.74).setScale(2));
        Assertions.assertEquals(Utils.formartAmount(817.132), BigDecimal.valueOf(817.13).setScale(2));
        Assertions.assertEquals(Utils.formartAmount(961.3),  BigDecimal.valueOf(961.3).setScale(2));
    }

    @Test
    public void shouldConvertStringToLocalDate(){
        Assertions.assertEquals(Utils.stringToLocalDate("20220308"), LocalDate.parse("2022-03-08"));
        Assertions.assertEquals(Utils.stringToLocalDate("20210612"), LocalDate.parse("2021-06-12"));
        Assertions.assertEquals(Utils.stringToLocalDate("19500513"), LocalDate.parse("1950-05-13"));
    }

    @Test
    public void shouldNotWriteFile() {
        Assertions.assertEquals("Arquivo não gerado !", Utils.writeFile("Texto do arquivo","/arquivo.txt"));
    }

    @Test
    public void shouldWriteFile() throws IOException {
        String filePath = getClass().getResource("/data_test.txt").getPath();
        var line = Utils.readLinesFromTextFile(filePath).get(0);
        Assertions.assertEquals("Arquivo gerado com sucesso! target/test-classes/file1.txt", Utils.writeFile(line,"target/test-classes/file1.txt"));
    }

}
