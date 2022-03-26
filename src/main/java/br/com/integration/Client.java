package br.com.integration;

import br.com.integration.service.IntegrationService;
import br.com.integration.util.Utils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.stream.Stream;

@Slf4j
public class Client {

    public static void main(String[] args) throws Exception {
        try (InputStream input = Client.class.getClassLoader().getResourceAsStream("config.properties")) {

            Properties prop = new Properties();
            prop.load(input);
            var inputPath = prop.getProperty("integration.input.path.files");
            var outputPath = prop.getProperty("integration.output.path.files");

            log.info("Lendo arquivos do diretório : " + inputPath);

            try (Stream<Path> files = Files.list(Paths.get(inputPath))) {
                files.forEach(p -> {
                    var output = IntegrationService.readFileAndConvertToJson(p.toString());
                    Utils.writeFile(output.toString(), outputPath.concat("/").concat(p.getFileName().toString()).concat(".json"));
                });
            }
        } catch (IOException ex) {
            throw new Exception("Erro ao ler arquivo de propriedades : " + ex.getMessage());
        }
    }

}
