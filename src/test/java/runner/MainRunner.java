package runner;

import application.api.ApiRequests;
import application.hooks.Hook;
import application.utils.ConfigReader;
import application.utils.FileUtils;
import application.utils.Unzipper;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.JUnitCore;

import java.io.IOException;

public class MainRunner {
    public static void main(String[] args) throws IOException, InterruptedException {
        //Création Objet et récupération du token, cette classe permet d'effectuer les requêtes API
        ApiRequests apiRequests = new ApiRequests();
        //Récupère fichier zip compressé
        apiRequests.getCompressedFile();
        //Dézip le fichier
        Unzipper.unzip(ConfigReader.getProperty("zipFile"), ConfigReader.getProperty("outputDir"));
        //Fonction cherchant le fichier pendant 10 secondes et retournant un boolean indiquant s'il est présent
        boolean fileFound = FileUtils.waitForFile(ConfigReader.getProperty("featureFile"), 10000);
        if (fileFound) {
            System.out.println("Feature file found");
            //Permet de run mon runner de test
            JUnitCore.runClasses(RunnerTest.class);
            //Fonction cherchant le fichier pendant 10 secondes et retournant un boolean indiquant s'il est présent
            boolean fileFound2 = FileUtils.waitForFile(ConfigReader.getProperty("testResultsFile"), 10000);
            if (fileFound2) {
                System.out.println("Cucumber file found");
                //Import les test results
                apiRequests.importTestResults();
            } else {
                throw new IOException("Cucumber result file not found");
            }
        } else {
            throw new IOException("File feature not found");
        }
    }
}
