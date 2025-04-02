package application.utils;
import java.io.File;

public class FileUtils {

    public static boolean waitForFile(String filePath, long timeoutMillis) {
        File file = new File(filePath);
        long startTime = System.currentTimeMillis();

        while (!file.exists()) {
            if (System.currentTimeMillis() - startTime > timeoutMillis) {
                return false;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return true;
    }
}
