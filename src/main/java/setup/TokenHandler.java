package setup;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TokenHandler {
    private static String TOKEN;

    public static String getToken() {
        if (TOKEN == null) {
            Properties currentProps = new Properties();
            try {
                FileInputStream in = new FileInputStream(System.getProperty("user.dir") + "/credentials.properties");
                currentProps.load(in);
                in.close();
            } catch (IOException e) {
                throw new RuntimeException("credentials file is not found");
            }

            TOKEN = currentProps.getProperty("token");
        }
        return TOKEN;
    }

    public static String insertToken(String src) {
        return src.replace("{token}", getToken());
    }
}
