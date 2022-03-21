package br.com.integration;

import br.com.integration.service.IntegrationService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Client {

    public static void main(String[] args) throws Exception {
        try (InputStream input = Client.class.getClassLoader().getResourceAsStream("config.properties")) {

            Properties prop = new Properties();
            prop.load(input);
            var pathFiles = prop.getProperty("integration.input.files.path");
           // System.out.println("Lendo arquivos do diretório : " + pathFiles);
            IntegrationService integrationService = new IntegrationService();
            Files.list(Paths.get(pathFiles)).forEach(p -> {
                integrationService.readFileAndConvertToModel(p.toString());
            });

        } catch (IOException ex) {
            throw new Exception("Erro ao ler arquivo de propriedades : " + ex.getMessage());
        }
    }

}
