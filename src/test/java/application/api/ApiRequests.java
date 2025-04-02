package application.api;

import application.utils.ConfigReader;
import io.restassured.response.Response;
import org.openqa.selenium.devtools.RequestFailedException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public class ApiRequests {

    private final String token;

    public ApiRequests() {
        this.token = this.getToken();
        System.out.println(token);
    }

    private String getToken() {
        Response rs = given()
                .baseUri("https://xray.cloud.getxray.app/api/v2/")
                .basePath("authenticate")
                .accept("application/json")
                .contentType("application/json")
                .request().body("{ \"client_id\": \"%s\" , \"client_secret\": \"%s\"}".formatted(ConfigReader.getProperty("client_id"), ConfigReader.getProperty("client_secret")))
                .when()
                .post();
        if(rs.getStatusCode() == 200) {
            String body = rs.getBody().asString();
            return body.replace("\"", "");
        } else {
            throw new RequestFailedException();
        }
    }

    public void getCompressedFile() {
        Response rs = given()
                .baseUri("https://xray.cloud.getxray.app/api/v2/export/cucumber")
                .accept("application/json")
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .param("keys", ConfigReader.getProperty("keys"))
                .when()
                .get();
        // Vérification de la réponse (statut et contenu)
        if (rs.getStatusCode() == 200) {
            // Sauvegarder la réponse (fichier ZIP) dans un fichier local
            try (InputStream inputStream = rs.asInputStream();
                 OutputStream outputStream = new FileOutputStream(new File(ConfigReader.getProperty("zipFile")))) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }
                System.out.println("Le fichier a été téléchargé avec succès.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Échec de la requête. Code de statut : " + rs.getStatusCode());
        }
    }

    public void importTestResults() throws IOException {
        Response rs = given()
                .baseUri("https://xray.cloud.getxray.app/api/v2/import/execution/cucumber")
                .accept("application/json")
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .request().body(Files.readString(Path.of(ConfigReader.getProperty("testResultsFile"))))
                .post();
            System.out.println(rs.getBody().asString());
    }
}
