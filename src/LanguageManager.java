import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageManager {
    private ResourceBundle resourceBundle;

    public LanguageManager() {

    }

    public void setLanguage(String languageCode) {
        Locale locale = new Locale(languageCode);
        resourceBundle = ResourceBundle.getBundle("messages", locale);
    }

    public String getString(String key) {
        return resourceBundle.getString(key);
    }
}
